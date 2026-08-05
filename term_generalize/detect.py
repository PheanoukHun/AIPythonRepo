from __future__ import annotations

from dataclasses import dataclass, field
from enum import Enum
from typing import Final

import httpx


class Service(Enum):
    LLAMA_CPP = "llama.cpp"
    LM_STUDIO = "LM Studio"
    OLLAMA = "Ollama"
    VLLM = "vLLM"
    UNKNOWN_OPENAI_COMPATIBLE = "OpenAI-compatible"


class BackendError(RuntimeError):
    """Raised when no reachable local AI server can be found."""


@dataclass(frozen=True)
class DetectedServer:
    service: Service
    openai_base: str
    model_ids: tuple[str, ...] = field(default_factory=tuple)


SUPPORTED_PORTS: Final[dict[Service, int]] = {
    Service.OLLAMA: 11434,
    Service.LM_STUDIO: 1234,
    Service.LLAMA_CPP: 8089,
    Service.VLLM: 8000,
}


class OPENAI_ENDPOINTS(Enum):
    COMPLETIONS = "/v1/chat/completions"
    BASE = "/v1"
    V0 = "/api/v0"


def _normalize_host(base_url: str) -> str:
    url = base_url.strip().rstrip("/")
    if not url.startswith(("http://", "https://")):
        url = f"http://{url}"
    for suffix in list(OPENAI_ENDPOINTS):

        sufval:str = suffix.value
        
        if url.endswith(sufval):
            return url[: -len(sufval)]
            
    return url


def _openai_base(host: str) -> str:
    return f"{host}{OPENAI_ENDPOINTS.BASE.value}"


def _json(response: httpx.Response | None) -> dict | None:
    if response is None:
        return None
    try:
        data = response.json()
    except ValueError:
        return None
    return data if isinstance(data, dict) else None


def _probe(client: httpx.Client, path: str) -> tuple[httpx.Response | None, bool]:
    try:
        response = client.get(path)
    except httpx.HTTPError:
        return None, False
    if response.status_code >= 400:
        return None, True
    return response, True


def _model_ids_from_data(payload: dict) -> tuple[str, ...]:
    data = payload.get("data")
    if not isinstance(data, list):
        return ()
    return tuple(
        entry["id"]
        for entry in data
        if isinstance(entry, dict) and isinstance(entry.get("id"), str)
    )


def _owned_by(payload: dict) -> set[str]:
    data = payload.get("data")
    if not isinstance(data, list):
        return set()
    return {
        entry["owned_by"]
        for entry in data
        if isinstance(entry, dict) and isinstance(entry.get("owned_by"), str)
    }


def _ollama_model_ids(client: httpx.Client) -> tuple[str, ...]:
    tags = _json(_probe(client, "/api/tags")[0])
    if tags is None:
        return ()
    models = tags.get("models")
    if not isinstance(models, list):
        return ()
    return tuple(
        model["name"]
        for model in models
        if isinstance(model, dict) and isinstance(model.get("name"), str)
    )


def detect_backend(
    base_url: str,
    api_key: str | None = None,
    *,
    timeout: float = 1.5,
) -> DetectedServer:
    host = _normalize_host(base_url)
    headers = {"Authorization": f"Bearer {api_key}"} if api_key else None
    reachable = False

    with httpx.Client(base_url=host, headers=headers, timeout=timeout) as client:
        props, ok = _probe(client, "/props")
        reachable = reachable or ok
        props_data = _json(props)
        if props_data is not None and (
            props_data.get("server_id") == "llama.cpp"
            or (
                "model_path" in props_data
                and "chat_template" in props_data
                and "default_generation_settings" in props_data
            )
        ):
            model_id = props_data.get("model_alias") or props_data.get("model_path")
            ids = (model_id,) if isinstance(model_id, str) else ()
            return DetectedServer(Service.LLAMA_CPP, _openai_base(host), ids)

        version, ok = _probe(client, "/api/version")
        reachable = reachable or ok
        version_data = _json(version)
        if version_data is not None and isinstance(version_data.get("version"), str):
            return DetectedServer(
                Service.OLLAMA, _openai_base(host), _ollama_model_ids(client)
            )

        lms, ok = _probe(client, "/api/v0/models")
        reachable = reachable or ok
        lms_data = _json(lms)
        if lms_data is not None and "data" in lms_data:
            return DetectedServer(
                Service.LM_STUDIO, _openai_base(host), _model_ids_from_data(lms_data)
            )

        health, ok = _probe(client, "/health")
        reachable = reachable or ok
        if health is not None:
            health_data = _json(health)
            if health_data is not None and health_data.get("status") == "ok":
                return DetectedServer(Service.LLAMA_CPP, _openai_base(host))
            body = health.text.strip()
            if body == "OK":
                models = _json(_probe(client, "/v1/models")[0])
                ids = _model_ids_from_data(models) if models else ()
                return DetectedServer(Service.VLLM, _openai_base(host), ids)
            if body.lower() == "ok":
                return DetectedServer(Service.LLAMA_CPP, _openai_base(host))

        models, ok = _probe(client, "/v1/models")
        reachable = reachable or ok
        models_data = _json(models)
        if models_data is not None and "data" in models_data:
            ids = _model_ids_from_data(models_data)
            owned = _owned_by(models_data)
            if owned & {"vllm", "VLLM"}:
                return DetectedServer(Service.VLLM, _openai_base(host), ids)
            if "lmstudio" in owned or any(_id.startswith("lmstudio") for _id in ids):
                return DetectedServer(Service.LM_STUDIO, _openai_base(host), ids)
            if owned & {"llama.cpp", "llamacpp"}:
                return DetectedServer(Service.LLAMA_CPP, _openai_base(host), ids)
            if "library" in owned:
                return DetectedServer(Service.OLLAMA, _openai_base(host), ids)
            return DetectedServer(
                Service.UNKNOWN_OPENAI_COMPATIBLE, _openai_base(host), ids
            )

    if reachable:
        return DetectedServer(Service.UNKNOWN_OPENAI_COMPATIBLE, _openai_base(host))

    defaults = "\n".join(
        f"  - {service.value} -> {service.name.lower()}:{port}"
        for service, port in SUPPORTED_PORTS.items()
    )
    raise BackendError(
        f"No local AI server detected at {host}.\n"
        f"Start one of the supported servers and try again:\n{defaults}"
    )

if __name__ == "__main__":
    url = input("URL: ")
    service:DetectedServer = detect_backend(url)
    print(service.service.value)
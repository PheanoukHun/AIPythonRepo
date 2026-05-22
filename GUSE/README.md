# GUSE v8 — FastAPI Backend

Three-stage AI trivia generation pipeline: **VALIDATOR → GENERATOR → QA**

## Project Structure

```
guse-v8/
├── main.py                  # FastAPI app entrypoint
├── requirements.txt
├── .env.example
│
├── core/
│   ├── config.py            # Settings (API key, model, max_tokens)
│   └── claude.py            # Anthropic client wrapper
│
├── models/
│   └── schemas.py           # All Pydantic request/response models
│
├── agents/
│   ├── prompts.py           # All system prompts (single source of truth)
│   ├── validator.py         # GUSE-VALIDATOR agent
│   ├── generator.py         # GUSE-GENERATOR agent
│   └── qa.py                # GUSE-QA agent
│
└── routers/
    ├── pipeline.py          # POST /pipeline/run — full orchestration
    └── agents.py            # POST /agents/{validate|generate|qa}
```

## Setup

```bash
pip install -r requirements.txt
cp .env.example .env
# Edit .env and set ANTHROPIC_API_KEY
uvicorn main:app --reload
```

## Endpoints

| Method | Path               | Description                             |
|--------|--------------------|-----------------------------------------|
| GET    | `/`                | Health check                            |
| GET    | `/health`          | Health check (JSON)                     |
| POST   | `/pipeline/run`    | Full VALIDATOR → GENERATOR → QA run     |
| POST   | `/agents/validate` | VALIDATOR stage only                    |
| POST   | `/agents/generate` | GENERATOR stage only (needs ValidatorSuccess body) |
| POST   | `/agents/qa`       | QA stage only (needs ValidatorSuccess + GeneratorOutput) |
| GET    | `/docs`            | Swagger UI                              |
| GET    | `/redoc`           | ReDoc UI                                |

## Example Request

```bash
curl -X POST http://localhost:8000/pipeline/run \
  -H "Content-Type: application/json" \
  -d '{
    "topic": "The Roman Empire",
    "difficulty": "medium",
    "count": 5,
    "language": "en",
    "seen_fingerprints": []
  }'
```

## Error Handling

- **HTTP 422** — VALIDATOR rejected the payload. Check `error` and `partial.validator` fields.
- **HTTP 500** — Agent failure during GENERATOR or QA. Check `stage` and `error` fields.
  The `partial` field contains the outputs of all stages that completed successfully.

## Success Metric

`regen_rate_pct` in the response must be `< 5.0` to meet the GUSE v8 quality target.

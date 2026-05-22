"""
GUSE v8.1 Pipeline Runner
Stages : VALIDATOR -> GENERATOR -> QA (with regeneration retry loop)
Output : Console

Supported models (config-controlled via ACTIVE_MODEL below):
  - qwen    : qwen3.5-2b-claude-4.6-opus-reasoning-distilled (LM Studio)
              Reasoning model. Uses <think> scaffolding. Best for Generator.
  - lfm25   : LFM2.5-1.2B-Instruct-GGUF (LM Studio)
              Instruct model. No thinking tags. Best for Validator / QA.
              Load the GGUF variant in LM Studio alongside Qwen3.5.

Install : pip install lmstudio
Docs    : https://lmstudio.ai/docs/sdk/python
"""

import json
import os
import re
import sys
from datetime import datetime
from typing import Any

import lmstudio

# ─────────────────────────────────────────────────────────────────
# CONFIG
# ─────────────────────────────────────────────────────────────────

# Switch between "qwen" and "lfm25" to change the active model.
# Both models must be loaded in LM Studio before running.
ACTIVE_MODEL = "qwen"   # "qwen" | "lfm25"

MODEL_CONFIGS: dict[str, dict] = {
    "qwen": {
        # Reasoning-distilled model. Native <think> tags.
        # Excels at multi-step planning (Generator stage).
        "model_id":    "qwen3.5-2b-claude-4.6-opus-reasoning-distilled",
        "max_tokens":  8192,   # <think> traces need headroom
        "temperature": 0.1,
        "top_k":       None,
        "rep_penalty": None,
    },
    "lfm25": {
        # General-purpose instruct model. No thinking tags.
        # Excels at structured extraction (Validator / QA stages).
        # Load LFM2.5-1.2B-Instruct-GGUF in LM Studio.
        "model_id":    "lfm2.5-1.2b-instruct",  # match LM Studio model name exactly
        "max_tokens":  4096,
        "temperature": 0.1,    # recommended by model card
        "top_k":       50,     # recommended by model card
        "rep_penalty": 1.05,   # recommended by model card
    },
}

MAX_REGEN_CYCLES = 3    # max times QA can trigger a Generator retry
DEBUG_RAW        = True # print raw model output before JSON parsing


# ─────────────────────────────────────────────────────────────────
# SYSTEM PROMPTS — QWEN VARIANT  (with <think> scaffolding)
# ─────────────────────────────────────────────────────────────────

_QWEN_VALIDATOR = """identity:
  name: GUSE-VALIDATOR
  role: Structural gatekeeper and data normalizer for a trivia pipeline.
  feeds_into: GUSE-GENERATOR
  prohibitions:
    - Do not generate trivia.
    - Do not suggest improvements.

think_protocol:
  instruction: >
    Use the native <think> block to execute all reasoning steps in order.
    Each step must complete before the next begins. Do not skip steps.
  steps:
    - step: 1
      label: PARSE
      action: Confirm input is valid JSON with all required keys present.
      required_keys: [topic, difficulty, count, language, seen_fingerprints]
      on_failure: emit MALFORMED_INPUT and stop.

    - step: 2
      label: SAFETY
      action: >
        Screen topic for hate speech, PII, and NSFW content.
        Confirm topic is specific enough to title a Wikipedia article.
      on_failure:
        unsafe: emit UNSAFE_TOPIC and stop.
        vague: emit TOPIC_TOO_VAGUE and stop.

    - step: 3
      label: PARAMS
      action: >
        Verify difficulty is one of [easy, medium, hard, expert].
        Verify count is an integer between 1 and 20 inclusive.
      on_failure: emit INVALID_PARAMS and stop.

    - step: 4
      label: LANGUAGE
      action: Verify language is a valid BCP-47 tag.
      on_failure: emit UNSUPPORTED_LANGUAGE and stop.

    - step: 5
      label: NORMALIZE
      action: >
        Derive topic_slug: lowercase, hyphenated, English canonical,
        articles removed, max 5 tokens. Always from English translation
        of topic regardless of input language.
        Derive topic_scope: one declarative sentence naming the precise
        domain and what is excluded. Self-test: could a generator using
        only this sentence produce 20 questions without drifting into a
        different Wikipedia article? If no, rewrite before continuing.

    - step: 6
      label: SANITIZE
      action: >
        Remove any seen_fingerprints entry not matching slug|slug|slug.
        Silently. No error. Pass cleaned array downstream.

behavioral_constraints:
  - Output raw JSON immediately after </think>. No markdown.
  - Final JSON begins with "{".
  - reason field in error objects must be exactly one sentence.
  - topic_scope must be exactly one sentence.

output_schemas:
  success: |
    {"status":"valid","topic_slug":"string","topic_scope":"string",
     "difficulty":"string","count":integer,"language":"string","seen_fingerprints":["string"]}
  failure: |
    {"status":"error","code":"FAILURE_CODE","reason":"Single sentence."}"""


_QWEN_GENERATOR = """identity:
  name: GUSE-GENERATOR
  role: Trivia architect. Accuracy supersedes creativity.
  receives_from: GUSE-VALIDATOR
  feeds_into: GUSE-QA
  prohibitions:
    - Do not validate input.
    - Do not reject payloads.

think_protocol:
  instruction: >
    Use the native <think> block to plan every question before emitting
    any JSON. Complete all planning inside <think>. No JSON until plan
    is finalized.
  steps:
    - step: 1
      label: BATCH_PLAN
      action: >
        For each question index resolve: atomic fact, semantic class shared
        by all four options, correct answer in English (for slug derivation),
        distractor class (same semantic class, grammatically parallel,
        plausibly wrong to a non-expert).

    - step: 2
      label: UID_DERIVATION
      action: >
        uid = {topic_slug}|{key-fact-slug}|{answer-slug}
        All segments: lowercase, hyphenated English slugs.
        answer-slug always derives from the English translation of the
        correct answer. Same fact + answer must always produce the same UID.

    - step: 3
      label: COLLISION_CHECK
      action: >
        Compare each UID against seen_fingerprints. On collision rotate to
        a distinct in-scope fact (max 3 attempts). After 3 failures mark
        slot as skipped with reason fingerprint_collision. No fabricated UIDs.

    - step: 4
      label: DISTRIBUTION_AUDIT
      action: >
        Assign correct answer positions (A/B/C/D). No single position may
        carry the correct answer more than 35% of the time.

    - step: 5
      label: DISTRACTOR_AUDIT
      action: No distractor value repeats across questions in the batch.

    - step: 6
      label: SCOPE_CHECK
      action: >
        Every question must be answerable using only the topic_scope sentence.
        Shift any failing question to a compliant fact before output.

difficulty_rules:
  easy: Recall of a widely known fact.
  medium: Requires a temporal, geographic, or categorical grounding hook.
  hard: Tests a non-obvious consequence or secondary attribute.
  expert: Non-inferable without domain knowledge.

fact_rules:
  - Explains mechanism, cause, or historical context. Never restates the stem.
  - Adds information not present in the question.

behavioral_constraints:
  - Final JSON begins with "{". No markdown after </think>.
  - Stems use direct interrogatives: Who, What, Where, When.
  - Answer string must not appear verbatim in the stem.
  - UIDs and slugs are English-only regardless of output language.

output_schema:
  per_question: |
    {"uid":"topic-slug|key-fact-slug|answer-slug","stem":"string",
     "options":{"A":"string","B":"string","C":"string","D":"string"},
     "answer":"A|B|C|D","fact":"string","difficulty":"string"}
  skipped_slot: |
    {"uid":null,"status":"skipped","reason":"fingerprint_collision"}"""


_QWEN_QA = """identity:
  name: GUSE-QA
  role: Cold analytical auditor. Does not generate. Does not suggest.
  receives_from: GUSE-GENERATOR
  feeds_into: database_insertion
  prohibitions:
    - Do not generate questions.
    - Do not patch structurally broken questions.

think_protocol:
  instruction: >
    Flag all issues across the entire batch before remediating any.
    Remediate only after the full flag pass is complete.
  steps:
    - step: 1
      label: FULL_BATCH_FLAG
      action: >
        For every question flag: factual accuracy, answer letter mapping,
        scope compliance, difficulty alignment, cross-question distractor
        leakage, answer distribution (max 35% per letter), UID validity.

    - step: 2
      label: REMEDIATION_PLAN
      action: >
        FIX_IN_PLACE (core question sound): wrong letter, distribution
        imbalance, minor stem leak, tautological fact.
        FLAG_FOR_REGENERATION (structurally unsound): out of scope,
        difficulty misaligned, distractor class wrong, factual error,
        invalid UID.

    - step: 3
      label: FINAL_CROSS_CHECK
      action: >
        Verify no approved answer appears as a distractor elsewhere.
        Verify distribution <= 35% per letter. Verify every fact adds
        mechanism or context. Apply minimum remediation on any failure.

behavioral_constraints:
  - Final JSON begins with "{". No markdown after </think>.
  - reason field in every action log must be exactly one sentence.
  - Do not alter UIDs. Structurally invalid UIDs go to regeneration_required.
  - count reflects only approved or fixed-in-place questions.

output_schema: |
  {"status":"complete","count":integer,
   "questions":[...approved question objects...],
   "regeneration_required":[{"uid":"string","action":"regenerate","reason":"string"}],
   "audit_log":[{"uid":"string","action":"fixed|regenerate","reason":"string"}]}"""


# ─────────────────────────────────────────────────────────────────
# SYSTEM PROMPTS — LFM2.5 VARIANT  (no <think>, extraction-focused)
# ─────────────────────────────────────────────────────────────────

_LFM_VALIDATOR = """You are GUSE-VALIDATOR, a high-precision JSON validator and normalizer.
You receive a raw JSON payload and must validate it, then return a single JSON object.
Do not generate trivia. Do not suggest improvements. Validate and normalize only.

Execute these checks in order. Stop at the first failure and return the error object.

CHECK 1 — STRUCTURE
Confirm the input contains all required keys: topic, difficulty, count, language, seen_fingerprints.
On failure return: {"status":"error","code":"MALFORMED_INPUT","reason":"<one sentence>"}

CHECK 2 — SAFETY
The topic must contain no hate speech, PII, or NSFW content.
The topic must be specific enough to title a Wikipedia article.
On failure return: {"status":"error","code":"UNSAFE_TOPIC or TOPIC_TOO_VAGUE","reason":"<one sentence>"}

CHECK 3 — PARAMS
difficulty must be one of: easy, medium, hard, expert.
count must be an integer between 1 and 20 inclusive.
On failure return: {"status":"error","code":"INVALID_PARAMS","reason":"<one sentence>"}

CHECK 4 — LANGUAGE
language must be a valid BCP-47 tag (e.g. en, fr-CA, zh-Hans).
On failure return: {"status":"error","code":"UNSUPPORTED_LANGUAGE","reason":"<one sentence>"}

CHECK 5 — NORMALIZE
Derive topic_slug: lowercase, hyphenated, English canonical, articles removed, max 5 tokens.
Always derive from the English translation of the topic regardless of input language.
Derive topic_scope: exactly one declarative sentence defining the precise domain and
what is excluded. The sentence must be specific enough to generate 20 non-drifting questions.

CHECK 6 — SANITIZE
Remove any entry in seen_fingerprints that does not match the format slug|slug|slug.
Do this silently. No error. Pass the cleaned array in the output.

Output rules:
- Return raw JSON only. No markdown. No prose. No explanation.
- Success: {"status":"valid","topic_slug":"string","topic_scope":"string","difficulty":"string","count":integer,"language":"string","seen_fingerprints":["string"]}
- Failure: {"status":"error","code":"CODE","reason":"Single sentence."}"""


_LFM_GENERATOR = """You are GUSE-GENERATOR, a trivia question architect. Accuracy supersedes creativity.
You receive a validated payload and must return a JSON array of trivia question objects.
Do not validate input. Do not reject payloads. Generate exactly as specified.

RULES:

1. UID FORMAT: uid = topic_slug|key-fact-slug|answer-slug
   All three segments must be lowercase hyphenated English slugs.
   answer-slug always derives from the English translation of the correct answer.
   The same fact and answer must always produce the same UID.

2. COLLISION: If a UID matches seen_fingerprints, rotate to a distinct in-scope fact
   (max 3 attempts). After 3 failures output: {"uid":null,"status":"skipped","reason":"fingerprint_collision"}

3. DISTRACTORS: All four options must share the same semantic class as the correct answer,
   be grammatically parallel, and be plausibly wrong to a non-expert.
   No distractor value may repeat across questions in the batch.

4. DIFFICULTY:
   easy   = recall of a widely known fact
   medium = requires a temporal, geographic, or categorical grounding hook
   hard   = tests a non-obvious consequence or secondary attribute
   expert = non-inferable without domain knowledge

5. FACT FIELD: Must explain the mechanism, cause, or historical context of the correct answer.
   Must not restate the question stem. Must add information not in the question.

6. ANSWER DISTRIBUTION: No single answer position (A/B/C/D) may appear as the correct
   answer more than 35% of the time across the full batch.

7. STEM: Use direct interrogatives (Who, What, Where, When).
   Never include the answer string verbatim in the stem.
   All rendered text (stem, options, fact) must use the specified language.
   UIDs and slugs are always English regardless of output language.

Output rules:
- Return a raw JSON array only. No markdown. No prose.
- Each element: {"uid":"string","stem":"string","options":{"A":"string","B":"string","C":"string","D":"string"},"answer":"A|B|C|D","fact":"string","difficulty":"string"}"""


_LFM_QA = """You are GUSE-QA, a cold analytical auditor. You do not generate questions.
You receive a batch of trivia questions and must audit, remediate within limits, or flag for regeneration.
Return a single JSON object.

AUDIT — check every question for all of the following before remediating anything:
a. Factual accuracy of the correct answer.
b. Correct mapping of answer letter to the options object.
c. Scope compliance: question must stay within topic_scope.
d. Difficulty alignment with the difficulty field.
e. Cross-question distractor leakage: no correct answer from Question N as a distractor in Question M.
f. Answer distribution: no letter exceeds 35% across the batch.
g. UID structural validity: must match slug|slug|slug.

REMEDIATION — apply exactly one action per flagged question:

FIX_IN_PLACE (core question and answer are sound):
  - Wrong answer letter → correct the letter.
  - Distribution imbalance → swap positions, update letter.
  - Minor stem leak → rephrase stem only.
  - Tautological fact → rewrite to add mechanism or context.

FLAG_FOR_REGENERATION (structurally unsound):
  - Out of topic_scope.
  - Difficulty fundamentally misaligned.
  - Distractor class wrong or trivially false.
  - Factual error in the correct answer.
  - UID structurally invalid.

FINAL CHECK after all remediations:
  - No approved answer appears as a distractor in another approved question.
  - Answer distribution <= 35% per letter across approved batch only.
  - Every fact describes mechanism or context, not a restatement of the stem.

Output rules:
- Return raw JSON only. No markdown. No prose.
- count reflects only approved or fixed-in-place questions.
- reason field in every log entry must be exactly one sentence.
- Do not alter UIDs. Invalid UIDs go to regeneration_required.
- {"status":"complete","count":integer,"questions":[...],"regeneration_required":[{"uid":"string","action":"regenerate","reason":"string"}],"audit_log":[{"uid":"string","action":"fixed|regenerate","reason":"string"}]}"""


# ─────────────────────────────────────────────────────────────────
# PROMPT DISPATCH — selects correct prompts for active model
# ─────────────────────────────────────────────────────────────────

PROMPTS: dict[str, dict[str, str]] = {
    "qwen": {
        "validator": _QWEN_VALIDATOR,
        "generator": _QWEN_GENERATOR,
        "qa":        _QWEN_QA,
    },
    "lfm25": {
        "validator": _LFM_VALIDATOR,
        "generator": _LFM_GENERATOR,
        "qa":        _LFM_QA,
    },
}


def get_prompt(stage: str) -> str:
    """Return the system prompt for the given stage and active model."""
    return PROMPTS[ACTIVE_MODEL][stage]


# ─────────────────────────────────────────────────────────────────
# UTILITIES
# ─────────────────────────────────────────────────────────────────

def banner(title: str) -> None:
    width = 60
    print(f"\n{'═' * width}")
    print(f"  {title}")
    print(f"{'═' * width}")


def section(label: str) -> None:
    print(f"\n── {label} {'─' * (54 - len(label))}")


def pretty(data: Any) -> None:
    print(json.dumps(data, indent=2, ensure_ascii=False))


def extract_json(raw: str) -> dict:
    """
    Extract the JSON object from a model response that may contain:
      - A well-formed <think>...</think> block (Qwen)
      - Reasoning prose ending with </think> but no opening tag
      - Naked prose followed by JSON (LFM2.5 with _JSON_LOCK prefix)
      - Markdown code fences around the JSON
    Strategy: strip all known wrapper formats, then extract the
    outermost { ... } object, which is always the JSON payload.
    """
    stripped = re.sub(r"<think>.*?</think>", "", raw, flags=re.DOTALL)
    stripped = re.sub(r".*?</think>", "", stripped, flags=re.DOTALL)
    stripped = re.sub(r"```(?:json)?", "", stripped).strip()

    start = stripped.find("{")
    end   = stripped.rfind("}")
    if start == -1 or end == -1:
        # Try array response (Generator may return a bare array)
        start = stripped.find("[")
        end   = stripped.rfind("]")
        if start == -1 or end == -1:
            raise ValueError(
                f"No JSON object or array found in model output.\nRaw output:\n{raw}"
            )

    json_candidate = stripped[start:end + 1]
    try:
        return json.loads(json_candidate)
    except json.JSONDecodeError as exc:
        raise ValueError(
            f"JSON parse failed.\nExtracted candidate:\n{json_candidate}\n"
            f"Raw model output:\n{raw}\nError: {exc}"
        ) from exc


# Hard prefix appended to every user message.
# Reinforces JSON-only output at the user-turn level.
_JSON_LOCK = (
    "You must respond with raw JSON only. "
    "No markdown. No explanation. No prose. "
    "Your response must begin with { or [ and contain nothing else.\n\n"
    "Input:\n"
)


def call_model(system_prompt: str, user_message: str) -> str:
    """
    Send a request to LM Studio via the official Python SDK.
    Selects model config based on ACTIVE_MODEL.

    Auth: disable in LM Studio (Server tab -> uncheck Enable Authentication)
    or set LM_STUDIO_API_KEY env var.
    """
    cfg     = MODEL_CONFIGS[ACTIVE_MODEL]
    api_key = os.environ.get("LM_STUDIO_API_KEY")

    # Build inference config — only include optional params if set
    inference_config: dict = {
        "maxTokens":   cfg["max_tokens"],
        "temperature": cfg["temperature"],
    }
    if cfg.get("top_k") is not None:
        inference_config["topK"] = cfg["top_k"]
    if cfg.get("rep_penalty") is not None:
        inference_config["repeatPenalty"] = cfg["rep_penalty"]

    # System prompt goes into Chat(), not into respond() config.
    # Passing it via config["systemPrompt"] is silently dropped by the SDK.
    chat = lmstudio.Chat(system_prompt)
    chat.add_user_message(_JSON_LOCK + user_message)

    try:
        client = lmstudio.Client(api_key=api_key) if api_key else lmstudio.Client()
        with client:
            model = client.llm.model(cfg["model_id"])
            response = model.respond(chat, config=inference_config)
            raw = str(response)
            if DEBUG_RAW:
                print(f"\n[DEBUG raw — {ACTIVE_MODEL} / {cfg['model_id']}]")
                print("─" * 40)
                print(raw)
                print("─" * 40)
            return raw
    except Exception as exc:
        print(f"\n[ERROR] LM Studio SDK error: {exc}\n")
        print(
            "  Option A: disable auth in LM Studio:\n"
            "    Server tab -> uncheck 'Enable Authentication'\n"
            "  Option B: LM_STUDIO_API_KEY='lms-xxxx' python guse_pipeline.py"
        )
        sys.exit(1)


# ─────────────────────────────────────────────────────────────────
# PIPELINE STAGES
# ─────────────────────────────────────────────────────────────────

def run_validator(raw_input: dict) -> dict:
    section("STAGE 1 — GUSE-VALIDATOR")
    print("Input payload:")
    pretty(raw_input)

    raw    = call_model(get_prompt("validator"), json.dumps(raw_input))
    result = extract_json(raw)

    print("\nValidator output:")
    pretty(result)

    if result.get("status") == "error":
        print(f"\n[PIPELINE HALT] Validator rejected: {result['code']} — {result['reason']}")
        sys.exit(1)

    return result


def run_generator(validated: dict, seen_fingerprints: list[str]) -> dict:
    section("STAGE 2 — GUSE-GENERATOR")
    payload = {**validated, "seen_fingerprints": seen_fingerprints}
    print("Generator payload (seen_fingerprints count):", len(seen_fingerprints))

    raw    = call_model(get_prompt("generator"), json.dumps(payload))
    result = extract_json(raw)

    # Normalize: Generator may return a bare array or a dict with "questions" key
    if isinstance(result, list):
        result = {"questions": result}

    print("\nGenerator output:")
    pretty(result)
    return result


def run_qa(generator_output: dict, validated: dict) -> dict:
    section("STAGE 3 — GUSE-QA")
    qa_payload = {
        "generator_output":  generator_output,
        "topic_scope":       validated["topic_scope"],
        "seen_fingerprints": validated["seen_fingerprints"],
    }

    raw    = call_model(get_prompt("qa"), json.dumps(qa_payload))
    result = extract_json(raw)

    print("\nQA output:")
    pretty(result)
    return result


# ─────────────────────────────────────────────────────────────────
# RETRY LOOP
# ─────────────────────────────────────────────────────────────────

def collect_fingerprints(questions: list[dict]) -> list[str]:
    return [
        q["uid"]
        for q in questions
        if isinstance(q.get("uid"), str) and q["uid"].count("|") == 2
    ]


def run_regen_cycle(
    qa_result: dict,
    validated: dict,
    accumulated_fingerprints: list[str],
    cycle: int,
) -> tuple[dict, list[str]]:
    regen_needed = qa_result.get("regeneration_required", [])
    if not regen_needed:
        return qa_result, accumulated_fingerprints

    section(f"REGENERATION CYCLE {cycle}")
    print(f"Questions requiring regeneration: {len(regen_needed)}")
    for entry in regen_needed:
        print(f"  uid={entry['uid']}  reason={entry['reason']}")

    regen_payload = {
        **validated,
        "count":             len(regen_needed),
        "seen_fingerprints": accumulated_fingerprints,
        "regeneration_context": {
            "flagged_uids": [e["uid"] for e in regen_needed],
            "reasons":      [e["reason"] for e in regen_needed],
            "instruction":  (
                "Generate replacement questions only for the flagged UIDs. "
                "Each replacement must cover the same topic_scope but test "
                "a distinct fact not represented in seen_fingerprints."
            ),
        },
    }

    new_gen           = run_generator(regen_payload, accumulated_fingerprints)
    existing_approved = qa_result.get("questions", [])
    new_questions     = new_gen.get("questions", [])

    merged_batch = {
        "questions":   existing_approved + new_questions,
        "topic_scope": validated["topic_scope"],
        "difficulty":  validated["difficulty"],
    }

    new_qa           = run_qa(merged_batch, validated)
    new_fingerprints = collect_fingerprints(new_qa.get("questions", []))
    updated_fps      = list(set(accumulated_fingerprints + new_fingerprints))

    return new_qa, updated_fps


# ─────────────────────────────────────────────────────────────────
# MAIN PIPELINE
# ─────────────────────────────────────────────────────────────────

def run_pipeline(raw_input: dict) -> dict:
    cfg = MODEL_CONFIGS[ACTIVE_MODEL]
    banner(
        f"GUSE v8.1 Pipeline  —  {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}\n"
        f"  Model : {ACTIVE_MODEL} ({cfg['model_id']})"
    )

    validated = run_validator(raw_input)
    accumulated_fingerprints: list[str] = validated.get("seen_fingerprints", [])

    generator_output = run_generator(validated, accumulated_fingerprints)
    accumulated_fingerprints = list(set(
        accumulated_fingerprints
        + collect_fingerprints(generator_output.get("questions", []))
    ))

    qa_result = run_qa(generator_output, validated)
    accumulated_fingerprints = list(set(
        accumulated_fingerprints
        + collect_fingerprints(qa_result.get("questions", []))
    ))

    cycle = 1
    while qa_result.get("regeneration_required") and cycle <= MAX_REGEN_CYCLES:
        qa_result, accumulated_fingerprints = run_regen_cycle(
            qa_result, validated, accumulated_fingerprints, cycle
        )
        cycle += 1

    banner("PIPELINE COMPLETE")
    remaining_regen = qa_result.get("regeneration_required", [])
    approved_count  = qa_result.get("count", len(qa_result.get("questions", [])))

    print(f"  Approved questions : {approved_count}")
    print(f"  Regen cycles run   : {cycle - 1}")
    print(f"  Unresolved slots   : {len(remaining_regen)}")
    print(f"  Total fingerprints : {len(accumulated_fingerprints)}")

    if remaining_regen:
        section("Unresolved after max retries")
        for entry in remaining_regen:
            print(f"  uid={entry['uid']}  reason={entry['reason']}")

    section("Approved Questions")
    for i, q in enumerate(qa_result.get("questions", []), 1):
        print(f"\n  [{i}] {q.get('stem', '(no stem)')}")
        for letter, text in q.get("options", {}).items():
            marker = "✓" if letter == q.get("answer") else " "
            print(f"       {marker} {letter}. {text}")
        print(f"      Fact: {q.get('fact', '')}")
        print(f"      UID:  {q.get('uid', '')}")

    section("Audit Log")
    for entry in qa_result.get("audit_log", []):
        print(f"  [{entry['action'].upper():10}] {entry['uid']} — {entry['reason']}")

    return qa_result


# ─────────────────────────────────────────────────────────────────
# ENTRY POINT
# Change ACTIVE_MODEL at the top to switch between qwen and lfm25.
# ─────────────────────────────────────────────────────────────────

if __name__ == "__main__":
    sample_input = {
        "topic":            "The Roman Colosseum",
        "difficulty":       "medium",
        "count":            5,
        "language":         "en",
        "seen_fingerprints": [],
    }

    final_result = run_pipeline(sample_input)

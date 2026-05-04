# keycloak-twitch portfolio roadmap

## Snapshot
- 90-day evidence: 5 commits, 18 files touched
- Last signal: `d85c7e6`
- Top modified areas: `src` (5), `00_agile` (6), `pom.xml`, `Dockerfile`, `RELEASE_NOTES.md`
- Stack: Other/Assets (Java + Keycloak SPI)
- Docs folder: no
- Roadmap folder: no
- Features docs: no
- Tests indexed: limited (one core test file observed)

## Implemented now (V1 baseline)
- OIDC identity provider integration is centered in `TwitchOidcIdentityProvider*` classes.
- Service loading metadata is present under `META-INF/services`.
- Packaging and release automation artifacts exist (`pom.xml`, `Dockerfile`, release notes).

## Gaps identified
- No docs/features structure yet, despite active source and release changes.
- Test coverage appears limited relative to provider auth flow risk.
- Onboarding/running guide is mostly outside versioned docs structure.
- No explicit release checklist connecting code changes and deployment behavior.

## V1 (stability and product hygiene)
- [ ] Create `docs/features/` with baseline files for identity provider flow and deployment model.
- [ ] Add `docs/roadmaps/` file with explicit compatibility and security checklist.
- [ ] Expand tests around token normalization, auth redirect handling, and error mapping.
- [ ] Add README sections for local and containerized run verification.

## V2 (security and reliability)
- [ ] Add integration harness against a disposable Keycloak + Twitch test realm.
- [ ] Standardize error contracts for OIDC callback and claim validation.
- [ ] Add release checks for docker image smoke startup and SPI registration.
- [ ] Introduce semantic versioning and migration notes for config/schema changes.

## V10 (identity platform maturity)
- [ ] Add observability and audit-focused event emission.
- [ ] Make provider configuration support multi-env templates.
- [ ] Build backward-compatible extension points for additional identity providers.
- [ ] Add long-term deprecation policy and security review cadence.

## Immediate checklists
- [ ] New release includes config docs, test evidence, and rollout notes.
- [ ] Feature owner confirms OAuth edge-case behavior is covered.

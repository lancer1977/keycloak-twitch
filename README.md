# Keycloak Twitch OIDC Provider

This project provides a custom Keycloak OIDC identity provider for Twitch that keeps Keycloak's normal OIDC broker behavior, but normalizes Twitch token responses where `scope` is returned as a JSON array instead of a string.

## What it does

- Registers a new provider id: `twitch-oidc`
- Extends Keycloak's standard OIDC broker flow
- Normalizes Twitch token responses before Keycloak deserializes them
- Keeps the rest of the broker behavior unchanged

## Build

### With Maven installed locally

```bash
mvn clean test package
```

### With Docker, if Maven is not installed on the host

```bash
docker run --rm \
  -v "$PWD":/workspace \
  -w /workspace \
  maven:3.9-eclipse-temurin-17 \
  mvn clean test package
```

The deployable artifact will be:

```text
target/keycloak-twitch-provider-1.0.0-provider.jar
```

## Install into Keycloak

1. Make the JAR available to Keycloak's providers directory.

   The easiest approach for this stack is to mount the repo's `target/` directory into the Keycloak container's `/opt/keycloak/providers/` directory, or copy the built JAR there directly.

   If you copy it manually, use:

   ```bash
   cp target/keycloak-twitch-provider-1.0.0-provider.jar /path/to/keycloak/providers/
   ```

2. Update the Keycloak identity provider entry for Twitch:
   - change `providerId` from `oidc` to `twitch-oidc`
   - keep the existing alias and config values

3. Restart Keycloak.

## Suggested realm config fields

Use the same values you already validated for Twitch OIDC:

- `authorizationUrl`: `https://id.twitch.tv/oauth2/authorize`
- `tokenUrl`: `https://id.twitch.tv/oauth2/token` or the shim/proxy you want to keep
- `userInfoUrl`: `https://id.twitch.tv/oauth2/userinfo`
- `issuer`: `https://id.twitch.tv/oauth2`
- `jwksUrl`: `https://id.twitch.tv/oauth2/keys`
- `validateSignature`: `true`
- `clientAuthMethod`: `client_secret_post`
- `defaultScope`: `openid`

## Repoint the realm

After the provider JAR is installed, update the `polyhydra` realm identity provider so the Twitch entry uses:

```json
{
  "providerId": "twitch-oidc"
}
```

Everything else can stay the same.

## Notes

- This provider only changes response normalization.
- It does **not** change account linking, mappers, or standard OIDC login behavior.
- The goal is to avoid the `AccessTokenResponse.scope` deserialization failure while keeping the rest of the flow native to Keycloak.

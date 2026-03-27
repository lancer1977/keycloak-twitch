# Keycloak Twitch Provider v1.0.0

Initial release of the custom Twitch OIDC provider for Keycloak 26.3.0.

## Highlights

- Adds a new Keycloak identity provider id: `twitch-oidc`
- Keeps Keycloak's standard OIDC broker flow
- Normalizes Twitch token responses where `scope` is returned as a JSON array
- Avoids the `AccessTokenResponse.scope` deserialization failure in Keycloak

## Included files

- Maven build and shaded provider JAR packaging
- ServiceLoader registration for the Keycloak SPI
- Dockerfile for building a provider-baked Keycloak image
- GitHub Actions workflow to publish the image to GHCR

## Install

Build the image locally:

```bash
docker build -t ghcr.io/lancer1977/keycloak-twitch:1.0.0 .
```

Or let GitHub Actions build and publish the image from the `main` branch or a `v*` tag.

Then update the `polyhydra` realm Twitch identity provider to use `providerId=twitch-oidc`.

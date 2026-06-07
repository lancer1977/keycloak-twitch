# Keycloak Community Identity Providers

This project packages a Keycloak provider JAR for Twitch plus a set of preconfigured community identity-provider factories. The Twitch provider keeps Keycloak's normal OIDC broker behavior, but normalizes Twitch token responses where `scope` is returned as a JSON array instead of a string.

## Tags

- keycloak
- identity-provider
- twitch
- discord
- spotify
- github
- patreon
- reddit
- microsoft
- apple
- youtube
- docker

## What it does

- Registers the Twitch OIDC provider id: `twitch-oidc`
- Registers preset provider ids for additional community/social providers:
  - `discord-oauth2`
  - `spotify-oauth2`
  - `github-oauth2`
  - `patreon-oauth2`
  - `reddit-oauth2`
  - `microsoft-oidc`
  - `apple-oidc`
  - `youtube-oidc`
- Extends Keycloak's standard OIDC broker flow for Twitch
- Uses Keycloak's standard OAuth2/OIDC broker behavior for the preset providers
- Keeps secrets out of source; each provider still needs a client id and client secret/private-key setup in the Keycloak realm

## Build

### Container image

Build the custom Keycloak image locally:

```bash
docker build -t ghcr.io/lancer1977/keycloak-twitch:local .
```

Run it the same way as your current Keycloak container, but with this provider JAR baked in.

### Provider JAR

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

2. Restart Keycloak so the provider factory SPI is reloaded.

3. Create or update realm identity providers with the provider ids listed above.

## Twitch realm config fields

Use these values for Twitch OIDC:

- `providerId`: `twitch-oidc`
- `authorizationUrl`: `https://id.twitch.tv/oauth2/authorize`
- `tokenUrl`: `https://id.twitch.tv/oauth2/token`
- `userInfoUrl`: `https://id.twitch.tv/oauth2/userinfo`
- `issuer`: `https://id.twitch.tv/oauth2`
- `jwksUrl`: `https://id.twitch.tv/oauth2/keys`
- `useJwksUrl`: `true`
- `validateSignature`: `true`
- `clientAuthMethod`: `client_secret_post`
- `defaultScope`: `openid user:read:email`

## Preset provider defaults

The preset factories supply endpoint and claim defaults so the Keycloak admin UI can start from a known-good configuration. You still need to create provider applications at each upstream service and paste the client credentials into Keycloak.

| Provider | Provider ID | Default scope | Notes |
| --- | --- | --- | --- |
| Discord | `discord-oauth2` | `identify email` | Good next community identity provider. |
| Spotify | `spotify-oauth2` | `user-read-email user-read-private` | Useful for music/community profile hints. |
| GitHub | `github-oauth2` | `read:user user:email` | Keycloak also has built-in GitHub in some distributions; this preset keeps it explicit in this JAR. |
| Patreon | `patreon-oauth2` | `identity identity[email]` | Patreon profile JSON is nested; verify claim mapping during live setup. |
| Reddit | `reddit-oauth2` | `identity` | Reddit does not provide email through the basic identity scope. |
| Microsoft | `microsoft-oidc` | `openid profile email User.Read` | Uses Microsoft common v2 endpoints and JWKS. |
| Apple | `apple-oidc` | `openid email name` | Apple production setup also requires Apple's private-key/client-secret JWT process. |
| YouTube / Google | `youtube-oidc` | `openid profile email https://www.googleapis.com/auth/youtube.readonly` | Use this when YouTube channel access is wanted in addition to Google login. |

## Steam path

Steam is not OAuth2/OIDC. Steam login is based on OpenID 2.0, so it does not fit Keycloak's standard OIDC/OAuth2 provider classes or the simple preset-factory pattern above.

Best path for Steam:

1. Add Steam as a separate custom Keycloak identity provider implementation, not as an `oauth2` or `oidc` preset.
2. Implement OpenID 2.0 redirect/validation against `https://steamcommunity.com/openid/login`.
3. Resolve the returned SteamID64 through the Steam Web API if richer profile data is needed.
4. Map the stable SteamID64 into a Keycloak user attribute such as `steam.id`.

That should be a follow-up slice because it is a different protocol and needs real callback validation tests rather than a small endpoint preset.

## Repoint an existing realm provider

After the provider JAR is installed, update the existing `polyhydra` realm identity provider alias to the matching custom provider id. For Twitch, keep alias `twitch` and use:

```json
{
  "providerId": "twitch-oidc"
}
```

Everything else can stay the same except the updated Twitch scope/JWKS values above.

## Notes

- The Twitch provider only changes response normalization.
- The preset providers do not store secrets or provision upstream OAuth apps.
- Account linking, claim mappers, and profile attribute persistence still need realm-level configuration.
- Steam requires a purpose-built provider because it uses OpenID 2.0, not OIDC.

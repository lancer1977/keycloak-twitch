package com.devforge.keycloak.twitch;

import java.util.Map;

public class YoutubeOidcIdentityProviderFactory extends PresetOidcIdentityProviderFactory {
    public YoutubeOidcIdentityProviderFactory() {
        super("youtube-oidc", "YouTube / Google OIDC", Map.of(
                "authorizationUrl", "https://accounts.google.com/o/oauth2/v2/auth",
                "tokenUrl", "https://oauth2.googleapis.com/token",
                "userInfoUrl", "https://openidconnect.googleapis.com/v1/userinfo",
                "issuer", "https://accounts.google.com",
                "jwksUrl", "https://www.googleapis.com/oauth2/v3/certs",
                "useJwksUrl", "true",
                "validateSignature", "true",
                "defaultScope", "openid profile email https://www.googleapis.com/auth/youtube.readonly"
        ));
    }
}

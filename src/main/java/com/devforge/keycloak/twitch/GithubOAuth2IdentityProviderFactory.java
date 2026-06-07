package com.devforge.keycloak.twitch;

import java.util.Map;

public class GithubOAuth2IdentityProviderFactory extends PresetOAuth2IdentityProviderFactory {
    public GithubOAuth2IdentityProviderFactory() {
        super("github-oauth2", "GitHub OAuth2", Map.of(
                "authorizationUrl", "https://github.com/login/oauth/authorize",
                "tokenUrl", "https://github.com/login/oauth/access_token",
                "userInfoUrl", "https://api.github.com/user",
                "defaultScope", "read:user user:email",
                "userIDClaim", "id",
                "userNameClaim", "login",
                "emailClaim", "email"
        ));
    }
}

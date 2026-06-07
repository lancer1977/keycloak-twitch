package com.devforge.keycloak.twitch;

import java.util.Map;

public class RedditOAuth2IdentityProviderFactory extends PresetOAuth2IdentityProviderFactory {
    public RedditOAuth2IdentityProviderFactory() {
        super("reddit-oauth2", "Reddit OAuth2", Map.of(
                "authorizationUrl", "https://www.reddit.com/api/v1/authorize",
                "tokenUrl", "https://www.reddit.com/api/v1/access_token",
                "userInfoUrl", "https://oauth.reddit.com/api/v1/me",
                "defaultScope", "identity",
                "userIDClaim", "id",
                "userNameClaim", "name"
        ));
    }
}

package com.devforge.keycloak.twitch;

import java.util.Map;

public class DiscordOAuth2IdentityProviderFactory extends PresetOAuth2IdentityProviderFactory {
    public DiscordOAuth2IdentityProviderFactory() {
        super("discord-oauth2", "Discord OAuth2", Map.of(
                "authorizationUrl", "https://discord.com/oauth2/authorize",
                "tokenUrl", "https://discord.com/api/oauth2/token",
                "userInfoUrl", "https://discord.com/api/users/@me",
                "defaultScope", "identify email",
                "userIDClaim", "id",
                "userNameClaim", "username",
                "emailClaim", "email"
        ));
    }
}

package com.devforge.keycloak.twitch;

import org.junit.jupiter.api.Test;
import org.keycloak.models.IdentityProviderModel;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SocialIdentityProviderDefaultsTest {
    @Test
    void discordDefaultsUseCommunityIdentityEndpoints() {
        IdentityProviderModel config = new DiscordOAuth2IdentityProviderFactory().createConfig();

        assertEquals("https://discord.com/oauth2/authorize", config.getConfig().get("authorizationUrl"));
        assertEquals("https://discord.com/api/oauth2/token", config.getConfig().get("tokenUrl"));
        assertEquals("https://discord.com/api/users/@me", config.getConfig().get("userInfoUrl"));
        assertEquals("identify email", config.getConfig().get("defaultScope"));
        assertEquals("id", config.getConfig().get("userIDClaim"));
        assertEquals("username", config.getConfig().get("userNameClaim"));
    }

    @Test
    void youtubeDefaultsRequestOidcAndReadonlyChannelScope() {
        IdentityProviderModel config = new YoutubeOidcIdentityProviderFactory().createConfig();

        assertEquals("https://accounts.google.com", config.getConfig().get("issuer"));
        assertEquals("https://www.googleapis.com/oauth2/v3/certs", config.getConfig().get("jwksUrl"));
        assertEquals("true", config.getConfig().get("useJwksUrl"));
        assertEquals("true", config.getConfig().get("validateSignature"));
        assertEquals("openid profile email https://www.googleapis.com/auth/youtube.readonly", config.getConfig().get("defaultScope"));
    }
}

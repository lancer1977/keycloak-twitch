package com.devforge.keycloak.twitch;

import org.junit.jupiter.api.Test;
import org.keycloak.broker.provider.IdentityProviderFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SocialIdentityProviderFactoriesTest {
    @Test
    void exposesCommunityProviderFactoriesWithStableIds() {
        List<IdentityProviderFactory<?>> factories = List.of(
                new TwitchOidcIdentityProviderFactory(),
                new DiscordOAuth2IdentityProviderFactory(),
                new SpotifyOAuth2IdentityProviderFactory(),
                new GithubOAuth2IdentityProviderFactory(),
                new PatreonOAuth2IdentityProviderFactory(),
                new RedditOAuth2IdentityProviderFactory(),
                new MicrosoftOidcIdentityProviderFactory(),
                new AppleOidcIdentityProviderFactory(),
                new YoutubeOidcIdentityProviderFactory()
        );

        assertEquals(List.of(
                "twitch-oidc",
                "discord-oauth2",
                "spotify-oauth2",
                "github-oauth2",
                "patreon-oauth2",
                "reddit-oauth2",
                "microsoft-oidc",
                "apple-oidc",
                "youtube-oidc"
        ), factories.stream().map(IdentityProviderFactory::getId).toList());
    }

    @Test
    void serviceRegistrationIncludesEveryFactory() throws Exception {
        try (var stream = getClass().getClassLoader().getResourceAsStream("META-INF/services/org.keycloak.broker.provider.IdentityProviderFactory")) {
            assertTrue(stream != null, "IdentityProviderFactory service file must exist");
            var registeredFactories = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))
                    .lines()
                    .filter(line -> !line.isBlank())
                    .collect(Collectors.toSet());

            assertTrue(registeredFactories.contains("com.devforge.keycloak.twitch.TwitchOidcIdentityProviderFactory"));
            assertTrue(registeredFactories.contains("com.devforge.keycloak.twitch.DiscordOAuth2IdentityProviderFactory"));
            assertTrue(registeredFactories.contains("com.devforge.keycloak.twitch.SpotifyOAuth2IdentityProviderFactory"));
            assertTrue(registeredFactories.contains("com.devforge.keycloak.twitch.GithubOAuth2IdentityProviderFactory"));
            assertTrue(registeredFactories.contains("com.devforge.keycloak.twitch.PatreonOAuth2IdentityProviderFactory"));
            assertTrue(registeredFactories.contains("com.devforge.keycloak.twitch.RedditOAuth2IdentityProviderFactory"));
            assertTrue(registeredFactories.contains("com.devforge.keycloak.twitch.MicrosoftOidcIdentityProviderFactory"));
            assertTrue(registeredFactories.contains("com.devforge.keycloak.twitch.AppleOidcIdentityProviderFactory"));
            assertTrue(registeredFactories.contains("com.devforge.keycloak.twitch.YoutubeOidcIdentityProviderFactory"));
        }
    }
}

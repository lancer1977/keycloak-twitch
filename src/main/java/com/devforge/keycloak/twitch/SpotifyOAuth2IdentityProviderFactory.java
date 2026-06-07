package com.devforge.keycloak.twitch;

import java.util.Map;

public class SpotifyOAuth2IdentityProviderFactory extends PresetOAuth2IdentityProviderFactory {
    public SpotifyOAuth2IdentityProviderFactory() {
        super("spotify-oauth2", "Spotify OAuth2", Map.of(
                "authorizationUrl", "https://accounts.spotify.com/authorize",
                "tokenUrl", "https://accounts.spotify.com/api/token",
                "userInfoUrl", "https://api.spotify.com/v1/me",
                "defaultScope", "user-read-email user-read-private",
                "userIDClaim", "id",
                "userNameClaim", "display_name",
                "emailClaim", "email"
        ));
    }
}

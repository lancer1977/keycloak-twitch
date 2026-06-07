package com.devforge.keycloak.twitch;

import java.util.Map;

public class PatreonOAuth2IdentityProviderFactory extends PresetOAuth2IdentityProviderFactory {
    public PatreonOAuth2IdentityProviderFactory() {
        super("patreon-oauth2", "Patreon OAuth2", Map.of(
                "authorizationUrl", "https://www.patreon.com/oauth2/authorize",
                "tokenUrl", "https://www.patreon.com/api/oauth2/token",
                "userInfoUrl", "https://www.patreon.com/api/oauth2/v2/identity?fields%5Buser%5D=email,full_name,vanity",
                "defaultScope", "identity identity[email]",
                "userIDClaim", "data.id",
                "userNameClaim", "data.attributes.vanity",
                "fullNameClaim", "data.attributes.full_name",
                "emailClaim", "data.attributes.email"
        ));
    }
}

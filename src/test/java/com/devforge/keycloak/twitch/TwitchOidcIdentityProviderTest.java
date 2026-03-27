package com.devforge.keycloak.twitch;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TwitchOidcIdentityProviderTest {
    @Test
    void normalizesScopeArrayToString() {
        String input = "{\"access_token\":\"abc\",\"scope\":[\"openid\",\"user:read:email\"],\"token_type\":\"bearer\"}";

        String normalized = TwitchTokenResponseNormalizer.normalize(input);

        assertEquals("{\"access_token\":\"abc\",\"scope\":\"openid user:read:email\",\"token_type\":\"bearer\"}", normalized);
    }

    @Test
    void leavesStringScopeUntouchedSemantically() {
        String input = "{\"access_token\":\"abc\",\"scope\":\"openid\",\"token_type\":\"bearer\"}";

        String normalized = TwitchTokenResponseNormalizer.normalize(input);

        assertEquals("{\"access_token\":\"abc\",\"scope\":\"openid\",\"token_type\":\"bearer\"}", normalized);
    }
}

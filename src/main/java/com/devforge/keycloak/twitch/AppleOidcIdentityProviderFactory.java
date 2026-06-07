package com.devforge.keycloak.twitch;

import java.util.Map;

public class AppleOidcIdentityProviderFactory extends PresetOidcIdentityProviderFactory {
    public AppleOidcIdentityProviderFactory() {
        super("apple-oidc", "Apple OIDC", Map.of(
                "authorizationUrl", "https://appleid.apple.com/auth/authorize",
                "tokenUrl", "https://appleid.apple.com/auth/token",
                "issuer", "https://appleid.apple.com",
                "jwksUrl", "https://appleid.apple.com/auth/keys",
                "useJwksUrl", "true",
                "validateSignature", "true",
                "defaultScope", "openid email name"
        ));
    }
}

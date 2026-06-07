package com.devforge.keycloak.twitch;

import java.util.Map;

public class MicrosoftOidcIdentityProviderFactory extends PresetOidcIdentityProviderFactory {
    public MicrosoftOidcIdentityProviderFactory() {
        super("microsoft-oidc", "Microsoft OIDC", Map.of(
                "authorizationUrl", "https://login.microsoftonline.com/common/oauth2/v2.0/authorize",
                "tokenUrl", "https://login.microsoftonline.com/common/oauth2/v2.0/token",
                "userInfoUrl", "https://graph.microsoft.com/oidc/userinfo",
                "issuer", "https://login.microsoftonline.com/common/v2.0",
                "jwksUrl", "https://login.microsoftonline.com/common/discovery/v2.0/keys",
                "useJwksUrl", "true",
                "validateSignature", "true",
                "defaultScope", "openid profile email User.Read"
        ));
    }
}

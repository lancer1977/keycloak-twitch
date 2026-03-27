package com.devforge.keycloak.twitch;

import org.keycloak.broker.oidc.OIDCIdentityProvider;
import org.keycloak.broker.oidc.OIDCIdentityProviderConfig;
import org.keycloak.broker.oidc.OIDCIdentityProviderFactory;
import org.keycloak.models.IdentityProviderModel;
import org.keycloak.models.KeycloakSession;

/**
 * Registers the custom Twitch OIDC provider under its own provider id.
 */
public class TwitchOidcIdentityProviderFactory extends OIDCIdentityProviderFactory {
    public static final String PROVIDER_ID = "twitch-oidc";

    @Override
    public String getName() {
        return "Twitch OpenID Connect";
    }

    @Override
    public OIDCIdentityProvider create(KeycloakSession session, IdentityProviderModel model) {
        return new TwitchOidcIdentityProvider(session, new OIDCIdentityProviderConfig(model));
    }

    @Override
    public OIDCIdentityProviderConfig createConfig() {
        return new OIDCIdentityProviderConfig();
    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }
}

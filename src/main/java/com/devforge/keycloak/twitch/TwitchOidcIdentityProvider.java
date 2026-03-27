package com.devforge.keycloak.twitch;

import org.keycloak.broker.oidc.OIDCIdentityProvider;
import org.keycloak.broker.oidc.OIDCIdentityProviderConfig;
import org.keycloak.broker.provider.BrokeredIdentityContext;
import org.keycloak.models.KeycloakSession;

/**
 * Twitch-flavored OIDC provider that keeps Keycloak's standard OIDC behavior,
 * but normalizes Twitch's token response before Keycloak deserializes it.
 */
public class TwitchOidcIdentityProvider extends OIDCIdentityProvider {
    public TwitchOidcIdentityProvider(KeycloakSession session, OIDCIdentityProviderConfig config) {
        super(session, config);
    }

    @Override
    public BrokeredIdentityContext getFederatedIdentity(String response) {
        return super.getFederatedIdentity(TwitchTokenResponseNormalizer.normalize(response));
    }
}

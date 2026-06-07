package com.devforge.keycloak.twitch;

import org.keycloak.broker.oidc.OIDCIdentityProviderConfig;
import org.keycloak.broker.oidc.OIDCIdentityProviderFactory;
import org.keycloak.models.IdentityProviderModel;
import org.keycloak.models.KeycloakSession;

import java.util.Map;

abstract class PresetOidcIdentityProviderFactory extends OIDCIdentityProviderFactory {
    private final String id;
    private final String name;
    private final Map<String, String> defaults;

    PresetOidcIdentityProviderFactory(String id, String name, Map<String, String> defaults) {
        this.id = id;
        this.name = name;
        this.defaults = Map.copyOf(defaults);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public OIDCIdentityProviderConfig createConfig() {
        OIDCIdentityProviderConfig config = new OIDCIdentityProviderConfig();
        defaults.forEach((key, value) -> ProviderDefaults.putIfAbsent(config, key, value));
        return config;
    }

    @Override
    public Map<String, String> parseConfig(KeycloakSession session, String config) {
        Map<String, String> parsed = super.parseConfig(session, config);
        defaults.forEach(parsed::putIfAbsent);
        return parsed;
    }
}

package com.devforge.keycloak.twitch;

final class ProviderDefaults {
    private ProviderDefaults() {
    }

    static void putIfAbsent(org.keycloak.models.IdentityProviderModel config, String key, String value) {
        if (value != null && !config.getConfig().containsKey(key)) {
            config.getConfig().put(key, value);
        }
    }
}

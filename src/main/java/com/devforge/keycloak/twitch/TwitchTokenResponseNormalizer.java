package com.devforge.keycloak.twitch;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Iterator;
import java.util.StringJoiner;

/**
 * Normalizes Twitch token responses into the shape Keycloak's OIDC broker expects.
 */
public final class TwitchTokenResponseNormalizer {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private TwitchTokenResponseNormalizer() {
    }

    public static String normalize(String response) {
        if (response == null || response.isBlank()) {
            return response;
        }

        try {
            JsonNode root = MAPPER.readTree(response);
            if (!(root instanceof ObjectNode objectNode)) {
                return response;
            }

            JsonNode scopeNode = objectNode.get("scope");
            if (scopeNode != null && scopeNode.isArray()) {
                StringJoiner joiner = new StringJoiner(" ");
                for (Iterator<JsonNode> it = scopeNode.elements(); it.hasNext(); ) {
                    JsonNode item = it.next();
                    if (item != null && !item.isNull()) {
                        String scope = item.asText();
                        if (!scope.isBlank()) {
                            joiner.add(scope);
                        }
                    }
                }
                objectNode.put("scope", joiner.toString());
            }

            return MAPPER.writeValueAsString(objectNode);
        } catch (Exception e) {
            return response;
        }
    }
}

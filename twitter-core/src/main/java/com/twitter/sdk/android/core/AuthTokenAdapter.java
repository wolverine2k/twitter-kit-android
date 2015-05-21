package com.twitter.sdk.android.core;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.twitter.sdk.android.core.internal.oauth.GuestAuthToken;
import com.twitter.sdk.android.core.internal.oauth.OAuth2Token;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Provides custom serialization and deserialization for classes that hold any type of
 * {@link com.twitter.sdk.android.core.AuthToken}.
 */
public class AuthTokenAdapter implements JsonSerializer<AuthToken>, JsonDeserializer<AuthToken> {

    private static final String AUTH_TYPE = "auth_type";
    private static final String AUTH_TOKEN = "auth_token";

    static final Map<String, Class<? extends AuthToken>> authTypeRegistry
            = new HashMap<String, Class<? extends AuthToken>>();
    static {
        authTypeRegistry.put("oauth1a", TwitterAuthToken.class);
        authTypeRegistry.put("oauth2", OAuth2Token.class);
        authTypeRegistry.put("guest", GuestAuthToken.class);
    }

    private final Gson gson;

    public AuthTokenAdapter() {
        this.gson = new Gson();
    }

    @Override
    public JsonElement serialize(AuthToken src, Type typeOfSrc, JsonSerializationContext context) {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(AUTH_TYPE, getAuthTypeString(src.getClass()));
        jsonObject.add(AUTH_TOKEN, gson.toJsonTree(src));
        return jsonObject;
    }

    @Override
    public AuthToken deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        final JsonObject jsonObject =  json.getAsJsonObject();
        final JsonPrimitive jsonAuthType = jsonObject.getAsJsonPrimitive(AUTH_TYPE);
        final String authType = jsonAuthType.getAsString();
        final JsonElement jsonAuthToken = jsonObject.get(AUTH_TOKEN);
        return gson.fromJson(jsonAuthToken, authTypeRegistry.get(authType));
    }

    static String getAuthTypeString(Class<? extends AuthToken> authTokenClass) {
        for (Map.Entry<String, Class<? extends AuthToken>> entry : authTypeRegistry.entrySet()) {
            if (entry.getValue().equals(authTokenClass)) {
                return entry.getKey();
            }
        }
        return "";
    }
}

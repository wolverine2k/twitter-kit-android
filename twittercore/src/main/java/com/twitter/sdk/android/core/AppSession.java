package com.twitter.sdk.android.core;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.twitter.sdk.android.core.internal.oauth.GuestAuthToken;
import com.twitter.sdk.android.core.internal.oauth.OAuth2Token;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.services.persistence.SerializationStrategy;

public class AppSession extends Session<OAuth2Token> {

    AppSession(OAuth2Token authToken) {
        super(authToken, TwitterSession.LOGGED_OUT_USER_ID);
    }

    AppSession(GuestAuthToken authToken) {
        super(authToken, TwitterSession.LOGGED_OUT_USER_ID);
    }

    static class Serializer implements SerializationStrategy<AppSession> {

        private final Gson gson;

        public Serializer() {
            this.gson = new GsonBuilder()
                    .registerTypeAdapter(OAuth2Token.class, new AuthTokenAdapter())
                    .create();
        }

        @Override
        public String serialize(AppSession session) {
            if (session != null && session.getAuthToken() != null) {
                try {
                    return gson.toJson(session);
                } catch (Exception e) {
                    Fabric.getLogger().d(TwitterCore.TAG,
                            "Failed to serialize session " + e.getMessage());
                }
            }
            return "";
        }

        @Override
        public AppSession deserialize(String serializedSession) {
            if (!TextUtils.isEmpty(serializedSession)) {
                try {
                    return gson.fromJson(serializedSession, AppSession.class);
                } catch (Exception e) {
                    Fabric.getLogger().d(TwitterCore.TAG,
                            "Failed to deserialize session " + e.getMessage());
                }
            }
            return null;
        }
    }
}

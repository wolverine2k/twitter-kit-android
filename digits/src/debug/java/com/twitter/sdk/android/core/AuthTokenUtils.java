package com.twitter.sdk.android.core;

public class AuthTokenUtils {

    public static TwitterAuthToken createTwitterAuthToken(String token, String secret,
            long createdAt) {
        return new TwitterAuthToken(token, secret, createdAt);
    }
}

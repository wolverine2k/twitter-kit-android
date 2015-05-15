package com.twitter.sdk.android.core.internal.oauth;

public class OAuthUtils {

    public static OAuth2Token createOAuth2Token(String tokenType, String accessToken,
            long createdAt) {
        return new OAuth2Token(tokenType, accessToken, createdAt);
    }

    public static GuestAuthToken createGuestAuthToken(String tokenType, String accessToken,
            String guestToken, long createdAt) {
        return new GuestAuthToken(tokenType, accessToken, guestToken, createdAt);
    }
}

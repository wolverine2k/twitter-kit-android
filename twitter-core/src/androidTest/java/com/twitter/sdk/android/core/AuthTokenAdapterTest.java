package com.twitter.sdk.android.core;

import com.twitter.sdk.android.core.internal.oauth.GuestAuthToken;
import com.twitter.sdk.android.core.internal.oauth.OAuth2Token;
import com.twitter.sdk.android.core.internal.oauth.OAuthUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

public class AuthTokenAdapterTest extends TwitterAndroidTestCase {
    private static final long CREATED_AT = 1414450780L;
    private static final String TOKEN_TYPE = "testTokenType";
    private static final String ACCESS_TOKEN = "testAccessToken";
    private static final String GUEST_TOKEN = "testGuestToken";
    private static final String JSON_OAUTH1A_TOKEN
            = "{\"authToken\":{\"auth_type\":\"oauth1a\","
            + "\"auth_token\":{\"secret\":\"" + SECRET + "\","
            + "\"token\":\"" + TOKEN + "\",\"createdAt\":" + CREATED_AT + "}}}";
    private static final String JSON_OAUTH2_TOKEN
            = "{\"authToken\":{\"auth_type\":\"oauth2\","
            + "\"auth_token\":{\"access_token\":\"" + ACCESS_TOKEN + "\","
            + "\"token_type\":\"" + TOKEN_TYPE + "\",\"createdAt\":" + CREATED_AT + "}}}";
    private static final String JSON_GUEST_AUTH_TOKEN
            = "{\"authToken\":{\"auth_type\":\"guest\","
            + "\"auth_token\":{\"guest_token\":\"" + GUEST_TOKEN + "\","
            + "\"access_token\":\"" + ACCESS_TOKEN + "\","
            + "\"token_type\":\"" + TOKEN_TYPE + "\",\"createdAt\":" + CREATED_AT + "}}}";
    private static final String JSON_OAUTH1A_TOKEN_MISSING_CREATED_AT
            = "{\"authToken\":{\"auth_type\":\"oauth1a\","
            + "\"auth_token\":{\"secret\":\"" + SECRET + "\","
            + "\"token\":\"" + TOKEN + "\"}}}";
    private static final String JSON_OAUTH2_TOKEN_MISSING_CREATED_AT
            = "{\"authToken\":{\"auth_type\":\"oauth2\","
            + "\"auth_token\":{\"access_token\":\"" + ACCESS_TOKEN + "\","
            + "\"token_type\":\"" + TOKEN_TYPE + "\"}}}";
    private static final String JSON_GUEST_AUTH_TOKEN_MISSING_CREATED_AT
            = "{\"authToken\":{\"auth_type\":\"guest\","
            + "\"auth_token\":{\"guest_token\":\"" + GUEST_TOKEN + "\","
            + "\"access_token\":\"" + ACCESS_TOKEN + "\","
            + "\"token_type\":\"" + TOKEN_TYPE + "\"}}}";

    private Gson gson;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        gson = new GsonBuilder()
                .registerTypeAdapter(AuthToken.class, new AuthTokenAdapter())
                .create();
    }

    public void testGetAuthTypeString() {
        assertEquals("oauth1a", AuthTokenAdapter.getAuthTypeString(TwitterAuthToken.class));
    }

    public void testGetAuthTypeString_unregisteredAuthType() {
        assertEquals("", AuthTokenAdapter.getAuthTypeString(TestAuthToken.class));
    }

    public void testSerialize_oauth1aToken() {
        final AuthTokenWrapper authTokenWrapper = new AuthTokenWrapper(
                new TwitterAuthToken(TOKEN, SECRET, CREATED_AT));
        final String json = gson.toJson(authTokenWrapper);
        assertEquals(JSON_OAUTH1A_TOKEN, json);
    }

    public void testSerialize_oauth2Token() {
        final AuthTokenWrapper test = new AuthTokenWrapper(
                OAuthUtils.createOAuth2Token(TOKEN_TYPE, ACCESS_TOKEN, CREATED_AT));
        final String json = gson.toJson(test);
        assertEquals(JSON_OAUTH2_TOKEN, json);
    }

    public void testSerialize_guestAuthToken() {
        final AuthTokenWrapper test = new AuthTokenWrapper(
                OAuthUtils.createGuestAuthToken(TOKEN_TYPE, ACCESS_TOKEN, GUEST_TOKEN,
                        CREATED_AT));
        final String json = gson.toJson(test);
        assertEquals(JSON_GUEST_AUTH_TOKEN, json);
    }

    public void testDeserialize_oauth1aToken() {
        final AuthTokenWrapper authTokenWrapper = gson.fromJson(JSON_OAUTH1A_TOKEN,
                AuthTokenWrapper.class);
        assertTrue(authTokenWrapper.authToken instanceof TwitterAuthToken);
        final TwitterAuthToken authToken = (TwitterAuthToken) authTokenWrapper.authToken;
        assertEquals(TOKEN, authToken.token);
        assertEquals(SECRET, authToken.secret);
    }

    public void testDeserialize_oauth2Token() {
        final AuthTokenWrapper authTokenWrapper = gson.fromJson(JSON_OAUTH2_TOKEN,
                AuthTokenWrapper.class);
        assertTrue(authTokenWrapper.authToken instanceof OAuth2Token);
        final OAuth2Token authToken = (OAuth2Token) authTokenWrapper.authToken;
        assertEquals(TOKEN_TYPE, authToken.getTokenType());
        assertEquals(ACCESS_TOKEN, authToken.getAccessToken());
    }

    public void testDeserialize_guestAuthToken() {
        final AuthTokenWrapper authTokenWrapper = gson.fromJson(JSON_GUEST_AUTH_TOKEN,
                AuthTokenWrapper.class);
        assertTrue(authTokenWrapper.authToken instanceof GuestAuthToken);
        final GuestAuthToken authToken = (GuestAuthToken) authTokenWrapper.authToken;
        assertEquals(GUEST_TOKEN, authToken.getGuestToken());
        assertEquals(TOKEN_TYPE, authToken.getTokenType());
        assertEquals(ACCESS_TOKEN, authToken.getAccessToken());
    }

    public void testDeserialize_oauth1aTokenMissingCreatedAt() {
        final AuthTokenWrapper authTokenWrapper = gson.fromJson(
                JSON_OAUTH1A_TOKEN_MISSING_CREATED_AT, AuthTokenWrapper.class);
        assertTrue(authTokenWrapper.authToken instanceof TwitterAuthToken);
        final TwitterAuthToken authToken = (TwitterAuthToken) authTokenWrapper.authToken;
        assertEquals(TOKEN, authToken.token);
        assertEquals(SECRET, authToken.secret);
        assertEquals(0, authToken.createdAt);
    }

    public void testDeserialize_oauth2TokenMissingCreatedAt() {
        final AuthTokenWrapper authTokenWrapper = gson.fromJson(
                JSON_OAUTH2_TOKEN_MISSING_CREATED_AT, AuthTokenWrapper.class);
        assertTrue(authTokenWrapper.authToken instanceof OAuth2Token);
        final OAuth2Token authToken = (OAuth2Token) authTokenWrapper.authToken;
        assertEquals(TOKEN_TYPE, authToken.getTokenType());
        assertEquals(ACCESS_TOKEN, authToken.getAccessToken());
        assertEquals(0, authToken.createdAt);
    }

    public void testDeserialize_guestAuthTokenMissingCreatedAt() {
        final AuthTokenWrapper authTokenWrapper = gson.fromJson(
                JSON_GUEST_AUTH_TOKEN_MISSING_CREATED_AT, AuthTokenWrapper.class);
        assertTrue(authTokenWrapper.authToken instanceof GuestAuthToken);
        final GuestAuthToken authToken = (GuestAuthToken) authTokenWrapper.authToken;
        assertEquals(GUEST_TOKEN, authToken.getGuestToken());
        assertEquals(TOKEN_TYPE, authToken.getTokenType());
        assertEquals(ACCESS_TOKEN, authToken.getAccessToken());
        assertEquals(0, authToken.createdAt);
    }

    private static class AuthTokenWrapper {
        final AuthToken authToken;

        public AuthTokenWrapper(AuthToken authToken) {
            this.authToken = authToken;
        }
    }

    private static class TestAuthToken extends AuthToken {

        @Override
        public Map<String, String> getAuthHeaders(TwitterAuthConfig authConfig, String method,
                String url, Map<String, String> postParams) {
            return null;
        }

        @Override
        public boolean isExpired() {
            return false;
        }
    }
}

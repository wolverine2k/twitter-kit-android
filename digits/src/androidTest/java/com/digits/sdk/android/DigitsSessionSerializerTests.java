package com.digits.sdk.android;

import com.twitter.sdk.android.core.AuthTokenUtils;

public class DigitsSessionSerializerTests extends DigitsAndroidTestCase {
    private static final long CREATED_AT = 1414450780L;
    public static final String SESSION_JSON = "{\"auth_token\":{\"auth_type\":\"oauth1a\","
            + "\"auth_token\":{"
            + "\"secret\":\"94a08da1fecbb6e8b46990538c7b50b2\","
            + "\"token\":\"5ebe2294ecd0e0f08eab7690d2a6ee69\","
            + "\"createdAt\":" + CREATED_AT + "}},"
            + "\"id\":-1}";
    public static final String FULL_SESSION_JSON = "{\"auth_token\":{\"auth_type\":\"oauth1a\","
            + "\"auth_token\":{"
            + "\"secret\":\"94a08da1fecbb6e8b46990538c7b50b2\","
            + "\"token\":\"5ebe2294ecd0e0f08eab7690d2a6ee69\","
            + "\"createdAt\":" + CREATED_AT + "}},"
            + "\"id\":1}";
    public static final String SESSION_JSON_NULL_USERNAME = "{\"auth_token\":{"
            + "\"auth_type\":\"oauth1a\","
            + "\"auth_token\":{"
            + "\"secret\":\"94a08da1fecbb6e8b46990538c7b50b2\","
            + "\"token\":\"5ebe2294ecd0e0f08eab7690d2a6ee69\","
            + "\"createdAt\":" + CREATED_AT + "}},"
            + "\"id\":1}";
    private static final String SESSION_JSON_INVALID_OAUTH_TYPE =
            "{\"auth_token\":{\"auth_type\":\"INVALID\",\"auth_token\":{"
                    + "\"secret\":\"94a08da1fecbb6e8b46990538c7b50b2\","
                    + "\"token\":\"5ebe2294ecd0e0f08eab7690d2a6ee69\","
                    + "\"createdAt\":" + CREATED_AT + "}},"
                    + "\"id\":1}";

    private DigitsSession.Serializer serializer;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        serializer = new DigitsSession.Serializer();
    }

    // TODO: Stop testing twittercore internal oauth components, this is causing unneeded coupling

    public void testDeserialize_sessionWithAuthToken() throws Exception {
        final DigitsSession session = serializer.deserialize(SESSION_JSON);
        final DigitsSession newSession = new DigitsSession(
                AuthTokenUtils.createTwitterAuthToken(TOKEN, SECRET, CREATED_AT),
                DigitsSession.UNKNOWN_USER_ID);
        assertEquals(session, newSession);
    }

    public void testDeserialize_session() throws Exception {
        final DigitsSession session = serializer.deserialize(FULL_SESSION_JSON);
        assertEquals(new DigitsSession(AuthTokenUtils.createTwitterAuthToken(TOKEN, SECRET,
                CREATED_AT), 1), session);
    }

    public void testDeserialize_sessionWithNullUserName() throws Exception {
        final DigitsSession session = serializer.deserialize(SESSION_JSON_NULL_USERNAME);
        assertEquals(new DigitsSession(AuthTokenUtils.createTwitterAuthToken(TOKEN, SECRET,
                CREATED_AT), 1), session);
    }

    public void testDeserialize_nullSerializedSession() throws Exception {
        final DigitsSession session = serializer.deserialize(null);
        assertNull(session);
    }

    public void testDeserialize_invalidOAuthType() {
        final DigitsSession session = serializer.deserialize(SESSION_JSON_INVALID_OAUTH_TYPE);
        assertNull(session);
    }

    public void testSerialize_sessionWithAuthToken() throws Exception {
        final DigitsSession session = new DigitsSession(
                AuthTokenUtils.createTwitterAuthToken(TOKEN, SECRET, CREATED_AT),
                DigitsSession.UNKNOWN_USER_ID);
        assertEquals(SESSION_JSON, serializer.serialize(session));
    }

    public void testSerialize_session() throws Exception {
        final DigitsSession session = new DigitsSession(AuthTokenUtils.createTwitterAuthToken(TOKEN,
                SECRET, CREATED_AT), 1);
        assertEquals(FULL_SESSION_JSON, serializer.serialize(session));
    }

    public void testSerialize_sessionWithNullUserName() throws Exception {
        final DigitsSession session = new DigitsSession(AuthTokenUtils.createTwitterAuthToken(TOKEN,
                SECRET, CREATED_AT), 1);
        assertEquals(SESSION_JSON_NULL_USERNAME, serializer.serialize(session));
    }
}

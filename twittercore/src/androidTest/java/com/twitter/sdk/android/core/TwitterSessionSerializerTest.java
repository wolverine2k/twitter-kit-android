package com.twitter.sdk.android.core;

public class TwitterSessionSerializerTest extends TwitterAndroidTestCase {
    // static unix timestamp so that tests are repeatable and more easily debugged
    private static final long CREATED_AT = 1414450780L;
    public static final String SESSION_JSON = "{\"user_name\":\"\","
            + "\"auth_token\":{"
            + "\"secret\":\"94a08da1fecbb6e8b46990538c7b50b2\","
            + "\"token\":\"5ebe2294ecd0e0f08eab7690d2a6ee69\","
            + "\"createdAt\":" + CREATED_AT + "},"
            + "\"id\":-1}";
    public static final String FULL_SESSION_JSON = "{\"user_name\":\"rallat\","
            + "\"auth_token\":{"
            + "\"secret\":\"94a08da1fecbb6e8b46990538c7b50b2\","
            + "\"token\":\"5ebe2294ecd0e0f08eab7690d2a6ee69\","
            + "\"createdAt\":" + CREATED_AT + "},"
            + "\"id\":1}";
    public static final String SESSION_JSON_NULL_USERNAME = "{\"auth_token\":{"
            + "\"secret\":\"94a08da1fecbb6e8b46990538c7b50b2\","
            + "\"token\":\"5ebe2294ecd0e0f08eab7690d2a6ee69\","
            + "\"createdAt\":" + CREATED_AT + "},"
            + "\"id\":1}";

    private TwitterSession.Serializer serializer;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        serializer = new TwitterSession.Serializer();
    }

    public void testDeserialize_sessionWithAuthToken() throws Exception {
        final TwitterSession session = serializer.deserialize(SESSION_JSON);
        final TwitterSession newSession = new TwitterSession(
                new TwitterAuthToken(TOKEN, SECRET, CREATED_AT), TwitterSession.UNKNOWN_USER_ID,
                TwitterSession.UNKNOWN_USER_NAME);
        assertEquals(session, newSession);
    }

    public void testDeserialize_session() throws Exception {
        final TwitterSession session = serializer.deserialize(FULL_SESSION_JSON);
        assertEquals(new TwitterSession(new TwitterAuthToken(TOKEN, SECRET, CREATED_AT),
                1, "rallat"), session);
    }

    public void testDeserialize_sessionWithNullUserName() throws Exception {
        final TwitterSession session = serializer.deserialize(SESSION_JSON_NULL_USERNAME);
        assertEquals(new TwitterSession(new TwitterAuthToken(TOKEN, SECRET, CREATED_AT),
                1, null), session);
    }

    public void testDeserialize_nullSerializedSession() throws Exception {
        final TwitterSession session = serializer.deserialize(null);
        assertNull(session);
    }

    public void testSerialize_sessionWithAuthToken() throws Exception {
        final TwitterSession session = new TwitterSession(
                new TwitterAuthToken(TOKEN, SECRET, CREATED_AT), TwitterSession.UNKNOWN_USER_ID,
                TwitterSession.UNKNOWN_USER_NAME);
        assertEquals(SESSION_JSON, serializer.serialize(session));
    }

    public void testSerialize_session() throws Exception {
        final TwitterSession session = new TwitterSession(
                new TwitterAuthToken(TOKEN, SECRET, CREATED_AT), 1, "rallat");
        assertEquals(FULL_SESSION_JSON, serializer.serialize(session));
    }

    public void testSerialize_sessionWithNullUserName() throws Exception {
        final TwitterSession session = new TwitterSession(
                new TwitterAuthToken(TOKEN, SECRET, CREATED_AT), 1, null);
        assertEquals(SESSION_JSON_NULL_USERNAME, serializer.serialize(session));
    }
}

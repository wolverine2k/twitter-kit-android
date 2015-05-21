package com.twitter.sdk.android.core;

public class TwitterSessionTest extends TwitterAndroidTestCase {

    public void testConstructor_noAuthToken() throws Exception {
        try {
            final TwitterSession session = new TwitterSession(null, TwitterSession.UNKNOWN_USER_ID,
                    TwitterSession.UNKNOWN_USER_NAME);
            fail();
        } catch (IllegalArgumentException ie) {
            assertEquals("AuthToken must not be null.", ie.getMessage());
        }
    }

    public void testEquals_sameObjects() throws Exception {
        final TwitterSession session = new TwitterSession(
                new TwitterAuthToken(TOKEN, SECRET), USER_ID, USER);
        final TwitterSession newSession = new TwitterSession(
                new TwitterAuthToken(TOKEN, SECRET), USER_ID, USER);
        assertEquals(session.hashCode(), newSession.hashCode());
        assertEquals(session, newSession);
    }

    public void testEquals_sameObjectsWithNullUserName() throws Exception {
        final TwitterSession session = new TwitterSession(
                new TwitterAuthToken(TOKEN, SECRET), USER_ID, null);
        final TwitterSession newSession = new TwitterSession(
                new TwitterAuthToken(TOKEN, SECRET), USER_ID, null);
        assertEquals(session.hashCode(), newSession.hashCode());
        assertEquals(session, newSession);
    }

    public void testEquals_diffObjects() throws Exception {
        final TwitterSession session = new TwitterSession(
                new TwitterAuthToken(TOKEN, SECRET), USER_ID, USER);
        final long differentUserId = USER_ID + 1;
        final TwitterSession newSession = new TwitterSession(
                new TwitterAuthToken(TOKEN, SECRET), differentUserId, USER);
        assertNotSame(session, newSession);
    }

    public void testEquals_diffObjectsWithNullUserName() throws Exception {
        final TwitterSession session = new TwitterSession(
                new TwitterAuthToken(TOKEN, SECRET), USER_ID, null);
        final long differentUserId = USER_ID + 1;
        final TwitterSession newSession = new TwitterSession(
                new TwitterAuthToken(TOKEN, SECRET), differentUserId, null);
        assertNotSame(session, newSession);
    }

}


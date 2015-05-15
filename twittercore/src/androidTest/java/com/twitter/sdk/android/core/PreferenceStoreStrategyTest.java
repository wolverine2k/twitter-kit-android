package com.twitter.sdk.android.core;

import io.fabric.sdk.android.services.persistence.PreferenceStore;
import io.fabric.sdk.android.services.persistence.PreferenceStoreImpl;
import io.fabric.sdk.android.services.persistence.PreferenceStoreStrategy;

public class PreferenceStoreStrategyTest extends TwitterAndroidTestCase {
    private static final String TOKEN = "5ebe2294ecd0e0f08eab7690d2a6ee69";
    private static final String SECRET = "94a08da1fecbb6e8b46990538c7b50b2";

    private PreferenceStore preferenceStore;
    private PreferenceStoreStrategy<TwitterSession> preferenceStrategy;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        preferenceStore = new PreferenceStoreImpl(getContext(), "testSession");
        preferenceStrategy = new PreferenceStoreStrategy<TwitterSession>(preferenceStore,
                new TwitterSession.Serializer(), "testSession");
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        preferenceStrategy.clear();
    }

    public void testRestore_emptyStore() throws Exception {
        assertEquals(null, preferenceStrategy.restore());
    }

    public void testSaveAndRestore_nullSession() throws Exception {
        preferenceStrategy.save(null);
        final TwitterSession restoredSession = preferenceStrategy.restore();
        assertEquals(null, restoredSession);
    }

    public void testSaveAndRestore_session() throws Exception {
        final TwitterSession session = new TwitterSession(new TwitterAuthToken
                (TOKEN, SECRET), TwitterSession.UNKNOWN_USER_ID, TwitterSession.UNKNOWN_USER_NAME);
        preferenceStrategy.save(session);
        final TwitterSession restoredSession = preferenceStrategy.restore();
        assertEquals(session, restoredSession);
    }
}

package com.twitter.sdk.android.core.internal.scribe;

import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.SessionManager;
import com.twitter.sdk.android.core.TwitterCore;

import java.util.List;

import io.fabric.sdk.android.services.common.IdManager;

public class TwitterCoreScribeClientHolder {

    private static final String KIT_NAME = "TwitterCore";

    private static DefaultScribeClient instance;

    /**
     * @return instance can be null
     */
    public static DefaultScribeClient getScribeClient() {
        return instance;
    }

    /**
     * Must be called on background thread
     */
    public static void initialize(TwitterCore kit,
            List<SessionManager<? extends Session>> sessionManagers, IdManager idManager) {
        instance = new DefaultScribeClient(kit, KIT_NAME, sessionManagers, idManager);
    }
}

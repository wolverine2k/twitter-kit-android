package com.twitter.sdk.android.core.internal.scribe;

import android.content.Context;

import io.fabric.sdk.android.services.common.IdManager;

import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.SessionManager;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.net.ssl.SSLSocketFactory;

/**
 * Test class to allow mocking of ScribeFilesSender.
 */
public class TestScribeFilesSender extends ScribeFilesSender {

    public TestScribeFilesSender(Context context, ScribeConfig scribeConfig, long ownerId,
            TwitterAuthConfig authConfig, List<SessionManager<? extends Session>> sessionManagers,
            SSLSocketFactory sslSocketFactory, ExecutorService executorService,
            IdManager idManager) {
        super(context, scribeConfig, ownerId, authConfig, sessionManagers, sslSocketFactory,
                executorService, idManager);
    }
}

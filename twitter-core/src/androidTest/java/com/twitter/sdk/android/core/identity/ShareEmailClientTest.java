package com.twitter.sdk.android.core.identity;

import io.fabric.sdk.android.FabricTestUtils;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.TwitterAndroidTestCase;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.User;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ShareEmailClientTest extends TwitterAndroidTestCase {

    private ShareEmailClient.EmailService mockEmailService;
    private ShareEmailClient shareEmailClient;

    public void setUp() throws Exception {
        super.setUp();

        FabricTestUtils.resetFabric();
        FabricTestUtils.with(getContext(),
                new TwitterCore(new TwitterAuthConfig(CONSUMER_KEY, CONSUMER_SECRET)));

        mockEmailService = mock(ShareEmailClient.EmailService.class);
        shareEmailClient = new ShareEmailClient(mock(TwitterSession.class)) {

            @Override
            protected <T> T getService(Class<T> cls) {
                if (cls.equals(EmailService.class)) {
                    return (T) mockEmailService;
                } else {
                    return super.getService(cls);
                }
            }
        };
    }

    @Override
    protected void tearDown() throws Exception {
        FabricTestUtils.resetFabric();
        super.tearDown();
    }

    public void testGetEmail() throws Exception {
        final Callback<User> mockCallback = mock(Callback.class);
        shareEmailClient.getEmail(mockCallback);

        verify(mockEmailService).verifyCredentials(eq(true), eq(true), eq(mockCallback));
    }
}

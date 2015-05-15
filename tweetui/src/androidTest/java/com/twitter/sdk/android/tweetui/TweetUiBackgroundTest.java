package com.twitter.sdk.android.tweetui;

import com.twitter.sdk.android.core.TwitterAndroidTestCase;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.FabricTestUtils;
import io.fabric.sdk.android.services.concurrency.PriorityThreadPoolExecutor;

import static org.mockito.Mockito.*;

/**
 * Call Fabric.with instead of FabricTestUtils.with to detect background thread issues.
 */
public class TweetUiBackgroundTest extends TwitterAndroidTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        // Set a mock thread pool executor so we can run these tests knowing that doInBackground
        // has not been run.
        Fabric.with(new Fabric.Builder(getContext())
                .threadPoolExecutor(mock(PriorityThreadPoolExecutor.class))
                .kits(
                        new TwitterCore(new TwitterAuthConfig(CONSUMER_KEY, CONSUMER_SECRET)),
                        new TweetUi())
                .build());
    }

    @Override
    protected void tearDown() throws Exception {
        FabricTestUtils.resetFabric();
        super.tearDown();
    }

    public void testRenderTweet_beforeInBackground() {
        try {
            final TweetView tv = new TweetView(getContext(), TestFixtures.TEST_TWEET);
        } catch (IllegalArgumentException e) {
            fail(e.getMessage());
        } finally {
            FabricTestUtils.resetFabric();
        }
    }

    public void testGetTweetRepository() {
        assertNotNull(TweetUi.getInstance().getTweetRepository());
    }

    public void testGetAuthRequestQueue() {
        assertNotNull(TweetUi.getInstance().getAuthRequestQueue());
    }
}

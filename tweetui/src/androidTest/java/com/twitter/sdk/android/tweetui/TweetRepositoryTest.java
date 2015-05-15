package com.twitter.sdk.android.tweetui;

import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.models.Tweet;

import static org.mockito.Mockito.*;

public class TweetRepositoryTest extends TweetUiTestCase {
    private TwitterCore twitterCoreKit;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        twitterCoreKit = TwitterCore.getInstance();
    }

    @Override
    protected void tearDown() throws Exception {
        twitterCoreKit.logOut();
        scrubClass(TweetRepositoryTest.class);  // gross
        super.tearDown();
    }

    public void testDefaultApiCallbackRunnableSuccess_updateCache() {
        final TestTweetRepository mockRepo = mock(TestTweetRepository.class);

        final TestTweetRepository.TweetApiCallback callback
                = mockRepo.new TweetApiCallback(null);
        callback.success(null, null);

        verify(mockRepo, times(1)).updateCache(any(Tweet.class));
    }
}

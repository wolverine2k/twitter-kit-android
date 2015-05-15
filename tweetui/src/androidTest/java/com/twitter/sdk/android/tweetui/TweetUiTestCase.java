package com.twitter.sdk.android.tweetui;

import android.os.Handler;

import io.fabric.sdk.android.FabricTestUtils;

import com.twitter.sdk.android.core.AppSession;
import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.TwitterAndroidTestCase;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterTestUtils;
import com.twitter.sdk.android.core.internal.scribe.DefaultScribeClient;
import com.twitter.sdk.android.core.services.StatusesService;

import com.squareup.picasso.Picasso;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

import static org.mockito.Mockito.mock;

public class TweetUiTestCase extends TwitterAndroidTestCase {

    protected TweetUi tweetUi;

    // mocks
    protected AuthRequestQueue queue;
    protected Picasso picasso;
    private StatusesService statusesService;
    protected DefaultScribeClient scribeClient;
    protected Handler mainHandler;
    protected ExecutorService executorService;
    private TwitterApiClient apiClient;
    private ConcurrentHashMap<Session, TwitterApiClient> clients;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        createMocks();

        FabricTestUtils.resetFabric();
        final TwitterCore twitterCore = TwitterTestUtils.createTwitter(
                new TwitterAuthConfig("", ""), clients);
        FabricTestUtils.with(getContext(), twitterCore, new TweetUi());

        tweetUi = TweetUi.getInstance();
        final TweetRepository tweetRepository
                = new TweetRepository(tweetUi, executorService, mainHandler, queue);
        tweetUi.setTweetRepository(tweetRepository);
        tweetUi.setImageLoader(picasso);
    }

    @Override
    protected void tearDown() throws Exception {
        FabricTestUtils.resetFabric();
        scrubClass(TweetUiTestCase.class);
        super.tearDown();
    }

    public void testClearSession() {
        final AppSession session = mock(AppSession.class);
        TwitterCore.getInstance().getAppSessionManager().setActiveSession(session);
        TweetUi.getInstance().clearAppSession(session.getId());
        assertEquals(null, TwitterCore.getInstance().getAppSessionManager().getActiveSession());
    }

    // Mocks

    private void createMocks() {
        mainHandler = mock(Handler.class);
        queue = mock(TweetRepositoryTest.TestAuthRequestQueue.class);
        picasso = mock(Picasso.class);
        MockUtils.mockPicasso(picasso);

        statusesService = mock(StatusesService.class);

        scribeClient = mock(DefaultScribeClient.class);

        executorService = mock(ExecutorService.class);
        MockUtils.mockExecutorService(executorService);

        apiClient = mock(TwitterApiClient.class);
        MockUtils.mockStatusesServiceClient(apiClient, statusesService);

        clients = mock(ConcurrentHashMap.class);
        MockUtils.mockClients(clients, apiClient);
    }

    // Make AuthRequestQueue public so we can mock it using Mockito
    public static class TestAuthRequestQueue extends AuthRequestQueue {
        public TestAuthRequestQueue() {
            super(null, null);
        }
    }
}

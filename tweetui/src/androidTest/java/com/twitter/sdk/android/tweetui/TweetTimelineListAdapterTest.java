package com.twitter.sdk.android.tweetui;

import android.view.View;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.internal.TimelineDelegate;

import java.util.LinkedList;

public class TweetTimelineListAdapterTest extends TweetUiTestCase {
    private TweetTimelineListAdapter listAdapter;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    /**
     * Requires TweetUi to be setup by the test class. Without TweetUi, TweetView construction
     * returns before calling setTweet to support IDE edit mode, so getTweetId would always be -1.
     */
    public void testGetView_getsCompactTweetView() {
        final Timeline<Tweet> fakeTimeline = new FakeTweetTimeline(10);
        final TimelineDelegate<Tweet> fakeDelegate = new TimelineDelegate<Tweet>(fakeTimeline);
        listAdapter = new TweetTimelineListAdapter(getContext(), fakeDelegate);

        final View view = listAdapter.getView(0, null, null);
        // assert that
        // - default implementation of getView returns a CompactTweetView
        // - sanity check that CompactTweetView tweet id matches the Tweet's id
        assertEquals(CompactTweetView.class, view.getClass());
        final BaseTweetView tv = (BaseTweetView) view;
        assertEquals(listAdapter.getItemId(0), tv.getTweetId());
    }

    public static class FakeTweetTimeline implements Timeline<Tweet> {
        private long numItems;

        /**
         * Constructs a FakeTweetTimeline
         * @param numItems the number of Tweets to return per call to next/previous
         */
        public FakeTweetTimeline(long numItems) {
            this.numItems = numItems;
        }

        @Override
        public void next(Long sinceId, Callback<TimelineResult<Tweet>> cb) {
            final LinkedList<Tweet> tweets = TestFixtures.getTweetLinkedList(numItems);
            final TimelineCursor timelineCursor = new TimelineCursor(tweets);
            final TimelineResult<Tweet> timelineResult
                    = new TimelineResult<>(timelineCursor, tweets);
            cb.success(timelineResult, null);
        }

        @Override
        public void previous(Long maxId, Callback<TimelineResult<Tweet>> cb) {
            final LinkedList<Tweet> tweets = TestFixtures.getTweetLinkedList(numItems);
            final TimelineCursor timelineCursor = new TimelineCursor(tweets);
            final TimelineResult<Tweet> timelineResult
                    = new TimelineResult<>(timelineCursor, tweets);
            cb.success(timelineResult, null);
        }
    }
}

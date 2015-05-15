package com.twitter.sdk.android.tweetui;

import android.content.Context;

import com.twitter.cobalt.metrics.MetricsManager;
import com.twitter.sdk.android.core.internal.scribe.EventNamespace;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.internal.TweetViewMetric;

public class CompactTweetViewTest extends BaseTweetViewTest {
    private static final String REQUIRED_TFW_SCRIBE_COMPONENT = "compact";
    private static final String REQUIRED_SDK_SCRIBE_SECTION = "compact";

    @Override
    CompactTweetView createView(Context context, Tweet tweet) {
        return new CompactTweetView(context, tweet);
    }

    @Override
    CompactTweetView createView(Context context, Tweet tweet, int styleResId) {
        return new CompactTweetView(context, tweet, styleResId);
    }

    @Override
    CompactTweetView createViewInEditMode(Context context, Tweet tweet) {
        return new CompactTweetView(context, tweet) {
            @Override
            public boolean isInEditMode() {
                return true;
            }
        };
    }

    @Override
    CompactTweetView createViewWithMocks(Context context, Tweet tweet) {
        return new CompactTweetView(context, tweet) {
            @Override
            TweetViewMetric getTweetViewMetric(MetricsManager metricsManager) {
                return mockTweetViewMetric;
            }
        };
    }

    @Override
    BaseTweetView createViewWithMocks(Context context, Tweet tweet, int styleResId,
            BaseTweetView.DependencyProvider dependencyProvider) {
        return new CompactTweetView(context, tweet, styleResId, dependencyProvider);
    }

    // Layout

    public void testLayout() {
        final CompactTweetView compactView = new CompactTweetView(context, TestFixtures.TEST_TWEET);
        assertEquals(R.layout.tw__tweet_compact, compactView.getLayout());
    }

    // Scribing

    @Override
    void assertSdkScribeSection(EventNamespace ns) {
        assertEquals(REQUIRED_SDK_SCRIBE_SECTION, ns.section);
    }

    @Override
    void assertTfwScribeComponent(EventNamespace ns) {
        assertEquals(REQUIRED_TFW_SCRIBE_COMPONENT, ns.component);
    }
}

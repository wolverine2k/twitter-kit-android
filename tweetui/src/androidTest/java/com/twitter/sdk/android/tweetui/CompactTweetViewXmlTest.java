package com.twitter.sdk.android.tweetui;

public class CompactTweetViewXmlTest extends BaseTweetViewXmlTest {

    @Override
    CompactTweetView getView() {
        return (CompactTweetView) getInflatedLayout().findViewById(R.id.compact_tweet_view);
    }

    @Override
    CompactTweetView getViewDark() {
        return (CompactTweetView) getInflatedLayout().findViewById(R.id.compact_tweet_view_dark);
    }

    // Layout

    public void testLayout() {
        final CompactTweetView view = getView();
        assertNotNull(view);
        assertEquals(R.layout.tw__tweet_compact, view.getLayout());
    }
}

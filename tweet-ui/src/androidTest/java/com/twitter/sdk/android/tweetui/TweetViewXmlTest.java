package com.twitter.sdk.android.tweetui;

public class TweetViewXmlTest extends BaseTweetViewXmlTest {
    @Override
    TweetView getView() {
        return (TweetView) getInflatedLayout().findViewById(R.id.tweet_view);
    }

    @Override
    TweetView getViewDark() {
        return (TweetView) getInflatedLayout().findViewById(R.id.tweet_view_dark);
    }

    // Layout

    public void testLayout() {
        final TweetView view = getView();
        assertNotNull(view);
        assertEquals(R.layout.tw__tweet, view.getLayout());
    }

    // Styling

    public void testActionColorDefault() {
        final TweetView view = getView();
        final int color = getResources().getColor(R.color.tw__tweet_action_color);
        assertEquals(color, view.shareButton.getCurrentTextColor());
    }

    public void testActionColorDark() {
        final TweetView view = getViewDark();
        final int color = getResources().getColor(R.color.tw__tweet_action_color);
        assertEquals(color, view.shareButton.getCurrentTextColor());
    }

    // TODO: tests for verified icon, photo, other TweetView specific components
}

package com.twitter.sdk.android.tweetui;

import com.squareup.picasso.Picasso;

public class TestDependencyProvider extends BaseTweetView.DependencyProvider {
    @Override
    public TweetUi getTweetUi() {
        return super.getTweetUi();
    }

    @Override
    public Picasso getImageLoader() {
        return super.getImageLoader();
    }
}

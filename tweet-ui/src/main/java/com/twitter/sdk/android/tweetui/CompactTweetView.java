package com.twitter.sdk.android.tweetui;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.core.models.MediaEntity;
import com.twitter.sdk.android.core.models.Tweet;

public class CompactTweetView extends BaseTweetView {

    private static final String VIEW_TYPE_NAME = "compact";

    public CompactTweetView(Context context, Tweet tweet) {
        super(context, tweet);
    }

    public CompactTweetView(Context context, Tweet tweet, int styleResId) {
        super(context, tweet, styleResId);
    }

    CompactTweetView(Context context, Tweet tweet, int styleResId,
            DependencyProvider dependencyProvider) {
        super(context, tweet, styleResId, dependencyProvider);
    }

    public CompactTweetView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public CompactTweetView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected int getLayout() {
        return R.layout.tw__tweet_compact;
    }

    @Override
    void render() {
        super.render();
        // Redraw screen name on recycle
        screenNameView.requestLayout();
    }

    @Override
    protected void setTweetPhoto(MediaEntity photoEntity) {
        final Picasso imageLoader = dependencyProvider.getImageLoader();

        if (imageLoader == null) return;

        imageLoader.load(photoEntity.mediaUrlHttps)
                .placeholder(mediaBg)
                .fit()
                .centerCrop()
                .into(mediaPhotoView, new Callback() {
                    @Override
                    public void onSuccess() { /* intentionally blank */ }

                    @Override
                    public void onError() {
                        setErrorImage();
                    }
                });
        mediaPhotoView.setVisibility(ImageView.VISIBLE);
    }

    @Override
    String getViewTypeName() {
        return VIEW_TYPE_NAME;
    }
}

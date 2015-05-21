package com.twitter.sdk.android.tweetui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.View;
import android.widget.Button;

import com.twitter.sdk.android.core.models.Tweet;

class OnShareButtonClickListener implements Button.OnClickListener {
    final Tweet tweet;

    OnShareButtonClickListener(Tweet tweet) {
        super();
        this.tweet = tweet;
    }

    @Override
    public void onClick(View v) {
        onClick(v.getContext(), v.getResources());
    }

    void onClick(Context context, Resources resources) {
        if (tweet == null || tweet.user == null) return;

        final String shareSubject = getShareSubject(resources);
        final String shareContent = getShareContent(resources);
        final Intent shareIntent = getShareIntent(shareSubject, shareContent);
        final String shareText = resources.getString(R.string.tw__share_tweet);
        final Intent chooser = Intent.createChooser(shareIntent, shareText);
        launchShareIntent(chooser, context);
    }

    String getShareContent(Resources resources) {
        return resources.getString(R.string.tw__share_content_format,
                tweet.user.screenName, tweet.id);
    }

    String getShareSubject(Resources resources) {
        return resources.getString(R.string.tw__share_subject_format, tweet.user.name,
                tweet.user.screenName);
    }

    void launchShareIntent(Intent chooser, Context context) {
        context.startActivity(chooser);
    }

    Intent getShareIntent(String subject, String content) {
        final Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, content);
        intent.setType("text/plain");
        return intent;
    }
}

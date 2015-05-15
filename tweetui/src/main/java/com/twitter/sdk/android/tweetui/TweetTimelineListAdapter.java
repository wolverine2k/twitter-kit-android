package com.twitter.sdk.android.tweetui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.internal.TimelineDelegate;

/**
 * TweetTimelineListAdapter is a ListAdapter which can provide Timeline Tweets to ListViews.
 */
public class TweetTimelineListAdapter extends TimelineListAdapter<Tweet> {

    /**
     * Constructs a TweetTimelineListAdapter for the given Tweet Timeline.
     * @param context the context for row views.
     * @param timeline a Timeline<Tweet> providing access to Tweet data items.
     * @throws java.lang.IllegalArgumentException if timeline is null
     */
    public TweetTimelineListAdapter(Context context, Timeline<Tweet> timeline) {
        super(context, timeline);
    }

      /* for testing */
    TweetTimelineListAdapter(Context context, TimelineDelegate<Tweet> delegate) {
        super(context, delegate);
    }

    /**
     * Returns a CompactTweetView by default. Override to provide another view for the Tweet item.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        final Tweet tweet = getItem(position);
        if (rowView == null) {
            rowView = new CompactTweetView(context, tweet);
        } else {
            ((BaseTweetView) rowView).setTweet(tweet);
        }
        return rowView;
    }
}

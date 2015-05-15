package com.twitter.sdk.android.tweetui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter to provide a collection of TweetViews to AdapterViews (such as ListView).
 */
public class TweetViewAdapter<T extends BaseTweetView> extends BaseAdapter {
    protected final Context context;
    protected List<Tweet> tweets;

    /**
     * Constructs a TweetViewAdapter with an empty collection of Tweets.
     * @param context the context of the views
     */
    public TweetViewAdapter(Context context) {
        this.context = context;
        tweets = new ArrayList<>();
    }

    /**
     * Constructs a TweetViewAdapter for the given collection of Tweets.
     * @param context the context of the views
     * @param tweets collection of Tweets
     */
    public TweetViewAdapter(Context context, List<Tweet> tweets) {
        super();
        this.context = context;
        this.tweets = tweets;
    }

    /**
     * Override to customize the Tweet view that should be used in the list.
     */
    public T getTweetView(Context context, Tweet tweet) {
        return (T) new CompactTweetView(context, tweet);
    }


    @Override
    public int getCount() {
        return (tweets == null) ? 0 : tweets.size();
    }

    @Override
    public Tweet getItem(int position) {
        return tweets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        final Tweet tweet = getItem(position);
        if (rowView == null) {
            rowView = getTweetView(context, tweet);
        } else {
            ((BaseTweetView) rowView).setTweet(tweet);
        }
        return rowView;
    }

    /**
     * Get the collection of Tweets.
     */
    public List<Tweet> getTweets() {
        return tweets;
    }

    /**
     * Set the collection of Tweets.
     */
    public void setTweets(List<Tweet> tweets) {
        if (tweets == null) {
            this.tweets = new ArrayList<>();
        } else {
            this.tweets = tweets;
        }
        notifyDataSetChanged();
    }
}

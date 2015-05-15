package com.twitter.sdk.android.tweetui;

import android.content.Context;

import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

/**
 * Adapter to provide a collection of TweetViews to AdapterViews (such as ListView) which
 * allows Tweets to be specified by id and handles fetching them from the API.
 */
public class TweetViewFetchAdapter<T extends BaseTweetView> extends TweetViewAdapter<T> {

    /**
     * Constructs a TweetViewFetchAdapter.
     * @param context the context of the views
     */
    public TweetViewFetchAdapter(Context context) {
        super(context);
    }

    /**
     * Constructs a TweetViewFetchAdapter with a collection of ids for Tweets to fetch.
     * @param context the context of the views
     * @param tweetIds Tweet ids
     */
    public TweetViewFetchAdapter(Context context, List<Long> tweetIds) {
        this(context, tweetIds, null);
    }

    /**
     * Constructs a TweetViewFetchAdapter with a collection of ids for Tweets to fetch.
     * @param context the context of the views
     * @param tweetIds Tweet ids
     * @param cb callback
     */
    public TweetViewFetchAdapter(Context context, List<Long> tweetIds,
            LoadCallback<List<Tweet>> cb) {
        super(context);
        setTweetIds(tweetIds, cb);
    }

    /**
     * Fetches the requested Tweet ids and sets the collection of Tweets in the adapter.
     * @param tweetIds Tweet ids
     */
    public void setTweetIds(final List<Long> tweetIds) {
        setTweetIds(tweetIds, null);
    }

    /**
     * Fetches the requested Tweet ids and sets the collection of Tweets in the adapter. Calls
     * the given callback's success or failure.
     * @param tweetIds Tweet ids
     * @param cb callback
     */
    public void setTweetIds(final List<Long> tweetIds, final LoadCallback<List<Tweet>> cb) {
        final LoadCallback<List<Tweet>> repoCallback = new LoadCallback<List<Tweet>>() {
            @Override
            public void success(List<Tweet> tweets) {
                setTweets(tweets);
                if (cb != null) {
                    cb.success(tweets);
                }
            }

            @Override
            public void failure(TwitterException error) {
                // purposefully not logging the failure to lookup the Tweets
                if (cb != null) {
                    cb.failure(error);
                }
            }
        };
        TweetUi.getInstance().getTweetRepository().loadTweets(tweetIds, repoCallback);
    }
}

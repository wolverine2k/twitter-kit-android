package com.twitter.sdk.android.tweetui;

import com.twitter.sdk.android.core.TwitterException;

/**
 * Callback for making an API request through a load utility. Logic in
 * callbacks is executed on the main thread.
 * @param <T> type of requested item (e.g. Tweet, List<Tweet>)
 */
public interface LoadCallback<T> {
    void success(T loadedItem);
    void failure(TwitterException exception);
}

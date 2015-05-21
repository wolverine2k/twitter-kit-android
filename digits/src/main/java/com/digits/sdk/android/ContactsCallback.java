package com.digits.sdk.android;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;

/**
 * Callback used to indicate the completion of asynchronous request. Callbacks are executed on
 * the application's main (UI) thread.
 *
 * @param <T> expected response type
 * @see com.twitter.sdk.android.core.Callback
 */
public abstract class ContactsCallback<T> extends Callback<T> {

    /**
     * Called when call completes successfully.
     *
     * @param result the parsed result.
     * @see com.twitter.sdk.android.core.Callback#success
     */
    @Override
    public abstract void success(Result<T> result);

    /**
     * Unsuccessful call due to network failure, non-2XX status code, or unexpected
     * exception.
     *
     * @param exception the exception.
     * @see com.twitter.sdk.android.core.Callback#failure
     */
    @Override
    public abstract void failure(TwitterException exception);
}

package com.twitter.sdk.android.tweetui;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiException;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.internal.TwitterApiConstants;

import io.fabric.sdk.android.Fabric;

/**
 * ApiCallback is a Callback ensuring that on failure, if the guest or app
 * auth token has expired, the session is cleared. Otherwise it calls through
 * to the repository callback.
 * @param <T> success result type (e.g. Tweet, List<Tweet>, etc.)
 * @deprecated Use GuestCallback instead.
 */
@Deprecated
abstract class ApiCallback<T> extends Callback<T> {
    // TODO: Remove class when LoadCallback is removed.
    private static final String TAG = TweetUi.LOGTAG;
    private static final String API_CALL_ERROR = "API call failure.";
    protected final LoadCallback<T> cb;

    ApiCallback(LoadCallback<T> cb) {
        this.cb = cb;
    }

    @Override
    abstract public void success(Result<T> result);

    @Override
    public void failure(TwitterException exception) {
        // Clear session if guest auth token or app auth token expired
        final TwitterApiException apiException = (TwitterApiException) exception;
        final int errorCode = apiException.getErrorCode();
        Fabric.getLogger().e(TAG, API_CALL_ERROR, apiException);

        if (errorCode == TwitterApiConstants.Errors.APP_AUTH_ERROR_CODE ||
                errorCode == TwitterApiConstants.Errors.GUEST_AUTH_ERROR_CODE) {
            TweetUi.getInstance().clearAppSession(TwitterSession.LOGGED_OUT_USER_ID);
        }

        if (cb != null) {
            cb.failure(exception);
        }
    }
}

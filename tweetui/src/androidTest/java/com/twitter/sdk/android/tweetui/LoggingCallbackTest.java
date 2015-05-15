package com.twitter.sdk.android.tweetui;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAndroidTestCase;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;

import io.fabric.sdk.android.Logger;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LoggingCallbackTest extends TwitterAndroidTestCase {

    public void testFailure_callsCb() {
        final Callback<Tweet> developerCallback = mock(Callback.class);
        final LoggingCallback<Tweet> cb
                = new TestLoggingCallback<>(developerCallback, mock(Logger.class));
        cb.failure(mock(TwitterException.class));
        verify(developerCallback).failure(any(TwitterException.class));
    }

    public void testFailure_handlesNullCb() {
        final Logger logger = mock(Logger.class);
        final LoggingCallback<Tweet> cb = new TestLoggingCallback<>(null, logger);
        try {
            cb.failure(mock(TwitterException.class));
            verify(logger).e(any(String.class), any(String.class), any(Throwable.class));
        } catch (NullPointerException e) {
            fail("Should have handled null callback");
        }
    }

    public void testFailure_logsFailure() {
        final Callback<Tweet> developerCallback = mock(Callback.class);
        final Logger logger = mock(Logger.class);
        final LoggingCallback<Tweet> cb = new TestLoggingCallback<>(developerCallback, logger);
        cb.failure(mock(TwitterException.class));
        verify(logger).e(any(String.class), any(String.class), any(Throwable.class));
    }

    public class TestLoggingCallback<T> extends LoggingCallback<T> {

        public TestLoggingCallback(Callback<T> cb, Logger logger) {
            super(cb, logger);
        }

        @Override
        public void success(Result<T> result) {
            // intentionally blank, implements abstract success method
        }
    }
}

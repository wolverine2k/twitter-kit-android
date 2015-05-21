package com.twitter.sdk.android.core;

/**
 * Represents a Twitter error. Base class for all Twitter related exceptions.
 */
public class TwitterException extends RuntimeException {

    public TwitterException(String detailMessage) {
        super(detailMessage);
    }

    public TwitterException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }
}

package com.twitter.sdk.android.core;

import java.util.List;

import retrofit.client.Header;

/**
 * Represents the rate limit data returned on the headers of a request
 *
 * @see <a href="https://dev.twitter.com/docs/rate-limiting/1.1">Rate Limiting</a>
 */
class TwitterRateLimit  {

    private final static String LIMIT_KEY = "x-rate-limit-limit";
    private final static String REMAINING_KEY = "x-rate-limit-remaining";
    private final static String RESET_KEY = "x-rate-limit-reset";

    private final long timeStamp;
    private int limit;
    private int remainingRequest;
    private long reset;

    TwitterRateLimit(final List<Header> headers) {
        if (headers == null) {
            throw new IllegalArgumentException("headers must not be null");
        }
        timeStamp = System.currentTimeMillis();
        for (Header header : headers) {
            if (LIMIT_KEY.equals(header.getName())) {
               limit = Integer.valueOf(header.getValue());
            } else if (REMAINING_KEY.equals(header.getName())) {
                remainingRequest = Integer.valueOf(header.getValue());
            } else if (RESET_KEY.equals(header.getName())) {
                reset = Long.valueOf(header.getValue());
            }
        }
    }

    /**
     * Returns the rate limit ceiling for that given request
     */
    public int getLimit() {
        return limit;
    }

    /**
     * Returns the number of requests left for the 15 minute window
     */
    public int getRemaining() {
        return remainingRequest;
    }

    /**
     * Returns epoch time that rate limit reset will happen.
     */
    public long getReset() {
        return reset;
    }

    /**
     * Returns epoch time that request was made.
     */
    public long getRequestedTime() {
        return timeStamp;
    }

    /**
     * Returns epoch time remaining in rate limit window.
     */
    public long getRemainingTime() {
        if (reset > timeStamp) {
            return 0;
        } else {
            return reset - timeStamp;
        }
    }
}

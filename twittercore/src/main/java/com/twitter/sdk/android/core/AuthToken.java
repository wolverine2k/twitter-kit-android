package com.twitter.sdk.android.core;

import com.twitter.sdk.android.core.internal.oauth.AuthHeaders;

/**
 * Base class for authentication tokens.
 */
public abstract class AuthToken implements AuthHeaders {

    /**
     * Unit time or epoch time when the token was created (always in UTC). The
     * time may be 0 if the token is deserialized from data missing the field.
     */
    protected final long createdAt;

    public AuthToken() {
        createdAt = System.currentTimeMillis();
    }

    protected AuthToken(long createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Determines whether a token is known to have expired.
     * @return true if the token is known to have expired, otherwise false to indicate the token
     * may or may not be considered expired by the server.
     */
    public abstract boolean isExpired();
}

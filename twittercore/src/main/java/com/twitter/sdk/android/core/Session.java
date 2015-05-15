package com.twitter.sdk.android.core;

import com.google.gson.annotations.SerializedName;

/**
 * Base class for session associated with {@link com.twitter.sdk.android.core.AuthToken}.
 */
public class Session<T extends AuthToken> {

    @SerializedName("auth_token")
    private final T authToken;

    @SerializedName("id")
    private final long id;

    public Session(T authToken, long id) {
        this.authToken = authToken;
        this.id = id;
    }

    public T getAuthToken() {
        return authToken;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Session session = (Session) o;

        if (id != session.id) return false;
        if (authToken != null ? !authToken.equals(session.authToken) : session.authToken != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = authToken != null ? authToken.hashCode() : 0;
        result = 31 * result + (int) (id ^ (id >>> 32));
        return result;
    }
}

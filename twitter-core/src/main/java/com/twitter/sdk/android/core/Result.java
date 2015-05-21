package com.twitter.sdk.android.core;

import retrofit.client.Response;

/**
 * Encapsulates parsed result for delivery.
 *
 * @param <T> Parsed type, available in the {@link Result#data}
 */
public class Result<T> {
    public final T data;
    public final Response response;

    public Result(T data, Response response) {
        this.data = data;
        this.response = response;
    }
}

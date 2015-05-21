package com.twitter.sdk.android.core;

import com.twitter.sdk.android.core.models.ApiError;

import retrofit.RetrofitError;

public class MockTwitterApiException extends TwitterApiException {
    public MockTwitterApiException(ApiError apiError, TwitterRateLimit rateLimit,
                                   RetrofitError retrofitError) {
        super(apiError, rateLimit, retrofitError);
    }
}

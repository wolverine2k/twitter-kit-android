package com.twitter.sdk.android.core.models;

import com.google.gson.annotations.SerializedName;

/**
 * Represents error returned from the Twitter API. For complete list of error codes, see
 * https://dev.twitter.com/overview/api/response-codes
 */
public class ApiError {

    @SerializedName("message")
    private final String message;

    @SerializedName("code")
    private final int code;

    public ApiError(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}

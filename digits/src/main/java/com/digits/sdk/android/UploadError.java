package com.digits.sdk.android;

import com.google.gson.annotations.SerializedName;

class UploadError {
    @SerializedName("code")
    final int code;

    @SerializedName("message")
    final String message;

    @SerializedName("item")
    final int item;

    UploadError(int code, String message, int item) {
        this.code = code;
        this.message = message;
        this.item = item;
    }
}

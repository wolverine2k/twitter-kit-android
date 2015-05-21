package com.digits.sdk.android;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UploadResponse {
    @SerializedName("errors")
    final List<UploadError> errors;

    UploadResponse(List<UploadError> errors) {
        this.errors = errors;
    }
}

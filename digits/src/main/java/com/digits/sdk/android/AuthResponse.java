package com.digits.sdk.android;


import com.google.gson.annotations.SerializedName;

class AuthResponse {
    @SerializedName("phone_number")
    public String normalizedPhoneNumber;
    @SerializedName("login_verification_request_id")
    public String requestId;
    @SerializedName("login_verification_user_id")
    public long userId;
    @SerializedName("config")
    public AuthConfig authConfig;
}

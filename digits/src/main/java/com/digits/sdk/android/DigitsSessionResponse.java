package com.digits.sdk.android;

import com.google.gson.annotations.SerializedName;

class DigitsSessionResponse {
    @SerializedName("oauth_token")
    public String token;
    @SerializedName("oauth_token_secret")
    public String secret;
    @SerializedName("screen_name")
    public String screenName;
    @SerializedName("user_id")
    public long userId;

    public boolean isEmpty() {
        return token == null && secret == null && screenName == null && userId == 0;
    }
}

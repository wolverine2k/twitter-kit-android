package com.digits.sdk.android;

import com.google.gson.annotations.SerializedName;

public class DeviceRegistrationResponse {
    @SerializedName("device_id")
    public String deviceId;
    @SerializedName("phone_number")
    public String normalizedPhoneNumber;
    @SerializedName("state")
    public String state;

}

package com.digits.sdk.android;

import com.google.gson.annotations.SerializedName;

/**
 * Digits user.
 */
public class DigitsUser {
    /**
     * The integer representation of the unique identifier for this DigitsUser. This number is
     * greater than 53 bits and some programming languages may have difficulty/silent defects in
     * interpreting it. Using a signed 64 bit integer for storing this identifier is safe. Use
     * id_str for fetching the identifier to stay on the safe side. See Twitter IDs, JSON and
     * Snowflake.
     */
    @SerializedName("id")
    public final long id;

    /**
     * The string representation of the unique identifier for this DigitsUser. Implementations
     * should use this rather than the large, possibly un-consumable integer in id.
     */
    @SerializedName("id_str")
    public final String idStr;

    public DigitsUser(long id, String idStr) {
        this.id = id;
        this.idStr = idStr;
    }
}

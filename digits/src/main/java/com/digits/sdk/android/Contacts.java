package com.digits.sdk.android;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * List of matched Digits users.
 */
public class Contacts {

    /**
     * List of matched Digits users.
     */
    @SerializedName("users")
    public ArrayList<DigitsUser> users;

    /**
     * When present, indicates there are more matches to fetch.
     */
    @SerializedName("next_cursor")
    public String nextCursor;
}

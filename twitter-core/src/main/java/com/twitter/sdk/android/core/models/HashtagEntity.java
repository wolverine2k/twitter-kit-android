package com.twitter.sdk.android.core.models;

import com.google.gson.annotations.SerializedName;

/**
 * Represents hashtags which have been parsed out of the Tweet text.
 */
public class HashtagEntity extends Entity {

    /**
     * Name of the hashtag, minus the leading ‘#’ character.
     */
    @SerializedName("text")
    public final String text;

    public HashtagEntity(String text, int start, int end) {
        super(start, end);
        this.text = text;
    }
}

package com.twitter.sdk.android.core.models;

import com.google.gson.annotations.SerializedName;

/**
 * Represents other Twitter users mentioned in the text of the Tweet.
 */
public class MentionEntity extends Entity {

    /**
     * ID of the mentioned user, as an integer.
     */
    @SerializedName("id")
    public final long id;

    /**
     * ID of the mentioned user, as a string.
     */
    @SerializedName("id_str")
    public final String idStr;

    /**
     * Display name of the referenced user.
     */
    @SerializedName("name")
    public final String name;

    /**
     * Screen name of the referenced user.
     */
    @SerializedName("screen_name")
    public final String screenName;

    public MentionEntity(long id, String idStr, String name, String screenName, int start,
            int end) {
        super(start, end);
        this.id = id;
        this.idStr = idStr;
        this.name = name;
        this.screenName = screenName;
    }
}

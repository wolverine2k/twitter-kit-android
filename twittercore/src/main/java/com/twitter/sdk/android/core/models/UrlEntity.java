package com.twitter.sdk.android.core.models;

import com.google.gson.annotations.SerializedName;

/**
 * Represents URLs included in the text of a Tweet or within textual fields of a user object.
 */
public class UrlEntity extends Entity {

    /**
     * Wrapped URL, corresponding to the value embedded directly into the raw Tweet text, and the
     * values for the indices parameter.
     */
    @SerializedName("url")
    public final String url;

    /**
     * Expanded version of display_url
     */
    @SerializedName("expanded_url")
    public final String expandedUrl;

    /**
     * Version of the URL to display to clients.
     */
    @SerializedName("display_url")
    public final String displayUrl;

    public UrlEntity(String url, String expandedUrl, String displayUrl, int start, int end) {
        super(start, end);

        this.url = url;
        this.expandedUrl = expandedUrl;
        this.displayUrl = displayUrl;
    }
}

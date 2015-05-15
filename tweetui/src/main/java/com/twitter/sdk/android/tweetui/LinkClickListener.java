package com.twitter.sdk.android.tweetui;

import com.twitter.sdk.android.core.models.MediaEntity;

/**
 * Tweet interaction listener
 */
interface LinkClickListener {
    /**
     * A URL was clicked
     *
     * @param url The source URL
     */
    void onUrlClicked(String url);

    /**
     * MediaEntity was clicked.
     * @param mediaEntity the media entity
     */
    void onPhotoClicked(MediaEntity mediaEntity);
}

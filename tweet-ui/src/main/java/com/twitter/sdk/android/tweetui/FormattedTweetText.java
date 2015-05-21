package com.twitter.sdk.android.tweetui;

import java.util.ArrayList;
import java.util.List;

/**
 * This class holds values we need to correctly render tweet text. The values returned directly
 * from the REST API are html escaped for & < and > characters as well as not counting emoji
 * characters correctly in the entity indices.
 */
class FormattedTweetText {
    String text;
    final List<FormattedUrlEntity> urlEntities;
    final List<FormattedMediaEntity> mediaEntities;

    FormattedTweetText() {
        urlEntities = new ArrayList<>();
        mediaEntities = new ArrayList<>();
    }
}

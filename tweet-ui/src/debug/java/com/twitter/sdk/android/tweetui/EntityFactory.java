package com.twitter.sdk.android.tweetui;

import com.twitter.sdk.android.core.models.UrlEntity;

class EntityFactory {

    public static UrlEntity newUrlEntity(String text, String url, String displayUrl) {
        final int start = text.length() - url.length();
        final int end = text.length();

        return new UrlEntity(url, "http://" + displayUrl, displayUrl, start, end);
    }
}

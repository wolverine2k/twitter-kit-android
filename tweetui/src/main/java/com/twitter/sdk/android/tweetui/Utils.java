package com.twitter.sdk.android.tweetui;

import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

final class Utils {
    private Utils() {}

    static Long numberOrDefault(String candidate, long defaultLong) {
        try {
            return Long.parseLong(candidate);
        } catch (NumberFormatException e) {
            return defaultLong;
        }
    }

    static String stringOrEmpty(String candidate) {
        return stringOrDefault(candidate, "");
    }

    static String stringOrDefault(String candidate, String defaultString) {
        return (candidate != null) ? candidate : defaultString;
    }

    static CharSequence charSeqOrEmpty(CharSequence candidate) {
        return charSeqOrDefault(candidate, "");
    }

    static CharSequence charSeqOrDefault(CharSequence candidate, CharSequence defaultSequence) {
        return (candidate != null) ? candidate : defaultSequence;
    }

    /**
     * Orders tweets by the tweetIds order. If tweetIds contains duplicates, the result Tweet list
     * will duplicate Tweets accordingly.
     * @param tweetIds ordered list of Tweet ids
     * @param tweets unordered list of Tweet results
     */
    static List<Tweet> orderTweets(List<Long> tweetIds, List<Tweet> tweets) {
        final HashMap<Long, Tweet> idToTweet = new HashMap<>();
        final ArrayList<Tweet> ordered = new ArrayList<>();
        for (Tweet tweet: tweets) {
            idToTweet.put(tweet.id, tweet);
        }
        for (Long id: tweetIds) {
            if (idToTweet.containsKey(id)) {
                ordered.add(idToTweet.get(id));
            }
        }
        return ordered;
    }
}

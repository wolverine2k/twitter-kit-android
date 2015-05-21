package com.twitter.sdk.android.tweetui;

import com.twitter.sdk.android.core.TwitterAndroidTestCase;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.List;

public class UtilsTest extends TwitterAndroidTestCase {

    public void testNumberOrDefault_validNumber() {
        assertEquals(Long.valueOf(123), Utils.numberOrDefault("123", -1L));
    }

    public void testNumberOrDefault_invalidNumber() {
        assertEquals(Long.valueOf(-1L), Utils.numberOrDefault("abc", -1L));
    }

    public void testStringOrEmpty_validString() {
        assertEquals("string", Utils.stringOrEmpty("string"));
    }

    public void testStringOrEmpty_nullString() {
        assertEquals("", Utils.stringOrEmpty(null));
    }

    public void testStringOrDefault_validString() {
        assertEquals("string", Utils.stringOrDefault("string", "default"));
    }

    public void testStringOrDefault_nullString() {
        assertEquals("default", Utils.stringOrDefault(null, "default"));
    }

    public void testCharSeqOrEmpty_validCharSeq() {
        assertEquals("string", Utils.charSeqOrEmpty("string"));
    }

    public void testCharSeqOrEmpty_nullCharSeq() {
        assertEquals("", Utils.charSeqOrEmpty(null));
    }

    public void testCharSeqOrDefault_validCharSeq() {
        assertEquals("string", Utils.charSeqOrDefault("string", "default"));
    }

    public void testCharSeqOrDefault_nullCharSeq() {
        assertEquals("default", Utils.charSeqOrDefault(null, "default"));
    }

    public void testSortTweets() {
        final List<Long> requestedIds = TestFixtures.TWEET_IDS;
        final List<Tweet> tweets = new ArrayList<>();
        tweets.addAll(TestFixtures.UNORDERED_TWEETS);
        final List<Tweet> ordered = Utils.orderTweets(requestedIds, tweets);
        assertEquals(TestFixtures.ORDERED_TWEETS, ordered);
    }

    // Tweet results will match the requested Tweet ids, duplicate requested ids duplicate Tweets.
    public void testSortTweets_duplicateRequestedIds() {
        final List<Long> requestedIds = TestFixtures.DUPLICATE_TWEET_IDS;
        final List<Tweet> tweets = new ArrayList<>();
        tweets.addAll(TestFixtures.UNORDERED_TWEETS);
        final List<Tweet> ordered = Utils.orderTweets(requestedIds, tweets);
        assertEquals(TestFixtures.ORDERED_DUPLICATE_TWEETS, ordered);
    }

    // Tweet results will match the requested Tweet ids, duplicate results ignored.
    public void testSortTweets_duplicateTweets() {
        final List<Long> requestedIds = TestFixtures.TWEET_IDS;
        final List<Tweet> tweets = new ArrayList<>();
        tweets.addAll(TestFixtures.UNORDERED_DUPLICATE_TWEETS);
        final List<Tweet> ordered = Utils.orderTweets(requestedIds, tweets);
        assertEquals(TestFixtures.ORDERED_TWEETS, ordered);
    }

    public void testSortTweets_missingTweets() {
        final List<Long> requestedIds = TestFixtures.TWEET_IDS;
        final List<Tweet> tweets = new ArrayList<>();
        tweets.addAll(TestFixtures.UNORDERED_MISSING_TWEETS);
        final List<Tweet> ordered = Utils.orderTweets(requestedIds, tweets);
        assertEquals(TestFixtures.ORDERED_MISSING_TWEETS, ordered);
    }

    // Tweet result with an extra, unrequested Tweet, not included in the result.
    public void testSortTweets_extraTweetsFirst() {
        final List<Long> requestedIds = TestFixtures.TWEET_IDS;
        final List<Tweet> tweets = new ArrayList<>();
        tweets.addAll(TestFixtures.UNORDERED_TWEETS);
        tweets.add(TestFixtures.TEST_TWEET);

        final List<Tweet> ordered = Utils.orderTweets(requestedIds, tweets);
        assertEquals(TestFixtures.ORDERED_TWEETS, ordered);
    }
}


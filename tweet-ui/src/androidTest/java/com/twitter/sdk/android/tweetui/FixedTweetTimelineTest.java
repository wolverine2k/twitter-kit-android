/*
 * Copyright (C) 2015 Twitter, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.twitter.sdk.android.tweetui;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.List;

public class FixedTweetTimelineTest extends TweetUiTestCase {
    private static final Long ANY_ID = 1234L;
    private List<Tweet> fixedTweets = new ArrayList<>();

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        fixedTweets.add(TestFixtures.TEST_TWEET);
        fixedTweets.add(TestFixtures.TEST_RETWEET);
        fixedTweets.add(TestFixtures.TEST_PHOTO_TWEET);
    }

    public void testConstructor() {
        final FixedTweetTimeline timeline = new FixedTweetTimeline(fixedTweets);
        assertNotNull(timeline.tweets);
        assertEquals(fixedTweets, timeline.tweets);
    }

    public void testConstructor_nullTweets() {
        final FixedTweetTimeline timeline = new FixedTweetTimeline(null);
        assertTrue(timeline.tweets.isEmpty());
    }

    public void testNext_succeedsWithFixedTweets() {
        final FixedTweetTimeline timeline = new FixedTweetTimeline(fixedTweets);
        timeline.next(ANY_ID, new Callback<TimelineResult<Tweet>>() {
            @Override
            public void success(Result<TimelineResult<Tweet>> result) {
                assertEquals(fixedTweets, result.data.items);
                assertEquals((Long) TestFixtures.TEST_PHOTO_TWEET.getId(),
                        result.data.timelineCursor.minPosition);
                assertEquals((Long) TestFixtures.TEST_TWEET.getId(),
                        result.data.timelineCursor.maxPosition);
                assertNull(result.response);
            }
            @Override
            public void failure(TwitterException exception) {
                fail("Expected FixedTweetTimeline next to always succeed.");
            }
        });
    }

    public void testNext_succeedsWithEmptyTweets() {
        final FixedTweetTimeline timeline = new FixedTweetTimeline(fixedTweets);
        timeline.previous(ANY_ID, new Callback<TimelineResult<Tweet>>() {
            @Override
            public void success(Result<TimelineResult<Tweet>> result) {
                assertTrue(result.data.items.isEmpty());
                assertNull(result.data.timelineCursor.maxPosition);
                assertNull(result.data.timelineCursor.minPosition);
                assertNull(result.response);
            }

            @Override
            public void failure(TwitterException exception) {
                fail("Expected FixedTweetTimeline previous to always succeed.");
            }
        });
    }

    /* Builder */

    public void testBuilder() {
        final FixedTweetTimeline timeline = new FixedTweetTimeline.Builder()
                .setTweets(fixedTweets).build();
        assertEquals(fixedTweets, timeline.tweets);
    }

    public void testBuilder_empty() {
        final FixedTweetTimeline timeline = new FixedTweetTimeline.Builder().build();
        assertTrue(timeline.tweets.isEmpty());
    }
}

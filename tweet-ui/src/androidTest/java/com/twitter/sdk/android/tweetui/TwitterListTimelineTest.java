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

import com.twitter.sdk.android.core.TwitterCore;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

public class TwitterListTimelineTest extends TweetUiTestCase {
    private static final Integer REQUIRED_DEFAULT_ITEMS_PER_REQUEST = 30;
    private static final Long TEST_LIST_ID = 128271137L;
    private static final String TEST_SLUG = "cool-accounts";
    private static final Long TEST_OWNER_ID =  623265148L;
    private static final String TEST_OWNER_SCREEN_NAME = "dghubble";
    private static final Integer TEST_ITEMS_PER_REQUEST = 100;
    private static final Long TEST_SINCE_ID = 1000L;
    private static final Long TEST_MAX_ID = 1111L;
    private static final String REQUIRED_IMPRESSION_SECTION = "list";

    public void testConstructor() {
        final TwitterListTimeline timeline = new TwitterListTimeline(TEST_LIST_ID,
                TEST_SLUG, TEST_OWNER_ID, TEST_OWNER_SCREEN_NAME, TEST_ITEMS_PER_REQUEST, true);
        assertEquals(TEST_LIST_ID, timeline.listId);
        assertEquals(TEST_SLUG, timeline.slug);
        assertEquals(TEST_OWNER_ID, timeline.ownerId);
        assertEquals(TEST_OWNER_SCREEN_NAME, timeline.ownerScreenName);
        assertEquals(TEST_ITEMS_PER_REQUEST, timeline.maxItemsPerRequest);
        assertTrue(timeline.includeRetweets);
    }

    // most api arguments should default to Null to allow the backend to determine default behavior
    public void testConstructor_defaults() {
        final TwitterListTimeline timeline = new TwitterListTimeline(TEST_LIST_ID, null,
                null, null, null, null);
        assertEquals(TEST_LIST_ID, timeline.listId);
        assertNull(timeline.slug);
        assertNull(timeline.ownerId);
        assertNull(timeline.ownerScreenName);
        assertNull(timeline.maxItemsPerRequest);
        assertNull(timeline.includeRetweets);
    }

    public void testCreateListTimelineRequest() {
        // build a timeline with test params
        final TwitterListTimeline timeline = new TwitterListTimeline(TEST_LIST_ID,
                TEST_SLUG, TEST_OWNER_ID, TEST_OWNER_SCREEN_NAME, TEST_ITEMS_PER_REQUEST, true);

        timeline.createListTimelineRequest(TEST_SINCE_ID, TEST_MAX_ID);

        // assert twitterListTimeline call is made with the correct arguments
        verify(TwitterCore.getInstance().getApiClient().getListService()).statuses(eq(TEST_LIST_ID),
                eq(TEST_SLUG), eq(TEST_OWNER_SCREEN_NAME), eq(TEST_OWNER_ID), eq(TEST_SINCE_ID),
                eq(TEST_MAX_ID), eq(TEST_ITEMS_PER_REQUEST), eq(true), eq(true));
    }

    public void testGetScribeSection() {
        final TwitterListTimeline timeline = new TwitterListTimeline.Builder().id(TEST_LIST_ID)
                .build();
        assertEquals(REQUIRED_IMPRESSION_SECTION, timeline.getTimelineType());
    }

    /* Builder */

    public void testBuilder_viaLlistId() {
        final TwitterListTimeline timeline = new TwitterListTimeline.Builder()
                .id(TEST_LIST_ID)
                .maxItemsPerRequest(TEST_ITEMS_PER_REQUEST)
                .includeRetweets(true)
                .build();
        assertEquals(TEST_LIST_ID, timeline.listId);
        assertEquals(TEST_ITEMS_PER_REQUEST, timeline.maxItemsPerRequest);
        assertTrue(timeline.includeRetweets);
    }

    public void testBuilder_viaSlugOwnerId() {
        final TwitterListTimeline timeline = new TwitterListTimeline.Builder()
                .slugWithOwnerId(TEST_SLUG, TEST_OWNER_ID)
                .maxItemsPerRequest(TEST_ITEMS_PER_REQUEST)
                .includeRetweets(true)
                .build();
        assertEquals(TEST_SLUG, timeline.slug);
        assertEquals(TEST_OWNER_ID, timeline.ownerId);
        assertEquals(TEST_ITEMS_PER_REQUEST, timeline.maxItemsPerRequest);
        assertTrue(timeline.includeRetweets);
    }

    public void testBuilder_viaSlugOwnerScreenName() {
        final TwitterListTimeline timeline = new TwitterListTimeline.Builder()
                .slugWithOwnerScreenName(TEST_SLUG, TEST_OWNER_SCREEN_NAME)
                .maxItemsPerRequest(TEST_ITEMS_PER_REQUEST)
                .includeRetweets(true)
                .build();
        assertEquals(TEST_SLUG, timeline.slug);
        assertEquals(TEST_OWNER_SCREEN_NAME, timeline.ownerScreenName);
        assertEquals(TEST_ITEMS_PER_REQUEST, timeline.maxItemsPerRequest);
        assertTrue(timeline.includeRetweets);
    }

    // api arguments should default to Null to allow the backend to determine default behavior
    public void testBuilder_defaults() {
        final TwitterListTimeline timeline = new TwitterListTimeline.Builder()
                .id(TEST_LIST_ID)
                .build();
        assertEquals(REQUIRED_DEFAULT_ITEMS_PER_REQUEST, timeline.maxItemsPerRequest);
        assertNull(timeline.includeRetweets);
    }

    public void testBuilder_listId() {
        final TwitterListTimeline timeline = new TwitterListTimeline.Builder()
                .id(TEST_LIST_ID)
                .build();
        assertEquals(TEST_LIST_ID, timeline.listId);
    }

    public void testBuilder_slugWithOwnerId() {
        final TwitterListTimeline timeline = new TwitterListTimeline.Builder()
                .slugWithOwnerId(TEST_SLUG, TEST_OWNER_ID)
                .build();
        assertEquals(TEST_SLUG, timeline.slug);
        assertEquals(TEST_OWNER_ID, timeline.ownerId);
    }

    public void testBuilder_slugWithOwnerScreenName() {
        final TwitterListTimeline timeline = new TwitterListTimeline.Builder()
                .slugWithOwnerScreenName(TEST_SLUG, TEST_OWNER_SCREEN_NAME)
                .build();
        assertEquals(TEST_SLUG, timeline.slug);
        assertEquals(TEST_OWNER_SCREEN_NAME, timeline.ownerScreenName);
    }

    public void testBuilder_maxItemsPerRequest() {
        final TwitterListTimeline timeline = new TwitterListTimeline.Builder()
                .id(TEST_LIST_ID)
                .maxItemsPerRequest(TEST_ITEMS_PER_REQUEST)
                .build();
        assertEquals(TEST_ITEMS_PER_REQUEST, timeline.maxItemsPerRequest);
    }

    public void testBuilder_includeRetweets() {
        TwitterListTimeline timeline = new TwitterListTimeline.Builder().id(TEST_LIST_ID)
                .build();
        assertNull(timeline.includeRetweets);
        timeline = new TwitterListTimeline.Builder().id(TEST_LIST_ID).includeRetweets(true)
            .build();
        assertTrue(timeline.includeRetweets);
        timeline = new TwitterListTimeline.Builder().id(TEST_LIST_ID).includeRetweets(false)
                .build();
        assertFalse(timeline.includeRetweets);
    }

    public void testBuilder_noIdOrSlugOwnerPair() {
        try {
            new TwitterListTimeline.Builder().build();
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalStateException e) {
            assertEquals("must specify either a list id or slug/owner pair", e.getMessage());
        }
    }

    public void testBuilder_bothIdAndSlugOwnerPair() {
        try {
            new TwitterListTimeline.Builder()
                    .id(TEST_LIST_ID)
                    .slugWithOwnerId(TEST_SLUG, TEST_OWNER_ID)
                    .build();
        } catch (IllegalStateException e) {
            assertEquals("must specify either a list id or slug/owner pair", e.getMessage());
        }
    }
}

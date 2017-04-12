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
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.services.params.Geocode;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.mockito.Mockito.*;

public class SearchTimelineTest extends TweetUiTestCase {
    private static final String TEST_QUERY = "twitterflock";
    private static final Geocode TEST_GEOCODE =
            new Geocode(37.7767902, -122.4164055, 1, Geocode.Distance.MILES);
    private static final String TEST_FILTER_QUERY = "from:twitter";
    private static final String TEST_RESULT_TYPE = "popular";
    private static final String TEST_LANG = "en";
    private static final String TEST_UNTIL_DATE = "2012-08-20";
    private static final Date TEST_UNTIL =
            new GregorianCalendar(2012, Calendar.AUGUST, 20).getTime();
    private static final Integer REQUIRED_DEFAULT_ITEMS_PER_REQUEST = 30;
    private static final Integer TEST_ITEMS_PER_REQUEST = 100;
    private static final Long TEST_SINCE_ID = 1000L;
    private static final Long TEST_MAX_ID = 1111L;
    private static final String REQUIRED_IMPRESSION_SECTION = "search";


    public void testConstructor() {
        final SearchTimeline timeline = new SearchTimeline(TEST_QUERY, TEST_GEOCODE,
                TEST_RESULT_TYPE, TEST_LANG, TEST_ITEMS_PER_REQUEST, TEST_UNTIL_DATE);
        assertEquals(TEST_QUERY + SearchTimeline.FILTER_RETWEETS, timeline.query);
        assertEquals(TEST_LANG, timeline.languageCode);
        assertEquals(TEST_ITEMS_PER_REQUEST, timeline.maxItemsPerRequest);
        assertEquals(TEST_UNTIL_DATE, timeline.untilDate);
    }

    // most api arguments should default to Null to allow the backend to determine default behavior
    public void testConstructor_defaults() {
        final SearchTimeline timeline = new SearchTimeline(null, null, null, null, null, null);
        assertNull(timeline.query);
        assertNull(timeline.languageCode);
        assertNull(timeline.maxItemsPerRequest);
        assertNull(timeline.untilDate);
    }

    // FILTER_RETWEETS modifier should be added to the end of the non-null search queries

    public void testFilterRetweets() {
        final SearchTimeline timeline = new SearchTimeline(TEST_QUERY, null, null, null, null,
                null);
        assertTrue(timeline.query.endsWith(SearchTimeline.FILTER_RETWEETS));
    }

    public void testAddFilterRetweets() {
        final SearchTimeline timeline = new SearchTimeline(TEST_FILTER_QUERY, null, null, null,
                null, null);
        assertEquals("from:twitter -filter:retweets", timeline.query);
    }

    public void testFilterRetweets_nullQuery() {
        // handle null queries, do not append FILTER_RETWEETS
        final SearchTimeline timeline = new SearchTimeline(null, null, null, null, null, null);
        assertNull(timeline.query);
    }

    public void testNext_createsCorrectRequest() {
        final SearchTimeline timeline = spy(new TestSearchTimeline(TEST_QUERY, TEST_GEOCODE,
                TEST_RESULT_TYPE, TEST_LANG, TEST_ITEMS_PER_REQUEST, TEST_UNTIL_DATE));
        timeline.next(TEST_SINCE_ID, mock(Callback.class));
        verify(timeline).createSearchRequest(eq(TEST_SINCE_ID),
                isNull(Long.class));
    }

    public void testPrevious_createsCorrectRequest() {
        final SearchTimeline timeline = spy(new TestSearchTimeline(TEST_QUERY, TEST_GEOCODE,
                TEST_RESULT_TYPE, TEST_LANG, TEST_ITEMS_PER_REQUEST, TEST_UNTIL_DATE));
        timeline.previous(TEST_MAX_ID, mock(Callback.class));
        // intentionally decrementing the maxId which is passed through to the request
        verify(timeline).createSearchRequest(isNull(Long.class),
                eq(TEST_MAX_ID - 1));
    }

    public void testCreateSearchRequest() {
        // build a timeline with test params
        final SearchTimeline timeline = spy(new TestSearchTimeline(TEST_QUERY, null,
                TEST_RESULT_TYPE, TEST_LANG, TEST_ITEMS_PER_REQUEST, TEST_UNTIL_DATE));
        // create a request directly
        timeline.createSearchRequest(TEST_SINCE_ID, TEST_MAX_ID);

        // assert searchTimeline call is made with the correct arguments
        verify(TwitterCore.getInstance().getApiClient().getSearchService())
                .tweets(eq(TEST_QUERY + SearchTimeline.FILTER_RETWEETS),
                isNull(Geocode.class), eq(TEST_LANG), isNull(String.class),
                eq(TEST_RESULT_TYPE), eq(TEST_ITEMS_PER_REQUEST), eq(TEST_UNTIL_DATE),
                eq(TEST_SINCE_ID), eq(TEST_MAX_ID), eq(true));
    }

    public void testGetScribeSection() {
        final SearchTimeline timeline = new SearchTimeline.Builder().query(TEST_QUERY).build();
        assertEquals(REQUIRED_IMPRESSION_SECTION, timeline.getTimelineType());
    }

    /* Builder */

    public void testBuilder() {
        final SearchTimeline timeline = new SearchTimeline.Builder()
                .query(TEST_QUERY)
                .geocode(TEST_GEOCODE)
                .languageCode(TEST_LANG)
                .maxItemsPerRequest(TEST_ITEMS_PER_REQUEST)
                .resultType(SearchTimeline.ResultType.POPULAR)
                .untilDate(TEST_UNTIL)
                .build();
        assertEquals(TEST_QUERY + SearchTimeline.FILTER_RETWEETS, timeline.query);
        assertEquals(TEST_RESULT_TYPE, timeline.resultType);
        assertEquals(TEST_LANG, timeline.languageCode);
        assertEquals(TEST_ITEMS_PER_REQUEST, timeline.maxItemsPerRequest);
        assertEquals(TEST_UNTIL_DATE, timeline.untilDate);
        assertEquals(TEST_GEOCODE, timeline.geocode);
    }

    // api arguments should default to Null to allow the backend to determine default behavior
    public void testBuilder_defaults() {
        final SearchTimeline timeline = new SearchTimeline.Builder()
                .query(TEST_QUERY)
                .build();
        assertNull(timeline.languageCode);
        assertEquals(REQUIRED_DEFAULT_ITEMS_PER_REQUEST, timeline.maxItemsPerRequest);
        assertNull(timeline.untilDate);
    }

    public void testBuilder_query() {
        final SearchTimeline timeline = new SearchTimeline.Builder()
                .query(TEST_QUERY)
                .build();
        assertEquals(TEST_QUERY + SearchTimeline.FILTER_RETWEETS, timeline.query);
    }

    public void testBuilder_nullQuery() {
        try {
            new SearchTimeline.Builder().build();
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            assertEquals("query must not be null", e.getMessage());
        }
    }

    public void testBuilder_lang() {
        final SearchTimeline timeline = new SearchTimeline.Builder()
                .query(TEST_QUERY)
                .languageCode(TEST_LANG)
                .build();
        assertEquals(TEST_LANG, timeline.languageCode);
    }

    public void testBuilder_geocode() {
        final SearchTimeline timeline = new SearchTimeline.Builder()
                .query(TEST_QUERY)
                .geocode(TEST_GEOCODE)
                .build();
        assertEquals(TEST_GEOCODE, timeline.geocode);
    }

    public void testBuilder_maxItemsPerRequest() {
        final SearchTimeline timeline = new SearchTimeline.Builder()
                .query(TEST_QUERY)
                .maxItemsPerRequest(TEST_ITEMS_PER_REQUEST)
                .build();
        assertEquals(TEST_ITEMS_PER_REQUEST, timeline.maxItemsPerRequest);
    }
}

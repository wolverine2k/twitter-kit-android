package com.twitter.sdk.android.tweetui;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.TwitterAndroidTestCase;
import com.twitter.sdk.android.core.models.Identifiable;
import com.twitter.sdk.android.tweetui.internal.TimelineDelegate;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class TimelineListAdapterTest extends TwitterAndroidTestCase {
    private static final int TEST_POSITION = 10;
    private TimelineListAdapter<TestItem> listAdapter;
    private TimelineDelegate<TestItem> mockTimelineDelegate;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mockTimelineDelegate = mock(TestTimelineDelegate.class);
    }

    public void testConstructor() {
        listAdapter = new TestTimelineListAdapter<>(getContext(), mockTimelineDelegate);
        verify(mockTimelineDelegate).refresh(null);
    }

    public void testConstructor_nullTimeline() {
        try {
            new TestTimelineListAdapter<TestItem>(getContext(), (Timeline) null);
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {
            assertEquals("Timeline must not be null", e.getMessage());
        }
    }

    public void testRefresh() {
        listAdapter = new TestTimelineListAdapter<>(getContext(), mockTimelineDelegate);
        final Callback<TimelineResult<TestItem>> mockCallback = mock(Callback.class);
        listAdapter.refresh(mockCallback);
        verify(mockTimelineDelegate).refresh(mockCallback);
    }

    public void testGetCount() {
        listAdapter = new TestTimelineListAdapter<>(getContext(), mockTimelineDelegate);
        listAdapter.getCount();
        verify(mockTimelineDelegate).getCount();
    }

    public void testGetItem() {
        listAdapter = new TestTimelineListAdapter<>(getContext(), mockTimelineDelegate);
        listAdapter.getItem(TEST_POSITION);
        verify(mockTimelineDelegate).getItem(TEST_POSITION);
    }

    public void testGetItemId() {
        listAdapter = new TestTimelineListAdapter<>(getContext(), mockTimelineDelegate);
        listAdapter.getItemId(TEST_POSITION);
        verify(mockTimelineDelegate).getItemId(TEST_POSITION);
    }

    public void testRegisterDataSetObserver() {
        listAdapter = new TestTimelineListAdapter<>(getContext(), mockTimelineDelegate);
        listAdapter.registerDataSetObserver(mock(DataSetObserver.class));
        verify(mockTimelineDelegate, times(1)).registerDataSetObserver(any(DataSetObserver.class));
    }

    public void testUnregisterDataSetObserver() {
        listAdapter = new TestTimelineListAdapter<>(getContext(), mockTimelineDelegate);
        listAdapter.unregisterDataSetObserver(mock(DataSetObserver.class));
        verify(mockTimelineDelegate, times(1))
                .unregisterDataSetObserver(any(DataSetObserver.class));
    }

    public void testNotifyDataSetChanged() {
        listAdapter = new TestTimelineListAdapter<>(getContext(), mockTimelineDelegate);
        listAdapter.notifyDataSetChanged();
        verify(mockTimelineDelegate, times(1)).notifyDataSetChanged();
    }

    public void testNotifyDataSetInvalidated() {
        listAdapter = new TestTimelineListAdapter<>(getContext(), mockTimelineDelegate);
        listAdapter.notifyDataSetInvalidated();
        verify(mockTimelineDelegate, times(1)).notifyDataSetInvalidated();
    }

    /**
     * Implement abstract method getView to create a concrete subclass TestTimelineListAdapter so
     * that TimelineListAdapter non-view related behavior can be tested.
     */
    public class TestTimelineListAdapter<T extends Identifiable> extends TimelineListAdapter<T> {

        TestTimelineListAdapter(Context context, Timeline<T> timeline) {
            super(context, timeline);
        }

        TestTimelineListAdapter(Context context, TimelineDelegate<T> delegate) {
           super(context, delegate);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }

    /**
     * Makes class public so it can be mocked on ART runtime.
     * @param <T>
     */
    public class TestTimelineDelegate<T extends Identifiable> extends TimelineDelegate {
        public TestTimelineDelegate(Timeline<T> timeline) {
            super(timeline);
        }
    }
}

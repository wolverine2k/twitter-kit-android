package com.twitter.sdk.android.core.internal.scribe;

import io.fabric.sdk.android.services.events.DisabledEventsStrategy;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import io.fabric.sdk.android.services.events.EventsStrategy;

import com.twitter.sdk.android.core.TwitterAndroidTestCase;

import java.util.concurrent.ScheduledExecutorService;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ScribeHandlerTest extends TwitterAndroidTestCase {

    private ScheduledExecutorService mockExecutorService;
    private ScribeHandler scribeHandler;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mockExecutorService = mock(ScheduledExecutorService.class);
        scribeHandler = new ScribeHandler(getContext(), mock(EventsStrategy.class),
                mock(EventsFilesManager.class), mockExecutorService);
    }

    public void testScribe() {
        scribeHandler.scribe(mock(ScribeEvent.class));
        verify(mockExecutorService).submit(any(Runnable.class));
    }

    public void testScribeAndFlush() {
        scribeHandler.scribeAndFlush(mock(ScribeEvent.class));
        verify(mockExecutorService).submit(any(Runnable.class));
    }

    public void testGetDisabledEventsStrategy() {
        final EventsStrategy<ScribeEvent> strategy = scribeHandler.getDisabledEventsStrategy();
        assertTrue(strategy instanceof DisabledEventsStrategy);
    }
}

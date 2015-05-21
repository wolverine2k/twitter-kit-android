package com.twitter.sdk.android.core.internal.scribe;

import android.content.Context;

import io.fabric.sdk.android.services.events.DisabledEventsStrategy;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import io.fabric.sdk.android.services.events.EventsHandler;
import io.fabric.sdk.android.services.events.EventsStrategy;

import java.util.concurrent.ScheduledExecutorService;

class ScribeHandler extends EventsHandler<ScribeEvent> {

    public ScribeHandler(Context context, EventsStrategy<ScribeEvent> strategy,
            EventsFilesManager filesManager, ScheduledExecutorService executorService) {
        super(context, strategy, filesManager, executorService);
    }

    /**
     * Scribes an event.
     */
    public void scribe(ScribeEvent event) {
        recordEventAsync(event, false);
    }

    /**
     * Scribes an event and immediately flushes the event.
     */
    public void scribeAndFlush(ScribeEvent event) {
        recordEventAsync(event, true);
    }

    @Override
    protected EventsStrategy<ScribeEvent> getDisabledEventsStrategy() {
        return new DisabledEventsStrategy<>();
    }
}

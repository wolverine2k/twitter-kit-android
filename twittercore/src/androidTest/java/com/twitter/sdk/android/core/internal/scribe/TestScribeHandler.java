package com.twitter.sdk.android.core.internal.scribe;

import android.content.Context;

import io.fabric.sdk.android.services.events.EventsFilesManager;
import io.fabric.sdk.android.services.events.EventsStrategy;

import java.util.concurrent.ScheduledExecutorService;

public class TestScribeHandler extends ScribeHandler {

    public TestScribeHandler(Context context, EventsStrategy<ScribeEvent> strategy,
            EventsFilesManager filesManager, ScheduledExecutorService executorService) {
        super(context, strategy, filesManager, executorService);
    }
}

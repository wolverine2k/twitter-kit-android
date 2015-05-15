package com.twitter.sdk.android.core.internal.scribe;

import android.content.Context;

import io.fabric.sdk.android.services.common.CurrentTimeProvider;
import io.fabric.sdk.android.services.events.EventTransform;
import io.fabric.sdk.android.services.events.QueueFileEventStorage;

import java.io.IOException;

/**
 * Test class to allow mocking of ScribeFilesManager.
 */
public class TestScribeFilesManager extends ScribeFilesManager {

    public TestScribeFilesManager(Context context, EventTransform<ScribeEvent> transform,
            CurrentTimeProvider currentTimeProvider, QueueFileEventStorage eventsStorage,
            int maxFilesToKeep) throws IOException {
        super(context, transform, currentTimeProvider, eventsStorage, maxFilesToKeep);
    }
}

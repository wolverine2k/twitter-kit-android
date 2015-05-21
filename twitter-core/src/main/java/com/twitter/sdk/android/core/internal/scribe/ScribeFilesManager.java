package com.twitter.sdk.android.core.internal.scribe;

import android.content.Context;

import io.fabric.sdk.android.services.common.CurrentTimeProvider;
import io.fabric.sdk.android.services.events.EventTransform;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import io.fabric.sdk.android.services.events.QueueFileEventStorage;

import java.io.IOException;
import java.util.UUID;

class ScribeFilesManager extends EventsFilesManager<ScribeEvent> {

    static final String FILE_PREFIX = "se";
    static final String FILE_EXTENSION = ".tap";

    public ScribeFilesManager(Context context, EventTransform<ScribeEvent> transform,
            CurrentTimeProvider currentTimeProvider, QueueFileEventStorage eventsStorage,
            int defaultMaxFilesToKeep) throws IOException {
        super(context, transform, currentTimeProvider, eventsStorage, defaultMaxFilesToKeep);
    }

    @Override
    protected String generateUniqueRollOverFileName() {
        final UUID targetUUIDComponent = UUID.randomUUID();

        return new StringBuilder()
                .append(FILE_PREFIX)
                .append(ROLL_OVER_FILE_NAME_SEPARATOR)
                .append(targetUUIDComponent.toString())
                .append(ROLL_OVER_FILE_NAME_SEPARATOR)
                .append(currentTimeProvider.getCurrentTimeMillis())
                .append(FILE_EXTENSION)
                .toString();
    }
}

package com.twitter.sdk.android.core.internal.scribe;

import android.content.Context;

import com.twitter.sdk.android.core.TwitterAndroidTestCase;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class EnabledScribeStrategyTest extends TwitterAndroidTestCase {

    private static final String ANY_USER_AGENT = "ua";
    private static final String ANY_PATH_VERSION = "version";
    private static final String ANY_PATH_TYPE = "type";
    private static final int TEST_SEND_INTERVAL_SECONDS = 60;

    public void testConstructor() {
        final ScheduledExecutorService mockExecutor = mock(ScheduledExecutorService.class);
        final ScribeConfig scribeConfig = new ScribeConfig(true, ScribeConfig.BASE_URL,
                ANY_PATH_VERSION, ANY_PATH_TYPE, null, ANY_USER_AGENT,
                ScribeConfig.DEFAULT_MAX_FILES_TO_KEEP, TEST_SEND_INTERVAL_SECONDS);
        new EnabledScribeStrategy(mock(Context.class),
                mockExecutor, mock(TestScribeFilesManager.class), scribeConfig,
                mock(TestScribeFilesSender.class));

        verify(mockExecutor).scheduleAtFixedRate(any(Runnable.class), anyLong(),
                eq((long) TEST_SEND_INTERVAL_SECONDS), eq(TimeUnit.SECONDS));
    }

    public void testGetFileSender() {
        final ScribeConfig scribeConfig = new ScribeConfig(true, ScribeConfig.BASE_URL,
                ANY_PATH_VERSION, ANY_PATH_TYPE, null, ANY_USER_AGENT,
                ScribeConfig.DEFAULT_MAX_FILES_TO_KEEP, TEST_SEND_INTERVAL_SECONDS);
        final ScribeFilesSender mockSender = mock(TestScribeFilesSender.class);
        final EnabledScribeStrategy strategy = new EnabledScribeStrategy(mock(Context.class),
                mock(ScheduledExecutorService.class), mock(TestScribeFilesManager.class),
                scribeConfig, mockSender);
        assertEquals(mockSender, strategy.getFilesSender());
    }
}

package com.digits.sdk.android;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.FabricTestUtils;
import io.fabric.sdk.android.Kit;

import com.twitter.sdk.android.core.ParallelCallableExecutor;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.internal.scribe.DefaultScribeClient;
import com.twitter.sdk.android.core.internal.scribe.EventNamespace;

import java.util.List;
import java.util.concurrent.Callable;

import static org.mockito.Mockito.*;

public class DigitsTest extends DigitsAndroidTestCase {

    public void testGetIdentifier() {
        final Kit kit = new Digits();
        final String identifier = BuildConfig.GROUP + ":" + BuildConfig.ARTIFACT_ID;
        assertEquals(identifier, kit.getIdentifier());
    }

    public void testGetDigitClients_multipleThreads() throws Exception {
        try {
            Fabric.with(getContext(), new TwitterCore(new TwitterAuthConfig("", "")), new Digits());
            final ParallelCallableExecutor<DigitsClient> executor =
                    new ParallelCallableExecutor<>(
                            new DigitsClientCallable(),
                            new DigitsClientCallable());

            final List<DigitsClient> values = executor.getAllValues();
            assertNotNull(values.get(0));
            assertSame(values.get(0), values.get(1));
        } finally {
            FabricTestUtils.resetFabric();
        }
    }

    public void testScribe_withEventNamespaceAndClient() {
        final DefaultScribeClient scribeClient = mock(DefaultScribeClient.class);
        Fabric.with(getContext(), new TwitterCore(new TwitterAuthConfig("", "")), new Digits());
        final Digits kit = Digits.getInstance();
        kit.scribeClient = scribeClient;

        kit.scribe(mock(EventNamespace.class));
        verify(scribeClient).scribeSyndicatedSdkImpressionEvents(any(EventNamespace.class));
    }

    public void testScribe_withNullClient() {
        try {
            Fabric.with(getContext(), new TwitterCore(new TwitterAuthConfig("", "")), new Digits());
            final Digits kit = Digits.getInstance();
            kit.scribeClient = null;

            kit.scribe(mock(EventNamespace.class));
        } catch (NullPointerException e) {
            fail("should not have thrown npe");
        }
    }

    private static class DigitsClientCallable implements Callable<DigitsClient> {
        @Override
        public DigitsClient call() {
            final Digits digits = Fabric.getKit(Digits.class);
            return digits.getDigitsClient();
        }
    }
}

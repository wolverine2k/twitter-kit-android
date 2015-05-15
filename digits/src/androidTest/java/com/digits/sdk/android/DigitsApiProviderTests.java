package com.digits.sdk.android;

import com.twitter.sdk.android.core.TwitterAuthConfig;

import java.util.concurrent.ExecutorService;

import javax.net.ssl.SSLSocketFactory;

import static org.mockito.Mockito.mock;

public class DigitsApiProviderTests extends DigitsAndroidTestCase {
    private TwitterAuthConfig authConfig;
    private DigitsSession guestSession;
    private DigitsApiProvider provider;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        authConfig = new TwitterAuthConfig(CONSUMER_SECRET, CONSUMER_KEY);
        guestSession = DigitsSession.create(DigitsSessionTests.getNewLoggedOutUser());


        provider = new DigitsApiProvider(guestSession, authConfig,
                mock(SSLSocketFactory.class), mock(ExecutorService.class),
                mock(MockDigitsUserAgent.class));

    }

    public void testGetSdkService() throws Exception {
        final DigitsApiProvider.SdkService sdkService = provider.getSdkService();
        final DigitsApiProvider.SdkService newSdkService = provider.getSdkService();
        assertTrue(sdkService == newSdkService);
    }

    public void testGetDeviceService() throws Exception {
        final DigitsApiProvider.DeviceService deviceService = provider.getDeviceService();
        final DigitsApiProvider.DeviceService newDeviceService = provider.getDeviceService();
        assertTrue(deviceService == newDeviceService);
    }

}

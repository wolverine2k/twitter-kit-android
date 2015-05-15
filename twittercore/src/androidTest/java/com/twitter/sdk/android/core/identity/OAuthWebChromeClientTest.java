package com.twitter.sdk.android.core.identity;

import android.webkit.ConsoleMessage;

import com.twitter.sdk.android.core.TwitterAndroidTestCase;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;

public class OAuthWebChromeClientTest extends TwitterAndroidTestCase {

    OAuthWebChromeClient client;

    public void setUp() throws Exception {
        super.setUp();
        client = new OAuthWebChromeClient();
    }

    public void testOnConsoleMessage() throws Exception {
        final ConsoleMessage message = mock(ConsoleMessage.class);
        client.onConsoleMessage(message);
        verifyZeroInteractions(message);
    }
}

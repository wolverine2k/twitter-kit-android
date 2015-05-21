package com.twitter.sdk.android.core.identity;

import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;

import com.twitter.sdk.android.core.TwitterAndroidTestCase;

import org.mockito.ArgumentCaptor;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class OAuthWebViewClientTest extends TwitterAndroidTestCase {

    private static final String COMPLETE_URL = "twittersdk://callback";
    private static final String RETURNED_URL = "twittersdk://callback?version=1.0.1-SNAPSHOT.dev"
            + "&app=gfKkD56t74u3IxLDpxo5SQ"
            + "&oauth_token=v9N2bPf6HPZbqq8nS7O2wML81p41iqWd"
            + "&oauth_verifier=e1BmelAyStYjkAEtqGPBQ8xNlh5GOVtU";
    private static final String TEST_URL = "https://test.com";
    private static final int TEST_ERROR_CODE = 1000;
    private static final String TEST_ERROR_DESC = "ERROR ERROR ERROR!";

    private static final String EXPECTED_VERSION_KEY = "version";
    private static final String EXPECTED_VERSION_VALUE = "1.0.1-SNAPSHOT.dev";
    private static final String EXPECTED_APP_KEY = "app";
    private static final String EXPECTED_APP_VALUE = "gfKkD56t74u3IxLDpxo5SQ";
    private static final String EXPECTED_OAUTH_TOKEN_KEY = "oauth_token";
    private static final String EXPECTED_OAUTH_TOKEN_VALUE = "v9N2bPf6HPZbqq8nS7O2wML81p41iqWd";
    private static final String EXPECTED_OAUTH_VERIFIER_KEY = "oauth_verifier";
    private static final String EXPECTED_OAUTH_VERIFIER_VALUE = "e1BmelAyStYjkAEtqGPBQ8xNlh5GOVtU";

    private OAuthWebViewClient.Listener mockListener;
    private OAuthWebViewClient webViewClient;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        mockListener = mock(OAuthWebViewClient.Listener.class);
        webViewClient = new OAuthWebViewClient(COMPLETE_URL, mockListener);
    }

    public void testOnPageFinished() {
        final WebView mockWebView = mock(WebView.class);
        webViewClient.onPageFinished(mockWebView, COMPLETE_URL);
        verify(mockListener).onPageFinished(eq(mockWebView), eq(COMPLETE_URL));
    }

    public void testShouldOverrideUrlLoading_urlStartsWithCompleteUrl() {
        webViewClient.shouldOverrideUrlLoading(mock(WebView.class), RETURNED_URL);

        final ArgumentCaptor<Bundle> bundleArgCaptor = ArgumentCaptor.forClass(Bundle.class);
        verify(mockListener).onSuccess(bundleArgCaptor.capture());

        final Bundle bundle = bundleArgCaptor.getValue();
        assertEquals(EXPECTED_VERSION_VALUE, bundle.getString(EXPECTED_VERSION_KEY));
        assertEquals(EXPECTED_APP_VALUE, bundle.getString(EXPECTED_APP_KEY));
        assertEquals(EXPECTED_OAUTH_TOKEN_VALUE, bundle.getString(EXPECTED_OAUTH_TOKEN_KEY));
        assertEquals(EXPECTED_OAUTH_VERIFIER_VALUE, bundle.getString(EXPECTED_OAUTH_VERIFIER_KEY));
    }

    public void testShouldOverrideUrlLoading_urlDoesNotStartWithCompleteUrl() {
        webViewClient.shouldOverrideUrlLoading(mock(WebView.class), TEST_URL);
        verifyZeroInteractions(mockListener);
    }

    public void testOnReceivedError() {
        webViewClient.onReceivedError(mock(WebView.class), TEST_ERROR_CODE, TEST_ERROR_DESC,
                TEST_URL);
        verifyOnError(TEST_ERROR_CODE, TEST_ERROR_DESC, TEST_URL);
    }

    public void testOnReceivedSslError() {
        final SslError mockSslError = mock(SslError.class);
        when(mockSslError.getPrimaryError()).thenReturn(TEST_ERROR_CODE);

        webViewClient.onReceivedSslError(mock(WebView.class), mock(SslErrorHandler.class),
                mockSslError);
        verifyOnError(TEST_ERROR_CODE, null, null);
    }

    private void verifyOnError(int expectedErrorCode, String expectedErrorDesc,
            String expectedFailingUrl) {
        final ArgumentCaptor<WebViewException> exceptionArgCaptor
                = ArgumentCaptor.forClass(WebViewException.class);
        verify(mockListener).onError(exceptionArgCaptor.capture());

        final WebViewException exception = exceptionArgCaptor.getValue();
        assertEquals(expectedErrorCode, exception.getErrorCode());
        assertEquals(expectedErrorDesc, exception.getMessage());
        assertEquals(expectedFailingUrl, exception.getFailingUrl());
    }
}

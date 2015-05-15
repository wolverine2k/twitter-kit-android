package com.twitter.sdk.android.core.identity;

import android.webkit.WebView;
import android.widget.ProgressBar;

import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.internal.oauth.OAuth1aService;

/**
 * Test class to allow mocking of OAuthController.
 */
public class TestOAuthController extends OAuthController {

    TestOAuthController(ProgressBar spinner, WebView webView, TwitterAuthConfig authConfig,
            OAuth1aService oAuth1aService, Listener listener) {
        super(spinner, webView, authConfig, oAuth1aService, listener);
    }
}

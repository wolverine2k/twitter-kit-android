package com.twitter.sdk.android.core.identity;

import android.app.Activity;
import android.content.Intent;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterSession;

/**
 * OAuth 1.0a implementation of an {@link AuthHandler}
 */
class OAuthHandler extends AuthHandler {

    /**
     * @param authConfig The {@link com.twitter.sdk.android.core.TwitterAuthConfig}.
     * @param callback   The listener to callback when authorization completes.
     */
    public OAuthHandler(TwitterAuthConfig authConfig, Callback<TwitterSession> callback,
            int requestCode) {
        super(authConfig, callback, requestCode);
    }

    @Override
    public boolean authorize(Activity activity) {
        activity.startActivityForResult(newIntent(activity), requestCode);
        return true;
    }

    Intent newIntent(Activity activity) {
        final Intent intent = new Intent(activity, OAuthActivity.class);
        intent.putExtra(OAuthActivity.EXTRA_AUTH_CONFIG, getAuthConfig());
        return intent;
    }
}

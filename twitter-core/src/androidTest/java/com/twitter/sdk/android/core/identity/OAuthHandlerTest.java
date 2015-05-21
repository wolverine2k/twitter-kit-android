package com.twitter.sdk.android.core.identity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.TwitterAndroidTestCase;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class OAuthHandlerTest extends TwitterAndroidTestCase {

    private static final int REQUEST_CODE = TwitterAuthConfig.DEFAULT_AUTH_REQUEST_CODE;

    private TwitterAuthConfig mockAuthConfig;
    private OAuthHandler authHandler;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mockAuthConfig = mock(TwitterAuthConfig.class);
        authHandler = new OAuthHandler(mockAuthConfig, mock(Callback.class), REQUEST_CODE);
    }

    public void testAuthorize() {
        final Activity mockActivity = mock(Activity.class);
        doNothing().when(mockActivity).startActivityForResult(any(Intent.class), anyInt());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            doNothing().when(mockActivity).startActivityForResult(any(Intent.class), anyInt(),
                    any(Bundle.class));
        }
        authHandler.authorize(mockActivity);
        verify(mockActivity).startActivityForResult(any(Intent.class), eq(REQUEST_CODE));
    }

    public void testNewIntent() {
        final Activity mockActivity = mock(Activity.class);
        final Intent intent = authHandler.newIntent(mockActivity);
        assertEquals(mockAuthConfig, intent.getParcelableExtra(OAuthActivity.EXTRA_AUTH_CONFIG));
    }
}

package com.twitter.sdk.android.core.identity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.TwitterAndroidTestCase;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import static org.mockito.Mockito.*;

public class SSOAuthHandlerTest extends TwitterAndroidTestCase {

    private static final int REQUEST_CODE = TwitterAuthConfig.DEFAULT_AUTH_REQUEST_CODE;
    private static final String INVALID_SIGNATURE = "AAAAAAAAAA";

    private SSOAuthHandler ssoAuthHandler;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        ssoAuthHandler = new SSOAuthHandler(mock(TwitterAuthConfig.class),
                mock(Callback.class), REQUEST_CODE);
    }

    public void testIsAvailable_twitterInstalled() throws PackageManager.NameNotFoundException {
        final Context mockContext = mock(Context.class);
        TestUtils.setupTwitterInstalled(mockContext, SSOAuthHandler.APP_SIGNATURE);
        assertTrue(SSOAuthHandler.isAvailable(mockContext));
    }

    public void testIsAvailable_twitterInstalledInvalidSignature()
            throws PackageManager.NameNotFoundException {
        final Context mockContext = mock(Context.class);
        TestUtils.setupTwitterInstalled(mockContext, INVALID_SIGNATURE);
        assertFalse(SSOAuthHandler.isAvailable(mockContext));
    }

    public void testIsAvailable_twitterNotInstalled() throws PackageManager.NameNotFoundException {
        final Context mockContext = mock(Context.class);
        TestUtils.setUpTwitterNotInstalled(mockContext);
        assertFalse(SSOAuthHandler.isAvailable(mockContext));
    }

    public void testAuthorize_twitterInstalled() throws PackageManager.NameNotFoundException {
        final Activity mockActivity = mock(Activity.class);
        TestUtils.setupTwitterInstalled(mockActivity, SSOAuthHandler.APP_SIGNATURE);
        assertTrue(ssoAuthHandler.authorize(mockActivity));
    }

    public void testAuthorize_twitterInstalledInvalidSignature()
            throws PackageManager.NameNotFoundException {
        final Activity mockActivity = mock(Activity.class);
        TestUtils.setupTwitterInstalled(mockActivity, INVALID_SIGNATURE);
        assertFalse(ssoAuthHandler.authorize(mockActivity));
    }

    public void testAuthorize_twitterInstalledNoSsoActivity()
            throws PackageManager.NameNotFoundException {
        final Activity mockActivity = mock(Activity.class);
        TestUtils.setupTwitterInstalled(mockActivity, SSOAuthHandler.APP_SIGNATURE);
        final PackageManager mockPm = mockActivity.getPackageManager();
        when(mockPm.getActivityInfo(SSOAuthHandler.SSO_ACTIVITY, 0))
                .thenThrow(mock(PackageManager.NameNotFoundException.class));
        assertFalse(ssoAuthHandler.authorize(mockActivity));
    }

    public void testAuthorize_twitterNotInstalled() throws PackageManager.NameNotFoundException {
        final Activity mockActivity = mock(Activity.class);
        TestUtils.setUpTwitterNotInstalled(mockActivity);
        assertFalse(ssoAuthHandler.authorize(mockActivity));
    }

    public void testAuthorize_startActivityForResultThrowsException()
            throws PackageManager.NameNotFoundException {
        final Activity mockActivity = mock(Activity.class);
        TestUtils.setupTwitterInstalled(mockActivity, SSOAuthHandler.APP_SIGNATURE);
        doThrow(new RuntimeException()).when(mockActivity)
                .startActivityForResult(any(Intent.class), eq(REQUEST_CODE));
        assertFalse(ssoAuthHandler.authorize(mockActivity));
    }
}

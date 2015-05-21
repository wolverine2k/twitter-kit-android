package com.twitter.sdk.android.core.identity;

import android.app.Activity;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.TwitterAndroidTestCase;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthStateTest extends TwitterAndroidTestCase {

    private Activity mockActivity;
    private AuthHandler mockAuthHandler;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mockActivity = mock(Activity.class);
        mockAuthHandler = mock(AuthHandler.class);
        when(mockAuthHandler.authorize(mockActivity)).thenReturn(true);
    }

    public void testBeginAuthorize() {
        final AuthState authState = new AuthState();
        final boolean result = authState.beginAuthorize(mockActivity, mockAuthHandler);
        assertTrue(result);
        assertTrue(authState.isAuthorizeInProgress());
        assertEquals(mockAuthHandler, authState.getAuthHandler());
    }

    public void testBeginAuthorize_authorizeFails() {
        when(mockAuthHandler.authorize(mockActivity)).thenReturn(false);

        final AuthState authState = new AuthState();
        final boolean result = authState.beginAuthorize(mockActivity, mockAuthHandler);
        // Verify that attempting to begin authorize fails if the AuthHandler#authorize returns
        // false.
        assertFalse(result);
        assertFalse(authState.isAuthorizeInProgress());
        assertNull(authState.getAuthHandler());
    }

    public void testBeginAuthorize_authorizeInProgress() {
        final AuthState authState = new AuthState();
        final boolean result = authState.beginAuthorize(mockActivity, mockAuthHandler);
        assertTrue(result);
        // Verify that attempting to begin another authorize fails since one is already in progress.
        assertFalse(authState.beginAuthorize(mockActivity, mockAuthHandler));
    }

    public void testBeginAuthorize_authHandlerCompareAndSetFails() {
        final AuthState authState = new AuthState();
        final boolean result = authState.beginAuthorize(mockActivity,
                new AuthHandler(mock(TwitterAuthConfig.class), mock(Callback.class),
                        TwitterAuthConfig.DEFAULT_AUTH_REQUEST_CODE) {
                    @Override
                    public boolean authorize(Activity activity) {
                        // We use this opportunity to set authState's authHandlerRef so that we
                        // can verify behavior when compare and set fails. This is done because
                        // AtomicReference has methods that cannot be mocked.
                        authState.authHandlerRef.set(mock(AuthHandler.class));
                        return true;
                    }
                });
        assertFalse(result);
    }

    public void testEndAuthorize() {
        final AuthState authState = new AuthState();
        final boolean result = authState.beginAuthorize(mockActivity, mockAuthHandler);
        assertTrue(result);
        assertTrue(authState.isAuthorizeInProgress());
        assertEquals(mockAuthHandler, authState.getAuthHandler());

        authState.endAuthorize();

        // Verify that end authorize resets everything.
        assertFalse(authState.isAuthorizeInProgress());
        assertNull(authState.getAuthHandler());
    }

    public void testEndAuthorize_noAuthorizeInProgress() {
        final AuthState authState = new AuthState();
        // Verify that calling end authorize when there is no authorize in progress does not cause
        // any problems.
        authState.endAuthorize();
    }
}

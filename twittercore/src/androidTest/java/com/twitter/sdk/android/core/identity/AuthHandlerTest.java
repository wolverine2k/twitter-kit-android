package com.twitter.sdk.android.core.identity;

import android.app.Activity;
import android.content.Intent;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAndroidTestCase;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthException;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterSession;

import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

public class AuthHandlerTest extends TwitterAndroidTestCase {
    private static final TwitterAuthConfig AUTH_CONFIG
            = new TwitterAuthConfig("consumerKey", "consumerSecret");
    private static final int TEST_REQUEST_CODE = 1;
    private static final String TEST_CANCEL_MESSAGE = "Test cancel message";
    private static final String TEST_ERROR_MESSAGE = "Test error message";

    public void testHandleOnActivityResult_resultCodeOK() {
        // Verify that when handleOnActivityResult is called with ok, the listener receives the auth
        // response.
        final Callback<TwitterSession> mockCallback = mock(Callback.class);
        final AuthHandler authHandler = newAuthHandler(mockCallback);

        final boolean result = authHandler.handleOnActivityResult(TEST_REQUEST_CODE,
                Activity.RESULT_OK, new Intent()
                        .putExtra(AuthHandler.EXTRA_TOKEN, TOKEN)
                        .putExtra(AuthHandler.EXTRA_TOKEN_SECRET, SECRET)
                        .putExtra(AuthHandler.EXTRA_SCREEN_NAME, USER)
                        .putExtra(AuthHandler.EXTRA_USER_ID, USER_ID));

        assertTrue(result);
        final ArgumentCaptor<Result> argCaptor = ArgumentCaptor.forClass(Result.class);
        verify(mockCallback).success(argCaptor.capture());
        final TwitterSession session = (TwitterSession) argCaptor.getValue().data;
        final TwitterAuthToken authToken = session.getAuthToken();
        assertEquals(TOKEN, authToken.token);
        assertEquals(SECRET, authToken.secret);
        assertEquals(USER, session.getUserName());
        assertEquals(USER_ID, session.getUserId());
    }

    public void testHandleOnActivityResult_resultCodeCancel() {
        // Verify that when handleOnActivityResult is called with cancel code, the listener receives
        // the error.
        final Callback<TwitterSession> mockCallback = mock(Callback.class);
        final AuthHandler authHandler = newAuthHandler(mockCallback);
        final TwitterAuthException authException = new TwitterAuthException(TEST_CANCEL_MESSAGE);

        final boolean result = authHandler.handleOnActivityResult(TEST_REQUEST_CODE,
                Activity.RESULT_CANCELED, new Intent().putExtra(AuthHandler.EXTRA_AUTH_ERROR,
                        authException));

        assertTrue(result);
        assertCallbackFailureErrorMsg(mockCallback, authException.getMessage());
    }

    private void assertCallbackFailureErrorMsg(Callback<TwitterSession> mockCallback,
            String expectedErrorMsg) {
        final ArgumentCaptor<TwitterAuthException> argCaptor
                = ArgumentCaptor.forClass(TwitterAuthException.class);
        verify(mockCallback).failure(argCaptor.capture());
        assertEquals(expectedErrorMsg, argCaptor.getValue().getMessage());
    }

    public void testHandleOnActivityResult_resultCodeError() {
        // Verify that when handleOnActivityResult is called with an error code and intent data, the
        // listener receives the error.
        final Callback<TwitterSession> mockCallback = mock(Callback.class);
        final AuthHandler authHandler = newAuthHandler(mockCallback);
        final TwitterAuthException authException = new TwitterAuthException(TEST_ERROR_MESSAGE);

        final boolean result = authHandler.handleOnActivityResult(TEST_REQUEST_CODE,
                AuthHandler.RESULT_CODE_ERROR, new Intent().putExtra(AuthHandler.EXTRA_AUTH_ERROR,
                        authException));

        assertTrue(result);
        assertCallbackFailureErrorMsg(mockCallback, authException.getMessage());
    }

    public void testHandleOnActivityResult_resultCodeErrorDataNull() {
        // Verify that when handleOnActivityResult is called with an error code and no intent data,
        // the listener receives generic error.
        final Callback<TwitterSession> mockCallback = mock(Callback.class);
        final AuthHandler authHandler = newAuthHandler(mockCallback);

        final boolean result = authHandler.handleOnActivityResult(TEST_REQUEST_CODE,
                AuthHandler.RESULT_CODE_ERROR, null);

        assertTrue(result);
        assertCallbackFailureErrorMsg(mockCallback, "Authorize failed.");
    }

    public void testHandleOnActivityResult_resultCodeErrorDataWithNoAuthError() {
        // Verify that when handleOnActivityResult is called with an error code and intent data but
        // no auth error, the listener receives generic error.
        final Callback<TwitterSession> mockCallback = mock(Callback.class);
        final AuthHandler authHandler = newAuthHandler(mockCallback);

        final boolean result = authHandler.handleOnActivityResult(TEST_REQUEST_CODE,
                AuthHandler.RESULT_CODE_ERROR, new Intent());

        assertTrue(result);
        assertCallbackFailureErrorMsg(mockCallback, "Authorize failed.");
    }

    public void testHandleOnActivityResult_unrecognizedRequestCode() {
        // Verify that when handleOnActivityResult is called with a different request code, the
        // listener is not called.
        final Callback<TwitterSession> mockCallback = mock(Callback.class);
        final AuthHandler authHandler = newAuthHandler(mockCallback);

        final boolean result = authHandler.handleOnActivityResult(TEST_REQUEST_CODE + 1,
                Activity.RESULT_CANCELED, null);

        assertFalse(result);
        verifyZeroInteractions(mockCallback);
    }

    public void testHandleOnActivityResult_nullCallback() {
        final AuthHandler authHandler = newAuthHandler(null);
        final boolean result = authHandler.handleOnActivityResult(TEST_REQUEST_CODE,
                Activity.RESULT_OK, null);

        assertTrue(result);
    }

    private AuthHandler newAuthHandler(Callback<TwitterSession> callback) {
        return new AuthHandler(AUTH_CONFIG, callback, TEST_REQUEST_CODE) {
            @Override
            public boolean authorize(Activity activity) {
                return true;
            }
        };
    }
}

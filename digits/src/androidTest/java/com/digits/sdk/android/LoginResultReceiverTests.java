package com.digits.sdk.android;

import android.os.Bundle;

import com.twitter.sdk.android.core.SessionManager;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;

import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginResultReceiverTests extends DigitsAndroidTestCase {
    private static final String ERROR = "Big Error on login";
    static final String PHONE = "+17071234567";
    private AuthCallback callback;
    private Bundle bundle;

    private ArgumentCaptor<DigitsException> digitsErrorCaptor;
    private TwitterCore kit;
    private ArgumentCaptor<DigitsSession> sessionCaptor;
    private SessionManager<DigitsSession> mockSessionManager;
    private DigitsSession session;
    private LoginResultReceiver receiver;


    @Override
    public void setUp() throws Exception {
        super.setUp();
        session = new DigitsSession(new TwitterAuthToken(TOKEN, SECRET),
                USER_ID);
        mockSessionManager = mock(SessionManager.class);
        when(mockSessionManager.getActiveSession()).thenReturn(session);
        callback = mock(AuthCallback.class);
        bundle = new Bundle();
        bundle.putString(LoginResultReceiver.KEY_ERROR, ERROR);
        digitsErrorCaptor = ArgumentCaptor.forClass(DigitsException.class);
        sessionCaptor = ArgumentCaptor.forClass(DigitsSession.class);
    }

    public void testOnReceiveResult_nullListener() throws Exception {
        final LoginResultReceiver receiver = new LoginResultReceiver(null, mockSessionManager);
        receiver.onReceiveResult(LoginResultReceiver.RESULT_OK, bundle);
        receiver.onReceiveResult(LoginResultReceiver.RESULT_ERROR, bundle);
    }

    public void testOnReceiveResult_errorResultCode() throws Exception {
        receiver = new LoginResultReceiver(callback, mockSessionManager);
        receiver.onReceiveResult(LoginResultReceiver.RESULT_ERROR, bundle);
        Mockito.verify(callback).failure(digitsErrorCaptor.capture());
        digitsErrorCaptor.getValue().getMessage().equals(ERROR);
    }

    public void testOnReceiveResult_successResultCode() throws Exception {
        receiver = new LoginResultReceiver(callback, mockSessionManager);
        bundle.putString(DigitsClient.EXTRA_PHONE, PHONE);
        receiver.onReceiveResult(LoginResultReceiver.RESULT_OK, bundle);
        Mockito.verify(callback).success(sessionCaptor.capture(), eq(PHONE));
        assertEquals(session, sessionCaptor.getValue());
    }

    public void testOnReceiveResult_randomResultCode() throws Exception {
        receiver = new LoginResultReceiver(callback, mockSessionManager);
        receiver.onReceiveResult(-1, bundle);
        verifyNoInteractions(callback);
    }
}


package com.twitter.sdk.android.core;

import com.twitter.sdk.android.core.internal.oauth.OAuth2Service;
import com.twitter.sdk.android.core.internal.oauth.OAuth2Token;

/**
 * Client for requesting guest auth.
 */
class GuestAuthClient {
    private final OAuth2Service service;

    /**
     * Constructor.
     * @param service a OAuth2Service instance for obtaining guest or app auth
     * @throws java.lang.IllegalArgumentException if service is null
     */
    GuestAuthClient(OAuth2Service service) {
        if (service == null) {
            throw new IllegalArgumentException("OAuth2Service must not be null");
        }
        this.service = service;
    }

    /**
     * Request guest authentication token be set on the app session manager via the OAuth2Service.
     * @param callback callback receiving an AppSession on success
     * @throws java.lang.IllegalArgumentException if appSessionManager is null
     */
    void authorize(SessionManager<AppSession> appSessionManager, Callback<AppSession> callback) {
        if (appSessionManager == null) {
            throw new IllegalArgumentException("SessionManager must not be null");
        }
        service.requestGuestOrAppAuthToken(new CallbackWrapper(appSessionManager, callback));
    }

    /**
     * Callback to OAuth2Service wrapping a developer's logInGuest callback
     */
    class CallbackWrapper extends Callback<OAuth2Token> {
        private final SessionManager<AppSession> appSessionManager;
        private final Callback<AppSession> callback;

        CallbackWrapper(SessionManager<AppSession> appSessionManager,
                Callback<AppSession> callback) {
            this.appSessionManager = appSessionManager;
            this.callback = callback;
        }

        @Override
        public void success(Result<OAuth2Token> result) {
            final AppSession session = new AppSession(result.data);
            // set session in manager, manager makes session active if there is no active session
            appSessionManager.setSession(session.getId(), session);
            if (callback != null) {
                callback.success(new Result<>(session, result.response));
            }
        }

        @Override
        public void failure(TwitterException exception) {
            if (callback != null) {
                callback.failure(exception);
            }
        }
    }
}

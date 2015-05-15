package com.twitter.sdk.android.core.identity;

import android.app.Activity;

import com.twitter.sdk.android.core.TwitterCore;

import java.util.concurrent.atomic.AtomicReference;

import io.fabric.sdk.android.Fabric;

/**
 * The state of an authorization request. This class is thread safe.
 */
class AuthState {

    final AtomicReference<AuthHandler> authHandlerRef = new AtomicReference<>(null);

    public boolean beginAuthorize(Activity activity, AuthHandler authHandler) {
        boolean result = false;
        if (isAuthorizeInProgress()) {
            Fabric.getLogger().w(TwitterCore.TAG, "Authorize already in progress");
        } else if (authHandler.authorize(activity)) {
            result = authHandlerRef.compareAndSet(null, authHandler);
            if (!result) {
                Fabric.getLogger().w(TwitterCore.TAG, "Failed to update authHandler, authorize"
                        + " already in progress.");
            }
        }
        return result;
    }

    public void endAuthorize() {
        authHandlerRef.set(null);
    }

    public boolean isAuthorizeInProgress() {
        return authHandlerRef.get() != null;
    }

    public AuthHandler getAuthHandler() {
        return authHandlerRef.get();
    }
}

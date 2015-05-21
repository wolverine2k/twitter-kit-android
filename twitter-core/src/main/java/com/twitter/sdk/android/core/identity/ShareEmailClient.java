package com.twitter.sdk.android.core.identity;

import android.app.Activity;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.User;

import retrofit.http.GET;
import retrofit.http.Query;

class ShareEmailClient extends TwitterApiClient {
    static final int RESULT_CODE_CANCELED = Activity.RESULT_CANCELED;
    static final int RESULT_CODE_OK = Activity.RESULT_OK;
    static final int RESULT_CODE_ERROR = Activity.RESULT_FIRST_USER;

    static final String RESULT_DATA_EMAIL = "email";
    static final String RESULT_DATA_MSG = "msg";
    static final String RESULT_DATA_ERROR = "error";

    ShareEmailClient(TwitterSession session) {
        super(session);
    }

    /**
     * Gets the user's email address from the Twitter API service.
     *
     * @param callback
     */
    protected void getEmail(Callback<User> callback) {
        getService(EmailService.class).verifyCredentials(true, true, callback);
    }

    // TODO: Consider removing and adding to AccountService
    interface EmailService {
        @GET("/1.1/account/verify_credentials.json?include_email=true")
        void verifyCredentials(
                @Query("include_entities") Boolean includeEntities,
                @Query("skip_status") Boolean skipStatus,
                Callback<User> cb);
    }
}

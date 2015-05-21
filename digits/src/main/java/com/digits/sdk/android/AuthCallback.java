package com.digits.sdk.android;

/**
 * Callback interface with methods for success and error.
 * Only one method will be invoked.
 */
public interface AuthCallback {
    /**
     * Successful authentication
     *
     * @param session the active session
     * @param phoneNumber the phone number as entered by the user. May be null.
     */
    void success(DigitsSession session, String phoneNumber);

    /**
     * Unsuccessful authentication
     *
     * @param error exception with an error message
     */
    void failure(DigitsException error);

}

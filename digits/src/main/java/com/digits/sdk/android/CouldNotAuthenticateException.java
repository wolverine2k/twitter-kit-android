package com.digits.sdk.android;

/**
 * Exception for the phone numbers already registered on digits
 */
class CouldNotAuthenticateException extends DigitsException {
    public CouldNotAuthenticateException(String message) {
        super(message);
    }

    public CouldNotAuthenticateException(String message, int errorCode) {
        super(message, errorCode);
    }
}

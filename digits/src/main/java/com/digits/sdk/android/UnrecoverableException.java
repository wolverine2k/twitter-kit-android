package com.digits.sdk.android;

/**
 * Exception for fatal failures on the digits login flow.
 */
public class UnrecoverableException extends DigitsException {
    public UnrecoverableException(String message) {
        super(message);
    }

    public UnrecoverableException(String message, int errorCode) {
        super(message, errorCode);
    }
}

package com.digits.sdk.android;

import com.twitter.sdk.android.core.TwitterApiErrorConstants;
import com.twitter.sdk.android.core.TwitterApiException;
import com.twitter.sdk.android.core.TwitterException;

import retrofit.RetrofitError;

/**
 * Indicates that authentication was unable to complete successfully
 */
public class DigitsException extends RuntimeException {
    private final int errorCode;

    DigitsException(String message) {
        this(message, TwitterApiErrorConstants.UNKNOWN_ERROR);
    }

    DigitsException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    static DigitsException create(ErrorCodes errors, TwitterException exception) {
        String message;
        if (exception instanceof TwitterApiException) {
            final TwitterApiException apiException = (TwitterApiException) exception;
            message = getMessageForApiError(errors, apiException);
            return createException(apiException.getErrorCode(), message);
        } else {
            message = errors.getDefaultMessage();
            return new DigitsException(message);
        }
    }

    private static DigitsException createException(int error, String message) {
        if (error == TwitterApiErrorConstants.COULD_NOT_AUTHENTICATE) {
            return new CouldNotAuthenticateException(message, error);
        } else if (isUnrecoverable(error)) {
            return new UnrecoverableException(message, error);
        } else {
            return new DigitsException(message, error);
        }
    }

    private static boolean isUnrecoverable(int error) {
        return error == TwitterApiErrorConstants.OPERATOR_UNSUPPORTED ||
                error == TwitterApiErrorConstants.USER_IS_NOT_SDK_USER ||
                error == TwitterApiErrorConstants.EXPIRED_LOGIN_VERIFICATION_REQUEST ||
                error == TwitterApiErrorConstants.MISSING_LOGIN_VERIFICATION_REQUEST ||
                error == TwitterApiErrorConstants.DEVICE_REGISTRATION_RATE_EXCEEDED ||
                error == TwitterApiErrorConstants.REGISTRATION_GENERAL_ERROR;
    }

    private static String getMessageForApiError(ErrorCodes errors,
                                                TwitterApiException apiException) {
        String errorCodeMessage;
        final RetrofitError error = apiException.getRetrofitError();
        if (error.isNetworkError()) {
            errorCodeMessage = errors.getNetworkError();
        } else {
            errorCodeMessage = errors.getMessage(apiException.getErrorCode());
        }
        return errorCodeMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }
}

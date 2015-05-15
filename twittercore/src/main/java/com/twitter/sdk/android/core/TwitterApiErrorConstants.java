package com.twitter.sdk.android.core;

/**
 * Error codes that may be returned from the API. For complete list of error codes, see
 * https://dev.twitter.com/overview/api/response-codes
 */
public class TwitterApiErrorConstants {
    //phone normalization errors
    public static final int DEVICE_REGISTRATION_INVALID_INPUT = 44;
    public static final int REGISTRATION_INVALID_INPUT = 300;
    public static final int REGISTRATION_PHONE_NORMALIZATION_FAILED = 303;
    //device already registered by other user
    public static final int DEVICE_ALREADY_REGISTERED = 285;
    //rate limit for sms exceeded
    public static final int RATE_LIMIT_EXCEEDED = 88;
    //registration general error
    public static final int REGISTRATION_GENERAL_ERROR = 284;
    public static final int REGISTRATION_OPERATION_FAILED = 302;
    //spammer phone number
    public static final int SPAMMER = 240;

    public static final int COULD_NOT_AUTHENTICATE = 32;
    public static final int CLIENT_NOT_PRIVILEGED = 87;
    public static final int UNKNOWN_ERROR = -1;

    //Unrecoverable errors
    public static final int OPERATOR_UNSUPPORTED = 286;
    public static final int USER_IS_NOT_SDK_USER = 269;
    public static final int EXPIRED_LOGIN_VERIFICATION_REQUEST = 235;
    public static final int MISSING_LOGIN_VERIFICATION_REQUEST = 237;
    public static final int DEVICE_REGISTRATION_RATE_EXCEEDED = 299;
}

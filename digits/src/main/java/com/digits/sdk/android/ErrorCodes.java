package com.digits.sdk.android;

interface ErrorCodes {
    String getMessage(int code);

    String getDefaultMessage();

    String getNetworkError();
}

package com.digits.sdk.android;


import android.content.res.Resources;
import android.util.SparseIntArray;

import com.twitter.sdk.android.core.TwitterApiErrorConstants;

/**
 * More error information go/daasErrors
 */
class DigitsErrorCodes implements ErrorCodes {
    private static final int INITIAL_CAPACITY = 10;
    protected final SparseIntArray codeIdMap = new SparseIntArray(INITIAL_CAPACITY);

    {
        codeIdMap.put(TwitterApiErrorConstants.RATE_LIMIT_EXCEEDED,
                R.string.dgts__confirmation_error_alternative);
        codeIdMap.put(TwitterApiErrorConstants.REGISTRATION_GENERAL_ERROR,
                R.string.dgts__network_error);
        codeIdMap.put(TwitterApiErrorConstants.REGISTRATION_OPERATION_FAILED,
                R.string.dgts__network_error);
        codeIdMap.put(TwitterApiErrorConstants.SPAMMER, R.string.dgts__network_error);
        codeIdMap.put(TwitterApiErrorConstants.CLIENT_NOT_PRIVILEGED, R.string.dgts__network_error);

    }

    private final Resources resources;


    DigitsErrorCodes(Resources resources) {
        this.resources = resources;
    }


    @Override
    public String getMessage(int code) {
        final int id = codeIdMap.get(code, TwitterApiErrorConstants.UNKNOWN_ERROR);
        return id == TwitterApiErrorConstants.UNKNOWN_ERROR ? getDefaultMessage() : resources
                .getString(id);
    }

    @Override
    public String getDefaultMessage() {
        return resources.getString(R.string.dgts__try_again);
    }

    @Override
    public String getNetworkError() {
        return resources.getString(R.string.dgts__network_error);
    }
}

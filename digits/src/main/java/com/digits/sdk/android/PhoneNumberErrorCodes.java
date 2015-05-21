package com.digits.sdk.android;

import android.content.res.Resources;

import com.twitter.sdk.android.core.TwitterApiErrorConstants;

class PhoneNumberErrorCodes extends DigitsErrorCodes {

    PhoneNumberErrorCodes(Resources resources) {
        super(resources);
        codeIdMap.put(TwitterApiErrorConstants.DEVICE_REGISTRATION_INVALID_INPUT,
                R.string.dgts__try_again_phone_number);
        codeIdMap.put(TwitterApiErrorConstants.REGISTRATION_INVALID_INPUT,
                R.string.dgts__try_again_phone_number);
        codeIdMap.put(TwitterApiErrorConstants.REGISTRATION_PHONE_NORMALIZATION_FAILED,
                R.string.dgts__try_again_phone_number);
        codeIdMap.put(TwitterApiErrorConstants.DEVICE_ALREADY_REGISTERED,
                R.string.dgts__confirmation_error_alternative);
        codeIdMap.put(TwitterApiErrorConstants.OPERATOR_UNSUPPORTED,
                R.string.dgts__confirmation_error_alternative);
    }
}

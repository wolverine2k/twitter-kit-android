package com.digits.sdk.android;

import android.content.res.Resources;

import com.twitter.sdk.android.core.TwitterApiErrorConstants;

class ConfirmationErrorCodes extends DigitsErrorCodes {


    ConfirmationErrorCodes(Resources resources) {
        super(resources);
        codeIdMap.put(TwitterApiErrorConstants.DEVICE_REGISTRATION_INVALID_INPUT,
                R.string.dgts__try_again_confirmation);
    }
}

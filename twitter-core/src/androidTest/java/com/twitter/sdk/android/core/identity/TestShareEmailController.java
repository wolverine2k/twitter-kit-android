package com.twitter.sdk.android.core.identity;

import android.os.ResultReceiver;

public class TestShareEmailController extends ShareEmailController {

    public TestShareEmailController(ShareEmailClient emailClient, ResultReceiver resultReceiver) {
        super(emailClient, resultReceiver);
    }
}

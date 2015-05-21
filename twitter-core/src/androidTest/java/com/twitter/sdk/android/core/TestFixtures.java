package com.twitter.sdk.android.core;

import com.twitter.sdk.android.core.internal.TwitterApiConstants;
import com.twitter.sdk.android.core.models.ApiError;

public final class TestFixtures {

    public static final ApiError TEST_APP_AUTH_ERROR = new ApiError("app auth error",
            TwitterApiConstants.Errors.APP_AUTH_ERROR_CODE);
    public static final ApiError TEST_GUEST_AUTH_ERROR = new ApiError("guest auth error",
            TwitterApiConstants.Errors.GUEST_AUTH_ERROR_CODE);
    public static final ApiError TEST_LEGACY_ERROR = new ApiError("legacy error",
            TwitterApiConstants.Errors.LEGACY_ERROR);
}

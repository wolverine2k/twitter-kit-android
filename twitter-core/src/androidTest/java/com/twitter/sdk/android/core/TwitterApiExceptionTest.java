package com.twitter.sdk.android.core;

import com.twitter.sdk.android.core.models.ApiError;

import java.io.IOException;

public class TwitterApiExceptionTest extends TwitterAndroidTestCase {

    private static final int API_ERROR_CODE = 239;
    private static final int DEFAULT_ERROR_CODE = 0;
    private static final String API_ERROR_MESSAGE = "Bad guest token";
    private static final String API_ERROR_JSON = "{\"errors\":[{\"message\":\"Bad guest token\"," +
            "\"code\":239}]}\n";
    private static final String API_ERROR_NO_ERROR_CODE = "{\"errors\":[{\"message\":\"Bad " +
            "guest token\"}]}\n";
    private static final String API_ERROR_NO_ERROR_MESSAGE = "{\"errors\":[{\"code\":239}]}\n";
    private static final String API_ERROR_NON_JSON = "not a json";

    public void testParseErrorCode() throws IOException {
        final ApiError apiError = TwitterApiException.parseApiError(API_ERROR_JSON);
        assertEquals(API_ERROR_CODE, apiError.getCode());
        assertEquals(API_ERROR_MESSAGE, apiError.getMessage());
    }

    public void testParseError_null() throws Exception {
        assertNull(TwitterApiException.parseApiError(null));
    }

    public void testParseError_nonJSON() throws Exception {
        assertNull(TwitterApiException.parseApiError(API_ERROR_NON_JSON));
    }

    public void testParseError_noErrorCode() throws Exception {
        final ApiError apiError = TwitterApiException.parseApiError(API_ERROR_NO_ERROR_CODE);
        assertEquals(DEFAULT_ERROR_CODE, apiError.getCode());
        assertEquals(API_ERROR_MESSAGE, apiError.getMessage());
    }

    public void testParseError_noMessage() throws Exception {
        final ApiError apiError = TwitterApiException.parseApiError(API_ERROR_NO_ERROR_MESSAGE);
        assertEquals(API_ERROR_CODE, apiError.getCode());
        assertEquals(null, apiError.getMessage());
    }

}

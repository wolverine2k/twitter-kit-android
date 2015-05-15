package com.twitter.sdk.android.core.identity;

/**
 * Exception thrown when a WebDialog error occurs.
 */
class WebViewException extends Exception {
    private static final long serialVersionUID = -7397331487240298819L;

    private final int errorCode;
    private final String failingUrl;

    public WebViewException(int errorCode, String description, String failingUrl) {
        super(description);
        this.errorCode = errorCode;
        this.failingUrl = failingUrl;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getDescription() {
        return getMessage();
    }

    public String getFailingUrl() {
        return failingUrl;
    }
}

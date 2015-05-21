package com.twitter.sdk.android.core.internal.oauth;

import com.twitter.sdk.android.core.DefaultClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.internal.TwitterApi;

import javax.net.ssl.SSLSocketFactory;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * Base class for OAuth service.
 */
abstract class OAuthService {

    // TODO: REVISIT CLIENT_NAME
    private static final String CLIENT_NAME = "TwitterAndroidSDK";

    private final TwitterCore twitterCore;
    private final SSLSocketFactory sslSocketFactory;
    private final TwitterApi api;
    private final String userAgent;
    private final RestAdapter apiAdapter;

    public OAuthService(TwitterCore twitterCore, SSLSocketFactory sslSocketFactory,
            TwitterApi api) {
        this.twitterCore = twitterCore;
        this.sslSocketFactory = sslSocketFactory;
        this.api = api;
        userAgent = TwitterApi.buildUserAgent(CLIENT_NAME, twitterCore.getVersion());


        apiAdapter = new RestAdapter.Builder()
                .setEndpoint(getApi().getBaseHostUrl())
                .setClient(new DefaultClient(this.sslSocketFactory))
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addHeader("User-Agent", getUserAgent());
                    }
                })
                .build();
    }

    protected TwitterCore getTwitterCore() {
        return twitterCore;
    }

    protected SSLSocketFactory getSSLSocketFactory() {
        return sslSocketFactory;
    }

    protected TwitterApi getApi() {
        return api;
    }

    protected String getUserAgent() {
        return userAgent;
    }

    protected RestAdapter getApiAdapter() {
        return apiAdapter;
    }
}

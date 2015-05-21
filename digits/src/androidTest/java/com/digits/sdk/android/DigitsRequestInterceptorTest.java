package com.digits.sdk.android;


import io.fabric.sdk.android.FabricAndroidTestCase;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static retrofit.RequestInterceptor.RequestFacade;

public class DigitsRequestInterceptorTest extends FabricAndroidTestCase {
    private static final String ANY_USER_AGENT = "Digits/Test (Android Awesome)";

    public void testIntercept() throws Exception {
        final DigitsUserAgent userAgent = mock(MockDigitsUserAgent.class);
        final DigitsRequestInterceptor interceptor = new DigitsRequestInterceptor(userAgent);
        final RequestFacade facade = mock(RequestFacade.class);

        when(userAgent.toString()).thenReturn(ANY_USER_AGENT);

        interceptor.intercept(facade);

        verify(facade).addHeader(DigitsRequestInterceptor.USER_AGENT_KEY, ANY_USER_AGENT);
    }


}

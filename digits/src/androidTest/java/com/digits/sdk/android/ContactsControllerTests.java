package com.digits.sdk.android;

import android.content.ComponentName;
import android.content.Intent;
import android.test.mock.MockContext;

import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ContactsControllerTests extends DigitsAndroidTestCase {
    private ContactsController controller;
    private MockContext context;
    private ArgumentCaptor<Intent> intentCaptor;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        controller = new ContactsControllerImpl();
        context = mock(MockContext.class);
        intentCaptor = ArgumentCaptor.forClass(Intent.class);

        when(context.getPackageName()).thenReturn(ContactsUploadService.class.getPackage()
                .toString());
    }

    public void testStartUploadService() {
        controller.startUploadService(context);

        verify(context).startService(intentCaptor.capture());
        final Intent intent = intentCaptor.getValue();

        final ComponentName component = new ComponentName(context, ContactsUploadService.class
                .getName());
        assertEquals(component, intent.getComponent());
    }
}

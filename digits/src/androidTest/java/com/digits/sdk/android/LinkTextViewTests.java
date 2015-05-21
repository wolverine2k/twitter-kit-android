package com.digits.sdk.android;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.ContextThemeWrapper;

import com.digits.sdk.android.test.R;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;


public class LinkTextViewTests extends DigitsAndroidTestCase {

    public void testLinkTextView_themeLinkColor() throws Exception {
        final Context context = new ContextThemeWrapper(getContext(),
                R.style.Digits_default_link_color);
        final LinkTextView textView = new LinkTextView(context);
        assertEquals((getContext().getResources().getColor(R.color.link_text)),
                textView.getCurrentTextColor());
    }

    public void testLinkTextView_defaultAccent() throws Exception {
        final Context context = spy(new ContextWrapper(getContext()));
        when(context.getTheme()).thenReturn(getContext().getResources().newTheme());
        final LinkTextView textView = new LinkTextView(context);
        assertEquals(getContext().getResources().getColor(R.color
                .dgts__default_accent), textView.getCurrentTextColor());
    }

    public void testLinkTextView_customAccent() throws Exception {
        final Context context = new ContextThemeWrapper(getContext(),
                R.style.Digits_default_accent);
        final LinkTextView textView = new LinkTextView(context);
        assertEquals(getContext().getResources().getColor(R.color
                .accent), textView.getCurrentTextColor());
    }

}

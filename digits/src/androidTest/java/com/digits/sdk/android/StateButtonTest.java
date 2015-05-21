package com.digits.sdk.android;

import android.content.res.TypedArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StateButtonTest extends DigitsAndroidTestCase {
    private static final String SEND_TEXT = "Send confirmation code";
    private static final String PROGRESS_TEXT = "Sending confirmation code";
    private static final String END_TEXT = "Sent confirmation code";

    private StateButton button;
    private TextView textView;
    private ProgressBar progressBar;
    private ImageView imageView;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        button = new StateButton(getContext());
        final TypedArray array = mock(TypedArray.class);

        when(array.getText(R.styleable.StateButton_startStateText)).thenReturn(SEND_TEXT);
        when(array.getText(R.styleable.StateButton_progressStateText)).thenReturn
                (PROGRESS_TEXT);
        when(array.getText(R.styleable.StateButton_finishStateText)).thenReturn
                (END_TEXT);

        button.init(array);
        array.recycle();

        textView = (TextView) button.findViewById(R.id.dgts__state_button);
        progressBar = (ProgressBar) button.findViewById(R.id.dgts__state_progress);
        imageView = (ImageView) button.findViewById(R.id.dgts__state_success);
    }

    public void testShowStart() throws Exception {
        assertEquals(SEND_TEXT, textView.getText());
        assertEquals(View.GONE, progressBar.getVisibility());
        assertEquals(View.GONE, imageView.getVisibility());
        assertTrue(progressBar.isIndeterminate());
    }

    public void testShowProgress() throws Exception {
        button.showProgress();
        assertEquals(PROGRESS_TEXT, textView.getText());
        assertEquals(View.VISIBLE, progressBar.getVisibility());
        assertEquals(View.GONE, imageView.getVisibility());
        assertTrue(progressBar.isIndeterminate());
    }

    public void testShowFinish() throws Exception {
        button.showFinish();
        assertEquals(END_TEXT, textView.getText());
        assertEquals(View.GONE, progressBar.getVisibility());
        assertEquals(View.VISIBLE, imageView.getVisibility());
        assertTrue(progressBar.isIndeterminate());
    }

    public void testShowError() throws Exception {
        button.showError();
        testShowStart();
    }

}

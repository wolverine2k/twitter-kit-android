package com.digits.sdk.android;

import android.view.View;

import io.fabric.sdk.android.FabricTestUtils;

import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;

import java.util.Locale;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CountryListSpinnerTests extends DigitsAndroidTestCase {
    static final int US_COUNTRY_CODE = 1;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        FabricTestUtils.resetFabric();
        FabricTestUtils.with(getContext(), new TwitterCore(new TwitterAuthConfig("", "")),
                new Digits());
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        FabricTestUtils.resetFabric();
    }

    public void testConstructor_oneParam() {
        final CountryListSpinner spinner = new CountryListSpinner(getContext());

        verifyDefaultSpinnerText(spinner);
    }

    public void testConstructor_twoParam() {
        final CountryListSpinner spinner = new CountryListSpinner(getContext(), null);

        verifyDefaultSpinnerText(spinner);
    }

    public void testConstructor_threeParam() {
        final CountryListSpinner spinner = new CountryListSpinner(getContext(), null,
                android.R.attr.spinnerStyle);

        verifyDefaultSpinnerText(spinner);
    }

    void verifyDefaultSpinnerText(CountryListSpinner spinner) {
        final String spinnerFormat = getContext().getResources().getString(R.string
                .dgts__country_spinner_format);
        final String usTest = Locale.US.getDisplayCountry();
        final String countryInfoText = String.format(spinnerFormat, usTest, US_COUNTRY_CODE);

        assertEquals(countryInfoText, spinner.getText());
        assertEquals(US_COUNTRY_CODE, spinner.getTag());
    }

    public void testSetOnClickListener() {
        final CountryListSpinner.DialogPopup dialog = mock(CountryListSpinner.DialogPopup.class);
        final CountryListSpinner spinner = new CountryListSpinner(getContext());
        spinner.setDialogPopup(dialog);
        final View.OnClickListener listener = mock(View.OnClickListener.class);
        spinner.setOnClickListener(listener);
        spinner.performClick();
        verify(listener).onClick(any(View.class));
    }
}

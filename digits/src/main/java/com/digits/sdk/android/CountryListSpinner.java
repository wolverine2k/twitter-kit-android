package com.digits.sdk.android;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import io.fabric.sdk.android.services.common.CommonUtils;

import java.util.List;
import java.util.Locale;

public class CountryListSpinner extends TextView implements View.OnClickListener,
        CountryListLoadTask.Listener {
    private String textFormat;
    private DialogPopup dialogPopup;
    private CountryListAdapter countryListAdapter;
    private OnClickListener listener;
    private String selectedCountryName;

    public CountryListSpinner(Context context) {
        this(context, null, android.R.attr.spinnerStyle);
    }

    public CountryListSpinner(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.spinnerStyle);
    }

    public CountryListSpinner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        init();
    }

    /**
     * Only for Testing
     */
    void setDialogPopup(DialogPopup dialog) {
        this.dialogPopup = dialog;
    }

    private void init() {
        super.setOnClickListener(this);

        countryListAdapter = new CountryListAdapter(getContext());
        dialogPopup = new DialogPopup(countryListAdapter);
        textFormat = getResources().getString(R.string.dgts__country_spinner_format);
        selectedCountryName = "";
        // TODO (efrohnhoefer) determine country based on network, sim, or Locale.
        final String defaultCountry = Locale.US.getDisplayCountry();
        final int defaultCountryCode = 1;

        setSpinnerText(defaultCountryCode, defaultCountry);
    }

    private void setSpinnerText(int countryCode, String country) {
        setText(String.format(textFormat, country, countryCode));
        setTag(countryCode);
    }

    public void setSelectedForCountry(final String countryName, String countryCode) {
        if (!TextUtils.isEmpty(countryName) && !TextUtils.isEmpty(countryCode)) {
            selectedCountryName = countryName;
            setSpinnerText(Integer.valueOf(countryCode), countryName);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        if (dialogPopup.isShowing()) {
            dialogPopup.dismiss();
        }
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        listener = l;
    }

    // TODO (efrohnhoefer) test later using Robolectric?
    @Override
    public void onClick(View view) {
        if (countryListAdapter.getCount() == 0) {
            loadCountryList();
        } else {
            dialogPopup.show(countryListAdapter.getPositionForCountry(selectedCountryName));
        }
        CommonUtils.hideKeyboard(getContext(), CountryListSpinner.this);
        executeUserClickListener(view);
    }

    private void loadCountryList() {
        // Create the list adapter only once
        new CountryListLoadTask(this).executeOnExecutor(Digits.getInstance()
                .getExecutorService());
    }

    private void executeUserClickListener(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }

    // TODO (efrohnhoefer) test later using Robolectric?
    @Override
    public void onLoadComplete(List<CountryInfo> result) {
        countryListAdapter.setData(result);
        dialogPopup.show(countryListAdapter.getPositionForCountry(selectedCountryName));
    }

    public class DialogPopup implements DialogInterface.OnClickListener {
        //Delay for postDelayed to set selection without showing the scroll animation
        private static final long DELAY_MILLIS = 10L;
        private final CountryListAdapter listAdapter;
        private AlertDialog dialog;

        DialogPopup(CountryListAdapter adapter) {
            listAdapter = adapter;
        }

        public void dismiss() {
            if (dialog != null) {
                dialog.dismiss();
                dialog = null;
            }
        }

        public boolean isShowing() {
            return dialog != null ? dialog.isShowing() : false;
        }

        public void show(final int selected) {
            if (listAdapter == null) {
                return;
            }

            final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            dialog = builder.setSingleChoiceItems(listAdapter, 0, this).create();
            dialog.setCanceledOnTouchOutside(true);
            final ListView listView = dialog.getListView();
            listView.setFastScrollEnabled(true);
            listView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    listView.setSelection(selected);
                }
            }, DELAY_MILLIS);
            dialog.show();
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            final CountryInfo countryInfo = listAdapter.getItem(which);
            selectedCountryName = countryInfo.country;
            setSpinnerText(countryInfo.countryCode, countryInfo.country);
            dismiss();
        }
    }
}

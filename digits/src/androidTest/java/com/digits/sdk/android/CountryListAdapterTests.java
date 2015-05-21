package com.digits.sdk.android;

import java.util.ArrayList;

public class CountryListAdapterTests extends DigitsAndroidTestCase {
    private CountryListAdapter countryListAdapter;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        final ArrayList<CountryInfo> countries = new ArrayList<>();
        countries.add(new CountryInfo("Germany", 1));
        countries.add(new CountryInfo("Saoma", 2));
        countries.add(new CountryInfo("spain", 3));
        countries.add(new CountryInfo("United States", 4));

        countryListAdapter = new CountryListAdapter(getContext());
        countryListAdapter.setData(countries);
    }

    public void testGetSections() {
        assertEquals(3, countryListAdapter.getSections().length);
        assertEquals("G", countryListAdapter.getSections()[0]);
        assertEquals("S", countryListAdapter.getSections()[1]);
        assertEquals("U", countryListAdapter.getSections()[2]);
    }

    public void testGetPositionForSection() {
        assertEquals(0, countryListAdapter.getPositionForSection(-1));
        assertEquals(0, countryListAdapter.getPositionForSection(0));
        assertEquals(1, countryListAdapter.getPositionForSection(1));
        assertEquals(3, countryListAdapter.getPositionForSection(2));
        assertEquals(3, countryListAdapter.getPositionForSection(3));
    }
}

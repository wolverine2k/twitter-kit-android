package com.digits.sdk.android;

import java.text.Collator;
import java.util.Locale;

class CountryInfo implements Comparable<CountryInfo> {
    private final Collator collator;
    public final String country;
    public final int countryCode;

    public CountryInfo(String country, int countryCode) {
        collator = Collator.getInstance(Locale.getDefault());
        collator.setStrength(Collator.PRIMARY);

        this.country = country;
        this.countryCode = countryCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        final CountryInfo that = (CountryInfo) obj;

        if (countryCode != that.countryCode) {
            return false;
        }
        if (country != null ? !country.equals(that.country) : that.country != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = country != null ? country.hashCode() : 0;
        result = 31 * result + countryCode;
        return result;
    }

    @Override
    public String toString() {
        return country + " +" + countryCode;
    }

    @Override
    public int compareTo(CountryInfo info) {
        return collator.compare(country, info.country);
    }
}

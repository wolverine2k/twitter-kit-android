package com.twitter.sdk.android.core.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents coordinates of a Tweet's location.
 */
public class Coordinates {

    public static final int INDEX_LONGITUDE = 0;
    public static final int INDEX_LATITUDE = 1;

    /**
     * The longitude and latitude of the Tweet’s location, as an collection in the form of
     * [longitude, latitude].
     */
    @SerializedName("coordinates")
    public final List<Double> coordinates;

    /**
     * The type of data encoded in the coordinates property. This will be “Point” for Tweet
     * coordinates fields.
     */
    @SerializedName("type")
    public final String type;

    public Coordinates(Double longitude, Double latitude, String type) {
        final List<Double> coords = new ArrayList<Double>(2);
        coords.add(INDEX_LONGITUDE, longitude);
        coords.add(INDEX_LATITUDE, latitude);

        this.coordinates = coords;
        this.type = type;
    }

    public Double getLongitude() {
        return coordinates.get(INDEX_LONGITUDE);
    }

    public Double getLatitude() {
        return coordinates.get(INDEX_LATITUDE);
    }
}

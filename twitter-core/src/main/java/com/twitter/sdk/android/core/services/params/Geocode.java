package com.twitter.sdk.android.core.services.params;

/**
 *
 * The parameter value is specified by "latitude,longitude,radius", where radius units must be
 * specified as either "mi" (miles) or "km" (kilometers). Note that you cannot use the near
 * operator via the API to geocode arbitrary locations; however you can use this geocode parameter
 * to search near geocodes directly.
 *
 * Example Values: 37.781157,-122.398720,1mi
 *
 * <a href="https://dev.twitter.com/docs/api/1.1/get/search">GET Search</a>
 */
public class Geocode {

    public enum Distance {
        MILES("mi"),
        KILOMETERS("km");

        public final String identifier;

        Distance(String identifier) {
            this.identifier = identifier;
        }
    }

    public final double latitude;
    public final double longitude;
    public final int radius;
    public final Distance distance;

    public Geocode(double latitude, double longitude, int radius, Distance distance) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return latitude + "," + longitude + "," + radius + distance.identifier;
    }
}

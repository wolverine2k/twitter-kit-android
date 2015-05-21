package com.twitter.sdk.android.core.models;

/**
 * Identifiable is an abstraction of types with long identifiers.
 */
public interface Identifiable {

    /**
     * Returns the id.
     * @return The id.
     */
    long getId();
}

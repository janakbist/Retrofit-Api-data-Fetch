package com.applovin.sdk;

/**
 * This interface represents an ad that has been served by the AppLovin server and should be displayed to the user.
 *
 * @author Basil Shikin, Thomas So
 */

public interface AppLovinAd
{
    /**
     * Represents a unique ID for the current ad. Please include this if you report a broken/bad ad to AppLovin Support.
     *
     * @return A unique identifier of the ad.
     */
    long getAdIdNumber();

    /**
     * Get size of the ad to display.
     *
     * @return Ad size
     */
    AppLovinAdSize getSize();

    /**
     * The zone identifier for the ad, if any.
     *
     * @return zone identifier of this ad, If any.
     */
    String getZoneId();

    /**
     * Check whether this is a video ad. If so, upon display, a new video player activity will be launched.
     *
     * @return True if a video ad. False otherwise.
     */
    boolean isVideoAd();

    /**
     * Get the ad type of the current ad.
     *
     * @return Ad type
     */
    AppLovinAdType getType();

    /**
     * Get an arbitrary ad value for a given key.
     *
     * @param key The designated key to retrieve desired value for.
     *
     * @return An arbitrary ad value for a given key - or null if does not exist.
     */
    String getAdValue(final String key);

    /**
     * Get an arbitrary ad value for a given key.
     *
     * @param key          The designated key to retrieve desired value for.
     * @param defaultValue The default value to return if the desired value for does not exist or is null.
     *
     * @return An arbitrary ad value for a given key - or the default value if does not exist.
     */
    String getAdValue(final String key, final String defaultValue);
}

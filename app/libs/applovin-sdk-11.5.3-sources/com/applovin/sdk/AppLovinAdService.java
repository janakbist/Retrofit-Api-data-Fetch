package com.applovin.sdk;


import java.util.List;

/**
 * This class is responsible for loading and displaying advertisements.
 * <p>
 * An instance of this class can be obtained from an {@link AppLovinSdk} instance via <code>getAdService()</code>.
 *
 * @author Basil Shikin
 */
public interface AppLovinAdService
{
    /**
     * Fetch a new ad, of a given size, notifying a supplied listener on completion.
     *
     * @param adSize   Size of an ad to load. Must not be null.
     * @param callback A callback to notify of the fact that the ad is loaded. Must not be null. A reference to the callback will be persisted until the ad is loaded.
     */
    void loadNextAd(final AppLovinAdSize adSize, final AppLovinAdLoadListener callback);

    /**
     * Fetch a new ad, for a given zone, notifying a supplied listener on completion.
     *
     * @param zoneId   The zone to load an ad for. Must not be null.
     * @param callback A callback to notify of the fact that the ad is loaded.
     */
    void loadNextAdForZoneId(final String zoneId, final AppLovinAdLoadListener callback);

    /**
     * Generates a token used for advanced header bidding. Must be ran on a background thread.
     */
    String getBidToken();

    /**
     * Fetch a new ad for the given ad token. Provided ad token must be received from AppLovin S2S API.
     * <p>
     * <b>Please note:</b> this method is designed to be called by SDK mediation providers. Please use
     * {@link AppLovinAdService#loadNextAdForZoneId(String, AppLovinAdLoadListener)} for regular integrations.
     *
     * @param adToken  Ad token returned from AppLovin S2S API. Must not be null.
     * @param callback A callback to notify that the ad has been loaded. Must not be null.
     */
    void loadNextAdForAdToken(final String adToken, final AppLovinAdLoadListener callback);

    /**
     * Fetch a new ad for any of the provided zone ids.
     * <p>
     * <b>Please note:</b> this method is designed to be called by SDK mediation providers. Please use
     * {@link AppLovinAdService#loadNextAdForZoneId(String, AppLovinAdLoadListener)} for regular integrations.
     *
     * @param zoneIds  A list of zone identifiers for which an ad should be loaded. Must not be null.
     * @param callback A callback to notify that the ad has been loaded. Must not be null.
     */
    void loadNextAdForZoneIds(final List<String> zoneIds, final AppLovinAdLoadListener callback);
}

package com.applovin.adview;

import com.applovin.sdk.AppLovinAd;

/**
 * This interface defines a listener for ad view events.
 * <p>
 * Created by thomasso on 6/25/17.
 * <p>
 */
public interface AppLovinAdViewEventListener
{
    /**
     * This method is invoked after the ad view presents fullscreen content.
     * <p>
     * This method is invoked on the main UI thread.
     *
     * @param ad     Ad that the ad view presented fullscreen content for.
     * @param adView Ad view that presented fullscreen content.
     */
    void adOpenedFullscreen(final AppLovinAd ad, final AppLovinAdView adView);

    /**
     * This method is invoked after the fullscreen content is dismissed.
     * <p>
     * This method is invoked on the main UI thread.
     *
     * @param ad     Ad for which the fullscreen content is dismissed for.
     * @param adView Ad view for which the fullscreen content it presented is dismissed for.
     */
    void adClosedFullscreen(final AppLovinAd ad, final AppLovinAdView adView);

    /**
     * This method is invoked before the user is taken out of the application after a click.
     * <p>
     * This method is invoked on the main UI thread.
     *
     * @param ad     Ad for which the user will be taken out of the application for.
     * @param adView Ad view containing the ad for which the user will be taken out of the application for.
     */
    void adLeftApplication(final AppLovinAd ad, final AppLovinAdView adView);

    /**
     * This method is invoked if the ad view fails to display an ad.
     * <p>
     * This method is invoked on the main UI thread.
     *
     * @param ad     Ad for which the ad view failed to display for.
     * @param adView Ad view which failed to display the ad.
     * @param code   Error code specifying the reason why the ad view failed to display ad.
     */
    void adFailedToDisplay(final AppLovinAd ad, final AppLovinAdView adView, final AppLovinAdViewDisplayErrorCode code);
}

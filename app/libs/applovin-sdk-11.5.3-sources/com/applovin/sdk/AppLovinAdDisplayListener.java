package com.applovin.sdk;


/**
 * This interface defines a listener intended to be notified when an ad is shown or hidden to the user.
 *
 * @author Basil Shikin
 */
public interface AppLovinAdDisplayListener
{
    /**
     * This method is invoked when an ad is displayed inside of the {@link com.applovin.adview.AppLovinAdView} or {@link com.applovin.adview.AppLovinInterstitialAdDialog}.
     * <p>
     * This method is invoked on the main UI thread.
     *
     * @param ad Ad that was just displayed. Guaranteed not to be null.
     */
    void adDisplayed(final AppLovinAd ad);

    /**
     * This method is invoked when an ad is displayed inside of the {@link com.applovin.adview.AppLovinAdView} or {@link com.applovin.adview.AppLovinInterstitialAdDialog}. This occurs when it is explicitly closed (in the case of INTERSTITIALs).
     * <p>
     * This method is invoked on the main UI thread.
     *
     * @param ad Ad that was just hidden. Guaranteed not to be null.
     */
    void adHidden(final AppLovinAd ad);
}

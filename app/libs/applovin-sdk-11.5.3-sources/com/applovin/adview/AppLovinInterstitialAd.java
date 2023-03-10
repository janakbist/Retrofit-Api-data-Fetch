package com.applovin.adview;

import android.content.Context;

import com.applovin.impl.adview.InterstitialAdDialogWrapper;
import com.applovin.sdk.AppLovinSdk;

/**
 * This class represents an interstitial ad that is rendered on top of the current activity.
 *
 * @author Basil Shikin
 */
public class AppLovinInterstitialAd
{
    /**
     * Create a new interstitial dialog that can be shown to the user. This is primarily useful in advanced integrations as the Ad Dialog allows finer control - including manually pre-caching and rendering ads. If all you want to do is show an interstitial the default way, you're looking for
     * <code>show()</code>.
     *
     * @param sdk     An SDK instance to use.
     * @param context A non-stale reference to the current top activity.
     *
     * @return A new instance of {@link com.applovin.adview.AppLovinInterstitialAdDialog}.
     */
    public static AppLovinInterstitialAdDialog create(final AppLovinSdk sdk, final Context context)
    {
        // Check input
        if ( sdk == null ) throw new IllegalArgumentException( "No sdk specified" );
        if ( context == null ) throw new IllegalArgumentException( "No context specified" );

        return new InterstitialAdDialogWrapper( sdk, context );
    }

    @Override
    public String toString()
    {
        return "AppLovinInterstitialAd{}";
    }
}

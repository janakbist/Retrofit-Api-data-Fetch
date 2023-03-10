package com.applovin.adview;

import android.view.ViewGroup;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;

import androidx.lifecycle.Lifecycle;

/**
 * This interface defines an AppLovin interstitial dialog. The interface can be used for finer control of interstitial flow.
 *
 * @author Basil Shikin, Thomas So
 */
public interface AppLovinInterstitialAdDialog
{
    /**
     * Show the interstitial dialog and load the next ad.
     */
    void show();

    /**
     * Show the interstitial dialog and render the specified pre-loaded ad.
     *
     * @param ad AppLovinAd object of size Interstitial to show.
     */
    void showAndRender(final AppLovinAd ad);

    /**
     * Show the interstitial dialog and render the specified pre-loaded ad in a container view.
     *
     * @param ad            AppLovinAd object of size Interstitial to show.
     * @param containerView ViewGroup used to show the ad in.
     * @param lifecycle     Lifecycle object to manage ad lifecycle events in container views.
     */
    void showAndRender(final AppLovinAd ad, final ViewGroup containerView, final Lifecycle lifecycle);

    /**
     * Set a callback that will be notified of ad loading events. These include ad loaded and ad failed to load events.
     *
     * @param callback A callback to be notified of ad loading events.
     */
    void setAdLoadListener(final AppLovinAdLoadListener callback);

    /**
     * Set a callback that will be notified of ad display events. These include ad displayed and ad hidden.
     *
     * @param callback A callback to be notified of ad loading events.
     */
    void setAdDisplayListener(final AppLovinAdDisplayListener callback);

    /**
     * Set a callback that may be notified of video playback events, if and only if the ad contains a video.
     *
     * @param callback A callback to be notified of video events.
     */
    void setAdVideoPlaybackListener(AppLovinAdVideoPlaybackListener callback);

    /**
     * Set a callback that will be notified of ad click events, which are generated whenever the user clicks on the ad.
     *
     * @param callback A callback to be notified of ad loading events.
     */
    void setAdClickListener(final AppLovinAdClickListener callback);
}

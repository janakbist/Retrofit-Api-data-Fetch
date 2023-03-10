package com.applovin.mediation.ads;

import android.app.Activity;
import android.text.TextUtils;
import android.view.ViewGroup;

import com.applovin.impl.mediation.ads.MaxAdImplBase;
import com.applovin.impl.mediation.ads.MaxFullscreenAdImpl;
import com.applovin.mediation.MaxAdFormat;
import com.applovin.mediation.MaxAdRequestListener;
import com.applovin.mediation.MaxAdRevenueListener;
import com.applovin.mediation.MaxAdReviewListener;
import com.applovin.mediation.MaxRewardedAdListener;
import com.applovin.sdk.AppLovinSdk;

import java.lang.ref.WeakReference;

import androidx.lifecycle.Lifecycle;

/**
 * Created by Thomas So on June 01 2020
 * <p>
 * This class represents a fullscreen ad which the user can skip and be granted a reward upon successful completion of the ad.
 */
public class MaxRewardedInterstitialAd
        implements MaxFullscreenAdImpl.ActivityProvider
{
    private static final String TAG = "MaxRewardedInterstitialAd";

    private static WeakReference<Activity> sActivityRef = new WeakReference<>( null );

    private final MaxFullscreenAdImpl mImplementation;

    /**
     * Create a new MAX rewarded interstitial.
     *
     * @param adUnitId Ad unit id to load ads for. Must not be null.
     * @param activity Current activity. Must not be null.
     */
    public MaxRewardedInterstitialAd(final String adUnitId, final Activity activity)
    {
        this( adUnitId, AppLovinSdk.getInstance( activity ), activity );
    }

    /**
     * Create a new MAX rewarded interstitial.
     *
     * @param adUnitId Ad unit id to load ads for. Must not be null.
     * @param sdk      SDK to use. Must not be null. An instance of the SDK may be obtained by calling <code>AppLovinSdk.getInstance()</code>.
     * @param activity Current activity. Must not be null.
     */
    public MaxRewardedInterstitialAd(final String adUnitId, final AppLovinSdk sdk, final Activity activity)
    {
        MaxAdImplBase.logApiCall( TAG, "MaxRewardedInterstitialAd(adUnitId=" + adUnitId + ", sdk=" + sdk + ", activity=" + activity + ")" );

        // Check input
        if ( adUnitId == null ) throw new IllegalArgumentException( "No ad unit ID specified" );
        if ( TextUtils.isEmpty( adUnitId ) ) throw new IllegalArgumentException( "Empty ad unit ID specified" );
        if ( activity == null ) throw new IllegalArgumentException( "No activity specified" );
        if ( sdk == null ) throw new IllegalArgumentException( "No sdk specified" );

        sActivityRef = new WeakReference<>( activity );

        mImplementation = new MaxFullscreenAdImpl( adUnitId, MaxAdFormat.REWARDED_INTERSTITIAL, this, "MaxRewardedInterstitialAd", sdk.coreSdk );
    }

    /**
     * Set a listener that will be notified about ad events.
     *
     * @param listener Listener to be notified. May be null.
     */
    public void setListener(final MaxRewardedAdListener listener)
    {
        mImplementation.logApiCall( "setListener(listener=" + listener + ")" );
        mImplementation.setListener( listener );
    }

    /**
     * Set a listener that will be notified about ad revenue events.
     *
     * @param listener Listener to be notified. May be null.
     */
    public void setRevenueListener(final MaxAdRevenueListener listener)
    {
        mImplementation.logApiCall( "setRevenueListener(listener=" + listener + ")" );
        mImplementation.setRevenueListener( listener );
    }

    /**
     * Set a listener that will be notified about ad request events.
     *
     * @param listener Listener to be notified. May be null.
     */
    public void setRequestListener(final MaxAdRequestListener listener)
    {
        mImplementation.logApiCall( "setRequestListener(listener=" + listener + ")" );
        mImplementation.setRequestListener( listener );
    }

    /**
     * Set a listener that will be notified when the Ad Review creative id is available.
     */
    public void setAdReviewListener(final MaxAdReviewListener listener)
    {
        mImplementation.logApiCall( "setAdReviewListener(listener=" + listener + ")" );
        mImplementation.setAdReviewListener( listener );
    }

    /**
     * Load ad for the current rewarded interstitial.
     * Use {@link MaxRewardedInterstitialAd#setListener(MaxRewardedAdListener)} to assign a listener that should be notified about ad load state.
     */
    public void loadAd()
    {
        mImplementation.logApiCall( "loadAd()" );
        mImplementation.loadAd( getActivity() );
    }

    /**
     * Show the loaded rewarded interstitial ad.
     * <p>
     * Use {@link MaxRewardedInterstitialAd#setListener(MaxRewardedAdListener)} to assign a listener that should be notified about display events.
     * Use {@link MaxRewardedInterstitialAd#isReady()} to check if an ad was successfully loaded.
     */
    public void showAd()
    {
        showAd( null );
    }

    /**
     * Show the loaded rewarded interstitial ad for a given placement to tie ad events to.
     * <p>
     * Use {@link MaxRewardedInterstitialAd#setListener(MaxRewardedAdListener)} to assign a listener that should be notified about display events.
     * Use {@link MaxRewardedInterstitialAd#isReady()} to check if an ad was successfully loaded.
     *
     * @param placement The placement to tie the showing ad's events to.
     */
    public void showAd(final String placement)
    {
        showAd( placement, null );
    }

    /**
     * Show the loaded rewarded interstitial ad for a given placement and custom data to tie ad events to.
     * <p>
     * Use {@link MaxRewardedInterstitialAd#setListener(MaxRewardedAdListener)} to assign a listener that should be notified about display events.
     * Use {@link MaxRewardedInterstitialAd#isReady()} to check if an ad was successfully loaded.
     *
     * @param placement  The placement to tie the showing ad's events to.
     * @param customData The custom data to tie the showing ad's events to. Maximum size is 8KB.
     */
    public void showAd(final String placement, final String customData)
    {
        mImplementation.logApiCall( "showAd(placement=" + placement + ", customData=" + customData + ")" );
        mImplementation.showAd( placement, customData, getActivity() );
    }

    /**
     * Show the loaded rewarded interstitial ad in a container view.
     * <p>
     * Note: This {@link MaxRewardedInterstitialAd} ad can only show ads in container views after calling this method.
     * Please create another instance of {@link MaxRewardedInterstitialAd} if you wish to show ads in a fullscreen activity as well.
     * <p>
     * Use {@link MaxRewardedInterstitialAd#setListener(MaxRewardedAdListener)} to assign a listener that should be notified about display events.
     * Use {@link MaxRewardedInterstitialAd#isReady()} to check if an ad was successfully loaded.
     *
     * @param containerView ViewGroup used to show the ad in.
     * @param lifecycle     Lifecycle object to manage ad lifecycle events in container views.
     */
    public void showAd(final ViewGroup containerView, final Lifecycle lifecycle)
    {
        showAd( null, containerView, lifecycle );
    }

    /**
     * Show the loaded rewarded interstitial ad for a given placement to tie ad events to in a container view.
     * <p>
     * Note: This {@link MaxRewardedInterstitialAd} ad can only show ads in container views after calling this method.
     * Please create another instance of {@link MaxRewardedInterstitialAd} if you wish to show ads in a fullscreen activity as well.
     * <p>
     * Use {@link MaxRewardedInterstitialAd#setListener(MaxRewardedAdListener)} to assign a listener that should be notified about display events.
     * Use {@link MaxRewardedInterstitialAd#isReady()} to check if an ad was successfully loaded.
     *
     * @param placement     The placement to tie the showing ad's events to.
     * @param containerView ViewGroup used to show the ad in.
     * @param lifecycle     Lifecycle object to manage ad lifecycle events in container views.
     */
    public void showAd(final String placement, final ViewGroup containerView, final Lifecycle lifecycle)
    {
        showAd( placement, null, containerView, lifecycle );
    }

    /**
     * Show the loaded rewarded interstitial ad for a given placement and custom data to tie ad events to in a container view.
     * <p>
     * Note: This {@link MaxRewardedInterstitialAd} ad can only show ads in container views after calling this method.
     * Please create another instance of {@link MaxRewardedInterstitialAd} if you wish to show ads in a fullscreen activity as well.
     * <p>
     * Use {@link MaxRewardedInterstitialAd#setListener(MaxRewardedAdListener)} to assign a listener that should be notified about display events.
     * Use {@link MaxRewardedInterstitialAd#isReady()} to check if an ad was successfully loaded.
     *
     * @param placement     The placement to tie the showing ad's events to.
     * @param customData    The custom data to tie the showing ad's events to. Maximum size is 8KB.
     * @param containerView ViewGroup used to show the ad in.
     * @param lifecycle     Lifecycle object to manage ad lifecycle events in container views.
     */
    public void showAd(final String placement, final String customData, final ViewGroup containerView, final Lifecycle lifecycle)
    {
        mImplementation.logApiCall( "showAd(placement=" + placement + ", customData=" + customData + ", containerView=" + containerView + ", lifecycle=" + lifecycle + ")" );
        mImplementation.showAd( placement, customData, containerView, lifecycle, getActivity() );
    }

    /**
     * Check if this ad is ready to be shown.
     *
     * @return <code>true</code> if the ad is ready to be shown.
     */
    public boolean isReady()
    {
        boolean isReady = mImplementation.isReady();
        mImplementation.logApiCall( "isReady() " + isReady + " for ad unit id " + mImplementation.getAdUnitId() );
        return isReady;
    }

    /**
     * Destroy current ad and fully remove it from memory in the next GC cycle.
     */
    public void destroy()
    {
        mImplementation.logApiCall( "destroy()" );
        mImplementation.destroy();
    }

    @Override
    public Activity getActivity()
    {
        mImplementation.logApiCall( "getActivity()" );
        return sActivityRef.get();
    }

    /**
     * Set an extra parameter to pass to the server.
     *
     * @param key   Parameter key. Must not be null.
     * @param value Parameter value. May be null.
     */
    public void setExtraParameter(final String key, final String value)
    {
        mImplementation.logApiCall( "setExtraParameter(key=" + key + ", value=" + value + ")" );
        mImplementation.setExtraParameter( key, value );
    }

    /**
     * Set a local extra parameter to pass to the adapter instances. Will not be available in the {@code MaxAdapter#initialize()} method.
     *
     * @param key   Parameter key. Must not be null.
     * @param value Parameter value. May be null.
     */
    public void setLocalExtraParameter(final String key, final Object value)
    {
        mImplementation.logApiCall( "setLocalExtraParameter(key=" + key + ", value=" + value + ")" );
        mImplementation.setLocalExtraParameter( key, value );
    }

    @Override
    public String toString()
    {
        // Paranoia from past battles with Huawei where implementation may not have been initialized when toString() is called
        return "" + mImplementation;
    }
}

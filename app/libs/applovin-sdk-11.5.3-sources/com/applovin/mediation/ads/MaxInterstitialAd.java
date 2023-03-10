package com.applovin.mediation.ads;

import android.app.Activity;
import android.text.TextUtils;
import android.view.ViewGroup;

import com.applovin.impl.mediation.ads.MaxAdImplBase;
import com.applovin.impl.mediation.ads.MaxFullscreenAdImpl;
import com.applovin.impl.sdk.utils.Utils;
import com.applovin.mediation.MaxAdFormat;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxAdRequestListener;
import com.applovin.mediation.MaxAdRevenueListener;
import com.applovin.mediation.MaxAdReviewListener;
import com.applovin.sdk.AppLovinSdk;

import java.lang.ref.WeakReference;

import androidx.lifecycle.Lifecycle;

/**
 * This class represents a full screen interstitial ad.
 */
public class MaxInterstitialAd
        implements MaxFullscreenAdImpl.ActivityProvider
{
    private static final String TAG = "MaxInterstitialAd";

    private static WeakReference<Activity> sActivityRef = new WeakReference<>( null );

    private final MaxFullscreenAdImpl mImplementation;

    /**
     * Create a new MAX interstitial.
     *
     * @param adUnitId Ad unit id to load ads for. Must not be null.
     * @param activity Current activity. Must not be null.
     */
    public MaxInterstitialAd(final String adUnitId, final Activity activity)
    {
        this( adUnitId, AppLovinSdk.getInstance( activity ), activity );
    }

    /**
     * Create a new MAX interstitial.
     *
     * @param adUnitId Ad unit id to load ads for. Must not be null.
     * @param sdk      SDK to use. Must not be null. An instance of the SDK may be obtained by calling <code>AppLovinSdk.getInstance()</code>.
     * @param activity Current activity. Must not be null.
     */
    public MaxInterstitialAd(final String adUnitId, final AppLovinSdk sdk, final Activity activity)
    {
        MaxAdImplBase.logApiCall( TAG, "MaxInterstitialAd(adUnitId=" + adUnitId + ", sdk=" + sdk + ", activity=" + activity + ")" );

        // Check input
        if ( adUnitId == null ) throw new IllegalArgumentException( "No ad unit ID specified" );
        if ( TextUtils.isEmpty( adUnitId ) ) throw new IllegalArgumentException( "Empty ad unit ID specified" );
        if ( activity == null ) throw new IllegalArgumentException( "No activity specified" );
        if ( sdk == null ) throw new IllegalArgumentException( "No sdk specified" );

        sActivityRef = new WeakReference<>( activity );

        mImplementation = new MaxFullscreenAdImpl( adUnitId.trim(), MaxAdFormat.INTERSTITIAL, this, "MaxInterstitialAd", sdk.coreSdk );
    }

    /**
     * Set a listener that will be notified about ad events.
     *
     * @param listener Listener to be notified. May be null.
     */
    public void setListener(final MaxAdListener listener)
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
     * Load ad for the current interstitial.
     * <p>
     * Use {@link MaxInterstitialAd#setListener(MaxAdListener)} to assign a listener that should be notified about ad load state.
     */
    public void loadAd()
    {
        mImplementation.logApiCall( "loadAd()" );
        mImplementation.loadAd( getActivity() );
    }

    /**
     * Show the loaded interstitial.
     * <p>
     * Use {@link MaxInterstitialAd#setListener(MaxAdListener)} to assign a listener that should be notified about display events.
     * Use {@link MaxInterstitialAd#isReady()} to check if an ad was successfully loaded.
     */
    public void showAd()
    {
        showAd( null );
    }

    /**
     * Show the loaded interstitial ad for a given placement to tie ad events to.
     * <p>
     * Use {@link MaxInterstitialAd#setListener(MaxAdListener)} to assign a listener that should be notified about display events.
     * Use {@link MaxInterstitialAd#isReady()} to check if an ad was successfully loaded.
     *
     * @param placement The placement to tie the showing ad's events to.
     */
    public void showAd(final String placement)
    {
        showAd( placement, null );
    }

    /**
     * Show the loaded interstitial ad for a given placement and custom data to tie ad events to.
     * <p>
     * Use {@link MaxInterstitialAd#setListener(MaxAdListener)} to assign a listener that should be notified about display events.
     * Use {@link MaxInterstitialAd#isReady()} to check if an ad was successfully loaded.
     *
     * @param placement  The placement to tie the showing ad's events to.
     * @param customData The custom data to tie the showing ad's events to. Maximum size is 8KB.
     */
    public void showAd(final String placement, final String customData)
    {
        mImplementation.logApiCall( "showAd(placement=" + placement + ", customData=" + customData + ")" );

        Utils.maybeLogCustomDataSizeLimit( customData, TAG );
        mImplementation.showAd( placement, customData, getActivity() );
    }

    /**
     * Show the loaded interstitial ad in a container view.
     * <p>
     * Note: This {@link MaxInterstitialAd} ad can only show ads in container views after calling this method.
     * Please create another instance of {@link MaxInterstitialAd} if you wish to show ads in a fullscreen activity as well.
     * <p>
     * Use {@link MaxInterstitialAd#setListener(MaxAdListener)} to assign a listener that should be notified about display events.
     * Use {@link MaxInterstitialAd#isReady()} to check if an ad was successfully loaded.
     *
     * @param containerView ViewGroup used to show the ad in.
     * @param lifecycle     Lifecycle object to manage ad lifecycle events in container views.
     */
    public void showAd(final ViewGroup containerView, final Lifecycle lifecycle)
    {
        showAd( null, containerView, lifecycle );
    }

    /**
     * Show the loaded interstitial ad for a given placement to tie ad events to in a container view.
     * <p>
     * Note: This {@link MaxInterstitialAd} ad can only show ads in container views after calling this method.
     * Please create another instance of {@link MaxInterstitialAd} if you wish to show ads in a fullscreen activity as well.
     * <p>
     * Use {@link MaxInterstitialAd#setListener(MaxAdListener)} to assign a listener that should be notified about display events.
     * Use {@link MaxInterstitialAd#isReady()} to check if an ad was successfully loaded.
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
     * Show the loaded interstitial ad for a given placement and custom data to tie ad events to in a container view.
     * <p>
     * Note: This {@link MaxInterstitialAd} ad can only show ads in container views after calling this method.
     * Please create another instance of {@link MaxInterstitialAd} if you wish to show ads in a fullscreen activity as well.
     * <p>
     * Use {@link MaxInterstitialAd#setListener(MaxAdListener)} to assign a listener that should be notified about display events.
     * Use {@link MaxInterstitialAd#isReady()} to check if an ad was successfully loaded.
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
     * The ad unit identifier this {@link MaxInterstitialAd} was initialized with and is loading ads for.
     */
    public String getAdUnitId()
    {
        return mImplementation.getAdUnitId();
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

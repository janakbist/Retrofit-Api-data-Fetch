package com.applovin.mediation.ads;

import android.content.Context;

import com.applovin.impl.mediation.ads.MaxAdImplBase;
import com.applovin.impl.mediation.ads.MaxFullscreenAdImpl;
import com.applovin.impl.sdk.utils.Utils;
import com.applovin.mediation.MaxAdFormat;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxAdRequestListener;
import com.applovin.mediation.MaxAdRevenueListener;
import com.applovin.sdk.AppLovinSdk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * This class represents a full screen ad that can be shown upon opening an app.
 */
public class MaxAppOpenAd
{
    private static final String TAG = "MaxAppOpenAd";

    private final MaxFullscreenAdImpl implementation;

    /**
     * Create a new MAX app open ad.
     *
     * @param adUnitId Ad unit id to load ads for.
     * @param context  Context to use.
     */
    public MaxAppOpenAd(@NonNull final String adUnitId, @NonNull final Context context)
    {
        this( adUnitId, AppLovinSdk.getInstance( context ) );
    }

    /**
     * Create a new MAX app open ad.
     *
     * @param adUnitId Ad unit id to load ads for.
     * @param sdk      SDK to use. An instance of the SDK may be obtained by calling <code>AppLovinSdk.getInstance()</code>.
     */
    public MaxAppOpenAd(@NonNull final String adUnitId, @NonNull final AppLovinSdk sdk)
    {
        MaxAdImplBase.logApiCall( TAG, "MaxAppOpenAd(adUnitId=" + adUnitId + ", sdk=" + sdk + ")" );

        implementation = new MaxFullscreenAdImpl( adUnitId.trim(), MaxAdFormat.APP_OPEN, null, "MaxAppOpenAd", sdk.coreSdk );
    }

    /**
     * Set a listener that will be notified about ad events.
     *
     * @param listener Listener to be notified.
     */
    public void setListener(@Nullable final MaxAdListener listener)
    {
        implementation.logApiCall( "setListener(listener=" + listener + ")" );
        implementation.setListener( listener );
    }

    /**
     * Set a listener that will be notified about ad revenue events.
     *
     * @param listener Listener to be notified.
     */
    public void setRevenueListener(@Nullable final MaxAdRevenueListener listener)
    {
        implementation.logApiCall( "setRevenueListener(listener=" + listener + ")" );
        implementation.setRevenueListener( listener );
    }

    /**
     * Set a listener that will be notified about ad request events.
     *
     * @param listener Listener to be notified. May be null.
     */
    public void setRequestListener(final MaxAdRequestListener listener)
    {
        implementation.logApiCall( "setRequestListener(listener=" + listener + ")" );
        implementation.setRequestListener( listener );
    }

    /**
     * Load ad for the current app open ad.
     * <p>
     * Use {@link MaxAppOpenAd#setListener(MaxAdListener)} to assign a listener that should be notified about ad load state.
     */
    public void loadAd()
    {
        implementation.logApiCall( "loadAd()" );
        implementation.loadAd( null );
    }

    /**
     * Show the loaded app open ad.
     * <p>
     * Use {@link MaxAppOpenAd#setListener(MaxAdListener)} to assign a listener that should be notified about display events.
     * Use {@link MaxAppOpenAd#isReady()} to check if an ad was successfully loaded.
     */
    public void showAd()
    {
        showAd( null );
    }

    /**
     * Show the loaded app open ad for a given placement to tie ad events to.
     * <p>
     * Use {@link MaxAppOpenAd#setListener(MaxAdListener)} to assign a listener that should be notified about display events.
     * Use {@link MaxAppOpenAd#isReady()} to check if an ad was successfully loaded.
     *
     * @param placement The placement to tie the showing ad's events to.
     */
    public void showAd(@Nullable final String placement)
    {
        showAd( placement, null );
    }

    /**
     * Show the loaded app open ad for a given placement and custom data to tie ad events to.
     * <p>
     * Use {@link MaxAppOpenAd#setListener(MaxAdListener)} to assign a listener that should be notified about display events.
     * Use {@link MaxAppOpenAd#isReady()} to check if an ad was successfully loaded.
     *
     * @param placement  The placement to tie the showing ad's events to.
     * @param customData The custom data to tie the showing ad's events to. Maximum size is 8KB.
     */
    public void showAd(@Nullable final String placement, @Nullable final String customData)
    {
        implementation.logApiCall( "showAd(placement=" + placement + ", customData=" + customData + ")" );

        Utils.maybeLogCustomDataSizeLimit( customData, TAG );
        implementation.showAd( placement, customData, null );
    }

    /**
     * The ad unit identifier this {@link MaxAppOpenAd} was initialized with and is loading ads for.
     */
    @NonNull
    public String getAdUnitId()
    {
        return implementation.getAdUnitId();
    }

    /**
     * Check if this ad is ready to be shown.
     *
     * @return <code>true</code> if the ad is ready to be shown.
     */
    public boolean isReady()
    {
        boolean isReady = implementation.isReady();
        implementation.logApiCall( "isReady() " + isReady + " for ad unit id " + implementation.getAdUnitId() );
        return isReady;
    }

    /**
     * Destroy current ad and fully remove it from memory in the next GC cycle.
     */
    public void destroy()
    {
        implementation.logApiCall( "destroy()" );
        implementation.destroy();
    }

    /**
     * Set an extra parameter to pass to the server.
     *
     * @param key   Parameter key.
     * @param value Parameter value.
     */
    public void setExtraParameter(@NonNull final String key, @Nullable final String value)
    {
        implementation.logApiCall( "setExtraParameter(key=" + key + ", value=" + value + ")" );
        implementation.setExtraParameter( key, value );
    }

    /**
     * Set a local extra parameter to pass to the adapter instances. Will not be available in the {@code MaxAdapter#initialize()} method.
     *
     * @param key   Parameter key.
     * @param value Parameter value.
     */
    public void setLocalExtraParameter(@NonNull final String key, @Nullable final Object value)
    {
        implementation.logApiCall( "setLocalExtraParameter(key=" + key + ", value=" + value + ")" );
        implementation.setLocalExtraParameter( key, value );
    }

    @NonNull
    @Override
    public String toString()
    {
        // Paranoia from past battles with Huawei where implementation may not have been initialized when toString() is called
        return "" + implementation;
    }
}

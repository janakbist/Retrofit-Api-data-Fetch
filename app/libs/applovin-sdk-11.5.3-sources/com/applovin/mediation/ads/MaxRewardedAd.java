package com.applovin.mediation.ads;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;

import com.applovin.impl.mediation.ads.MaxAdImplBase;
import com.applovin.impl.mediation.ads.MaxFullscreenAdImpl;
import com.applovin.impl.sdk.utils.CollectionUtils;
import com.applovin.impl.sdk.utils.Utils;
import com.applovin.mediation.MaxAdFormat;
import com.applovin.mediation.MaxAdRequestListener;
import com.applovin.mediation.MaxAdRevenueListener;
import com.applovin.mediation.MaxAdReviewListener;
import com.applovin.mediation.MaxRewardedAdListener;
import com.applovin.sdk.AppLovinSdk;

import java.lang.ref.WeakReference;
import java.util.Map;

import androidx.lifecycle.Lifecycle;

/**
 * This class represents a full screen rewarded ad.
 */
public class MaxRewardedAd
        implements MaxFullscreenAdImpl.ActivityProvider
{
    private static final String TAG = "MaxRewardedAd";

    private static final Map<String, MaxRewardedAd> instances     = CollectionUtils.map();
    private static final Object                     instancesLock = new Object();

    private static WeakReference<Activity> activityRef = new WeakReference<>( null );

    private final MaxFullscreenAdImpl mImplementation;

    /**
     * Create a new MAX rewarded ad.
     *
     * @param adUnitId Ad unit id to load ads for. Must not be null.
     * @param sdk      SDK to use. Must not be null. An instance of the SDK may be obtained by calling {@link AppLovinSdk#getInstance(Context)}.
     */
    private MaxRewardedAd(final String adUnitId, final AppLovinSdk sdk)
    {
        mImplementation = new MaxFullscreenAdImpl( adUnitId.trim(), MaxAdFormat.REWARDED, this, "MaxRewardedAd", sdk.coreSdk );
    }

    /**
     * Set a listener that will be notified about ad display events (i.e. ad shown, hidden, etc.)
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
     * Load ad for the current rewarded ad.
     * <p>
     * Use {@link MaxRewardedAd#setListener(MaxRewardedAdListener)} to assign a listener that should be notified about ad load state.
     */
    public void loadAd()
    {
        mImplementation.logApiCall( "loadAd()" );
        mImplementation.loadAd( getActivity() );
    }

    /**
     * Show the loaded rewarded ad.
     * <p>
     * Use {@link MaxRewardedAd#setListener(MaxRewardedAdListener)} to assign a listener that should be notified about display events.
     * Use {@link MaxRewardedAd#isReady()} to check if an ad was successfully loaded.
     */
    public void showAd()
    {
        showAd( null );
    }

    /**
     * Show the loaded rewarded ad for a given placement to tie ad events to.
     * <p>
     * Use {@link MaxRewardedAd#setListener(MaxRewardedAdListener)} to assign a listener that should be notified about display events.
     * Use {@link MaxRewardedAd#isReady()} to check if an ad was successfully loaded.
     *
     * @param placement The placement to tie the showing ad's events to.
     */
    public void showAd(final String placement)
    {
        showAd( placement, null );
    }

    /**
     * Show the loaded rewarded ad for a given placement and custom data to tie ad events to.
     * <p>
     * Use {@link MaxRewardedAd#setListener(MaxRewardedAdListener)} to assign a listener that should be notified about display events.
     * Use {@link MaxRewardedAd#isReady()} to check if an ad was successfully loaded.
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
     * Show the loaded rewarded ad in a container view.
     * <p>
     * Use {@link MaxRewardedAd#setListener(MaxRewardedAdListener)} to assign a listener that should be notified about display events.
     * Use {@link MaxRewardedAd#isReady()} to check if an ad was successfully loaded.
     *
     * @param containerView ViewGroup used to show the ad in.
     * @param lifecycle     Lifecycle object to manage ad lifecycle events in container views.
     */
    public void showAd(final ViewGroup containerView, final Lifecycle lifecycle)
    {
        showAd( null, containerView, lifecycle );
    }

    /**
     * Show the loaded rewarded ad for a given placement to tie ad events to in a container view.
     * <p>
     * Use {@link MaxRewardedAd#setListener(MaxRewardedAdListener)} to assign a listener that should be notified about display events.
     * Use {@link MaxRewardedAd#isReady()} to check if an ad was successfully loaded.
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
     * Show the loaded rewarded ad for a given placement and custom data to tie ad events to in a container view.
     * <p>
     * Use {@link MaxRewardedAd#setListener(MaxRewardedAdListener)} to assign a listener that should be notified about display events.
     * Use {@link MaxRewardedAd#isReady()} to check if an ad was successfully loaded.
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
     * The ad unit identifier this {@link MaxRewardedAd} was initialized with and is loading ads for.
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

        // Remove this rewarded ad from instances map
        synchronized ( instancesLock )
        {
            instances.remove( mImplementation.getAdUnitId() );
        }

        mImplementation.destroy();
    }

    /**
     * Update currently active activity. This activity will be used to load new rewarded ads.
     * <p>
     * Note that MAX Rewarded holds a weak reference to the activity.
     * </p>
     *
     * @param activity New activity to use. Must not be null.
     */
    public static void updateActivity(final Activity activity)
    {
        MaxAdImplBase.logApiCall( TAG, "updateActivity(activity=" + activity + ")" );
        if ( activity != null ) activityRef = new WeakReference<>( activity );
    }

    /**
     * Set an extra parameter to pass to AppLovin server.
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

    /**
     * Get an instance of rewarded ad.
     *
     * @param adUnitId Ad unit id for which to get the instance. Must not be null.
     * @param activity Currently active activity. Must not be null.
     *
     * @return An instance of rewarded ad tied to the specified ad unit ID.
     */
    public static MaxRewardedAd getInstance(final String adUnitId, final Activity activity)
    {
        return getInstance( adUnitId, AppLovinSdk.getInstance( activity ), activity );
    }

    /**
     * Get an instance of rewarded ad.
     *
     * @param adUnitId Ad unit id for which to get the instance. Must not be null.
     * @param sdk      MAX ads SDK. Must not be null.
     * @param activity Currently active activity. Must not be null.
     *
     * @return An instance of rewarded ad tied to the specified ad unit ID.
     */
    public static MaxRewardedAd getInstance(final String adUnitId, final AppLovinSdk sdk, final Activity activity)
    {
        MaxAdImplBase.logApiCall( TAG, "getInstance(adUnitId=" + adUnitId + ", sdk=" + sdk + ", activity=" + activity + ")" );

        // Check input
        if ( adUnitId == null ) throw new IllegalArgumentException( "No ad unit ID specified" );
        if ( TextUtils.isEmpty( adUnitId ) ) throw new IllegalArgumentException( "Empty ad unit ID specified" );
        if ( activity == null ) throw new IllegalArgumentException( "No activity specified" );
        if ( sdk == null ) throw new IllegalArgumentException( "No sdk specified" );

        updateActivity( activity );

        synchronized ( instancesLock )
        {
            final MaxRewardedAd rewardedAd = instances.get( adUnitId );
            if ( rewardedAd != null )
            {
                return rewardedAd;
            }
            else
            {
                final MaxRewardedAd newAd = new MaxRewardedAd( adUnitId, sdk );
                instances.put( adUnitId, newAd );

                return newAd;
            }
        }
    }

    @Override
    public Activity getActivity()
    {
        mImplementation.logApiCall( "getActivity()" );
        return activityRef.get();
    }

    @Override
    public String toString()
    {
        // Paranoia from past battles with Huawei where implementation may not have been initialized when toString() is called
        return "" + mImplementation;
    }
}

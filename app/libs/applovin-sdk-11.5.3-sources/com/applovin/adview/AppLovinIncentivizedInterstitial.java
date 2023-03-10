package com.applovin.adview;

import android.content.Context;
import android.view.ViewGroup;

import com.applovin.impl.sdk.Logger;
import com.applovin.impl.sdk.reward.IncentivizedAdController;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinSdk;

import androidx.lifecycle.Lifecycle;

/**
 * This class shows rewarded videos to the user. These differ from regular interstitials in that they allow you to provide your user virtual currency in exchange for watching a video.
 */
public class AppLovinIncentivizedInterstitial
{
    private final static String TAG = "AppLovinIncentivizedInterstitial";

    private final IncentivizedAdController controller;

    /**
     * Create a new instance of AppLovinIncentivizedInterstitial.
     *
     * @param context Context to use.
     */
    public AppLovinIncentivizedInterstitial(final Context context)
    {
        this( AppLovinSdk.getInstance( context ) );
    }

    /**
     * Create a new instance of AppLovinIncentivizedInterstitial.
     *
     * @param sdk An instance of AppLovinSdk to use.
     */
    public AppLovinIncentivizedInterstitial(final AppLovinSdk sdk)
    {
        this( null, sdk );
    }

    /**
     * Create a new instance of AppLovinIncentivizedInterstitial.
     *
     * @param zoneId The id of the zone for which to load ads for.
     * @param sdk    An instance of AppLovinSdk to use.
     */
    public AppLovinIncentivizedInterstitial(final String zoneId, final AppLovinSdk sdk)
    {
        if ( sdk == null ) throw new IllegalArgumentException( "No sdk specified" );

        controller = createIncentivizedAdController( zoneId, sdk );
    }

    /**
     * The zone identifier this incentivized ad was initialized with and is loading ads for, if any.
     */
    public String getZoneId()
    {
        return controller.getZoneId();
    }

    /**
     * Pre-load an incentivized interstitial.
     * <p>
     * Calling this method will overwrite any existing cached ad. This is not a queue. Invoke once to preload, then do not invoke again until the ad has has been closed (e.g., AppLovinAdDisplayListener's adHidden callback). You may pass a null argument to preload if you intend to use the synchronous
     * ( isAdReadyToDisplay ) flow. Note that this is NOT recommended; we HIGHLY RECOMMEND you use an ad load delegate. Note that we internally try to pull down the next ad's resources before you need it. Therefore, this method may complete immediately in many circumstances.
     *
     * @param adLoadListener Ad load listener to notify. May be null..
     */
    public void preload(final AppLovinAdLoadListener adLoadListener)
    {
        if ( adLoadListener == null )
        {
            if ( Logger.isVerbose() )
            {
                Logger.userInfo( TAG, "AppLovinAdLoadListener was null when preloading incentivized interstitials; using a listener is highly recommended." );
            }
        }
        controller.preload( adLoadListener );
    }

    /**
     * Show a pre-loaded incentivized interstitial. You must call preload before calling show.
     */
    public void show(final Context context)
    {
        show( context, null, null );
    }

    /**
     * Show a pre-loaded incentivized interstitial. You must call preload before calling show.
     *
     * @param context          Parent activity or application context. MUST be an activity if you want to show dialog prompt to view ad. Must not be null.
     * @param adRewardListener Reward listener to notify, if desired. May be null. Using the reward listener, you will be able to verify with AppLovin servers if the video view is legitimate, as we will confirm whether the specific ad was actually served - then we will ping your server with a url for you to
     *                         update the user's balance. The Reward Validation Listener will tell you whether we were able to reach our servers or not. If you receive a successful response, you should refresh the user's balance from your server. For more info, see the documentation.
     */
    public void show(final Context context, final AppLovinAdRewardListener adRewardListener)
    {
        show( context, adRewardListener, null );
    }

    /**
     * Check if an ad is currently ready on this object. You must call preloadAndNotify in order to reach this state.
     * <p>
     * It is highly recommended that you implement an asynchronous flow (using an AppLovinAdDisplayListener with preload) rather than checking this method. This class does not contain a queue and can hold only one preloaded ad at a time. Further calls to preload will overwrite any existing ad.
     * Therefore, you should NOT simply call preload any time this method returns false; it is important to invoke only one ad load - then not invoke any further loads until the ad has been closed (e.g., AppLovinAdDisplayListener's adHidden callback).
     *
     * @return True if an ad has been loaded into this incentivized interstitial and is ready to display. False otherwise.
     */
    public boolean isAdReadyToDisplay()
    {
        return controller.isAdReady();
    }

    /**
     * Show a pre-loaded incentivized interstitial. You must call preload before calling show.
     *
     * @param context               Parent activity or application context. MUST be an activity if you want to show dialog prompt to view ad. Must not be null.
     * @param videoPlaybackListener Video playback listener to notify. Must not be null.
     * @param adRewardListener      Reward listener to notify, if desired. May be null.
     */
    public void show(final Context context,
                     final AppLovinAdRewardListener adRewardListener,
                     final AppLovinAdVideoPlaybackListener videoPlaybackListener)
    {
        show( context, adRewardListener, videoPlaybackListener, null );
    }

    /**
     * Show a pre-loaded incentivized interstitial. You must call preload before calling show.
     *
     * @param context               Parent activity or application context. MUST be an activity if you want to show dialog prompt to view ad. Must not be null.
     * @param videoPlaybackListener Video playback listener to notify. Must not be null.
     * @param adDisplayListener     Ad display listener to notify, if desired. May be null.
     * @param adRewardListener      Reward listener to notify, if desired. May be null.
     */
    public void show(final Context context,
                     final AppLovinAdRewardListener adRewardListener,
                     final AppLovinAdVideoPlaybackListener videoPlaybackListener,
                     final AppLovinAdDisplayListener adDisplayListener)
    {
        show( context, adRewardListener, videoPlaybackListener, adDisplayListener, null );
    }

    /**
     * Show a pre-loaded incentivized interstitial. You must call preload before calling show.
     *
     * @param context               Parent activity or application context. MUST be an activity if you want to show dialog prompt to view ad. Must not be null.
     * @param videoPlaybackListener Video playback listener to notify. May be null.
     * @param adDisplayListener     Ad display listener to notify, if desired. May be null.
     * @param adClickListener       Ad click listener to notify, if desired. May be null.
     * @param adRewardListener      Reward listener to notify, if desired. May be null.
     */
    public void show(final Context context,
                     final AppLovinAdRewardListener adRewardListener,
                     final AppLovinAdVideoPlaybackListener videoPlaybackListener,
                     final AppLovinAdDisplayListener adDisplayListener,
                     final AppLovinAdClickListener adClickListener)
    {
        show( null, context, adRewardListener, videoPlaybackListener, adDisplayListener, adClickListener );
    }

    /**
     * Show a pre-loaded incentivized interstitial. You must call preload before calling show.
     *
     * @param ad                    The ad to render into this incentivized ad. Must not be null.
     * @param context               Parent activity or application context. MUST be an activity if you want to show dialog prompt to view ad. Must not be null.
     * @param videoPlaybackListener Video playback listener to notify. May be null.
     * @param adDisplayListener     Ad display listener to notify, if desired. May be null.
     * @param adClickListener       Ad click listener to notify, if desired. May be null.
     * @param adRewardListener      Reward listener to notify, if desired. May be null.
     */
    public void show(final AppLovinAd ad,
                     final Context context,
                     final AppLovinAdRewardListener adRewardListener,
                     final AppLovinAdVideoPlaybackListener videoPlaybackListener,
                     final AppLovinAdDisplayListener adDisplayListener,
                     final AppLovinAdClickListener adClickListener)
    {
        controller.show( ad, context, null, adRewardListener, videoPlaybackListener, adDisplayListener, adClickListener );
    }

    /**
     * Show a pre-loaded incentivized interstitial in a container view. You must call preload before calling show.
     *
     * @param ad                    The ad to render into this incentivized ad. Must not be null.
     * @param containerView         ViewGroup used to show the ad in. Must not be null.
     * @param lifecycle             Lifecycle object to manage ad lifecycle events in container views. Must not be null.
     * @param context               Parent activity or application context. MUST be an activity if you want to show dialog prompt to view ad. Must not be null.
     * @param videoPlaybackListener Video playback listener to notify. May be null.
     * @param adDisplayListener     Ad display listener to notify, if desired. May be null.
     * @param adClickListener       Ad click listener to notify, if desired. May be null.
     * @param adRewardListener      Reward listener to notify, if desired. May be null.
     */
    public void show(final AppLovinAd ad,
                     final ViewGroup containerView,
                     final Lifecycle lifecycle,
                     final Context context,
                     final AppLovinAdRewardListener adRewardListener,
                     final AppLovinAdVideoPlaybackListener videoPlaybackListener,
                     final AppLovinAdDisplayListener adDisplayListener,
                     final AppLovinAdClickListener adClickListener)
    {
        controller.show( ad, containerView, lifecycle, context, adRewardListener, videoPlaybackListener, adDisplayListener, adClickListener );
    }

    /**
     * Create a new instance of AppLovinIncentivizedInterstitial.
     *
     * @param context Parent activity or application context. MUST be an activity if you want to show dialog prompt to view ad. Must not be null.
     */
    public static AppLovinIncentivizedInterstitial create(final Context context)
    {
        return create( AppLovinSdk.getInstance( context ) );
    }

    /**
     * Create a new instance of AppLovinIncentivizedInterstitial.
     *
     * @param sdk An instance of AppLovinSdk to use.
     */
    public static AppLovinIncentivizedInterstitial create(final AppLovinSdk sdk)
    {
        return create( null, sdk );
    }

    /**
     * Create a new instance of AppLovinIncentivizedInterstitial.
     *
     * @param zoneId The id of the zone to load ads for.
     * @param sdk    An instance of AppLovinSdk to use.
     */
    public static AppLovinIncentivizedInterstitial create(final String zoneId, final AppLovinSdk sdk)
    {
        return new AppLovinIncentivizedInterstitial( zoneId, sdk );
    }

    /**
     * Create a new controller that would load and render this ad. This method is called from the constructor. For internal usage only.
     */
    protected IncentivizedAdController createIncentivizedAdController(final String zoneId, final AppLovinSdk sdk)
    {
        return new IncentivizedAdController( zoneId, sdk );
    }

    @Override
    public String toString()
    {
        return "AppLovinIncentivizedInterstitial{" +
                "zoneId='" + getZoneId() + "'" +
                ", isAdReadyToDisplay=" + isAdReadyToDisplay() +
                '}';
    }
}

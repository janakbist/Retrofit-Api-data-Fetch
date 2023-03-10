package com.applovin.mediation.hybridAds;

import android.app.Activity;

import com.applovin.impl.mediation.model.MediatedFullscreenAd;
import com.applovin.impl.sdk.CoreSdk;
import com.applovin.impl.sdk.utils.ActivityUtils;
import com.applovin.impl.sdk.utils.Utils;
import com.applovin.mediation.adapter.MaxAdapterError;
import com.applovin.mediation.adapter.listeners.MaxAdapterListener;
import com.applovin.mediation.adapter.listeners.MaxAppOpenAdapterListener;
import com.applovin.mediation.adapter.listeners.MaxInterstitialAdapterListener;

import lombok.val;

public class MaxHybridAdService
{
    private static final String TAG = "MaxHybridAdService";

    private final CoreSdk sdk;

    public MaxHybridAdService(final CoreSdk sdk)
    {
        this.sdk = sdk;
    }

    /**
     * Show a fullscreen native/MREC hybrid ad for the provided ad using MAX.
     *
     * @param ad       The native/MREC hybrid ad to display as a fullscreen ad. This only supports interstitial and app open ads.
     * @param activity The Activity passed in from the adapter.
     * @param listener The adapter's listener. Traditionally this is abstracted away in each adapter, but it is consolidated for hybrid ads.
     */
    public void showFullscreenAd(final MediatedFullscreenAd ad, final Activity activity, final MaxAdapterListener listener)
    {
        Utils.assertMainThread();

        val userActivity = activity != null ? activity : sdk.getActivityLifecycleManager().getTopActivity();
        if ( ad.getNativeAd() != null )
        {
            sdk.getLogger().d( TAG, "Showing fullscreen native ad..." );

            ActivityUtils.startActivity( userActivity,
                                         MaxHybridNativeAdActivity.class,
                                         sdk.getActivityLifecycleManager(),
                                         new ActivityUtils.ActivityInitializer<MaxHybridNativeAdActivity>()
                                         {
                                             @Override
                                             public void onActivityCreated(final MaxHybridNativeAdActivity activity)
                                             {
                                                 activity.initialize( ad, ad.getNativeAd(), sdk, listener );
                                             }
                                         } );
        }
        else if ( ad.getAdView() != null )
        {
            sdk.getLogger().d( TAG, "Showing fullscreen MREC ad..." );

            ActivityUtils.startActivity( userActivity,
                                         MaxHybridMRecAdActivity.class,
                                         sdk.getActivityLifecycleManager(),
                                         new ActivityUtils.ActivityInitializer<MaxHybridMRecAdActivity>()
                                         {
                                             @Override
                                             public void onActivityCreated(final MaxHybridMRecAdActivity activity)
                                             {
                                                 activity.initialize( ad, ad.getAdView(), sdk, listener );
                                             }
                                         } );
        }
        else
        {
            if ( listener instanceof MaxInterstitialAdapterListener )
            {
                ( (MaxInterstitialAdapterListener) listener ).onInterstitialAdDisplayFailed( MaxAdapterError.AD_DISPLAY_FAILED );
            }
            else if ( listener instanceof MaxAppOpenAdapterListener )
            {
                ( (MaxAppOpenAdapterListener) listener ).onAppOpenAdDisplayFailed( MaxAdapterError.AD_DISPLAY_FAILED );
            }
            else
            {
                throw new IllegalStateException( "Failed to display hybrid ad: neither native nor adview ad" );
            }
        }
    }
}

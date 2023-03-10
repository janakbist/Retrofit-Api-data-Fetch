package com.applovin.mediation.hybridAds;

import android.os.Bundle;
import android.view.ViewGroup;

import com.applovin.impl.mediation.model.MediatedFullscreenAd;
import com.applovin.impl.sdk.CoreSdk;
import com.applovin.mediation.adapter.listeners.MaxAdapterListener;
import com.applovin.mediation.nativeAds.MaxNativeAd;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.applovin.mediation.nativeAds.MaxNativeAdViewBinder;
import com.applovin.sdk.R;

import androidx.annotation.Nullable;
import lombok.val;

public class MaxHybridNativeAdActivity
        extends MaxHybridAdActivity
{
    private MaxNativeAdView nativeAdView;

    protected void initialize(final MediatedFullscreenAd ad, final MaxNativeAd nativeAd, final CoreSdk sdk, final MaxAdapterListener listener)
    {
        super.initialize( ad, sdk, listener );

        // NOTE: Do not render thru MAX, since the original opportunity is an inter
        val binder = new MaxNativeAdViewBinder.Builder( R.layout.max_hybrid_native_ad_view )
                .setTitleTextViewId( R.id.applovin_native_title_text_view )
                .setBodyTextViewId( R.id.applovin_native_body_text_view )
                .setAdvertiserTextViewId( R.id.applovin_native_advertiser_text_view )
                .setIconImageViewId( R.id.applovin_native_icon_image_view )
                .setMediaContentViewGroupId( R.id.applovin_native_media_content_view )
                .setOptionsContentViewGroupId( R.id.applovin_native_options_view )
                .setCallToActionButtonId( R.id.applovin_native_cta_button )
                .build();
        nativeAdView = new MaxNativeAdView( nativeAd, binder, this );
        nativeAdView.renderCustomNativeAdView( nativeAd );
        nativeAd.prepareViewForInteraction( nativeAdView );
    }

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState)
    {
        super.onCreate( savedInstanceState );

        val rootView = (ViewGroup) findViewById( android.R.id.content );
        rootView.addView( nativeAdView );

        closeButton.bringToFront();
    }
}

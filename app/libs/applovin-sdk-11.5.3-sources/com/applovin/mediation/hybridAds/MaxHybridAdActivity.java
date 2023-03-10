package com.applovin.mediation.hybridAds;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.applovin.impl.mediation.model.MediatedFullscreenAd;
import com.applovin.impl.sdk.CoreSdk;
import com.applovin.impl.sdk.setting.Setting;
import com.applovin.impl.sdk.utils.ActivityUtils;
import com.applovin.impl.sdk.utils.ViewUtils;
import com.applovin.mediation.adapter.listeners.MaxAdapterListener;
import com.applovin.mediation.adapter.listeners.MaxAppOpenAdapterListener;
import com.applovin.mediation.adapter.listeners.MaxInterstitialAdapterListener;

import androidx.annotation.Nullable;
import lombok.val;

public class MaxHybridAdActivity
        extends Activity
        implements MaxCloseButton.MaxCloseButtonListener
{
    // Parent Objects
    protected MaxCloseButton           closeButton;
    protected MaxHybridAdConfiguration configuration;

    // Controlled Fields
    private CoreSdk            sdk;
    private MaxAdapterListener listener;

    protected void initialize(final MediatedFullscreenAd ad, final CoreSdk sdk, final MaxAdapterListener listener)
    {
        this.sdk = sdk;
        this.listener = listener;

        configuration = ad.getHybridAdConfiguration();
    }

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState)
    {
        super.onCreate( savedInstanceState );

        requestWindowFeature( Window.FEATURE_NO_TITLE );
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        getWindow().addFlags( WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED );
        getWindow().addFlags( WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON );

        val rootView = (ViewGroup) findViewById( android.R.id.content );
        rootView.setBackgroundColor( configuration.getBackgroundColor() );

        val shouldUseInsetsController = sdk.get( Setting.FULLSCREEN_AD_SHOULD_USE_INSETS_CONTROLLER );
        ActivityUtils.hideSystemUi( shouldUseInsetsController, this );

        closeButton = new MaxCloseButton( configuration, this );
        closeButton.setListener( this );
        closeButton.setVisibility( View.INVISIBLE );

        rootView.addView( closeButton );
        ViewUtils.fadeIn( closeButton, configuration.getCloseButtonDelayMillis() );

        // TODO: Implement OnBackInvokedDispatcher

        if ( listener == null ) return;

        if ( listener instanceof MaxInterstitialAdapterListener )
        {
            ( (MaxInterstitialAdapterListener) listener ).onInterstitialAdDisplayed();
        }
        else if ( listener instanceof MaxAppOpenAdapterListener )
        {
            ( (MaxAppOpenAdapterListener) listener ).onAppOpenAdDisplayed();
        }
    }

    //region Close Button

    @Override
    public void onCloseButtonTapReceived(final MaxCloseButton closeButton)
    {
        if ( !isFinishing() )
        {
            finish();
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        if ( listener == null ) return;

        if ( listener instanceof MaxInterstitialAdapterListener )
        {
            ( (MaxInterstitialAdapterListener) listener ).onInterstitialAdHidden();
        }
        else if ( listener instanceof MaxAppOpenAdapterListener )
        {
            ( (MaxAppOpenAdapterListener) listener ).onAppOpenAdHidden();
        }
    }

    //endregion
}

package com.applovin.adview;

import com.applovin.impl.adview.InterstitialAdDialogWrapper;
import com.applovin.impl.adview.activity.presenters.FullscreenAdPresenter;
import com.applovin.impl.sdk.CoreSdk;

import java.util.concurrent.atomic.AtomicBoolean;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * Lifecycle Observer class to manage fullscreen ad view lifecycle events.
 */
public class AppLovinFullscreenAdViewObserver
        implements LifecycleObserver
{
    private final CoreSdk       sdk;
    private final AtomicBoolean isFirstLifecycleSetup = new AtomicBoolean( true );

    private FullscreenAdPresenter       presenter;
    private InterstitialAdDialogWrapper parentInterstitialWrapper;

    public AppLovinFullscreenAdViewObserver(final Lifecycle lifecycle, final InterstitialAdDialogWrapper parentInterstitialWrapper, final CoreSdk sdk)
    {
        this.parentInterstitialWrapper = parentInterstitialWrapper;
        this.sdk = sdk;

        lifecycle.addObserver( this );
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause()
    {
        if ( presenter != null )
        {
            presenter.onPause();
            presenter.pauseVideo();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume()
    {
        if ( isFirstLifecycleSetup.getAndSet( false ) ) return;

        if ( presenter != null )
        {
            presenter.onResume();
            presenter.resumeVideoFromLastPosition( 0 );
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop()
    {
        if ( presenter != null )
        {
            presenter.onStop();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy()
    {
        if ( parentInterstitialWrapper != null )
        {
            parentInterstitialWrapper.cleanupForAdView();
            parentInterstitialWrapper = null;
        }

        if ( presenter != null )
        {
            presenter.dismiss();
            presenter.onDestroy();
            presenter = null;
        }
    }

    public void setPresenter(final FullscreenAdPresenter presenter)
    {
        this.presenter = presenter;
    }
}

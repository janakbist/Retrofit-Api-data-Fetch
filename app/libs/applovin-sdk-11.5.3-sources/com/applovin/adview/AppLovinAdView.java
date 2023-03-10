package com.applovin.adview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.applovin.impl.adview.AdViewController;
import com.applovin.impl.sdk.Logger;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinSdk;

/**
 * This class provides a subclass of android.view.View which is used to render AppLovin advertisements.
 */
public class AppLovinAdView
        extends RelativeLayout
{
    /**
     * This is a namespace that should be used for custom properties of <code>AppLovinAdView</code>.
     */
    public static final String NAMESPACE = "http://schemas.applovin.com/android/1.0";

    private AdViewController controller;

    /**
     * Create a new AppLovin ad view for a given size. Default parameters will be used.
     *
     * @param adSize  Size of the ad to display. Must not be null.
     * @param context Parent activity or application context. Must not be null.
     */
    public AppLovinAdView(final AppLovinAdSize adSize, final Context context)
    {
        this( adSize, null, context );
    }

    /**
     * Create a new AppLovin ad view for a given size and zone.
     *
     * @param adSize  Size of the ad to display. Must not be null.
     * @param zoneId  Id of the zone this ALAdView should load ads for.
     * @param context Parent activity or application context. Must not be null.
     */
    public AppLovinAdView(final AppLovinAdSize adSize, final String zoneId, final Context context)
    {
        super( context );
        initializeAdView( adSize, zoneId, null, context, null );
    }

    /**
     * Create a new AppLovin ad view for a given sdk and size.
     *
     * @param sdk     AppLovin SDK to use. Must not be null. An instance of the SDK may be obtained by calling <code>AppLovinSdk.getInstance()</code>. See {@link AppLovinSdk} for more details.
     * @param adSize  Size of the ad to display. Must not be null.
     * @param context Parent activity or application context. Must not be null.
     */
    public AppLovinAdView(final AppLovinSdk sdk, final AppLovinAdSize adSize, final Context context)
    {
        this( sdk, adSize, null, context );
    }

    /**
     * Create a new AppLovin ad view for a given sdk, size, and zone.
     *
     * @param sdk     AppLovin SDK to use. Must not be null. An instance of the SDK may be obtained by calling <code>AppLovinSdk.getInstance()</code>. See {@link AppLovinSdk} for more details.
     * @param adSize  Size of the ad to display. Must not be null.
     * @param zoneId  Id of the zone this ALAdView should load ads for.
     * @param context Parent activity or application context. Must not be null.
     */
    public AppLovinAdView(final AppLovinSdk sdk, final AppLovinAdSize adSize, final String zoneId, final Context context)
    {
        super( context.getApplicationContext() );

        initializeAdView( adSize, zoneId, sdk, context, null );
    }

    /**
     * Create a new AppLovin ad view.
     * <p/>
     * <p>
     * <b>Please note:</b> This constructor should not be invoked manually, it is meant to be called when the view is created from the XML.
     * </p>
     *
     * @hide
     */
    public AppLovinAdView(Context context, AttributeSet attrs)
    {
        this( context, attrs, 0 );
    }

    /**
     * Create a new AppLovin ad view.
     * <p/>
     * <p>
     * <b>Please note:</b> This constructor should not be invoked manually, it is meant to be called when the view is created from the XML.
     * </p>
     *
     * @hide
     */
    public AppLovinAdView(Context context, AttributeSet attrs, int defStyle)
    {
        super( context, attrs, defStyle );

        initializeAdView( null, null, null, context, attrs );
    }

    /**
     * Loads <b>AND</b> displays an ad into the view. This method returns immediately.
     * <p>
     * <b>Please note:</b> To load ad but not display it, use <code>AppLovinSdk.getInstance(context).getAdService().loadNextAd(...)</code> then <code>adView.renderAd(...)</code> to render it.
     */
    public void loadNextAd()
    {
        if ( controller != null )
        {
            controller.loadNextAd();
        }
        else
        {
            Logger.userInfo( "AppLovinSdk", "Unable to load next ad: AppLovinAdView is not initialized." );
        }
    }

    /**
     * Set a callback that would be notified of ad loading events. These include add loaded and ad failed to load events
     *
     * @param callback A callback to be notified of ad loading events.
     */
    public void setAdLoadListener(final AppLovinAdLoadListener callback)
    {
        if ( controller != null )
        {
            controller.setAdLoadListener( callback );
        }
    }

    /**
     * Set a callback that would be notified of ad display events. These include add displayed and ad hidden.
     *
     * @param callback A callback to be notified of ad loading events.
     */
    public void setAdDisplayListener(final AppLovinAdDisplayListener callback)
    {
        if ( controller != null )
        {
            controller.setAdDisplayListener( callback );
        }
    }

    /**
     * Set a callback that would be notified of ad click events.
     *
     * @param callback A callback to be notified of ad click events.
     */
    public void setAdClickListener(final AppLovinAdClickListener callback)
    {
        if ( controller != null )
        {
            controller.setAdClickListener( callback );
        }
    }

    /**
     * Set a callback that would be notified of events specific to this ad view.
     *
     * @param callback A callback to be notified of events specific to this ad view.
     */
    public void setAdViewEventListener(final AppLovinAdViewEventListener callback)
    {
        if ( controller != null )
        {
            controller.setAdViewEventListener( callback );
        }
    }

    /**
     * Render specified ad.
     *
     * @param ad Ad to render. Must not be null.
     */
    public void renderAd(final AppLovinAd ad)
    {
        if ( controller != null )
        {
            controller.renderAd( ad );
        }
    }

    /**
     * Get current ad view size.
     *
     * @return Current size or <code>null</code> if none defined
     */
    public AppLovinAdSize getSize()
    {
        if ( controller != null )
        {
            return controller.getSize();
        }

        return null;
    }

    /**
     * The zone identifier this AppLovinAdView was initialized with and is loading ads for, if any.
     */
    public String getZoneId()
    {
        if ( controller != null )
        {
            return controller.getZoneId();
        }

        return null;
    }

    /**
     * Pause all animations and clear current ad
     */
    public void pause()
    {
        if ( controller != null )
        {
            controller.pause();
        }
    }

    /**
     * Resume all animations and the current ad
     */
    public void resume()
    {
        if ( controller != null )
        {
            controller.resume();
        }
    }

    /**
     * Destroy current ad.
     * <p/>
     * <p>
     * <b>Please note:</b> Ads can not be loaded after the view is destroyed.
     * </p>
     */
    public void destroy()
    {
        if ( controller != null )
        {
            controller.destroy();
        }
    }

    /**
     * This should only be called by the Android system and not the developer.
     *
     * @hide
     */
    @Override
    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();

        if ( controller != null )
        {
            controller.onAttachedToWindow();
        }
    }

    /**
     * This should only be called by the Android system and not the developer.
     *
     * @hide
     */
    @Override
    protected void onDetachedFromWindow()
    {
        if ( controller != null )
        {
            controller.onDetachedFromWindow();
        }

        super.onDetachedFromWindow();
    }

    private void initializeAdView(final AppLovinAdSize size, final String zoneId, final AppLovinSdk sdk, final Context context, final AttributeSet viewAttributes)
    {
        if ( !isInEditMode() )
        {
            controller = new AdViewController();
            controller.initializeAdView( this, context, size, zoneId, sdk, viewAttributes );
        }
        else
        {
            initializeAdViewInEditMode( viewAttributes, context );
        }
    }

    private void initializeAdViewInEditMode(final AttributeSet attrs, final Context context)
    {
        final DisplayMetrics dm = context.getResources().getDisplayMetrics();
        final int adWidth = dm.widthPixels;
        final int adHeight = (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 50, dm );

        final TextView appLovinAd = new TextView( context );
        appLovinAd.setBackgroundColor( Color.rgb( 220, 220, 220 ) );
        appLovinAd.setTextColor( Color.BLACK );
        appLovinAd.setText( "AppLovin Ad" );
        appLovinAd.setGravity( Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL );

        addView( appLovinAd, adWidth, adHeight );
    }

    public AdViewController getController()
    {
        return controller;
    }

    @Override
    public String toString()
    {
        return "AppLovinAdView{" +
                "zoneId='" + getZoneId() + "\"" +
                ", size=" + getSize() +
                '}';
    }
}

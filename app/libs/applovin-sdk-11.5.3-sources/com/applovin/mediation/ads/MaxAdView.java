package com.applovin.mediation.ads;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.applovin.impl.mediation.ads.MaxAdImplBase;
import com.applovin.impl.mediation.ads.MaxAdViewImpl;
import com.applovin.impl.sdk.utils.AdViewUtils;
import com.applovin.impl.sdk.utils.StringUtils;
import com.applovin.impl.sdk.utils.ViewUtils;
import com.applovin.mediation.MaxAdFormat;
import com.applovin.mediation.MaxAdRequestListener;
import com.applovin.mediation.MaxAdRevenueListener;
import com.applovin.mediation.MaxAdReviewListener;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkSettings;

import androidx.annotation.NonNull;

/**
 * This class represents a view-based ad - i.e. banner/leader or MREC.
 */
public class MaxAdView
        extends RelativeLayout
{
    private static final String TAG = "MaxAdView";

    /**
     * This is a namespace that should be used for custom properties of <code>MaxAdView</code>.
     */
    private static final String NAMESPACE         = "http://schemas.applovin.com/android/1.0";
    private static final String ANDROID_NAMESPACE = "http://schemas.android.com/apk/res/android";
    private static final int    DEFAULT_GRAVITY   = Gravity.TOP | Gravity.CENTER_HORIZONTAL;

    private MaxAdViewImpl mImplementation;
    private View          mBackgroundView;
    private int           mScreenVisibility;

    /**
     * Create a new MAX ad view. By default, this creates a banner/leader or MREC.
     *
     * @param adUnitId Ad unit id to load ads for. Must not be null.
     * @param context  Current context. Must not be null.
     */
    public MaxAdView(final String adUnitId, final Context context)
    {
        this( adUnitId, AppLovinSdk.getInstance( context ), context );
    }

    /**
     * Create a new MAX ad view.
     *
     * @param adUnitId Ad unit id to load ads for. Must not be null.
     * @param sdk      SDK to use. Must not be null. An instance of the SDK may be obtained by calling <code>AppLovinSdk.getInstance()</code>. See {@link AppLovinSdk} for more details.
     * @param context  Current context. Must not be null.
     */
    public MaxAdView(final String adUnitId, final AppLovinSdk sdk, final Context context)
    {
        this( adUnitId, AdViewUtils.getDeviceSpecificAdViewAdFormat( context ), sdk, context );
    }

    /**
     * Create a new MAX ad view.
     *
     * @param adUnitId Ad unit id to load ads for. Must not be null.
     * @param adFormat Ad format to load ads for. Must not be null.
     * @param context  Current context. Must not be null.
     */
    public MaxAdView(final String adUnitId, final MaxAdFormat adFormat, final Context context)
    {
        this( adUnitId, adFormat, AppLovinSdk.getInstance( context ), context );
    }

    /**
     * Create a new MAX ad view.
     *
     * @param adUnitId Ad unit id to load ads for. Must not be null.
     * @param adFormat Ad format to load ads for. Must not be null.
     * @param sdk      SDK to use. Must not be null. An instance of the SDK may be obtained by calling {@link AppLovinSdk#getInstance(String, AppLovinSdkSettings, Context)}.
     * @param context  Current context. Must not be null.
     */
    public MaxAdView(final String adUnitId, final MaxAdFormat adFormat, final AppLovinSdk sdk, final Context context)
    {
        super( context.getApplicationContext() );

        MaxAdImplBase.logApiCall( TAG, "MaxAdView(adUnitId=" + adUnitId + ", adFormat=" + adFormat + ", sdk=" + sdk + ")" );

        initializeAdView( adUnitId, adFormat, DEFAULT_GRAVITY, sdk, context );
    }

    /**
     * Set a listener that will be notified about ad events.
     *
     * @param listener Listener to be notified. May be null.
     */
    public void setListener(final MaxAdViewAdListener listener)
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
     * Load the next banner or MREC ad.
     * <p>
     * Use {@link MaxAdView#setListener(MaxAdViewAdListener)} to assign a listener that should be notified about ad load state.
     */
    public void loadAd()
    {
        mImplementation.logApiCall( "loadAd()" );
        mImplementation.loadAd();
    }

    /**
     * Starts or resumes auto-refreshing of the banner/leader or MREC.
     */
    public void startAutoRefresh()
    {
        mImplementation.logApiCall( "startAutoRefresh()" );
        mImplementation.startAutoRefresh();
    }

    /**
     * Pauses auto-refreshing of the banner/leader or MREC.
     */
    public void stopAutoRefresh()
    {
        mImplementation.logApiCall( "stopAutoRefresh()" );
        mImplementation.stopAutoRefresh();
    }

    /**
     * Set the placement to tie the future ad events to.
     */
    public void setPlacement(final String placement)
    {
        mImplementation.logApiCall( "setPlacement(placement=" + placement + ")" );
        mImplementation.setPlacement( placement );
    }

    /**
     * Get the placement to which the ad events are tied to.
     */
    public String getPlacement()
    {
        mImplementation.logApiCall( "getPlacement()" );
        return mImplementation.getPlacement();
    }

    /**
     * The ad unit identifier this {@link MaxAdView} was initialized with and is loading ads for.
     */
    public String getAdUnitId()
    {
        return mImplementation.getAdUnitId();
    }

    /**
     * The format of the ad view.
     */
    public MaxAdFormat getAdFormat()
    {
        return mImplementation.getAdFormat();
    }

    /**
     * Destroy current ad and fully remove it from memory in the next GC cycle.
     */
    public void destroy()
    {
        mImplementation.destroy();
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
     * The custom data to tie the showing ad to, for ILRD and rewarded postbacks via the @c {CUSTOM_DATA}  macro. Maximum size is 8KB.
     */
    public void setCustomData(final String value)
    {
        mImplementation.logApiCall( "setCustomData(value=" + value + ")" );
        mImplementation.setCustomData( value );
    }

    /**
     * <b>Please note:</b> This constructor should not be invoked manually, it is meant to be called when the view is created from the XML.
     *
     * @hide
     */
    public MaxAdView(Context context, AttributeSet attrs)
    {
        this( context, attrs, 0 );
    }

    /**
     * <b>Please note:</b> This constructor should not be invoked manually, it is meant to be called when the view is created from the XML.
     *
     * @hide
     */
    public MaxAdView(Context context, AttributeSet attrs, int defStyle)
    {
        super( context, attrs, defStyle );

        // Extract ad unit and ad format from the attribute set
        final String adUnitId = AdViewUtils.getAttributeStringValue( context, attrs, NAMESPACE, "adUnitId" );

        final String adFormatValue = AdViewUtils.getAttributeStringValue( context, attrs, NAMESPACE, "adFormat" );
        final MaxAdFormat adFormat;
        if ( StringUtils.isValidString( adFormatValue ) )
        {
            adFormat = MaxAdFormat.formatFromString( adFormatValue );
        }
        else // banner/leader or MREC won't require a specified ad format in the XML for backwards compatibility
        {
            adFormat = AdViewUtils.getDeviceSpecificAdViewAdFormat( context );
        }

        final int gravity = attrs.getAttributeIntValue( ANDROID_NAMESPACE, "gravity", DEFAULT_GRAVITY );

        if ( adUnitId == null ) throw new IllegalArgumentException( "No ad unit ID specified" );
        if ( TextUtils.isEmpty( adUnitId ) ) throw new IllegalArgumentException( "Empty ad unit ID specified" );

        initializeAdView( adUnitId, adFormat, gravity, AppLovinSdk.getInstance( context ), context );
    }

    private void initializeAdView(final String adUnitId, final MaxAdFormat adFormat, final int gravity, final AppLovinSdk sdk, final Context context)
    {
        if ( !isInEditMode() )
        {
            mBackgroundView = new View( context.getApplicationContext() );
            mBackgroundView.setBackgroundColor( Color.TRANSPARENT );

            addView( mBackgroundView );
            mBackgroundView.setLayoutParams( new RelativeLayout.LayoutParams( LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT ) );

            mScreenVisibility = getVisibility();

            mImplementation = new MaxAdViewImpl( adUnitId.trim(), adFormat, this, mBackgroundView, sdk.coreSdk, context );

            setGravity( gravity );

            // If color has been set by the publisher in Layout Editor - use that
            if ( getBackground() instanceof ColorDrawable )
            {
                ColorDrawable colorDrawable = (ColorDrawable) getBackground();
                setBackgroundColor( colorDrawable.getColor() );
            }

            // Container will always be transparent - so "background" view and ad content transparencies are separated
            super.setBackgroundColor( Color.TRANSPARENT );
        }
        else
        {
            initializeAdViewInEditMode( adFormat, context );
        }
    }

    private void initializeAdViewInEditMode(final MaxAdFormat adFormat, final Context context)
    {
        final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        final int adWidth;
        if ( adFormat == MaxAdFormat.MREC )
        {
            adWidth = (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, adFormat.getSize().getWidth(), displayMetrics );
        }
        else // banner/leader or MRECs
        {
            adWidth = displayMetrics.widthPixels;
        }

        final int adHeight = (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, adFormat.getSize().getHeight(), displayMetrics );

        final TextView adPlaceholder = new TextView( context );
        adPlaceholder.setBackgroundColor( Color.rgb( 220, 220, 220 ) );
        adPlaceholder.setTextColor( Color.BLACK );
        adPlaceholder.setText( "AppLovin MAX Ad" );
        adPlaceholder.setGravity( Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL );

        addView( adPlaceholder, adWidth, adHeight );
    }

    @Override
    public void setBackgroundColor(final int color)
    {
        mImplementation.logApiCall( "setBackgroundColor(color=" + color + ")" );

        // Null check for this being called during view inflation
        if ( mImplementation != null ) mImplementation.setPublisherBackgroundColor( color );
        if ( mBackgroundView != null ) mBackgroundView.setBackgroundColor( color );
    }

    @Override
    public void setAlpha(final float alpha)
    {
        mImplementation.logApiCall( "setAlpha(alpha=" + alpha + ")" );

        // Null check for this being called during view inflation
        if ( mBackgroundView != null ) mBackgroundView.setAlpha( alpha );
    }

    @Override
    @NonNull
    public String toString()
    {
        // Null check for this being called during view inflation
        return ( mImplementation != null ) ? mImplementation.toString() : "MaxAdView";
    }

    @Override
    protected void onWindowVisibilityChanged(final int visibility)
    {
        super.onWindowVisibilityChanged( visibility );

        mImplementation.logApiCall( "onWindowVisibilityChanged(visibility=" + visibility + ")" );

        // Null check for this being called during view inflation.
        // only forward visibility change if the view actually changed to/from View.VISIBLE state.
        if ( mImplementation != null && ViewUtils.hasVisibilityChanged( mScreenVisibility, visibility ) ) mImplementation.onWindowVisibilityChanged( visibility );
        mScreenVisibility = visibility;
    }
}

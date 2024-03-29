package com.applovin.mediation;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import com.applovin.impl.mediation.model.MediatedAdViewAd;
import com.applovin.impl.mediation.utils.MediationUtils;
import com.applovin.impl.sdk.Logger;
import com.applovin.sdk.AppLovinSdkUtils;

/**
 * This class defines a format of an ad.
 */
public class MaxAdFormat
{
    /**
     * Represents a 320x50 banner advertisement.
     */
    public static final MaxAdFormat BANNER = new MaxAdFormat( "BANNER", "Banner" );

    /**
     * Represents a 300x250 rectangular advertisement.
     */
    public static final MaxAdFormat MREC = new MaxAdFormat( "MREC", "MREC" );

    /**
     * Represents a 728x90 leaderboard advertisement (for tablets).
     */
    public static final MaxAdFormat LEADER = new MaxAdFormat( "LEADER", "Leader" );

    /**
     * Represents a full-screen advertisement.
     */
    public static final MaxAdFormat INTERSTITIAL = new MaxAdFormat( "INTER", "Interstitial" );

    /**
     * Similar to {@link MaxAdFormat#INTERSTITIAL}, except that it is shown upon opening the app.
     */
    public static final MaxAdFormat APP_OPEN = new MaxAdFormat( "APPOPEN", "App Open" );

    /**
     * Similar to {@link MaxAdFormat#INTERSTITIAL}, except that users are given a reward at the end of the advertisement.
     */
    public static final MaxAdFormat REWARDED = new MaxAdFormat( "REWARDED", "Rewarded" );

    /**
     * Represents a fullscreen ad which the user can skip and be granted a reward upon successful completion of the ad.
     */
    public static final MaxAdFormat REWARDED_INTERSTITIAL = new MaxAdFormat( "REWARDED_INTER", "Rewarded Interstitial" );

    /**
     * Represents a native advertisement.
     */
    public static final MaxAdFormat NATIVE = new MaxAdFormat( "NATIVE", "Native" );

    /**
     * Represents a cross promo advertisement.
     */
    public static final MaxAdFormat CROSS_PROMO = new MaxAdFormat( "XPROMO", "Cross Promo" );

    /**
     * Create a {@link MaxAdFormat} object from a string representation.
     *
     * @param formatName Name of the format.
     *
     * @return Ad format or null in case input string is null or empty.
     */
    public static MaxAdFormat formatFromString(final String formatName)
    {
        if ( TextUtils.isEmpty( formatName ) ) return null;

        // NOTE: {@link String#equalsIgnoreCase} is locale-sensitive :)
        if ( formatName.equalsIgnoreCase( "banner" ) )
        {
            return BANNER;
        }
        else if ( formatName.equalsIgnoreCase( "mrec" ) )
        {
            return MREC;
        }
        else if ( formatName.equalsIgnoreCase( "xpromo" ) )
        {
            return CROSS_PROMO;
        }
        else if ( formatName.equalsIgnoreCase( "native" ) )
        {
            return NATIVE;
        }
        else if ( formatName.equalsIgnoreCase( "leaderboard" ) || formatName.equalsIgnoreCase( "leader" ) )
        {
            return LEADER;
        }
        else if ( formatName.equalsIgnoreCase( "interstitial" ) || formatName.equalsIgnoreCase( "inter" ) )
        {
            return INTERSTITIAL;
        }
        else if ( formatName.equalsIgnoreCase( "appopen" ) || formatName.equalsIgnoreCase( "app_open" ) )
        {
            return APP_OPEN;
        }
        else if ( formatName.equalsIgnoreCase( "rewarded" ) || formatName.equalsIgnoreCase( "reward" ) )
        {
            return REWARDED;
        }
        else if ( formatName.equalsIgnoreCase( "rewarded_inter" ) || formatName.equalsIgnoreCase( "rewarded_interstitial" ) )
        {
            return REWARDED_INTERSTITIAL;
        }
        else
        {
            if ( Logger.isVerbose() )
            {
                Logger.userError( Logger.SDK_TAG, "Unknown ad format: " + formatName );
            }
            return null;
        }
    }

    private final String label;
    private final String displayName;

    private MaxAdFormat(final String label, final String displayName)
    {
        this.label = label;
        this.displayName = displayName;
    }

    /**
     * @return The ad format name as "BANNER", "MREC", "INTER", "REWARDED", etc.
     */
    public String getLabel()
    {
        return label;
    }

    /**
     * @return The size of the AdView format ad, or Size with (width: 0, height: 0) otherwise.
     */
    public AppLovinSdkUtils.Size getSize()
    {
        if ( this == BANNER )
        {
            return new AppLovinSdkUtils.Size( 320, 50 );
        }
        else if ( this == LEADER )
        {
            return new AppLovinSdkUtils.Size( 728, 90 );
        }
        else if ( this == MREC )
        {
            return new AppLovinSdkUtils.Size( 300, 250 );
        }
        else if ( this == CROSS_PROMO )
        {
            return new AppLovinSdkUtils.Size( MediatedAdViewAd.ADSIZE_SPAN, MediatedAdViewAd.ADSIZE_SPAN );
        }
        else
        {
            return new AppLovinSdkUtils.Size( 0, 0 );
        }
    }

    /**
     * Get the adaptive banner size for the screen width at the current orientation.
     * <p>
     * NOTE: The height is currently the only "adaptive" dimension and the width will span the screen.
     * NOTE: Only AdMob / Google Ad Manager currently has support for adaptive banners and the maximum height is 15% the height of the screen.
     */
    public AppLovinSdkUtils.Size getAdaptiveSize(final Activity activity)
    {
        return getAdaptiveSize( MediatedAdViewAd.ADSIZE_SPAN, activity );
    }

    /**
     * Get the adaptive banner {@code AppLovinSdkUtils#Size} for the provided width at the current orientation.
     * <p>
     * NOTE: The height is currently the only "adaptive" dimension and the provided width will be passed back in the returned {@code AppLovinSdkUtils#Size}.
     * NOTE: Only AdMob / Google Ad Manager currently has support for adaptive banners.
     *
     * @param widthDp The width in density-independent pixels to retrieve the adaptive banner size for.
     * @param context The {@link Context} in which the banner will be shown.
     *
     * @return The adaptive banner size for the current orientation and width.
     */
    public AppLovinSdkUtils.Size getAdaptiveSize(final int widthDp, final Context context)
    {
        if ( this == BANNER || this == LEADER )
        {
            return MediationUtils.getAdaptiveBannerAdSize( widthDp, this, context );
        }
        else
        {
            return getSize();
        }
    }

    /**
     * @return {@code true} if the ad format is an interstitial, rewarded, or rewarded interstitial.
     */
    public boolean isFullscreenAd()
    {
        return this == INTERSTITIAL || this == APP_OPEN || this == REWARDED || this == REWARDED_INTERSTITIAL;
    }

    /**
     * @return {@code true} if the ad format is a banner, leader, or MREC.
     */
    public boolean isAdViewAd()
    {
        return this == BANNER || this == MREC || this == LEADER || this == CROSS_PROMO;
    }

    @Override
    public String toString()
    {
        return "MaxAdFormat{" +
                "label='" + label + '\'' +
                '}';
    }

    /**
     * @deprecated Use {@link #getLabel()} instead.
     */
    @Deprecated
    public String getDisplayName()
    {
        return displayName;
    }
}

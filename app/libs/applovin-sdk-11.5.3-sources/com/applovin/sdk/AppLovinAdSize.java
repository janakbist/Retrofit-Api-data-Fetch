package com.applovin.sdk;

import java.util.Locale;

/**
 * This class defines the possible sizes of an ad.
 */
public class AppLovinAdSize
{
    public static final int SPAN = -1;

    /**
     * Represents a 350x50 banner advertisement.
     */
    public static final AppLovinAdSize BANNER = new AppLovinAdSize( SPAN, 50, "BANNER" );

    /**
     * Represents a 728x90 leaderboard advertisement indented for tablets.
     */
    public static final AppLovinAdSize LEADER = new AppLovinAdSize( SPAN, 90, "LEADER" );

    /**
     * Represents a 300x250 rectangular advertisement.
     */
    public static final AppLovinAdSize MREC = new AppLovinAdSize( 300, 250, "MREC" );

    /**
     * Represents a full-screen advertisement.
     */
    public static final AppLovinAdSize INTERSTITIAL = new AppLovinAdSize( SPAN, SPAN, "INTER" );

    /**
     * Represents a cross-promo advertisement. The size of the ad will expand to the size of the view.
     */
    public static final AppLovinAdSize CROSS_PROMO = new AppLovinAdSize( SPAN, SPAN, "XPROMO" );

    /**
     * Represents a native advertisement. Can be integrated seemlessly into the environment of your app.
     */
    public static final AppLovinAdSize NATIVE = new AppLovinAdSize( SPAN, SPAN, "NATIVE" );

    private final String label;
    private final int    width;
    private final int    height;

    /**
     * Create an ad size.
     * <p>
     * <b>Please note:</b> This constructor is for internal use only.
     * </p>
     *
     * @param width  Ad width. Must be a positive number or 0 if label is specified.
     * @param height Ad height. Must be a positive number or 0 if label is specified.
     * @param label  Size name. Must not be null. Should be shorter then 9 characters.
     *
     * @hide
     */
    private AppLovinAdSize(int width, int height, String label)
    {
        this.width = width;
        this.height = height;
        this.label = label;
    }

    /**
     * Get the default width of the AdView (banner, leader, mrec) ad. Returns -1 if should span width of screen.
     *
     * @return The default width of the AdView (banner, leader, mrec) ad, -1 if should span width of screen.
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * Get the default height of the AdView (banner, leader, mrec) ad.
     *
     * @return The default height of the AdView (banner, leader, mrec) ad.
     */
    public int getHeight()
    {
        return height;
    }

    /**
     * Get a name of the ad size
     *
     * @return Name of the ad size.
     */
    public String getLabel()
    {
        return label.toUpperCase( Locale.ENGLISH );
    }

    /**
     * Simply calls <code>getLabel()</code>.
     *
     * @return Name of the ad size.
     */
    @Override
    public String toString()
    {
        return getLabel();
    }

    /**
     * Create a size object from a string representation
     *
     * @param sizeName Name of the size.
     *
     * @return Ad size or null in case input string is null or unknown.
     */
    public static AppLovinAdSize fromString(String sizeName)
    {
        if ( "BANNER".equalsIgnoreCase( sizeName ) )
        {
            return AppLovinAdSize.BANNER;
        }
        else if ( "MREC".equalsIgnoreCase( sizeName ) )
        {
            return AppLovinAdSize.MREC;
        }
        else if ( "LEADER".equalsIgnoreCase( sizeName ) )
        {
            return AppLovinAdSize.LEADER;
        }
        else if ( "INTERSTITIAL".equalsIgnoreCase( sizeName ) || "INTER".equalsIgnoreCase( sizeName ) )
        {
            return AppLovinAdSize.INTERSTITIAL;
        }
        else if ( "XPROMO".equalsIgnoreCase( sizeName ) )
        {
            return AppLovinAdSize.CROSS_PROMO;
        }
        else if ( "NATIVE".equalsIgnoreCase( sizeName ) )
        {
            return AppLovinAdSize.NATIVE;
        }
        else
        {
            throw new IllegalArgumentException( "Unknown Ad Size: " + sizeName );
        }
    }
}

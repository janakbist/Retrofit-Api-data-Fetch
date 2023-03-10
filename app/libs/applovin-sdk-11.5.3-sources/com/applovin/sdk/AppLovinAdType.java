package com.applovin.sdk;

import java.util.Locale;


/**
 * This class defines a categorization of the behavior of an ad.
 */
public class AppLovinAdType
{
    /**
     * Represents a standard advertisement that does not provide a reward to the user.
     */
    public static final AppLovinAdType REGULAR = new AppLovinAdType( "REGULAR" );

    /**
     * Represents a standard advertisement that does not provide a reward to the user and is shown upon opening the app.
     */
    public static final AppLovinAdType APP_OPEN = new AppLovinAdType( "APPOPEN" );

    /**
     * Represents a rewarded ad which will provide the user virtual currency upon completion.
     */
    public static final AppLovinAdType INCENTIVIZED = new AppLovinAdType( "VIDEOA" );

    /**
     * Represents a rewarded interstitial ad which the user can skip and be granted a reward upon successful completion of the ad.
     */
    public static final AppLovinAdType AUTO_INCENTIVIZED = new AppLovinAdType( "AUTOREW" );

    /**
     * Represents a native ad which can be integrated seamlessly into the environment of your app.
     */
    public static final AppLovinAdType NATIVE = new AppLovinAdType( "NATIVE" );

    private final String label;

    /**
     * @hide
     **/
    public static AppLovinAdType fromString(String type)
    {
        if ( "REGULAR".equalsIgnoreCase( type ) )
        {
            return REGULAR;
        }
        else if ( "APPOPEN".equalsIgnoreCase( type ) )
        {
            return APP_OPEN;
        }
        else if ( "VIDEOA".equalsIgnoreCase( type ) )
        {
            return INCENTIVIZED;
        }
        else if ( "AUTOREW".equalsIgnoreCase( type ) )
        {
            return AUTO_INCENTIVIZED;
        }
        else if ( "NATIVE".equalsIgnoreCase( type ) )
        {
            return NATIVE;
        }
        else
        {
            throw new IllegalArgumentException( "Unknown Ad Type: " + type );
        }
    }

    /**
     * @hide
     **/
    private AppLovinAdType(String label)
    {
        this.label = label;
    }

    /**
     * @hide
     **/
    public String getLabel()
    {
        return label.toUpperCase( Locale.ENGLISH );
    }

    /**
     * Simply calls <code>getLabel()</code>.
     *
     * @return Name of the ad type.
     */
    @Override
    public String toString()
    {
        return getLabel();
    }
}

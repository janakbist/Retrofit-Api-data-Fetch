package com.applovin.sdk;

import android.content.Context;

import com.applovin.impl.sdk.ComplianceManager;
import com.applovin.impl.sdk.Logger;

/**
 * This class contains privacy settings for AppLovin.
 */
public class AppLovinPrivacySettings
{
    private static final String TAG = "AppLovinPrivacySettings";

    /**
     * Set whether or not user has provided consent for information sharing.
     *
     * @param hasUserConsent {@code true} if the user has provided consent for information sharing.
     * @param context        Parent activity or application context. Must not be {@code null}.
     */
    public static void setHasUserConsent(final boolean hasUserConsent, final Context context)
    {
        Logger.userDebug( TAG, "setHasUserConsent()" );

        final boolean isValueChanged = ComplianceManager.setHasUserConsent( hasUserConsent, context );
        if ( isValueChanged )
        {
            AppLovinSdk.reinitializeAll( hasUserConsent, null, null );
        }
    }

    /**
     * Check if user has provided consent for information sharing.
     *
     * @param context Parent activity or application context. Must not be {@code null}.
     *
     * @return {@code true} if user has provided consent for information sharing. {@code false} if the user declined to share information or the consent value has not been set (see {@link #isUserConsentSet(Context)}).
     */
    public static boolean hasUserConsent(final Context context)
    {
        Logger.userDebug( TAG, "hasUserConsent()" );

        final Boolean hasUserConsent = ComplianceManager.hasUserConsent().getValue( context );
        if ( hasUserConsent != null )
        {
            return hasUserConsent;
        }
        else
        {
            return false;
        }
    }

    /**
     * Check if user has set consent for information sharing.
     *
     * @param context Parent activity or application context. Must not be {@code null}.
     *
     * @return {@code true} if user has set a value of consent for information sharing.
     */
    public static boolean isUserConsentSet(final Context context)
    {
        Logger.userDebug( TAG, "isUserConsentSet()" );

        return ComplianceManager.hasUserConsent().getValue( context ) != null;
    }

    /**
     * Mark user as age restricted (i.e. under 16).
     *
     * @param isAgeRestrictedUser {@code true} if the user is age restricted (i.e. under 16).
     * @param context             Parent activity or application context. Must not be {@code null}.
     */
    public static void setIsAgeRestrictedUser(final boolean isAgeRestrictedUser, final Context context)
    {
        Logger.userDebug( TAG, "setIsAgeRestrictedUser()" );

        final boolean isValueChanged = ComplianceManager.setIsAgeRestrictedUser( isAgeRestrictedUser, context );
        if ( isValueChanged )
        {
            AppLovinSdk.reinitializeAll( null, isAgeRestrictedUser, null );
        }
    }

    /**
     * Check if user is age restricted.
     *
     * @param context Parent activity or application context. Must not be {@code null}.
     *
     * @return {@code true} if the user is age-restricted. {@code false} if the user is not age-restricted or the age-restriction value has not been set (see {@link #isAgeRestrictedUserSet(Context)}).
     */
    public static boolean isAgeRestrictedUser(final Context context)
    {
        Logger.userDebug( TAG, "isAgeRestrictedUser()" );

        final Boolean isAgeRestrictedUser = ComplianceManager.isAgeRestrictedUser().getValue( context );
        if ( isAgeRestrictedUser != null )
        {
            return isAgeRestrictedUser;
        }
        else
        {
            return false;
        }
    }

    /**
     * Check if user has set its age restricted settings.
     *
     * @param context Parent activity or application context. Must not be {@code null}.
     *
     * @return {@code true} if user has set its age restricted settings.
     */
    public static boolean isAgeRestrictedUserSet(final Context context)
    {
        Logger.userDebug( TAG, "isAgeRestrictedUserSet()" );

        return ComplianceManager.isAgeRestrictedUser().getValue( context ) != null;
    }

    /**
     * Set whether or not user has opted out of the sale of their personal information.
     *
     * @param doNotSell {@code true} if the user has opted out of the sale of their personal information.
     * @param context   Parent activity or application context. Must not be {@code null}.
     */
    public static void setDoNotSell(final boolean doNotSell, final Context context)
    {
        Logger.userDebug( TAG, "setDoNotSell()" );

        final boolean isValueChanged = ComplianceManager.setDoNotSell( doNotSell, context );
        if ( isValueChanged )
        {
            AppLovinSdk.reinitializeAll( null, null, doNotSell );
        }
    }

    /**
     * Check if the user has opted out of the sale of their personal information.
     *
     * @param context Parent activity or application context. Must not be {@code null}.
     *
     * @return {@code true} if user has opted out of the sale of their personal information. {@code false} if the user opted in to the sale of their personal information or the value has not been set (see {@link #isDoNotSellSet(Context)}).
     */
    public static boolean isDoNotSell(final Context context)
    {
        Logger.userDebug( TAG, "isDoNotSell()" );

        final Boolean isDoNotSell = ComplianceManager.doNotSell().getValue( context );
        if ( isDoNotSell != null )
        {
            return isDoNotSell;
        }
        else
        {
            return false;
        }
    }

    /**
     * Check if the user has set the option to sell their personal information.
     *
     * @param context Parent activity or application context. Must not be {@code null}.
     *
     * @return {@code true} if user has chosen an option to sell their personal information.
     */
    public static boolean isDoNotSellSet(final Context context)
    {
        Logger.userDebug( TAG, "isDoNotSellSet()" );

        return ComplianceManager.doNotSell().getValue( context ) != null;
    }
}

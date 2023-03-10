package com.applovin.sdk;

import android.content.Context;
import android.text.TextUtils;

import com.applovin.impl.sdk.CoreSdk;
import com.applovin.impl.sdk.Logger;
import com.applovin.impl.sdk.utils.CollectionUtils;
import com.applovin.impl.sdk.utils.StringUtils;
import com.applovin.impl.sdk.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * This class contains settings for AppLovin SDK.
 *
 * @author Basil Shikin
 */
public class AppLovinSdkSettings
{
    private static final String TAG = "AppLovinSdkSettings";

    private boolean isVerboseLoggingEnabled;
    private boolean muted;
    private boolean creativeDebuggerEnabled;
    private boolean exceptionHandlerEnabled;
    private boolean locationCollectionEnabled;
    private boolean failAdDisplayIfDontKeepActivitiesIsEnabled = true;

    // Values to set once SDK is attached
    private String testModeNetworkToSet;

    private final Map<String, Object> localSettings            = CollectionUtils.map(); // NOTE: do not rename `localSettings` - it is used internally via reflection.
    private final Map<String, String> metaData                 = CollectionUtils.map(); // NOTE: on not rename `metaData` - it is used internally via reflection.
    private       List<String>        testDeviceAdvertisingIds = Collections.emptyList();
    private       List<String>        initializationAdUnitIds  = Collections.emptyList();
    private final Map<String, String> extraParameters          = CollectionUtils.map();
    private final Object              extraParametersLock      = new Object();

    private CoreSdk sdk;

    /**
     * Creates an instance of AppLovin SDK's settings object with the given context to extract.
     */
    public AppLovinSdkSettings(final Context context)
    {
        this.isVerboseLoggingEnabled = Utils.isVerboseLoggingEnabled( context );
        this.creativeDebuggerEnabled = true;
        this.exceptionHandlerEnabled = true;
        this.locationCollectionEnabled = true;
    }

    /**
     * Enable devices to receive test ads, by passing in the advertising identifier (GAID) of each test device.
     * Refer to AppLovin logs for the GAID of your current device.
     */
    public void setTestDeviceAdvertisingIds(final List<String> testDeviceAdvertisingIds)
    {
        // Sanitize input and make copy of the list
        if ( testDeviceAdvertisingIds != null )
        {
            final List<String> sanitized = new ArrayList<>( testDeviceAdvertisingIds.size() );

            for ( final String advertisingId : testDeviceAdvertisingIds )
            {
                if ( advertisingId != null && advertisingId.length() == 36 )
                {
                    sanitized.add( advertisingId );
                }
                else
                {
                    if ( Logger.isVerbose() )
                    {
                        Logger.userError( TAG, "Unable to set test device advertising id (" + advertisingId + ") - please make sure it is in the format of xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx" );
                    }
                }
            }

            this.testDeviceAdvertisingIds = sanitized;
        }
        else
        {
            this.testDeviceAdvertisingIds = Collections.emptyList();
        }
    }

    /**
     * Get the list of advertising identifiers that will receive test ads.
     */
    public List<String> getTestDeviceAdvertisingIds()
    {
        return testDeviceAdvertisingIds;
    }

    /**
     * Set the MAX ad unit ids that will be used for this instance of the SDK. 3rd-party SDKs will be initialized with the credentials configured for these ad unit ids.
     */
    public void setInitializationAdUnitIds(final List<String> initializationAdUnitIds)
    {
        // Sanitize input and make copy of the list
        if ( initializationAdUnitIds != null )
        {
            final List<String> sanitized = new ArrayList<>( initializationAdUnitIds.size() );

            for ( final String initializationAdUnitId : initializationAdUnitIds )
            {
                // Check for empty string from upstream deserialization of "" into [""], ignore...
                if ( StringUtils.isValidString( initializationAdUnitId ) && initializationAdUnitId.length() > 0 )
                {
                    // Correct length
                    if ( initializationAdUnitId.length() == 16 )
                    {
                        sanitized.add( initializationAdUnitId );
                    }
                    // Incorrect length
                    else
                    {
                        if ( Logger.isVerbose() )
                        {
                            Logger.userError( TAG, "Unable to set initialization ad unit id (" + initializationAdUnitId + ") - please make sure it is in the format of XXXXXXXXXXXXXXXX" );
                        }
                    }
                }
            }

            this.initializationAdUnitIds = sanitized;
        }
        else
        {
            this.initializationAdUnitIds = Collections.emptyList();
        }
    }

    /**
     * Get the list of MAX ad unit ids that will be used for this instance of the SDK.
     */
    public List<String> getInitializationAdUnitIds()
    {
        return initializationAdUnitIds;
    }

    /**
     * Toggle verbose logging of AppLovin SDK. If enabled AppLovin messages will appear in standard application log accessible via logcat. All log messages will have "AppLovinSdk" tag.
     *
     * @param isVerboseLoggingEnabled True if log messages should be output.
     */
    public void setVerboseLogging(boolean isVerboseLoggingEnabled)
    {
        // If enabled from Android manifest, ignore programmatic setting.
        // This makes life easier for PubOps folks when mediation networks override this setting.
        if ( Utils.isVerboseLoggingConfigured() )
        {
            if ( Logger.isVerbose() )
            {
                Logger.userError( TAG, "Ignoring setting of verbose logging - it is configured from Android manifest already." );
            }

            if ( Utils.isVerboseLoggingEnabled( null ) != isVerboseLoggingEnabled )
            {
                Logger.userError( TAG, "Attempted to programmatically set verbose logging flag to value different from value configured in Android Manifest." );
            }
        }
        else
        {
            this.isVerboseLoggingEnabled = isVerboseLoggingEnabled;
        }
    }

    /**
     * Check if verbose logging is enabled for the AppLovin SDK.
     * <p/>
     * If enabled AppLovin messages will appear in standard application log accessible via logcat. All log messages will have "AppLovinSdk" tag.
     */
    public boolean isVerboseLoggingEnabled()
    {
        return isVerboseLoggingEnabled;
    }

    /**
     * Whether video ads begin in a muted state or not. Defaults to {@code false}.
     */
    public boolean isMuted()
    {
        return muted;
    }

    /**
     * Set whether to begin video ads in a muted state or not.
     *
     * @param muted If ads should begin in a muted state.
     */
    public void setMuted(boolean muted)
    {
        this.muted = muted;
    }

    /**
     * Set whether the creative debugger will be displayed on fullscreen ads after flipping the device screen down twice. Defaults to {@code true}.
     */
    public void setCreativeDebuggerEnabled(boolean creativeDebuggerEnabled)
    {
        this.creativeDebuggerEnabled = creativeDebuggerEnabled;
    }

    /**
     * Whether the creative debugger will be displayed on fullscreen ads after flipping the device screen down twice. Defaults to {@code true}.
     */
    public boolean isCreativeDebuggerEnabled()
    {
        return creativeDebuggerEnabled;
    }

    /**
     * Set whether or not the AppLovin SDK listens to exceptions. Defaults to {@code true}.
     */
    public void setExceptionHandlerEnabled(boolean exceptionHandlerEnabled)
    {
        this.exceptionHandlerEnabled = exceptionHandlerEnabled;
    }

    /**
     * Whether or not the AppLovin SDK listens to exceptions. Defaults to {@code true}.
     */
    public boolean isExceptionHandlerEnabled()
    {
        return exceptionHandlerEnabled;
    }

    /**
     * Set whether or not the AppLovin SDK will collect the device location if available. Defaults to {@code true}.
     */
    public void setLocationCollectionEnabled(final boolean locationCollectionEnabled)
    {
        this.locationCollectionEnabled = locationCollectionEnabled;
    }

    /**
     * Whether or not the AppLovin SDK will collect the device location if available.  Defaults to {@code true}.
     */
    public boolean isLocationCollectionEnabled()
    {
        return locationCollectionEnabled;
    }

    /**
     * A copy of the extra parameters that are currently set.
     */
    public Map<String, String> getExtraParameters()
    {
        synchronized ( extraParametersLock )
        {
            // Note: Returning a copy of the extra parameters so that our copy may not be modified by the publisher.
            return CollectionUtils.map( extraParameters );
        }
    }

    /**
     * Set an extra parameter to pass to the AppLovin server.
     *
     * @param key   Parameter key. Must not be null.
     * @param value Parameter value. May be null.
     */
    public void setExtraParameter(final String key, @Nullable final String value)
    {
        if ( TextUtils.isEmpty( key ) )
        {
            if ( Logger.isVerbose() )
            {
                Logger.userError( TAG, "Failed to set extra parameter for null or empty key: " + key );
            }
            return;
        }

        final String sanitizedValue = ( value != null ) ? value.trim() : null;

        synchronized ( extraParametersLock )
        {
            extraParameters.put( key, sanitizedValue );
        }

        if ( "test_mode_network".equalsIgnoreCase( key ) )
        {
            if ( sdk != null )
            {
                if ( StringUtils.isValidString( sanitizedValue ) )
                {
                    sdk.getTestModeService().setForceNetworkEnabled( true );
                    sdk.getTestModeService().setNetwork( sanitizedValue );
                }
                else
                {
                    sdk.getTestModeService().setForceNetworkEnabled( false );
                    sdk.getTestModeService().setNetwork( null );
                }
            }
            else
            {
                testModeNetworkToSet = sanitizedValue;
            }
        }
    }

    protected void attachAppLovinSdk(CoreSdk sdk)
    {
        this.sdk = sdk;

        if ( StringUtils.isValidString( testModeNetworkToSet ) )
        {
            sdk.getTestModeService().setForceNetworkEnabled( true );
            sdk.getTestModeService().setNetwork( testModeNetworkToSet );

            testModeNetworkToSet = null;
        }
    }

    /**
     * The SDK will automatically fail fullscreen ad display and invoke the {@code com.applovin.mediation.MaxAdListener#onAdDisplayFailed(...)}
     * when the "Don't Keep Activities" developer setting is enabled. This setting allows bypassing that.
     * <p>
     * This flag will only be honored in debuggable builds.
     *
     * @param shouldFailAdDisplayIfDontKeepActivitiesIsEnabled Set to @{code false} to disable the SDK automatically failing fullscreen ad display when the "Don't Keep Activities" developer setting is enabled.
     */
    public void setShouldFailAdDisplayIfDontKeepActivitiesIsEnabled(final boolean shouldFailAdDisplayIfDontKeepActivitiesIsEnabled)
    {
        this.failAdDisplayIfDontKeepActivitiesIsEnabled = shouldFailAdDisplayIfDontKeepActivitiesIsEnabled;
    }

    /**
     * Whether or not the SDK will automatically fail fullscreen ad display when the "Don't Keep Activities" developer setting is enabled. Defaults to {@code true}.
     */
    public boolean shouldFailAdDisplayIfDontKeepActivitiesIsEnabled()
    {
        return failAdDisplayIfDontKeepActivitiesIsEnabled;
    }

    @Override
    @NonNull
    public String toString()
    {
        return "AppLovinSdkSettings{" +
                "isVerboseLoggingEnabled=" + isVerboseLoggingEnabled +
                ", muted=" + muted +
                ", testDeviceAdvertisingIds=" + testDeviceAdvertisingIds.toString() +
                ", initializationAdUnitIds=" + initializationAdUnitIds.toString() +
                ", creativeDebuggerEnabled=" + creativeDebuggerEnabled +
                ", exceptionHandlerEnabled=" + exceptionHandlerEnabled +
                ", locationCollectionEnabled=" + locationCollectionEnabled +
                '}';
    }
}

package com.applovin.sdk;

import android.content.Context;
import android.text.TextUtils;

import com.applovin.impl.mediation.MaxMediatedNetworkInfoImpl;
import com.applovin.impl.mediation.utils.MediationUtils;
import com.applovin.impl.sdk.CoreSdk;
import com.applovin.impl.sdk.Logger;
import com.applovin.impl.sdk.utils.AndroidManifest;
import com.applovin.impl.sdk.utils.CollectionUtils;
import com.applovin.impl.sdk.utils.JsonUtils;
import com.applovin.mediation.MaxMediatedNetworkInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;

/**
 * This is the base class for AppLovin SDK.
 *
 * @author Basil Shikin
 */
public final class AppLovinSdk
{
    /**
     * Current SDK version. The returned value will be in the format of "<Major>.<Minor>.<Revision>".
     */
    public static final String VERSION = getVersion();

    public static final int VERSION_CODE = getVersionCode();

    private static final String TAG = "AppLovinSdk";

    private static final Map<String, AppLovinSdk> sdkInstances     = new HashMap<>( 1 ); // NOTE: We can't use `CollectionUtils.map()` here since it relies on this field being initialized.
    private static final Object                   sdkInstancesLock = new Object();

    /**
     * NOTE: This field used to be private and retrieved via reflection, but was causing crashes
     *
     * @hide
     */
    public final CoreSdk coreSdk;

    /**
     * Listener interface to be used with {@link AppLovinSdk#initializeSdk(SdkInitializationListener)}
     */
    public interface SdkInitializationListener
    {
        void onSdkInitialized(AppLovinSdkConfiguration config);
    }

    /**
     * Get client SDK key.
     *
     * @return Client SDK key.
     * @hide
     */
    public String getSdkKey()
    {
        return coreSdk.getSdkKey();
    }

    /**
     * Get SDK settings provided on initialization
     *
     * @return Provided settings.
     */
    public AppLovinSdkSettings getSettings()
    {
        return coreSdk.getSettings();
    }

    /**
     * Set Plugin version.
     *
     * @param version Plugin version to set.
     */
    public void setPluginVersion(String version)
    {
        coreSdk.setPluginVersion( version );
    }

    /**
     * Set an identifier for the current user. This identifier will be tied to SDK events and our optional S2S postbacks.
     * <p>
     * If you're using reward validation, you can optionally set an identifier to be included with currency validation postbacks.
     * For example, a username or email. We'll include this in the postback when we ping your currency endpoint from our server.
     *
     * @param userIdentifier The user identifier to be set.
     */
    public void setUserIdentifier(String userIdentifier)
    {
        coreSdk.setUserId( userIdentifier );
    }

    /**
     * An identifier for the current user. This identifier will be tied to SDK events and our optional S2S postbacks.
     * <p>
     * If you're using reward validation, you can optionally set an identifier to be included with currency validation postbacks.
     * For example, a username or email. We'll include this in the postback when we ping your currency endpoint from our server.
     */
    public String getUserIdentifier()
    {
        return coreSdk.getUserId();
    }

    /**
     * User segments allow us to serve ads using custom-defined rules based on which segment the user is in. For now, we only support a custom string 32 alphanumeric characters or less as the user segment.
     */
    public AppLovinUserSegment getUserSegment()
    {
        return coreSdk.getUserSegment();
    }

    /**
     * A class which allows you to send any demographic or interest-based targeting data.
     */
    public AppLovinTargetingData getTargetingData()
    {
        return coreSdk.getTargetingData();
    }

    /**
     * Get an instance of the AppLovin Ad service. This service is used to fetch ads from AppLovin servers.
     *
     * @return Ad service. Guaranteed not to be null.
     */
    public AppLovinAdService getAdService()
    {
        return coreSdk.getAdService();
    }

    /**
     * Get an instance of the AppLovin postback service. This service is used to dispatch HTTP GET postbacks to arbitrary URLs.
     *
     * @return Postback service. Guaranteed not to be null.
     */
    public AppLovinPostbackService getPostbackService()
    {
        return coreSdk.getPostbackService();
    }

    /**
     * Get an instance of the AppLovin event service. This service is used to track post-install user events.
     *
     * @return Event service. Guaranteed not to be null.
     */
    public AppLovinEventService getEventService()
    {
        return coreSdk.getEventService();
    }

    /**
     * Get an instance of the AppLovin user service object for performing user-related tasks.
     *
     * @return User service. Guaranteed not to be null.
     */
    public AppLovinUserService getUserService()
    {
        return coreSdk.getUserService();
    }

    /**
     * Get an instance of the AppLovin variable service. This service is used to perform various AB tests that you have set up on your AppLovin dashboard on your users.
     *
     * @return Variable service. Guaranteed not to be null.
     */
    public AppLovinVariableService getVariableService()
    {
        return coreSdk.getVariableService();
    }

    /**
     * Set mediation provider using one of the provided strings above specified by {@link AppLovinMediationProvider}, or your own if not defined.
     *
     * @param mediationProvider The name of the mediation provider.
     */
    public void setMediationProvider(String mediationProvider)
    {
        coreSdk.setMediationProvider( mediationProvider );
    }

    /**
     * Get the mediation provider that was last set using {@link AppLovinSdk#setMediationProvider(String)}, or null if none was set.
     *
     * @return The mediation provider that was last set, or null if none was set.
     */
    public String getMediationProvider()
    {
        return coreSdk.getMediationProvider();
    }

    /**
     * Returns the list of available mediation networks.
     *
     * @return List of {@link MaxMediatedNetworkInfo} objects.
     */
    public List<MaxMediatedNetworkInfo> getAvailableMediatedNetworks()
    {
        JSONArray availableMediationAdapters = MediationUtils.getAvailableMediationAdapters( coreSdk );
        List<MaxMediatedNetworkInfo> availableMediatedNetworks = new ArrayList<>( availableMediationAdapters.length() );

        for ( int i = 0; i < availableMediationAdapters.length(); i++ )
        {
            JSONObject adapter = JsonUtils.getJSONObject( availableMediationAdapters, i, null );
            availableMediatedNetworks.add( new MaxMediatedNetworkInfoImpl( adapter ) );
        }

        return availableMediatedNetworks;
    }

    /**
     * Present the mediation debugger UI.
     * This debugger tool provides the status of your integration for each third-party ad network.
     * <p>
     * Please call this method after the SDK has initialized, e.g. {@link AppLovinSdk#initializeSdk(SdkInitializationListener)}.
     */
    public void showMediationDebugger()
    {
        coreSdk.showMediationDebugger();
    }

    /**
     * Present the mediation debugger UI.
     * This debugger tool provides the status of your integration for each third-party ad network.
     * <p>
     * Please call this method after the SDK has initialized, e.g. {@link AppLovinSdk#initializeSdk(SdkInitializationListener)}.
     *
     * @param amazonAdSizes A map of the MAX Ad Unit Id to Amazon Publisher Services' {@code com.amazon.device.ads.DTBAdSize}.
     */
    public void showMediationDebugger(@Nullable final Map<String, List<?>> amazonAdSizes)
    {
        coreSdk.showMediationDebugger( amazonAdSizes );
    }

    /**
     * Check whether the SDK has fully been initialized without errors and the completion callback called.
     */
    public boolean isInitialized()
    {
        return coreSdk.isEnabled();
    }

    /**
     * Initialize the SDK
     */
    public void initializeSdk()
    {
        // Syntactic sugar
    }

    /**
     * Initialize the SDK with a given listener.
     * <p>
     * The callback will be invoked on the main thread.
     *
     * @param listener The callback that will be run on the main thread when the SDK finishes initializing. May be null.
     */
    public void initializeSdk(SdkInitializationListener listener)
    {
        coreSdk.initializeSdk( listener );
    }

    /**
     * Initialize the default version of the SDK.
     * <p>
     * Please make sure that <code>AndroidManifest.xml</code> includes following line:
     * <p>
     * <pre>
     *     &lt;application>
     *                     . . .
     *         &lt;meta-data android:name="applovin.sdk.key" android:value="APPLOVIN_SDK_KEY" />
     *     &lt;/application>
     * </pre>
     *
     * @param context Android application context.
     */
    public static void initializeSdk(Context context)
    {
        initializeSdk( context, null );
    }

    /**
     * Initialize the default version of the SDK.
     * <p>
     * Please make sure that <code>AndroidManifest.xml</code> includes following line:
     * <p>
     * <pre>
     *     &lt;application>
     *                     . . .
     *         &lt;meta-data android:name="applovin.sdk.key" android:value="APPLOVIN_SDK_KEY" />
     *     &lt;/application>
     * </pre>
     *
     * @param context  Android application context.
     * @param listener The callback that will be run on the main thread when the SDK finishes initializing. May be null.
     */
    public static void initializeSdk(Context context, SdkInitializationListener listener)
    {
        // Check input
        if ( context == null ) throw new IllegalArgumentException( "No context specified" );

        final AppLovinSdk sdk = getInstance( context );
        if ( sdk != null )
        {
            sdk.initializeSdk( listener );
        }
        else if ( Logger.isVerbose() )
        {
            Logger.userError( TAG, "Unable to initialize AppLovin SDK: SDK object not created" );

        }
    }

    /**
     * Get the SDK configuration object provided upon initialization.
     *
     * @return An instance of the SDK configuration.
     */
    public AppLovinSdkConfiguration getConfiguration()
    {
        return coreSdk.getConfiguration();
    }

    /**
     * Get instance of AppLovin SDK that is configured in <code>AndroidManifest.xml</code>. Please make sure that <code>AndroidManifest.xml</code> includes following line:
     * <p>
     * <pre>
     *     &lt;application>
     *                     . . .
     *         &lt;meta-data android:value="YOUR_SDK_KEY_HERE" android:name="APPLOVIN_SDK_KEY" />
     *     &lt;/application>
     * </pre>
     *
     * @param context Android application context.
     *
     * @return An instance of AppLovinSDK
     */
    public static AppLovinSdk getInstance(final Context context)
    {
        return getInstance( new AppLovinInternalSdkSettings( context ), context );
    }

    /**
     * Get instance of AppLovin SDK that is configured in <code>AndroidManifest.xml</code>. Please make sure that <code>AndroidManifest.xml</code> includes following line:
     * <p>
     * <pre>
     *     &lt;application>
     *                     . . .
     *         &lt;meta-data android:value="YOUR_SDK_KEY_HERE" android:name="applovin.sdk.key" />
     *     &lt;/application>
     * </pre>
     *
     * @param settings Settings to use with an SDK. Must not be null.
     * @param context  Android application context.
     *
     * @return An instance of AppLovinSDK
     */
    public static AppLovinSdk getInstance(final AppLovinSdkSettings settings, final Context context)
    {
        // Check input
        if ( context == null ) throw new IllegalArgumentException( "No context specified" );

        final String sdkKey = AndroidManifest.getManifest( context ).getMetaDataString( "applovin.sdk.key", "" );
        return getInstance( sdkKey, settings, context );
    }

    /**
     * Get an instance of AppLovin SDK.
     *
     * @param sdkKey       Client SDK's key.
     * @param userSettings User-provided settings. May be null.
     * @param context      Android application context.
     *
     * @return An instance of AppLovinSDK
     */
    public static AppLovinSdk getInstance(String sdkKey, AppLovinSdkSettings userSettings, Context context)
    {
        // Check input
        if ( userSettings == null ) throw new IllegalArgumentException( "No userSettings specified" );
        if ( context == null ) throw new IllegalArgumentException( "No context specified" );

        synchronized ( sdkInstancesLock )
        {
            // Check if SDK exists already
            if ( sdkInstances.containsKey( sdkKey ) )
            {
                return sdkInstances.get( sdkKey );
            }

            // Hack inspired by Fanatee who entered an invalid SDK key in one of their 50+ AdMob mediation group
            // with a path separator which crashed SharedPreferences during the SDK initialization
            //
            // The exception thrown, for the curious: https://github.com/aosp-mirror/platform_frameworks_base/blob/master/core/java/android/app/ContextImpl.java#L2555
            if ( !TextUtils.isEmpty( sdkKey ) && sdkKey.contains( File.separator ) )
            {
                if ( Logger.isVerbose() )
                {
                    final String log =
                            "\n**************************************************\n" +
                                    "INVALID SDK KEY: " + sdkKey + "\n" +
                                    "**************************************************\n";

                    Logger.userError( TAG, log );
                }

                // Attempt to use an SDK that already exists (with the correct SDK key)
                if ( !sdkInstances.isEmpty() )
                {
                    return sdkInstances.values().iterator().next();
                }

                // No existing SDKs... Explicitly remove the path separator to prevent crash...
                sdkKey = sdkKey.replace( File.separator, "" );
            }

            final CoreSdk newImpl = new CoreSdk();
            newImpl.initialize( sdkKey, userSettings, context );

            final AppLovinSdk newSdk = new AppLovinSdk( newImpl );
            newImpl.setWrappingSdk( newSdk );

            // Attach the SDK instance to the settings object.
            userSettings.attachAppLovinSdk( newImpl );

            sdkInstances.put( sdkKey, newSdk );

            return newSdk;
        }
    }

    /**
     * Check if SDK is enabled. SDK is enabled when (1) client successfully registered with the AppLovin server and (2) client is not banned from the server.
     *
     * @return True if SDK is ready to be used.
     */
    public boolean isEnabled()
    {
        return coreSdk.isEnabled();
    }

    private AppLovinSdk(CoreSdk sdk)
    {
        this.coreSdk = sdk;
    }

    /**
     * Get the SDK version string from the Build.gradle file.
     * <p>
     * Abstracted into method to prevent compiler optimization in adapters which hardcodes references to `VERSION` and `VERSION_CODE`
     * when it should dynamically retrieve versioning info depending on current SDK installed.
     */
    private static String getVersion()
    {
        return BuildConfig.VERSION_NAME;
    }

    /**
     * Get the SDK version code from the Build.gradle file.
     * <p>
     * Abstracted into method to prevent compiler optimization in adapters which hardcodes references to `VERSION` and `VERSION_CODE`
     * when it should dynamically retrieve versioning info depending on current SDK installed.
     */
    private static int getVersionCode()
    {
        return BuildConfig.VERSION_CODE;
    }

    /**
     * Force re-initialization of all known SDKs for when user consent, age restriction, or "do not sell" status state has explicitly changed.
     * <p>
     * <b>Please note:</b> This method is for internal use only.
     * </p>
     *
     * @hide
     */
    static void reinitializeAll(final Boolean hasUserConsent, final Boolean ageRestrictedUser, final Boolean doNotSell)
    {
        synchronized ( sdkInstancesLock )
        {
            for ( AppLovinSdk sdk : sdkInstances.values() )
            {
                sdk.coreSdk.reinitialize();
                sdk.coreSdk.notifyPrivacySettingUpdated();

                // Fire an event if the user consent state has explicitly changed
                if ( hasUserConsent != null )
                {
                    sdk.coreSdk.getLogger().i( TAG, "Toggled 'huc' to " + hasUserConsent );

                    Map<String, String> parameters = CollectionUtils.map( "value", hasUserConsent.toString() );
                    sdk.getEventService().trackEvent( "huc", parameters );
                }

                // Fire an event if the age restricted state has explicitly changed
                if ( ageRestrictedUser != null )
                {
                    sdk.coreSdk.getLogger().i( TAG, "Toggled 'aru' to " + ageRestrictedUser );

                    Map<String, String> parameters = CollectionUtils.map( "value", ageRestrictedUser.toString() );
                    sdk.getEventService().trackEvent( "aru", parameters );
                }

                // Fire an event if the "do not sell" state has explicitly changed
                if ( doNotSell != null )
                {
                    sdk.coreSdk.getLogger().i( TAG, "Toggled 'dns' to " + doNotSell );

                    Map<String, String> parameters = CollectionUtils.map( "value", doNotSell.toString() );
                    sdk.getEventService().trackEvent( "dns", parameters );
                }
            }
        }
    }

    /**
     * Internal SDK settings class.
     * <p>
     * <b>Please note:</b> This class is for internal use only.
     * </p>
     *
     * @hide
     */
    private static class AppLovinInternalSdkSettings
            extends AppLovinSdkSettings
    {
        AppLovinInternalSdkSettings(final Context context) { super( context ); }
    }

    @Override
    public String toString()
    {
        return "AppLovinSdk{" +
                "sdkKey='" + getSdkKey() + "'" +
                ", isEnabled=" + isEnabled() +
                ", isFirstSession=" + coreSdk.isFirstSession() +
                '}';
    }
}

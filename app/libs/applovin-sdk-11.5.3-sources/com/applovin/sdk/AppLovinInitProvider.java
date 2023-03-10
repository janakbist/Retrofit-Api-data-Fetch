package com.applovin.sdk;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import com.applovin.impl.sdk.CoreSdk;
import com.applovin.impl.sdk.DataCollector;

import androidx.annotation.Nullable;

/**
 * A content provider that performs operations on app launch before SDK is initialized.
 * <p>
 * Created by Lorenzo Gentile on 4/1/21.
 */
public class AppLovinInitProvider
        extends ContentProvider
{
    @Override
    public boolean onCreate()
    {
        // Collect advertising info early so it's ready if mediators collect our bid token on the main thread early.
        DataCollector.collectAdvertisingInfoInBackground( getContext() );

        // Collect app set info early so its value is set by the time data collector needs it.
        DataCollector.collectAppSetIdInfo( getContext() );

        // Attach Activity Lifecycle Manager
        CoreSdk.getActivityLifecycleManager( getContext() );

        return true;
    }

    @Nullable
    @Override
    public Cursor query(final Uri uri, @Nullable final String[] projection, @Nullable final String selection, @Nullable final String[] selectionArgs, @Nullable final String sortOrder)
    {
        return null;
    }

    @Nullable
    @Override
    public String getType(final Uri uri)
    {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(final Uri uri, @Nullable final ContentValues values)
    {
        return null;
    }

    @Override
    public int delete(final Uri uri, @Nullable final String selection, @Nullable final String[] selectionArgs)
    {
        return 0;
    }

    @Override
    public int update(final Uri uri, @Nullable final ContentValues values, @Nullable final String selection, @Nullable final String[] selectionArgs)
    {
        return 0;
    }
}

package com.applovin.mediation.hybridAds;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.applovin.impl.mediation.model.MediatedFullscreenAd;
import com.applovin.impl.sdk.CoreSdk;
import com.applovin.mediation.adapter.listeners.MaxAdapterListener;

import androidx.annotation.Nullable;
import lombok.val;

public class MaxHybridMRecAdActivity
        extends MaxHybridAdActivity
{
    private View mrec;

    protected void initialize(final MediatedFullscreenAd ad, final View mrec, final CoreSdk sdk, final MaxAdapterListener listener)
    {
        super.initialize( ad, sdk, listener );

        this.mrec = mrec;
    }

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState)
    {
        super.onCreate( savedInstanceState );

        val rootView = (ViewGroup) findViewById( android.R.id.content );
        rootView.addView( mrec );

        closeButton.bringToFront();
    }
}

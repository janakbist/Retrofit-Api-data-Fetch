package com.applovin.mediation.hybridAds;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import com.applovin.impl.adview.TransparentCloseButton;
import com.applovin.sdk.AppLovinSdkUtils;

import lombok.Setter;
import lombok.val;

@SuppressLint("ViewConstructor")
public class MaxCloseButton
        extends FrameLayout
        implements View.OnClickListener
{
    interface MaxCloseButtonListener
    {
        void onCloseButtonTapReceived(final MaxCloseButton closeButton);
    }

    @Setter
    private MaxCloseButtonListener listener;

    public MaxCloseButton(final MaxHybridAdConfiguration configuration, final Context context)
    {
        super( context );

        setOnClickListener( this );

        val innerCloseButton = new TransparentCloseButton( context );
        val closeButtonSizePx = AppLovinSdkUtils.dpToPx( context, configuration.getCloseButtonSize() );
        val innerLayoutParams = new LayoutParams( closeButtonSizePx, closeButtonSizePx, Gravity.CENTER );
        innerCloseButton.setLayoutParams( innerLayoutParams );
        innerCloseButton.sizeToFit( closeButtonSizePx );

        addView( innerCloseButton );

        val closeButtonFullSizeDp = configuration.getCloseButtonSize() + ( 2 * configuration.getCloseButtonExtendedTouchAreaSize() );
        val closeButtonFullSizePx = AppLovinSdkUtils.dpToPx( context, closeButtonFullSizeDp );
        val layoutParams = new LayoutParams( closeButtonFullSizePx, closeButtonFullSizePx, Gravity.TOP | Gravity.END );

        val topMarginPx = AppLovinSdkUtils.dpToPx( context, configuration.getCloseButtonTopMargin() );
        val horizontalMarginPx = AppLovinSdkUtils.dpToPx( context, configuration.getCloseButtonHorizontalMargin() );
        layoutParams.setMargins( horizontalMarginPx, topMarginPx, horizontalMarginPx, 0 );

        setLayoutParams( layoutParams );
    }

    @Override
    public void onClick(final View view)
    {
        listener.onCloseButtonTapReceived( this );
    }
}

package com.weather.kidouchi.weatherapp.ui.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.weather.kidouchi.weatherapp.R;

/**
 * This is custom square-shaped view that holds info on the owner's social media contact
 */
public class SocialPanelView extends LinearLayout {
    public SocialPanelView(Context context) {
        super(context);
        init(null, 0);
    }

    public SocialPanelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public SocialPanelView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        inflate(getContext(), R.layout.panel_view, this);

        final ImageView image = findViewById(R.id.social_media_image);
        final TextView text = findViewById(R.id.social_info);

        // Load attributes
        final TypedArray attributes = getContext().obtainStyledAttributes(
                attrs, R.styleable.PanelView, defStyle, 0);
        image.setImageDrawable(attributes.getDrawable(R.styleable.PanelView_image));
        text.setText(attributes.getText(R.styleable.PanelView_text));

        // Don't forget to recycle the attributes, it is necessary for android memory management.
        attributes.recycle();
    }
}

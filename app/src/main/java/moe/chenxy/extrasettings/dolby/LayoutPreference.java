package moe.chenxy.extrasettings.dolby;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.VisibleForTesting;
import androidx.core.content.res.TypedArrayUtils;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;

import moe.chenxy.extrasettings.R;


public class LayoutPreference extends Preference {
    private boolean mAllowDividerAbove;
    private boolean mAllowDividerBelow;
    private final View.OnClickListener mClickListener;
    @VisibleForTesting
    View mRootView;

    @SuppressLint("RestrictedApi")
    public LayoutPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mClickListener = (view) -> {
            performClick(view);
        };
        init(context, attributeSet, 0);
    }

    @SuppressLint("RestrictedApi")
    public LayoutPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mClickListener = (view) -> {
            performClick(view);
        };
        init(context, attributeSet, i);
    }

    public LayoutPreference(Context context, int i) {
        this(context, LayoutInflater.from(context).inflate(i, (ViewGroup) null, false));
    }

    @SuppressLint("RestrictedApi")
    public LayoutPreference(Context context, View view) {
        super(context);
        this.mClickListener = (view1) -> {
            performClick(view1);
        };
        setView(view);
    }

    /* JADX DEBUG: Failed to find minimal casts for resolve overloaded methods, cast all args instead
     method: ClspMth{android.view.LayoutInflater.inflate(int, android.view.ViewGroup, boolean):android.view.View}
     arg types: [int, ?[OBJECT, ARRAY], int]
     candidates:
      ClspMth{android.view.LayoutInflater.inflate(org.xmlpull.v1.XmlPullParser, android.view.ViewGroup, boolean):android.view.View}
      ClspMth{android.view.LayoutInflater.inflate(int, android.view.ViewGroup, boolean):android.view.View} */
    @SuppressLint("RestrictedApi")
    private void init(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Preference);
        this.mAllowDividerAbove = TypedArrayUtils.getBoolean(obtainStyledAttributes, 16, 16, false);
        this.mAllowDividerBelow = TypedArrayUtils.getBoolean(obtainStyledAttributes, 17, 17, false);
        obtainStyledAttributes.recycle();
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, Res.styleable.Preference, i, 0);
        int resourceId = obtainStyledAttributes2.getResourceId(Res.styleable.Preference_layout, 0);
        if (resourceId == 0) {
            throw new IllegalArgumentException("LayoutPreference requires a layout to be defined");
        }
        obtainStyledAttributes2.recycle();
        setView(LayoutInflater.from(getContext()).inflate(resourceId, (ViewGroup) null, false));
    }

    private void setView(View view) {
        setLayoutResource(R.layout.layout_preference_frame);
        ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.all_details);
        this.mRootView = view;
        setShouldDisableView(false);
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        preferenceViewHolder.itemView.setOnClickListener(this.mClickListener);
        boolean isSelectable = isSelectable();
        preferenceViewHolder.itemView.setFocusable(isSelectable);
        preferenceViewHolder.itemView.setClickable(isSelectable);
        preferenceViewHolder.setDividerAllowedAbove(this.mAllowDividerAbove);
        preferenceViewHolder.setDividerAllowedBelow(this.mAllowDividerBelow);
        FrameLayout frameLayout = (FrameLayout) preferenceViewHolder.itemView;
        frameLayout.removeAllViews();
        ViewGroup viewGroup = (ViewGroup) this.mRootView.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(this.mRootView);
        }
        frameLayout.addView(this.mRootView);
    }

    public <T extends View> T findViewById(int i) {
        return this.mRootView.findViewById(i);
    }
}
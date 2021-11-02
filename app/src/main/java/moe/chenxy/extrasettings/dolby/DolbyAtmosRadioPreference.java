package moe.chenxy.extrasettings.dolby;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;
import android.widget.RadioButton;

import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import androidx.preference.TwoStatePreference;

import moe.chenxy.extrasettings.R;

public class DolbyAtmosRadioPreference extends TwoStatePreference {
    public DolbyAtmosRadioPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public DolbyAtmosRadioPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, Res.styleable.CheckBoxPreference, i, i2);
        setSummaryOn(obtainStyledAttributes.getString(Res.styleable.CheckBoxPreference_summaryOn));
        setSummaryOff(obtainStyledAttributes.getString(Res.styleable.CheckBoxPreference_summaryOff));
        setDisableDependentsState(obtainStyledAttributes.getBoolean(Res.styleable.CheckBoxPreference_disableDependentsState, false));
        obtainStyledAttributes.recycle();
        setWidgetLayoutResource(R.layout.preference_widget_radiobutton);
        setLayoutResource(R.layout.dolby_preference_label);
    }

    public DolbyAtmosRadioPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, Res.attr.checkBoxPreferenceStyle);
    }

    public DolbyAtmosRadioPreference(Context context) {
        this(context, null);
        setLayoutResource(R.layout.dolby_preference_label);
    }

    public Preference.OnPreferenceClickListener getOnPreferenceClickListener() {
        return super.getOnPreferenceClickListener();
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        View findViewById = preferenceViewHolder.findViewById(R.id.checkbox);
        if (findViewById != null && (findViewById instanceof Checkable)) {
            ((Checkable) findViewById).setChecked(isChecked());
        }

    }
}
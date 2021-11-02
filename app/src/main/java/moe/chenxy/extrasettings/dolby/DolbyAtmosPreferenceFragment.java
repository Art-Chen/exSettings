package moe.chenxy.extrasettings.dolby;


import android.Manifest;
import android.app.ActionBar;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragment;

import com.dolby.dax.DolbyAudioEffect;
import com.dolby.dax.DsCommon;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import moe.chenxy.extrasettings.MainActivity;
import moe.chenxy.extrasettings.R;

import static androidx.core.content.PermissionChecker.checkSelfPermission;

public class DolbyAtmosPreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {
    private static final String TAG = "DolbyAtmosPreferenceFragment";
    private final int AI_DOLBY_MODE = 0;
    private final String KEY_AI_DOLBY_MODE = "ai_dolby_mode";
    private final String KEY_DOLBY_ATMOS_HEADER = "dolby_atmos_header";
    private final String KEY_MUSIC_DOLBY_MODE = "music_dolby_mode";
    private final String KEY_VIDEO_DOLBY_MODE = "video_dolby_mode";
    private final int MUSIC_DOLBY_MODE = 2;
    private final int VIDEO_DOLBY_MODE = 1;
    private SmartRefreshLayout layout;
    private DolbyAtmosRadioPreference mAiDolbyMode;
    private Context mContext;
    private boolean mDaxOn = false;
    private BroadcastReceiver mDolbyAtmosReceiver = new BroadcastReceiver() {
        /* class com.android.settings.notification.dolby.DolbyAtmosPreferenceFragment.AnonymousClass2 */

        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals(intent.getAction(), DsCommon.DOLBY_UPDATE_EVENT)) {
                String stringExtra = intent.getStringExtra(DsCommon.EVENTNAME);
                char c = 65535;
                int hashCode = stringExtra.hashCode();
                if (hashCode != -1146943738) {
                    if (hashCode != 173585614) {
                        if (hashCode == 1577092565 && stringExtra.equals(DsCommon.DOLBY_UPDATE_EVENT_PROFILE_SETTING)) {
                            c = 0;
                        }
                    } else if (stringExtra.equals(DsCommon.DOLBY_UPDATE_EVENT_DS_STATE)) {
                        c = 2;
                    }
                } else if (stringExtra.equals(DsCommon.DOLBY_UPDATE_EVENT_PROFILE)) {
                    c = 1;
                }
                switch (c) {
                    case 0:
                    case 2:
                    default:
                        return;
                    case 1:
                        int unused = DolbyAtmosPreferenceFragment.this.mSelectedProfile = intent.getIntExtra(DsCommon.CMDINTVALUE, 2);
                        DolbyAtmosPreferenceFragment.this.onDolbyModeChange(DolbyAtmosPreferenceFragment.this.mSelectedProfile);
                        return;
                }
            }
        }
    };
    private DolbyAudioEffect mDolbyAudio;
    private ImageView mDolbyHeaderImageView;
    private RadioPreferenceGroup mGroup;
    private DolbyAtmosRadioPreference mMusicDolbyMode;
    /* access modifiers changed from: private */
    public int mSelectedProfile = 2;
    private DolbyAtmosRadioPreference mVideoDolbyMode;

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return TAG;
    }

    public int getMetricsCategory() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return R.xml.dolby_atoms;
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.dolby_atoms);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        //this.layout = (SmartRefreshLayout) onCreateView.findViewById(R.id.refreshLayout);
        //this.layout.setBackgroundColor(getResources().getColor(R.color.dolby_background_color));
        View inflate = LayoutInflater.from(getActivity()).inflate((int) R.layout.cell_head, (ViewGroup) null);
        ((TextView) inflate.findViewById(R.id.title)).setText((int) R.string.dolby_atmos_title);
        ((ImageView) inflate.findViewById(R.id.actionbar_back_btn)).setOnClickListener(view -> getActivity().getFragmentManager().popBackStack());
        Window window = getActivity().getWindow();
        window.addFlags(Integer.MIN_VALUE);
        window.setNavigationBarColor(getActivity().getResources().getColor(R.color.dolby_background_color));
        window.setStatusBarColor(getActivity().getResources().getColor(R.color.dolby_background_color));
        toggleRefresh();
        return onCreateView;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        initView();
        getView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }

    private void toggleRefresh() {
        if (this.layout != null) {
            this.layout.setEnableLoadMore(false);
            this.layout.setEnableOverScrollDrag(false);
        }
    }

    public void onStart() {
        super.onStart();
        this.mContext = getContext();
        askPermission();
        this.mDolbyAudio = new DolbyAudioEffect(0, 0);
        this.mDaxOn = this.mDolbyAudio.getDsOn();
        if (this.mDaxOn) {
            this.mSelectedProfile = this.mDolbyAudio.getProfile();
            this.mDolbyAudio.setProfile(this.mSelectedProfile);
            onDolbyModeChange(this.mSelectedProfile);
        }
        this.mContext.registerReceiver(this.mDolbyAtmosReceiver, new IntentFilter(DsCommon.DOLBY_UPDATE_EVENT));
    }

    public void onResume() {
        super.onResume();
        Window window = getActivity().getWindow();
        window.addFlags(Integer.MIN_VALUE);
        //window.setNavigationBarColor(getActivity().getResources().getColor(R.color.fmsafe_title_text_color_white));
        window.setNavigationBarColor(getActivity().getResources().getColor(android.R.color.transparent));
    }

    public void onStop() {
        super.onStop();
        if (this.mContext != null) {
            this.mContext.unregisterReceiver(this.mDolbyAtmosReceiver);
        }
    }

    public boolean onPreferenceClick(Preference preference) {
        if (preference == this.mMusicDolbyMode) {
            setDolbyMode(2);
        } else if (preference == this.mVideoDolbyMode) {
            setDolbyMode(1);
        } else if (preference == this.mAiDolbyMode) {
            setDolbyMode(0);
        }
        return true;
    }

    private void initView() {
        this.mMusicDolbyMode = (DolbyAtmosRadioPreference) findPreference("music_dolby_mode");
        this.mVideoDolbyMode = (DolbyAtmosRadioPreference) findPreference("video_dolby_mode");
        this.mAiDolbyMode = (DolbyAtmosRadioPreference) findPreference("ai_dolby_mode");
        LayoutPreference layoutPreference = (LayoutPreference) findPreference("dolby_atmos_header");
        if (layoutPreference != null) {
            this.mDolbyHeaderImageView = (ImageView) layoutPreference.findViewById(R.id.dolby_effect_image);
        }
        this.mMusicDolbyMode.setOnPreferenceClickListener(this);
        this.mVideoDolbyMode.setOnPreferenceClickListener(this);
        this.mAiDolbyMode.setOnPreferenceClickListener(this);
        this.mGroup = new RadioPreferenceGroup();
        this.mGroup.addPreference(this.mMusicDolbyMode);
        this.mGroup.addPreference(this.mVideoDolbyMode);
        this.mGroup.addPreference(this.mAiDolbyMode);
    }

    private void setDolbyMode(int i) {
        if (this.mDolbyAudio != null) {
            try {
                this.mDolbyAudio.setDsOn(true);
                if (!this.mDolbyAudio.getDsOn()) {
                    this.mDolbyAudio.setDsOn(true);
                }
                this.mDolbyAudio.setProfile(i);
                this.mSelectedProfile = i;
                onDolbyModeChange(this.mSelectedProfile);
            } catch (Exception unused) {
                Log.d(TAG, "setProfile() FAILED");
            }
        }
    }

    /* access modifiers changed from: private */
    public void onDolbyModeChange(int i) {
        switch (i) {
            case 0:
                this.mGroup.check(this.mAiDolbyMode);
                this.mDolbyHeaderImageView.setImageResource(R.drawable.ai_dolby_atmos);
                return;
            case 1:
                this.mGroup.check(this.mVideoDolbyMode);
                this.mDolbyHeaderImageView.setImageResource(R.drawable.video_dolby_atmos);
                return;
            case 2:
                this.mGroup.check(this.mMusicDolbyMode);
                this.mDolbyHeaderImageView.setImageResource(R.drawable.music_dolby_atmos);
                return;
            default:
                return;
        }
    }
    List<String> permissions = new ArrayList<String>();

    private boolean askPermission() {
            int RECORD_AUDIO = checkSelfPermission(mContext, Manifest.permission.RECORD_AUDIO );
            int MODIFY_AUDIO_SETTING = checkSelfPermission(mContext, Manifest.permission.MODIFY_AUDIO_SETTINGS);
            if (RECORD_AUDIO != PackageManager.PERMISSION_GRANTED || MODIFY_AUDIO_SETTING != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.MODIFY_AUDIO_SETTINGS);
                permissions.add(Manifest.permission.RECORD_AUDIO);
            }

            if (!permissions.isEmpty()) {
                requestPermissions(permissions.toArray(new String[permissions.size()]), 1);
            } else
                return false;
        return true;

    }
}
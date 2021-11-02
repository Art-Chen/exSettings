package moe.chenxy.extrasettings;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;

import androidx.preference.ListPreference;
import androidx.preference.MultiSelectListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragment;

import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import moe.chenxy.extrasettings.config.ConfigFile;
import moe.chenxy.extrasettings.util.FileUtils;
import moe.chenxy.extrasettings.util.RadioSendManage;


public class SettingFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    ConfigFile configFile = new ConfigFile();
    public RadioSendManage radioSendManage = RadioSendManage.radioSendManage;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private AlertDialog progressDialog;

    ArrayList<String> mVideoApps;
    ArrayList<String> mGameApps;
    ArrayList<String> m60HzApps;

    private final Runnable mWaitRFPolicyRunnable = () -> {
        hideProgressDialog();
        Snackbar mSnackbar = Snackbar.make(getView(),"获取系统刷新率配置超时！刷新率策略将无法生效！\n您可能替换了 电量与性能 \n",Snackbar.LENGTH_LONG);
        mSnackbar.setTextColor(Color.WHITE);
        mSnackbar.show();
    };

    private final BroadcastReceiver mRefreshRatePolicyReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("Art_Chen","got RefreshRatePolicy");
            mHandler.removeCallbacks(mWaitRFPolicyRunnable);
            mVideoApps = intent.getStringArrayListExtra("mVideoApps");
            mGameApps = intent.getStringArrayListExtra("mGameApps");
            m60HzApps = intent.getStringArrayListExtra("m60HzApps");
            Set<String> mVideoAppsSet = new HashSet<>(mVideoApps);
            Set<String> mGameAppsSet = new HashSet<>(mGameApps);
            Set<String> m60HzAppsSet = new HashSet<>(m60HzApps);
            MultiSelectListPreference m90HzGameApps = getPreferenceManager().findPreference("90Hz_GameApps");
            MultiSelectListPreference mVideoApps = getPreferenceManager().findPreference("mVideoApps");
            MultiSelectListPreference m60HzTopApps = getPreferenceManager().findPreference("m60HzTopApps");
            m90HzGameApps.setValues(mGameAppsSet);
            mVideoApps.setValues(mVideoAppsSet);
            m60HzTopApps.setValues(m60HzAppsSet);
            hideProgressDialog();

        }
    };

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        getPreferenceManager().setSharedPreferencesName("Chen_Settings");
        addPreferencesFromResource(R.xml.perf);
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        SharedPreferences sharedPreferences = getPreferenceManager().getSharedPreferences();
        configFile.threeTop = Integer.parseInt(sharedPreferences.getString("up","0"));
        configFile.threeCentre = Integer.parseInt(sharedPreferences.getString("center","0"));
        configFile.threeBottom = Integer.parseInt(sharedPreferences.getString("bottom","0"));
        configFile.forceLinearVibrateMode = sharedPreferences.getBoolean("force_linear", false);
        //configFile.refreshRateMode = Integer.parseInt(sharedPreferences.getString("refresh_rate","0"));
        //configFile.newNotificationVibrate = sharedPreferences.getBoolean("new_noti_vibrator_mode", true);
        configFile.mDisableRefreshRatePolicy = sharedPreferences.getBoolean("mDisableRefreshRatePolicy", false);
        //configFile.screenResolution = Integer.parseInt(sharedPreferences.getString("resolution_switch","0"));
        MultiSelectListPreference m90HzGameApps = getPreferenceManager().findPreference("90Hz_GameApps");
        configFile.m90HzGameApps = getListCustomApps(m90HzGameApps);
        MultiSelectListPreference mVideoApps = getPreferenceManager().findPreference("mVideoApps");
        configFile.mVideoApps = getListCustomApps(mVideoApps);
        MultiSelectListPreference m60HzTopApps = getPreferenceManager().findPreference("m60HzTopApps");
        configFile.m60HzTopApps = getListCustomApps(m60HzTopApps);
        showProgressDialog(null,"等待系统返回当前刷新率策略...");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("moe.chenxy.Setting.refreshRate.RefreshRatePolicy");
        getContext().getApplicationContext().registerReceiver(mRefreshRatePolicyReceiver, intentFilter);
        radioSendManage.requestCurrentValue(getContext());


        mHandler.postDelayed(mWaitRFPolicyRunnable,1000 * 10);

        setAppListPreferenceValue(loadAppsList(getResolveInfos()), m90HzGameApps);
        setAppListPreferenceValue(loadAppsList(getResolveInfos()), mVideoApps);
        setAppListPreferenceValue(loadAppsList(getResolveInfos()), m60HzTopApps);
        setSummaryForList("up");
        setSummaryForList("center");
        setSummaryForList("bottom");
		initBatteryInfo();
    }

    private Runnable mRunnable;

    private void initBatteryInfo() {
        mRunnable = () -> {
            refreshBatteryInfo();
            mHandler.postDelayed(mRunnable, 1000);
        };
        mHandler.post(mRunnable);

    }

    private void refreshBatteryInfo() {
        String batt_health = FileUtils.readOneLine("/sys/class/power_supply/bms/battery_health");
        String batt_current = FileUtils.readOneLine("/sys/class/power_supply/bms/current_now");
        String batt_cur_state = Integer.parseInt(batt_current) > 0 ? "正在放电，" : "正在充电，";

        Preference batt_fcc_pref = getPreferenceManager().findPreference("batt_fcc");
        Preference batt_cur_pref = getPreferenceManager().findPreference("batt_cur");

        DecimalFormat df = new DecimalFormat("0.00");

        batt_fcc_pref.setSummary(batt_health + " %");
        batt_cur_pref.setSummary(batt_cur_state + Math.abs(Integer.parseInt(batt_current)) / 1000 + " mA");
    }

    private List<String> getListCustomApps(MultiSelectListPreference perf) {
        return new ArrayList<String>(perf.getValues());
    }


    @Override
    public void onDestroy() {
        // 取消注册监听配置事件
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        super.onDestroy();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        // 当界面配置修改时则会回调该方法
        // 方法内需要调用sdk函数来修改sdk配置参数

        if ("up".equals(key)) {
            configFile.threeTop =  Integer.parseInt(sharedPreferences.getString(key, "0"));
        }
        if ("center".equals(key)) {
            configFile.threeCentre =  Integer.parseInt(sharedPreferences.getString(key, "0"));
        }
        if ("bottom".equals(key)) {
            configFile.threeBottom =  Integer.parseInt(sharedPreferences.getString(key, "0"));
        }
        if ("force_linear".equals(key)) {
            configFile.forceLinearVibrateMode = sharedPreferences.getBoolean(key, false);
        }
//        if ("refresh_rate".equals(key)) {
//            configFile.refreshRateMode =  Integer.parseInt(sharedPreferences.getString(key, "0"));
//            MultiSelectListPreference mForce60HzApps = getPreferenceManager().findPreference("60Hz_apps");
//            if (configFile.refreshRateMode == 0) {
//                mForce60HzApps.setSummary("");
//            } else {
//                mForce60HzApps.setSummary(R.string.force_60hz_apps_summary);
//            }
        if ("90Hz_GameApps".equals(key) || "mVideoApps".equals(key) || "m60HzTopApps".equals(key) || "mDisableRefreshRatePolicy".equals(key)) {
            if ("90Hz_GameApps".equals(key)) {
                MultiSelectListPreference m90HzGameApps = getPreferenceManager().findPreference("90Hz_GameApps");
                configFile.m90HzGameApps = getListCustomApps(m90HzGameApps);
            }
            if ("mVideoApps".equals(key)) {
                MultiSelectListPreference mVideoApps = getPreferenceManager().findPreference("mVideoApps");
                configFile.mVideoApps = getListCustomApps(mVideoApps);
            }
            if ("m60HzTopApps".equals(key)) {
                MultiSelectListPreference m60HzTopApps = getPreferenceManager().findPreference("m60HzTopApps");
                configFile.m60HzTopApps = getListCustomApps(m60HzTopApps);
            }
            if ("mDisableRefreshRatePolicy".equals(key)) {
                configFile.mDisableRefreshRatePolicy = sharedPreferences.getBoolean("mDisableRefreshRatePolicy", false);
            }
            radioSendManage.sendRefreshRateMsg(getActivity(), configFile);
        }
        updateConfig(configFile);
        setSummaryForList("up");
        setSummaryForList("center");
        setSummaryForList("bottom");
    }

    private void updateConfig(ConfigFile configFile) {
        radioSendManage.sendMsg(getActivity(), configFile);
    }

    /**
     * 为ListPerference 设置对应value为Summary
     * @param key
     */
    private void setSummaryForList(String key) {
        SharedPreferences sharedPreferences = getPreferenceManager().getSharedPreferences();
        ListPreference list = (ListPreference) getPreferenceManager().findPreference(key);
        String value = sharedPreferences.getString(key, "0");
        CharSequence[] entries = list.getEntries();
        int index = list.findIndexOfValue(value);
        list.setSummary(entries[index]);
    }

    private List<ResolveInfo> getResolveInfos(){
        List<ResolveInfo> appList = null;

        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager pm = getContext().getPackageManager();
        appList = pm.queryIntentActivities(intent, 0);
        Collections.sort(appList, new ResolveInfo.DisplayNameComparator(pm));

        return appList;

    }

    private List<AppInfo> loadAppsList(List<ResolveInfo> resolveInfos){
        PackageManager packageManager = getContext().getPackageManager();
        List<AppInfo> mAppInfoList = new ArrayList<>();

        for (int i = 0; i < resolveInfos.size(); i++) {
            String pkg = resolveInfos.get(i).activityInfo.packageName;
            String cls = resolveInfos.get(i).activityInfo.name;
            String title = null;

            try {
                ApplicationInfo applicationInfo = packageManager.getPackageInfo(pkg, i).applicationInfo;
                title = applicationInfo.loadLabel(packageManager).toString();
            } catch (Exception ignored){

            }
            mAppInfoList.add(new AppInfo(pkg, title));
        }
        //Log.i("Art_Chen_Apps",mAppInfoList.toString());
        return mAppInfoList;
    }
	
    public void showProgressDialog(String title, String message) {
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(getContext(), title,
                    message, true, false);
        } else if (progressDialog.isShowing()) {
            progressDialog.setTitle(title);
            progressDialog.setMessage(message);
        }
        progressDialog.show();
    }

    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    public void setAppListPreferenceValue(List<AppInfo> list, MultiSelectListPreference perf) {
        List<CharSequence> entriesCS = new ArrayList<CharSequence>();
        List<CharSequence> entryValueCS = new ArrayList<CharSequence>();
        for (AppInfo info: list) {
            entriesCS.add(info.getPackageTitle()+ " (" + info.getPackageName() + ")");
            entryValueCS.add(info.getPackageName());
        }
        final CharSequence[] entriesFinalCS = entriesCS.toArray(new CharSequence[entriesCS.size()]);
        final CharSequence[] entryValueFinalCS = entryValueCS.toArray(new CharSequence[entryValueCS.size()]);
        perf.setEntries(entriesFinalCS);
        perf.setEntryValues(entryValueFinalCS);
    }
}

class AppInfo {
    public AppInfo(String packageName, String packageTitle) {
        this.packageName = packageName;
        this.packageTitle = packageTitle;
    }

    private String packageName;
    private String packageTitle;

    @Override
    public String toString() {
        return "AppInfo{" +
                "packageName='" + packageName + '\'' +
                ", packageTitle='" + packageTitle + '\'' +
                '}';
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageTitle() {
        return packageTitle;
    }

    public void setPackageTitle(String packageTitle) {
        this.packageTitle = packageTitle;
    }
}


package moe.chenxy.extrasettings;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
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
import androidx.preference.SwitchPreferenceCompat;

import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import moe.chenxy.extrasettings.config.ConfigFile;
import moe.chenxy.extrasettings.util.FileUtils;
import moe.chenxy.extrasettings.util.RadioSendManage;


public class SettingFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    ConfigFile configFile = new ConfigFile();
    public RadioSendManage radioSendManage = RadioSendManage.radioSendManage;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private AlertDialog progressDialog;

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
        configFile.forceLinearVibrateMode = sharedPreferences.getBoolean("force_linear", false);
        configFile.dolbyDax = sharedPreferences.getBoolean("dolby_atmos", true);
        configFile.newNotificationVibrate = sharedPreferences.getBoolean("new_noti_vibrator_mode", true);
//        configFile.resetScreenAfterDoze = sharedPreferences.getBoolean("reset_screen", false);
        MultiSelectListPreference mForce60HzApps = getPreferenceManager().findPreference("60Hz_apps");
        MultiSelectListPreference m90HzGames = getPreferenceManager().findPreference("90Hz_games");
        configFile.mForce60HzPackageName = getForce60HzCustomApps(mForce60HzApps);
        configFile.m90HzGameName = get90HzGames(m90HzGames);

        setAppListPreferenceValue(loadAppsList(getResolveInfos()), mForce60HzApps);
        setAppListPreferenceValue(loadAppsList(getResolveInfos()), m90HzGames);
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
        String batt_fcc = "/sys/class/power_supply/battery/batt_fcc";
        String batt_current = "/sys/class/power_supply/battery/current_now";
        String usb_voltage = "/sys/class/power_supply/usb/voltage_now";
        String batt_status = FileUtils.readOneLine("/sys/class/power_supply/battery/status");
        String batt_cur_state = Integer.parseInt(FileUtils.readOneLine(batt_current)) > 0 ? "正在放电，" : "正在充电，";

        Preference batt_fcc_pref = getPreferenceManager().findPreference("batt_fcc");
        Preference batt_cur_pref = getPreferenceManager().findPreference("batt_cur");
        Preference batt_chg_speed_pref = getPreferenceManager().findPreference("batt_chg_speed");

        DecimalFormat df = new DecimalFormat("0.00");
        double batt_loss = Double.parseDouble(FileUtils.readOneLine(batt_fcc)) / 3900.00;
        double batt_chg_speed = (Double.parseDouble(FileUtils.readOneLine(usb_voltage)) / 1000000.0)
                                * Math.abs(Double.parseDouble(FileUtils.readOneLine(batt_current)) / 1000.0);

        batt_fcc_pref.setSummary(FileUtils.readOneLine(batt_fcc) + " mAh (电池理论最小值的 " + df.format(batt_loss * 100) + " %)");
        batt_cur_pref.setSummary(batt_cur_state + Math.abs(Integer.parseInt(FileUtils.readOneLine(batt_current))) + " mA");
        batt_chg_speed_pref.setSummary(((batt_status.equals("Charging") && Integer.parseInt(FileUtils.readOneLine(batt_current)) < 0) ? batt_chg_speed : "0") + " Watt");
    }

    private List<String> getForce60HzCustomApps(MultiSelectListPreference perf) {
        return new ArrayList<String>(perf.getValues());
    }

    private List<String> get90HzGames(MultiSelectListPreference perf) {
        return new ArrayList<>(perf.getValues());
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

        if ("force_linear".equals(key)) {
            configFile.forceLinearVibrateMode = sharedPreferences.getBoolean(key, false);
        }
        if ("new_noti_vibrator_mode".equals(key)) {
            configFile.newNotificationVibrate = sharedPreferences.getBoolean("new_noti_vibrator_mode", true);
        }
        if ("60Hz_apps".equals(key)) {
            MultiSelectListPreference mForce60HzApps = getPreferenceManager().findPreference("60Hz_apps");
            configFile.mForce60HzPackageName = getForce60HzCustomApps(mForce60HzApps);
        }
        if ("90Hz_games".equals(key)) {
            MultiSelectListPreference m90HzGameNames = getPreferenceManager().findPreference("90Hz_games");
            configFile.m90HzGameName = get90HzGames(m90HzGameNames);
        }
        if ("dolby_atmos".equals(key)) {
            configFile.dolbyDax = sharedPreferences.getBoolean(key, true);
        }

//        if ("reset_screen".equals(key)) {
//            configFile.resetScreenAfterDoze = sharedPreferences.getBoolean("reset_screen", false);
//            if (sharedPreferences.getBoolean("reset_screen", false)) {
//                Snackbar s = Snackbar.make(getView(), R.string.reset_screen_warning, Snackbar.LENGTH_INDEFINITE);
//
//                TextView snackBarTextView = (TextView) s.getView().findViewById(R.id.snackbar_text);
//                snackBarTextView.setMaxLines(8);
//                snackBarTextView.setTextSize(14.0f);
//
//                s.setAction(R.string.got_it, (view) -> {
//                    s.dismiss();
//                });
//                s.setTextColor(Color.parseColor("#f1f1f1"));
//                //s.setBackgroundTint(Color.parseColor("#424242"));
//                s.show();
//            }
//        }
        updateConfig(configFile);
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
        List<AppInfo> mAppInfoList = new ArrayList<AppInfo>();

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


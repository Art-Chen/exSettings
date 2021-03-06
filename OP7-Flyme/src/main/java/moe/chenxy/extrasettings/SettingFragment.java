package moe.chenxy.extrasettings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.preference.ListPreference;
import androidx.preference.MultiSelectListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragment;
import androidx.preference.SwitchPreferenceCompat;

import com.google.android.material.snackbar.Snackbar;

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
        configFile.dolbyDax = sharedPreferences.getBoolean("dolby_atmos", true);
        configFile.newNotificationVibrate = sharedPreferences.getBoolean("new_noti_vibrator_mode", true);
        MultiSelectListPreference mForce60HzApps = getPreferenceManager().findPreference("60Hz_apps");
        MultiSelectListPreference m90HzGames = getPreferenceManager().findPreference("90Hz_games");
        configFile.mForce60HzPackageName = getForce60HzCustomApps(mForce60HzApps);
        configFile.m90HzGameName = get90HzGames(m90HzGames);

        setAppListPreferenceValue(loadAppsList(getResolveInfos()), mForce60HzApps);
        setAppListPreferenceValue(loadAppsList(getResolveInfos()), m90HzGames);
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
        String chargeType = FileUtils.readOneLine("/sys/class/power_supply/usb/real_type");
        String batt_cur_state = Integer.parseInt(batt_current) > 0 ? "???????????????" : "???????????????";

        Preference batt_fcc_pref = getPreferenceManager().findPreference("batt_fcc");
        Preference batt_cur_pref = getPreferenceManager().findPreference("batt_cur");
        Preference chargeType_perf = getPreferenceManager().findPreference("charge_type");

        DecimalFormat df = new DecimalFormat("0.00");

        batt_fcc_pref.setSummary(batt_health + " %");
        batt_cur_pref.setSummary(batt_cur_state + Math.abs(Integer.parseInt(batt_current)) / 1000 + " mA");
        chargeType_perf.setSummary(chargeType);
    }

    private List<String> getForce60HzCustomApps(MultiSelectListPreference perf) {
        return new ArrayList<>(perf.getValues());
    }

    private List<String> get90HzGames(MultiSelectListPreference perf) {
        return new ArrayList<>(perf.getValues());
    }


    @Override
    public void onDestroy() {
        // ??????????????????????????????
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        super.onDestroy();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        // ?????????????????????????????????????????????
        // ?????????????????????sdk???????????????sdk????????????

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
        updateConfig(configFile);
        setSummaryForList("up");
        setSummaryForList("center");
        setSummaryForList("bottom");
    }

    private void updateConfig(ConfigFile configFile) {
        radioSendManage.sendMsg(getActivity(), configFile);
    }

    /**
     * ???ListPerference ????????????value???Summary
     * @param key
     */
    private void setSummaryForList(String key) {
        SharedPreferences sharedPreferences = getPreferenceManager().getSharedPreferences();
        ListPreference list = getPreferenceManager().findPreference(key);
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


    public void setAppListPreferenceValue(List<AppInfo> list, MultiSelectListPreference perf) {
        List<CharSequence> entriesCS = new ArrayList<>();
        List<CharSequence> entryValueCS = new ArrayList<>();
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


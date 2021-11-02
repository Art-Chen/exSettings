package moe.chenxy.extrasettings;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragment;

import moe.chenxy.extrasettings.config.ConfigFile;
import moe.chenxy.extrasettings.util.RadioSendManage;


public class SettingFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    ConfigFile configFile = new ConfigFile();
    public RadioSendManage radioSendManage = RadioSendManage.radioSendManage;

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
        configFile.vibrateongesture = sharedPreferences.getBoolean("vibrateongesture", true);
        configFile.pat = Integer.parseInt(sharedPreferences.getString("pat", "6"));
        configFile.chop = Integer.parseInt(sharedPreferences.getString("chop", "2"));
        configFile.camgest = Integer.parseInt(sharedPreferences.getString("camgest", "0"));
        configFile.cpumode = Integer.parseInt(sharedPreferences.getString("cpumode", "0"));
        setSummaryForList("pat");
        setSummaryForList("chop");
        setSummaryForList("camgest");
        setSummaryForList("cpumode");
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

        if ("vibrateongesture".equals(key)) {
            configFile.vibrateongesture = sharedPreferences.getBoolean(key, true);
        }
        if ("pat".equals(key)) {
            configFile.pat = Integer.parseInt(sharedPreferences.getString(key, "6"));
        }
        if ("chop".equals(key)) {
            configFile.chop = Integer.parseInt(sharedPreferences.getString(key, "2"));
        }
        if ("camgest".equals(key)) {
            configFile.camgest = Integer.parseInt(sharedPreferences.getString(key, "0"));
        }
        if ("cpumode".equals(key)) {
            configFile.cpumode = Integer.parseInt(sharedPreferences.getString(key, "0"));
        }
        updateConfig(configFile);
        setSummaryForList("pat");
        setSummaryForList("chop");
        setSummaryForList("camgest");
        setSummaryForList("cpumode");
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
}


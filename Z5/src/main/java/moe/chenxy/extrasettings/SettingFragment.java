package moe.chenxy.extrasettings;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragment;

import com.google.android.material.snackbar.Snackbar;

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
        configFile.camera_hal3 = sharedPreferences.getBoolean("camera_hal3", false);
        configFile.chop = Integer.parseInt(sharedPreferences.getString("chop", "2"));
        configFile.camgest = Integer.parseInt(sharedPreferences.getString("camgest", "0"));
        setSummaryForList("chop");
        setSummaryForList("camgest");
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
        if ("chop".equals(key)) {
            configFile.chop = Integer.parseInt(sharedPreferences.getString(key, "2"));
        }
        if ("camgest".equals(key)) {
            configFile.camgest = Integer.parseInt(sharedPreferences.getString(key, "0"));
        }
        if ("camera_hal3".equals(key)) {
            configFile.camera_hal3 = sharedPreferences.getBoolean(key, false);
        }
        updateConfig(configFile);
        setSummaryForList("chop");
        setSummaryForList("camgest");
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


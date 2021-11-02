package moe.chenxy.extrasettings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragment;

import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import moe.chenxy.extrasettings.config.ConfigFile;
import moe.chenxy.extrasettings.util.RadioSendManage;


public class SettingFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    ConfigFile configFile = new ConfigFile();
    public RadioSendManage radioSendManage = RadioSendManage.radioSendManage;
    boolean isFirstRunAndIsCustom;

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
        configFile.slide2answer = sharedPreferences.getBoolean("slide2answer", true);
        configFile.slide2reject = sharedPreferences.getBoolean("slide2reject", false);
        configFile.vibrateonslide = sharedPreferences.getBoolean("vibrateonslide", true);
        configFile.vibrateongesture = sharedPreferences.getBoolean("vibrateongesture", true);
        configFile.playsoundonslide = sharedPreferences.getBoolean("playsoundonslide", true);
        configFile.slide = Integer.parseInt(sharedPreferences.getString("slide", "1"));
        configFile.pat = Integer.parseInt(sharedPreferences.getString("pat", "6"));
        configFile.chop = Integer.parseInt(sharedPreferences.getString("chop", "2"));
        configFile.camgest = Integer.parseInt(sharedPreferences.getString("camgest", "0"));
        configFile.cpumode = Integer.parseInt(sharedPreferences.getString("cpumode", "0"));
        processSlideSoundPath(true);
        if (sharedPreferences.getBoolean("playsoundonslide", true)) {
            setSummaryForList("slidesoundpath");
        } else {
            findPreference("slidesoundpath").setSummary("Disabled");
        }
        setSummaryForList("slide");
        setSummaryForList("pat");
        setSummaryForList("chop");
        setSummaryForList("camgest");
        setSummaryForList("cpumode");
        findPreference("slidesoundpath").setShouldDisableView(true);
        findPreference("slidesoundpath").setEnabled(sharedPreferences.getBoolean("playsoundonslide", true));
        isFirstRunAndIsCustom = (getPreferenceManager().getSharedPreferences().getString("slidesoundpath", "0").equals("5"));
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

        if ("slide2answer".equals(key)) {
            configFile.slide2answer = sharedPreferences.getBoolean(key, true);
        }
        if ("slide2reject".equals(key)) {
            configFile.slide2reject = sharedPreferences.getBoolean(key, false);
        }
        if ("vibrateonslide".equals(key)) {
            configFile.vibrateonslide = sharedPreferences.getBoolean(key, true);
        }
        if ("vibrateongesture".equals(key)) {
            configFile.vibrateongesture = sharedPreferences.getBoolean(key, true);
        }
        if ("playsoundonslide".equals(key)) {
            configFile.playsoundonslide = sharedPreferences.getBoolean(key, true);
            findPreference("slidesoundpath").setEnabled(sharedPreferences.getBoolean(key, true));
        }
        if ("slide".equals(key)) {
            configFile.slide = Integer.parseInt(sharedPreferences.getString(key, "1"));
            final Snackbar snackbar = Snackbar.make(getView(), "滑盖操作将仅在桌面时触发", Snackbar.LENGTH_LONG);
            View snackbarView = snackbar.getView();
            ((TextView) snackbarView.findViewById(R.id.snackbar_text)).setTextColor(Color.WHITE);
            ((TextView) snackbarView.findViewById(R.id.snackbar_action)).setTextColor(Color.CYAN);
            snackbar.show();
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
        if ("slidesoundpath".equals(key)){
            processSlideSoundPath(false);
        }
        updateConfig(configFile);
        setSummaryForList("slide");
        setSummaryForList("pat");
        setSummaryForList("chop");
        setSummaryForList("camgest");
        setSummaryForList("cpumode");
        if (configFile.playsoundonslide) {
            setSummaryForList("slidesoundpath");
        } else {
            findPreference("slidesoundpath").setSummary("Disabled");
        }
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

    private void processSlideSoundPath(boolean isInit) {
        String value = getPreferenceManager().getSharedPreferences().getString("slidesoundpath", "0");
        if (value.equals("0")){
            configFile.SliderOpenSoundPath = "vendor/media/audio/ui/slide_keji_open.ogg";
            configFile.SliderCloseSoundPath = "vendor/media/audio/ui/slide_keji_close.ogg";
        } else if (value.equals("1")) {
            configFile.SliderOpenSoundPath = "vendor/media/audio/ui/slide_jianghu_open.ogg";
            configFile.SliderCloseSoundPath = "vendor/media/audio/ui/slide_jianghu_close.ogg";
        } else if (value.equals("2")) {
            configFile.SliderOpenSoundPath = "vendor/media/audio/ui/slide_jiguan_open.ogg";
            configFile.SliderCloseSoundPath = "vendor/media/audio/ui/slide_jiguan_close.ogg";
        } else if (value.equals("3")) {
            configFile.SliderOpenSoundPath = "vendor/media/audio/ui/slide_lingdong_open.ogg";
            configFile.SliderCloseSoundPath = "vendor/media/audio/ui/slide_lingdong_close.ogg";
        } else if (value.equals("4")) {
            configFile.SliderOpenSoundPath = "vendor/media/audio/ui/slide_zippo_open.ogg";
            configFile.SliderCloseSoundPath = "vendor/media/audio/ui/slide_zippo_close.ogg";
        } else if (value.equals("5")) {
            if (!isInit) {
                Intent intent = new Intent(getActivity(), CustomSlideSoundActivity.class);
                startActivity(intent);
            } else {
                BufferedReader br = null;
                FileReader fileReader = null;
                File saveFile = new File("/data/misc/chen/patch-config");
                if (!saveFile.exists()) {
                    return;
                }
                try {
                    fileReader = new FileReader(saveFile);
                    br = new BufferedReader(fileReader);
                    String config = br.readLine();
                    if (config != null) {
                        JSONObject jsonObject = new JSONObject(config);
                        configFile.SliderOpenSoundPath = jsonObject.getString("SliderOpenSoundPath");
                        configFile.SliderCloseSoundPath = jsonObject.getString("SliderCloseSoundPath");
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if( br!=null){
                            br.close();
                        }
                        if( fileReader!=null){
                            fileReader.close();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if (!isInit) {
            updateConfig(configFile);
        }
    }
}


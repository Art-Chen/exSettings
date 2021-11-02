package moe.chenxy.extrasettings.util;

import android.content.Context;
import android.content.Intent;

import com.alibaba.fastjson.JSON;
import moe.chenxy.extrasettings.config.ConfigFile;

public class RadioSendManage {
    public static RadioSendManage radioSendManage = new RadioSendManage();

    public RadioSendManage() {

    }

    public void sendMsg(Context context, ConfigFile value) {
        Intent intent = new Intent("moe.chenxy.SettingConfig");
        try {
            intent.putExtra("config", JSON.toJSONString(value));
            context.sendBroadcast(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendRefreshRateMsg(Context context, ConfigFile value) {
        Intent intent = new Intent("moe.chenxy.RefreshRateSettingConfig");
        try {
            intent.putExtra("config", JSON.toJSONString(value));
            context.sendBroadcast(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean requestCurrentValue(Context context) {
        Intent intent = new Intent("moe.chenxy.Setting.requestCurrentValue");
        try {
            context.sendBroadcast(intent);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}

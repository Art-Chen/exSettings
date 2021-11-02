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

}

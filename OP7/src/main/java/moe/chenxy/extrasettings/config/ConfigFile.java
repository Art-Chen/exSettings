package moe.chenxy.extrasettings.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfigFile {

    // 三段上
    public int threeTop = 0;

    // 三段中
    public int threeCentre = 0;

    // 三段下
    public int threeBottom = 0;

    // 强制映射线性马达震动
    public boolean forceLinearVibrateMode = false;

    public boolean newNotificationVibrate = true;

    // Refresh Rate
    public int refreshRateMode = 0;

    // Screen Resolution
    public int screenResolution = 0;

    public List<String> m90HzGameApps;
    public List<String> mVideoApps;
    public List<String> m60HzTopApps;
    public boolean mDisableRefreshRatePolicy = false;
}

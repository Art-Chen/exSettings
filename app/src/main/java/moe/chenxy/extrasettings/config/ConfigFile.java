package moe.chenxy.extrasettings.config;

public class ConfigFile {
    // 滑盖接听
    public boolean slide2answer = true;

    // 滑盖挂断
    public boolean slide2reject = false;

    // 滑盖震动
    public boolean vibrateonslide = true;

    // 手势震动
    public boolean vibrateongesture = true;

    // 滑盖音效
    public boolean playsoundonslide = true;

    // 滑盖
    public int slide = 1;

    // 拍一拍
    public int pat = 6;

    // 切一切
    public int chop = 2;

    // 转一转
    public int camgest = 0;

    // CPU Mode
    public int cpumode = 0;

    public String SliderOpenSoundPath = "/vendor/media/audio/ui/slide_keji_open.ogg";

    public String SliderCloseSoundPath = "/vendor/media/audio/ui/slide_keji_close.ogg";
}

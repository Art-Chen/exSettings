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

    public List<String> mForce60HzPackageName = new ArrayList<>(Arrays.asList(
            "com.qiyi.video",
            "com.ss.android.ugc.aweme",
            "com.smile.gifmaker",
            "com.ss.android.article.video",
            "com.tencent.qqlive",
            "tv.danmaku.bili",
            "com.ss.android.ugc.live",
            "com.youku.phone",
            "com.kuaishou.nebula",
            "tv.pps.mobile",
            "com.baidu.haokan",
            "com.ss.android.ugc.aweme.lite",
            "com.duowan.kiwi",
            "com.le123.ysdq",
            "com.hunantv.imgo.activity",
            "air.tv.douyu.android",
            "com.tencent.karaoke",
            "com.yeahka.mach.android.shuabao",
            "com.xunlei.downloadprovider",
            "com.tencent.weishi",
            "com.babycloud.hanju",
            "com.ss.android.ugc.livelite",
            "com.cmcc.cmvideo",
            "com.tencent.videolite.android",
            "com.duowan.mobile",
            "tv.yixia.bobo",
            "com.sohu.sohuvideo",
            "com.qihoo.video",
            "com.mt.mtxx.mtxx",
            "video.like",
            "com.tencent.qgame",
            "com.letv.android.client",
            "cn.cntv",
            "com.baidu.minivideo",
            "com.pplive.androidphone",
            "tv.acfundanmaku.video",
            "com.lemon.faceu",
            "com.meitu.meiyancamera",
            "com.changba",
            "com.diyidan"
    ));

    public List<String> m90HzGameName = new ArrayList<>(Arrays.asList(
            "com.yinhan.skzh",
            "com.yinhan.skzh.yinhan",
            "com.yinhan.skzh.mz",
            "com.netease.onmyoji",
            "com.netease.onmyoji.mz",
            "com.tencent.shootgame",
            "com.tencent.tmgp.speedmobile",
            "com.minitech.miniworld",
            "com.minitech.miniworld.meizu",
            "com.tencent.game.rhythmmaster",
            "com.cmplay.tiles2_cn",
            "com.cmplay.tiles2_cn.mz",
            "com.brianbaek.popstar",
            "com.mfp.jelly",
            "com.mfp.jelly.meizu",
            "com.fgol",
            "com.tencent.af",
            "com.netease.lztg.mz",
            "com.netease.lztg",
            "com.tencent.YiRen",
            "com.miHoYo.bh3.uc",
            "com.miHoYo.bh3.mz",
            "com.miHoYo.bh3",
            "com.tencent.tmgp.pubgmhd",
            "com.pwrd.xxajh.nearme.gamecenter",
            "com.tencent.qs",
            "com.tencent.tmgp.sgame",
            "com.protopop.brightridge",
            "com.tencent.tmgp.WePop",
            "com.tencent.tmgp.cf",
            "com.kiloo.subwaysurf",
            "com.tencent.KiHan"
    ));

    public boolean mUseAodAlwaysMode = false;
	    public boolean dolbyDax = true;

}

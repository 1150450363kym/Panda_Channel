package comuxi.example.administrator.panda_channel.app;

import android.app.Application;

import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import cn.jpush.android.api.JPushInterface;
import comuxi.example.administrator.panda_channel.Base.BaseActivity;

/**
 * Created by Administrator on 2017/7/11.
 */

public class App extends Application {

    {
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        PlatformConfig.setSinaWeibo("3783709797", "d316fac0ff73b81f15d56fbeacd10e3e", "http://sns.whalecloud.com");
    }


    @Override
    public void onCreate() {


//        Logger.d(TAG, "[ExampleApplication] onCreate");
        super.onCreate();

        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush
        Config.DEBUG = true;
        UMShareAPI.get(this);
//        CrashHandler.getInstance().init(this);//初始化全局异常管理
    }
    public static BaseActivity content = null;
}

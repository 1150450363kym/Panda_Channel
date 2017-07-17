package comuxi.example.administrator.panda_channel.app;

import android.app.Application;

import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import comuxi.example.administrator.panda_channel.Base.BaseActivity;

/**
 * Created by Administrator on 2017/7/11.
 */

public class App extends Application {

    {
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        PlatformConfig.setSinaWeibo("1299368296", "704310277911805d4f945a23fbb3dd60", "http://sns.whalecloud.com");
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Config.DEBUG = true;
        UMShareAPI.get(this);
    }
    public static BaseActivity content = null;
}

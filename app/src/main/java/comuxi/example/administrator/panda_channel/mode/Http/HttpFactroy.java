package comuxi.example.administrator.panda_channel.mode.Http;

/**
 * Created by Administrator on 2017/7/12.
 * 工厂类 返回 网络请求的方法
 *
 */

public class HttpFactroy {

    public static IHttp craet(){

        return OkHttpUtils.getInstance();
    }
}

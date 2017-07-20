package comuxi.example.administrator.panda_channel.mode.Http;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

import comuxi.example.administrator.panda_channel.app.App;
import comuxi.example.administrator.panda_channel.mode.CallBack.MyHttpCallBack;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/7/11.
 * IHttp 的实现类
 * 单例模式 懒汉式
 */

public class OkHttpUtils implements IHttp {
    private OkHttpClient client;
    private static OkHttpUtils okHttpUtils;

    //    懒汉构造函数私有化,在里面 new OkHttp的对象
    private OkHttpUtils() {
        client = new OkHttpClient.Builder().build();
    }
//    提供一个公共  静态 返回 本类对象

    public static OkHttpUtils getInstance() {
        if (okHttpUtils == null) {
            synchronized (OkHttpUtils.class) {
                if (okHttpUtils == null) {
                    okHttpUtils = new OkHttpUtils();

                }
            }

        }

        return okHttpUtils;

    }

    /**
     * 发送Get请求
     *
     * @param url
     * @param map
     * @param callBack
     * @param <T>
     */

    @Override
    public <T> void get(String url, Map<String, String> map, final MyHttpCallBack<T> callBack) {
//就是对网络请求的地址  追加  返回参数
        StringBuffer buffer = new StringBuffer(url);
        if (map != null && map.size() > 0) {
            buffer.append("?");
            Set<String> keys = map.keySet();

            for (String key : keys) {
                String valus = map.get(key);
                buffer.append(key).append("=").append(valus).append("&");
            }
//追加 参数 后的  一个 地址
            url = buffer.deleteCharAt(buffer.length() - 1).toString();


        }

        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                App.content.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        callBack.onError(404, e.getMessage().toString());
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();

                App.content.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onSuccess(getGeneric(string, callBack));
                    }
                });
            }
        });

    }


    /**
     * 发送POST请求
     *
     * @param url
     * @param map
     * @param callBack
     * @param <T>
     */

    @Override
    public <T> void post(String url, Map<String, String> map, MyHttpCallBack<T> callBack) {

    }


    /**
     * 自行解析 Json数据 回调 至JavaBean
     *
     * @param jsonData
     * @param callBack
     * @param <T>
     * @return
     */

    private <T> T getGeneric(String jsonData, MyHttpCallBack<T> callBack) {

        Gson gson = new Gson();
//        通过反射获取泛型的实例
        Type[] types = callBack.getClass().getGenericInterfaces();
        Type[] actualTypeArguments = ((ParameterizedType) types[0]).getActualTypeArguments();
        Type type = actualTypeArguments[0];
        T t = gson.fromJson(jsonData, type);

//        ACache aCache = ACache.get(App.content);
//        aCache.put(t.getClass().getSimpleName(), (Serializable) t);
        return t;
    }
}

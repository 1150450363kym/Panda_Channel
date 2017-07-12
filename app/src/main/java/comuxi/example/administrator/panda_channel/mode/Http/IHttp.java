package comuxi.example.administrator.panda_channel.mode.Http;

import java.util.Map;

import comuxi.example.administrator.panda_channel.mode.CallBack.MyHttpCallBack;

/**
 * Created by Administrator on 2017/7/11.
 * 网络请求的顶层接口
 */

public interface IHttp {
    //get请求。
    <T> void get(String url, Map<String, String> map, MyHttpCallBack<T> callBack);

    //post请求
    <T> void post(String url, Map<String, String> map, MyHttpCallBack<T> callBack);
}

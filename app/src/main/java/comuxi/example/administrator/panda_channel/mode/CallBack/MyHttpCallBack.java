package comuxi.example.administrator.panda_channel.mode.CallBack;

/**
 * Created by Administrator on 2017/7/11.
 */

public interface MyHttpCallBack<T> {
    void  onSuccess(T t);
    void  onError(int errorCode,String errorMsg);//返回错误值，错误信息

}

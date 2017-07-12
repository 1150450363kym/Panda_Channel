package comuxi.example.administrator.panda_channel.mode.biz;

import comuxi.example.administrator.panda_channel.mode.CallBack.MyHttpCallBack;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.Home_Data_TextBean;

/**
 * Created by Administrator on 2017/7/12.
 */

public interface PandaMode extends BaseMode {
//熊猫客户端首页
    void getHomeData(MyHttpCallBack<Home_Data_TextBean> callBack);

}

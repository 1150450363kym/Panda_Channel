package comuxi.example.administrator.panda_channel.mode.biz;

import comuxi.example.administrator.panda_channel.mode.CallBack.MyHttpCallBack;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.GG_TV_TextBean;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.Home_Data_TextBean;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.MoreLiveBean;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.PandaBroadCastBean;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.PandaBroadCastListBean;

/**
 * Created by Administrator on 2017/7/12.
 */

public interface PandaMode extends BaseMode {
//熊猫客户端首页
    void getHomeData(MyHttpCallBack<Home_Data_TextBean> callBack);
    //熊猫滚滚视频
    void getGGTV(MyHttpCallBack<GG_TV_TextBean>callBack);
    //熊猫直播-直播--多视角直播
    void getMoreLive(MyHttpCallBack<MoreLiveBean> myHttpCallBack);

    //熊猫播报
    void getPandaBroadcast(MyHttpCallBack<PandaBroadCastBean> myHttpCallBack);

    //熊猫播报列表
    void getPandaBroadcastList(String path, String primary_id, String serviceId, MyHttpCallBack<PandaBroadCastListBean> myHttpCallBack);

}

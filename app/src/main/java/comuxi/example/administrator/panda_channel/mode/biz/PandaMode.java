package comuxi.example.administrator.panda_channel.mode.biz;

import comuxi.example.administrator.panda_channel.mode.CallBack.MyHttpCallBack;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.CehuaBean;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.China_Live_Path_TextBean;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.GG_TV_TextBean;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.Home_Data_TextBean;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.LoginBean;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.MLiveBean;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.MoreLiveBean;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.PandaBroadCastBean;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.PandaBroadCastListBean;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.VideoplayerBean;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.WonderfulOneBean;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.XiquaimationBean;

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

    //熊猫直播--精彩一刻
//    vsid=VSET100167216881&n=7&serviceId=panda&o=desc&of=time&p=1
    void getWonderfulone(String vsid,String n,String serviceId,String o,String of,int p,MyHttpCallBack<WonderfulOneBean> myHttpCallBack);

//    熊猫直播 -- 直播--边看边聊
//    app=ipandaApp&itemid=zhiboye_chat&nature=1&page=1
    void getWatchChat(String app,String itemid,String nature,int page,MyHttpCallBack<WonderfulOneBean> myHttpCallBack);


    //熊猫直播
    //http:www.ipanda.com/kehuduan/PAGE14501769230331752/index.json
    void getPandaLive(MyHttpCallBack<MLiveBean> myHttpCallBack);

    //特别策划
    void getCehua(MyHttpCallBack<CehuaBean> myHttpCallBack);
    void get_Live_China(MyHttpCallBack<China_Live_Path_TextBean> live_path_textBeanMyHttpCallBack);

    //熊猫文化--第一个item点击跳转
    //http://api.cntv.cn/video/videolistById?n=6&vsid=VSET100311356635&p=1&serviceId=panda&em=1
    void getXiquanimation(String n,String vsid,String p,String serviceId,int em,MyHttpCallBack<XiquaimationBean> myHttpCallBack);

    //播放视频路径
    void getVideoplayer(String pid,MyHttpCallBack<VideoplayerBean> myHttpCallBack);

    //登录
    void getLogin(String username, String password, MyHttpCallBack<LoginBean> myHttpCallBack);

}

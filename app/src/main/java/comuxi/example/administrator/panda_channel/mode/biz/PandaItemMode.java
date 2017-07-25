package comuxi.example.administrator.panda_channel.mode.biz;

import java.util.HashMap;
import java.util.Map;

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
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.UpDateLoading;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.VideoplayerBean;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.WonderfulOneBean;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.XiquaimationBean;
import comuxi.example.administrator.panda_channel.mode.Url_Path.Url;


/**
 * Created by Administrator on 2017/7/12.
 */

public class PandaItemMode implements PandaMode {

    @Override
    public void getHomeData(MyHttpCallBack<Home_Data_TextBean> callBack) {


        I_HTTP.get(Url.PANDAHOME, null, callBack);

    }

    @Override
    public void getGGTV(MyHttpCallBack<GG_TV_TextBean> callBack) {

        I_HTTP.get(Url.GGTV, null, callBack);
    }

    @Override
    public void getMoreLive(MyHttpCallBack<MoreLiveBean> myHttpCallBack) {

        I_HTTP.get(Url.MORELIVE, null, myHttpCallBack);
    }

    @Override
    public void getPandaBroadcast(MyHttpCallBack<PandaBroadCastBean> myHttpCallBack) {

        I_HTTP.get(Url.PANDABROADCASTIMG, null, myHttpCallBack);
    }

    @Override
    public void getPandaBroadcastList(String path, String primary_id, String serviceId, MyHttpCallBack<PandaBroadCastListBean> myHttpCallBack) {

        Map<String, String> map = new HashMap<>();
        map.put("path", path);
        map.put("primary_id", primary_id);
        map.put("serviceId", serviceId);
        I_HTTP.get(Url.PANDABROADCAST, map, myHttpCallBack);
    }

    @Override
    public void getWonderfulone(String vsid, String n, String serviceId, String o, String of, int p, MyHttpCallBack<WonderfulOneBean> myHttpCallBack) {

        Map<String, String> map = new HashMap<>();
        map.put("vsid", vsid);
        map.put("n", n);
        map.put("serviceId", serviceId);
        map.put("o", o);
        map.put("of", of);
        map.put("p", p + "");
        I_HTTP.get(Url.WONDERFULONE, map, myHttpCallBack);
    }

    @Override
    public void getWatchChat(String app, String itemid, String nature, int page, MyHttpCallBack<WonderfulOneBean> myHttpCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("app", app);
        map.put("itemid", itemid);
        map.put("nature", nature);
        map.put("page", page + "");
        I_HTTP.get(Url.WATCHCHAT, map, myHttpCallBack);
    }

    @Override
    public void getPandaLive(MyHttpCallBack<MLiveBean> myHttpCallBack) {

        I_HTTP.get(Url.MPANDALIVE, null, myHttpCallBack);
    }

    @Override
    public void getCehua(MyHttpCallBack<CehuaBean> myHttpCallBack) {

        I_HTTP.get(Url.CEHUA, null, myHttpCallBack);
    }

    @Override
    public void get_Live_China(MyHttpCallBack<China_Live_Path_TextBean> live_path_textBeanMyHttpCallBack) {
        I_HTTP.get(Url.Live_CHINA, null, live_path_textBeanMyHttpCallBack);
    }

    @Override
    public void getXiquanimation(String n, String vsid, String p, String serviceId, int em, MyHttpCallBack<XiquaimationBean> myHttpCallBack) {

        Map<String,String> map = new HashMap<>();
        map.put("n",n);
        map.put("vsid",vsid);
        map.put("p",p);
        map.put("serviceId",serviceId);
        map.put("em",em+"");
        I_HTTP.get(Url.XIQUANIMATION,map,myHttpCallBack);
    }

    @Override
    public void getVideoplayer(String pid, MyHttpCallBack<VideoplayerBean> myHttpCallBack) {

        Map<String,String> map = new HashMap<>();

        I_HTTP.get(Url.TV_Url+pid,map,myHttpCallBack);

    }

    @Override
    public void getLogin(String username, String password, MyHttpCallBack<LoginBean> myHttpCallBack) {

        Map<String,String> map = new HashMap<>();
        map.put("from","https://reg.cntv.cn/login/login.action");
        map.put("service","client_transaction");
        map.put("username",username);
        map.put("password",password);
        I_HTTP.post(Url.LOGIN,map,myHttpCallBack);
    }

    @Override
    public void getVersion(MyHttpCallBack<UpDateLoading> myHttpCallBack) {
        I_HTTP.get(Url.UPDATE,null,myHttpCallBack);

    }

}

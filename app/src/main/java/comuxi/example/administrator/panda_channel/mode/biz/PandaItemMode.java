package comuxi.example.administrator.panda_channel.mode.biz;

import java.util.HashMap;
import java.util.Map;

import comuxi.example.administrator.panda_channel.mode.CallBack.MyHttpCallBack;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.GG_TV_TextBean;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.Home_Data_TextBean;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.MoreLiveBean;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.PandaBroadCastBean;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.PandaBroadCastListBean;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.WonderfulOneBean;
import comuxi.example.administrator.panda_channel.mode.Url_Path.Url;

/**
 * Created by Administrator on 2017/7/12.
 */

public class PandaItemMode implements PandaMode {

    @Override
    public void getHomeData(MyHttpCallBack<Home_Data_TextBean> callBack) {


        I_HTTP.get(Url.PANDAHOME,null,callBack);

    }

    @Override
    public void getGGTV(MyHttpCallBack<GG_TV_TextBean> callBack) {

        I_HTTP.get(Url.GGTV,null,callBack);
    }

    @Override
    public void getMoreLive(MyHttpCallBack<MoreLiveBean> myHttpCallBack) {

        I_HTTP.get(Url.MORELIVE,null,myHttpCallBack);
    }

    @Override
    public void getPandaBroadcast(MyHttpCallBack<PandaBroadCastBean> myHttpCallBack) {

        I_HTTP.get(Url.PANDABROADCASTIMG,null,myHttpCallBack);
    }

    @Override
    public void getPandaBroadcastList(String path, String primary_id, String serviceId, MyHttpCallBack<PandaBroadCastListBean> myHttpCallBack) {

        Map<String,String> map = new HashMap<>();
        map.put("path",path);
        map.put("primary_id",primary_id);
        map.put("serviceId",serviceId);
        I_HTTP.get(Url.PANDABROADCAST,map,myHttpCallBack);
    }

    @Override
    public void getWonderfulone(String vsid, String n, String serviceId, String o, String of, int p, MyHttpCallBack<WonderfulOneBean> myHttpCallBack) {

        Map<String,String> map = new HashMap<>();
        map.put("vsid",vsid);
        map.put("n",n);
        map.put("serviceId",serviceId);
        map.put("o",o);
        map.put("of",of);
        map.put("p",p+"");
        I_HTTP.get(Url.WONDERFULONE,map,myHttpCallBack);
    }

    @Override
    public void getWatchChat(String app, String itemid, String nature, int page, MyHttpCallBack<WonderfulOneBean> myHttpCallBack) {
        Map<String,String> map = new HashMap<>();
        map.put("app",app);
        map.put("itemid",itemid);
        map.put("nature",nature);
        map.put("page",page+"");
        I_HTTP.get(Url.WATCHCHAT,map,myHttpCallBack);
    }

}

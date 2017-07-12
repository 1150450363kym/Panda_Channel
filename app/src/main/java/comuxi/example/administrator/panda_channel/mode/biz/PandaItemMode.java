package comuxi.example.administrator.panda_channel.mode.biz;

import comuxi.example.administrator.panda_channel.mode.CallBack.MyHttpCallBack;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.Home_Data_TextBean;
import comuxi.example.administrator.panda_channel.mode.Url_Path.Url;

/**
 * Created by Administrator on 2017/7/12.
 */

public class PandaItemMode implements PandaMode {
    @Override
    public void getHomeData(MyHttpCallBack<Home_Data_TextBean> callBack) {


        I_HTTP.get(Url.PANDAHOME,null,callBack);

    }
}

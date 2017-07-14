package comuxi.example.administrator.panda_channel.moudel.Pandan_Broadcast;

import comuxi.example.administrator.panda_channel.Base.BasePresenter;
import comuxi.example.administrator.panda_channel.Base.BaseView;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.PandaBroadCastBean;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.PandaBroadCastListBean;

/**
 * 契约类
 * Created by Administrator on 2017/7/11.
 * 将 Viow 是图中 能改变的 东西 全部 抽调成为 接口
 */

public class Pandan_Broadcast_Contract {
    interface View extends BaseView<presenter> {

        void showData(PandaBroadCastBean pandaBroadCastListBean);
        void showDatalist(PandaBroadCastListBean pandaBroadCastListBean);
        void showMsg(String msg);


    }

//    契约类 网络请求的接口
    interface presenter extends BasePresenter {

    void showPandapresenter(String path, String primary_id, String serviceId);

    }
}

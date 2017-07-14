package comuxi.example.administrator.panda_channel.moudel.Panda_Live.contract;

import comuxi.example.administrator.panda_channel.Base.BasePresenter;
import comuxi.example.administrator.panda_channel.Base.BaseView;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.WonderfulOneBean;

/**
 * Created by lenovo on 2017/7/14.
 */

public interface PandaThingContract {

    interface View extends BaseView<presenter> {

        void showData(WonderfulOneBean moreLiveBean);
        void showMsg(String msg);
    }

    //    契约类 网络请求的接口
    interface presenter extends BasePresenter {

        void showData(String vsid, String n, String serviceId, String o, String of, int p);


    }
}

package comuxi.example.administrator.panda_channel.moudel.Panda_Live.contract;

import comuxi.example.administrator.panda_channel.Base.BasePresenter;
import comuxi.example.administrator.panda_channel.Base.BaseView;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.MoreLiveBean;

/**
 * Created by lenovo on 2017/7/13.
 */

public interface MoreLiveContract  {


    interface View extends BaseView<presenter> {

        void showData(MoreLiveBean moreLiveBean);
        void showMsg(String msg);
    }

    //    契约类 网络请求的接口
    interface presenter extends BasePresenter {


    }
}

package comuxi.example.administrator.panda_channel.moudel.Panda_Live.contract;

import comuxi.example.administrator.panda_channel.Base.BasePresenter;
import comuxi.example.administrator.panda_channel.Base.BaseView;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.MLiveBean;

/**
 * Created by lenovo on 2017/7/14.
 */

public interface LiveContract {

    interface View extends BaseView<presenter> {

        void showData(MLiveBean moreLiveBean);
        void showMsg(String msg);
    }

    //    契约类 网络请求的接口
    interface presenter extends BasePresenter {

    }
}

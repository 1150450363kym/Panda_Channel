package comuxi.example.administrator.panda_channel.moudel.GG_TV;

import comuxi.example.administrator.panda_channel.Base.BasePresenter;
import comuxi.example.administrator.panda_channel.Base.BaseView;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.GG_TV_TextBean;

/**
 * 契约类
 * 将 Viow 是图中 能改变的 东西 全部 抽调成为 接口
 * Created by Administrator on 2017/7/11.
 */

public interface GG_TV_Contract {

    interface View extends BaseView<presenter> {

        void showData(GG_TV_TextBean gg_tv_textBean);
        void showMsg(String msg);
    }

//    契约类 网络请求的接口
    interface presenter extends BasePresenter {


    }
}

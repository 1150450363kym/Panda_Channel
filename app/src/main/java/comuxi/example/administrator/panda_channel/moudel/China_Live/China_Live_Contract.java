package comuxi.example.administrator.panda_channel.moudel.China_Live;

import comuxi.example.administrator.panda_channel.Base.BasePresenter;
import comuxi.example.administrator.panda_channel.Base.BaseView;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.China_Live_Path_TextBean;

/**
 * 契约类
 * Created by Administrator on 2017/7/11.
 *  将 Viow 是图中 能改变的 东西 全部 抽调成为 接口

 */
////////
public interface China_Live_Contract {
    interface View extends BaseView<presenter> {

        void getMessage(String message);
        void getNetData(China_Live_Path_TextBean china_live_path_textBean);



    }

//    契约类 网络请求的接口
    interface presenter extends BasePresenter {


    }
}

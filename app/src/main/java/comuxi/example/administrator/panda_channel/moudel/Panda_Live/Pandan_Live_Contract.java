package comuxi.example.administrator.panda_channel.moudel.Panda_Live;


import comuxi.example.administrator.panda_channel.Base.BasePresenter;
import comuxi.example.administrator.panda_channel.Base.BaseView;
import comuxi.example.administrator.panda_channel.moudel.Home.HomeContract;

/**
 * 契约类
 * Created by Administrator on 2017/7/11.
 * 将 Viow 是图中 能改变的 东西 全部 抽调成为 接口
 */

public interface Pandan_Live_Contract {
    interface View extends BaseView<HomeContract.presenter> {


    }

//    契约类 网络请求的接口
    interface presenter extends BasePresenter {

    }
}

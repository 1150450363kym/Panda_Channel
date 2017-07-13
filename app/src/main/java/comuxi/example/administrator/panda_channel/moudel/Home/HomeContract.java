package comuxi.example.administrator.panda_channel.moudel.Home;

import comuxi.example.administrator.panda_channel.Base.BasePresenter;
import comuxi.example.administrator.panda_channel.Base.BaseView;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.Home_Data_TextBean;

/**
 * 首页契约类
 * Created by Administrator on 2017/7/11.
 * 将 Viow 是图中 能改变的 东西 全部 抽调成为 接口
 */

public interface HomeContract {
    interface View extends BaseView<presenter> {
            void shouProgress();//展示 进度条
            void dissProgress();//结束 进度条
            void  showCarousel(Home_Data_TextBean data_textBean);// 展示轮播图
            void  showMessage(String s);//打印报错信息
        void  showPandaBroadcast(Home_Data_TextBean data_textBean);//熊猫播报
        void showXrecycleView(Home_Data_TextBean data_textBean);//设置XrecycleView



    }

//    契约类 网络请求的接口
    interface presenter extends BasePresenter {

    }
}

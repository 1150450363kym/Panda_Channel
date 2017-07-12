package comuxi.example.administrator.panda_channel.moudel.Home;

import comuxi.example.administrator.panda_channel.mode.CallBack.MyHttpCallBack;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.Home_Data_TextBean;
import comuxi.example.administrator.panda_channel.mode.biz.PandaItemMode;
import comuxi.example.administrator.panda_channel.mode.biz.PandaMode;

/**
 * Created by Administrator on 2017/7/11.
 */

public class HomePresenter implements HomeContract.presenter {
    private HomeContract.View homeview;
    private PandaMode pandaMode; //网络请求的顶层接口

    //    这个是  将 对应的Fragment传过来
    public HomePresenter(HomeContract.View homeview) {

        this.homeview = homeview;

        this.homeview.setPresenter(this);
        pandaMode = new PandaItemMode();//new  它的子实现类

    }

    //    网络请求
    @Override
    public void start() {

        pandaMode.getHomeData(new MyHttpCallBack<Home_Data_TextBean>() {
            @Override
            public void onSuccess(Home_Data_TextBean home_data_textBean) {

            homeview.showCarousel(home_data_textBean);


            }

            @Override
            public void onError(int errorCode, String errorMsg) {

                homeview.showMessage(errorMsg);

            }
        });

    }
}

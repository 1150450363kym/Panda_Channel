package comuxi.example.administrator.panda_channel.moudel.Home;

/**
 * Created by Administrator on 2017/7/11.
 */

public class HomePresenter implements HomeContract.presenter {
    private HomeContract.View homeview;
    //    这个是  将 对应的Fragment传过来
    public HomePresenter(HomeContract.View homeview){

        this.homeview = homeview;
        this.homeview.setPresenter(this);
    }


//    网络请求
    @Override
    public void start() {

    }
}

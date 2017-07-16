package comuxi.example.administrator.panda_channel.moudel.Panda_Live.presenter;

import comuxi.example.administrator.panda_channel.mode.CallBack.MyHttpCallBack;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.MLiveBean;
import comuxi.example.administrator.panda_channel.mode.biz.PandaItemMode;
import comuxi.example.administrator.panda_channel.mode.biz.PandaMode;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.contract.LiveContract;

/**
 * Created by lenovo on 2017/7/14.
 */

public class LivePresenter implements LiveContract.presenter {

    private LiveContract.View oneView;
    private PandaMode pandaMode;

    public LivePresenter(LiveContract.View oneView){
        this.oneView = oneView;
        pandaMode = new PandaItemMode();
        this.oneView.setPresenter(this);
    }

    @Override
    public void start() {

        pandaMode.getPandaLive(new MyHttpCallBack<MLiveBean>() {
            @Override
            public void onSuccess(MLiveBean mLiveBean) {
                oneView.showData(mLiveBean);
            }

            @Override
            public void onError(int errorCode, String errorMsg) {

                oneView.showMsg(errorMsg);
            }
        });
    }
}

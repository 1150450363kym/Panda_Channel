package comuxi.example.administrator.panda_channel.moudel.Panda_Live.presenter;

import comuxi.example.administrator.panda_channel.mode.CallBack.MyHttpCallBack;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.MoreLiveBean;
import comuxi.example.administrator.panda_channel.mode.biz.PandaItemMode;
import comuxi.example.administrator.panda_channel.mode.biz.PandaMode;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.contract.MoreLiveContract;

/**
 * Created by lenovo on 2017/7/13.
 */

public class MoreLivePresenter implements MoreLiveContract.presenter {


    private MoreLiveContract.View  moreliveview;
    private PandaMode pandaMode;

    public MoreLivePresenter(MoreLiveContract.View moreliveview) {
        this.moreliveview = moreliveview;
        pandaMode = new PandaItemMode();
       this.moreliveview.setPresenter(this);
    }

    @Override
    public void start() {

        pandaMode.getMoreLive(new MyHttpCallBack<MoreLiveBean>() {
            @Override
            public void onSuccess(MoreLiveBean moreLiveBean) {
                moreliveview.showData(moreLiveBean);
            }

            @Override
            public void onError(int errorCode, String errorMsg) {

                moreliveview.showMsg(errorMsg);
            }
        });

    }
}

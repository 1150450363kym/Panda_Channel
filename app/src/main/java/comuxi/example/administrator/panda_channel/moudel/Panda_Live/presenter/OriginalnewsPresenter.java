package comuxi.example.administrator.panda_channel.moudel.Panda_Live.presenter;

import comuxi.example.administrator.panda_channel.mode.CallBack.MyHttpCallBack;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.WonderfulOneBean;
import comuxi.example.administrator.panda_channel.mode.biz.PandaItemMode;
import comuxi.example.administrator.panda_channel.mode.biz.PandaMode;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.contract.OriginalnewsContract;

/**
 * Created by lenovo on 2017/7/14.
 */

public class OriginalnewsPresenter implements OriginalnewsContract.presenter {

    private OriginalnewsContract.View oneView;
    private PandaMode pandaMode;


    public OriginalnewsPresenter(OriginalnewsContract.View oneView){
        this.oneView = oneView;
        pandaMode = new PandaItemMode();
        this.oneView.setPresenter(this);
    }

    @Override
    public void showData(String vsid, String n, String serviceId, String o, String of, int p) {

        pandaMode.getWonderfulone(vsid, n, serviceId, o, of, p, new MyHttpCallBack<WonderfulOneBean>() {
            @Override
            public void onSuccess(WonderfulOneBean wonderfulOneBean) {
                oneView.showData(wonderfulOneBean);
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
                oneView.showMsg(errorMsg);
            }
        });
    }

    @Override
    public void start() {

    }
}

package comuxi.example.administrator.panda_channel.moudel.Pandan_Broadcast;

import comuxi.example.administrator.panda_channel.mode.CallBack.MyHttpCallBack;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.PandaBroadCastBean;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.PandaBroadCastListBean;
import comuxi.example.administrator.panda_channel.mode.biz.PandaItemMode;
import comuxi.example.administrator.panda_channel.mode.biz.PandaMode;

/**
 * Created by Administrator on 2017/7/11.
 */

public class Pandan_Broadcast_Presenter implements Pandan_Broadcast_Contract.presenter {

    private Pandan_Broadcast_Contract.View panda_broadcastView;
    private PandaMode pandaMode;

    public Pandan_Broadcast_Presenter(Pandan_Broadcast_Contract.View panda_broadcastView){

        this.panda_broadcastView = panda_broadcastView;
        pandaMode = new PandaItemMode();
        this.panda_broadcastView.setPresenter(this);
    }
    @Override
    public void start() {

        pandaMode.getPandaBroadcast(new MyHttpCallBack<PandaBroadCastBean>() {
            @Override
            public void onSuccess(PandaBroadCastBean pandaBroadCastListBean) {
                panda_broadcastView.showData(pandaBroadCastListBean);
            }

            @Override
            public void onError(int errorCode, String errorMsg) {

                panda_broadcastView.showMsg(errorMsg);
            }
        });
    }


    @Override
    public void showPandapresenter(String path, String primary_id, String serviceId) {

        pandaMode.getPandaBroadcastList(path, primary_id, serviceId, new MyHttpCallBack<PandaBroadCastListBean>() {
            @Override
            public void onSuccess(PandaBroadCastListBean pandaBroadCastListBean) {

                panda_broadcastView.showDatalist(pandaBroadCastListBean);
            }

            @Override
            public void onError(int errorCode, String errorMsg) {

                panda_broadcastView.showMsg(errorMsg);
            }
        });

    }
}

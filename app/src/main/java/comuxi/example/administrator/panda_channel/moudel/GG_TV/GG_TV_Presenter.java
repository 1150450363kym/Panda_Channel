package comuxi.example.administrator.panda_channel.moudel.GG_TV;

import comuxi.example.administrator.panda_channel.mode.CallBack.MyHttpCallBack;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.GG_TV_TextBean;
import comuxi.example.administrator.panda_channel.mode.biz.PandaItemMode;
import comuxi.example.administrator.panda_channel.mode.biz.PandaMode;

/**
 * Created by Administrator on 2017/7/11.
 */

public class GG_TV_Presenter implements GG_TV_Contract.presenter {

    private GG_TV_Contract.View ggtv_view;
    private PandaMode pandaMode;

    public GG_TV_Presenter(GG_TV_Contract.View ggtv_view) {

        this.ggtv_view = ggtv_view;
        this.ggtv_view.setPresenter(this);
        pandaMode = new PandaItemMode();

    }

    @Override
    public void start() {


        pandaMode.getGGTV(new MyHttpCallBack<GG_TV_TextBean>() {
            @Override
            public void onSuccess(GG_TV_TextBean gg_tv_textBean) {

                ggtv_view.showData(gg_tv_textBean);
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
                ggtv_view.showMsg(errorMsg);
            }
        });


    }

}

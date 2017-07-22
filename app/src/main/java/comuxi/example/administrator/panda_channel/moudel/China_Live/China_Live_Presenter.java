package comuxi.example.administrator.panda_channel.moudel.China_Live;

import comuxi.example.administrator.panda_channel.mode.CallBack.MyHttpCallBack;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.China_Live_Path_TextBean;
import comuxi.example.administrator.panda_channel.mode.biz.PandaItemMode;
import comuxi.example.administrator.panda_channel.mode.biz.PandaMode;

/**
 * Created by Administrator on 2017/7/11.
 */

public class China_Live_Presenter implements China_Live_Contract.presenter {

    private China_Live_Contract.View chian_view;
    private PandaMode pandaMode; //网络请求的顶层接口

    public China_Live_Presenter(China_Live_Contract.View chian_view) {
        this.chian_view = chian_view;
        this.chian_view.setPresenter(this);

        pandaMode = new PandaItemMode();

    }


    //    网络请求 写在这里
    @Override
    public void start() {

        pandaMode.get_Live_China(new MyHttpCallBack<China_Live_Path_TextBean>() {
            @Override
            public void onSuccess(China_Live_Path_TextBean china_live_path_textBean) {

                chian_view.getNetData(china_live_path_textBean);

            }

            @Override
            public void onError(int errorCode, String errorMsg) {
                chian_view.getMessage(errorMsg);
            }
        });




    }
}

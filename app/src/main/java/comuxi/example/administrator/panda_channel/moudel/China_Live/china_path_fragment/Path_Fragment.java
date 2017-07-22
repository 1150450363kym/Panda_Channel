package comuxi.example.administrator.panda_channel.moudel.China_Live.china_path_fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import comuxi.example.administrator.panda_channel.Base.BaseFragment;
import comuxi.example.administrator.panda_channel.R;
import comuxi.example.administrator.panda_channel.Utils.ACache;
import comuxi.example.administrator.panda_channel.app.App;
import comuxi.example.administrator.panda_channel.mode.CallBack.MyHttpCallBack;
import comuxi.example.administrator.panda_channel.mode.Http.HttpFactroy;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.Chian_item_Path_TextBean;
import comuxi.example.administrator.panda_channel.moudel.China_Live.china_adapter.China_Live_Xrecycle;

/**
 * Created by Administrator on 2017/7/17.
 */

public class Path_Fragment extends BaseFragment {
    @BindView(R.id.Chian_live_path_xrecycle)
    XRecyclerView ChianLivePathXrecycle;
    Unbinder unbinder;
    @BindView(R.id.progress_bar_id)
    RelativeLayout progressBarId;
    private String url;
    ArrayList<Chian_item_Path_TextBean.LiveBean> list_array = new ArrayList<>();
    China_Live_Xrecycle live_xrecycle;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 300:

                    live_xrecycle.notifyDataSetChanged();
                    break;
            }
        }
    };

    public Path_Fragment(String url) {
        this.url = url;
        Log.e("TAG", "打印一下局部的地址" + url);

    }

    @Override
    protected int getlayoutID() {
        return R.layout.path_fragment;
    }

    @Override
    protected void init(View view) {

        progressBarId.setVisibility(View.VISIBLE);
    }


    @Override
    protected void loadData() {

        setAdapter();
        HttpFactroy.craet().get(url, null, new MyHttpCallBack<Chian_item_Path_TextBean>() {

            @Override
            public void onSuccess(Chian_item_Path_TextBean chian_item_path_textBean) {

                List<Chian_item_Path_TextBean.LiveBean> live = chian_item_path_textBean.getLive();
                list_array.addAll(live);


                handler.sendEmptyMessage(300);

                progressBarId.setVisibility(View.GONE);
            }

            @Override
            public void onError(int errorCode, String errorMsg) {

                ACache aCache = ACache.get(App.content);
                Chian_item_Path_TextBean china_live_path_textBean = (Chian_item_Path_TextBean) aCache.getAsObject("Chian_item_Path_TextBean");

                if (china_live_path_textBean != null) {
                    list_array.addAll(china_live_path_textBean.getLive());

                    handler.sendEmptyMessage(300);

                }

            }

        });


    }

    private void setAdapter() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ChianLivePathXrecycle.setLayoutManager(layoutManager);
        live_xrecycle = new China_Live_Xrecycle(getActivity(), list_array);
        ChianLivePathXrecycle.setAdapter(live_xrecycle);

        ChianLivePathXrecycle.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {



                live_xrecycle.notifyDataSetChanged();
                ChianLivePathXrecycle.refreshComplete();






            }

            @Override
            public void onLoadMore() {


            }
        });
        ChianLivePathXrecycle.setLoadingMoreEnabled(false);





    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

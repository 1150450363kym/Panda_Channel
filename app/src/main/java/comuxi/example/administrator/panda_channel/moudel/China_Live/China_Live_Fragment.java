package comuxi.example.administrator.panda_channel.moudel.China_Live;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import comuxi.example.administrator.panda_channel.Base.BaseFragment;
import comuxi.example.administrator.panda_channel.R;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.China_Live_Path_TextBean;
import comuxi.example.administrator.panda_channel.moudel.China_Live.china_adapter.China_Tablayout_PageAdapter;
import comuxi.example.administrator.panda_channel.moudel.China_Live.china_adapter.GridView_Adapter;
import comuxi.example.administrator.panda_channel.moudel.China_Live.china_adapter.More_GridView_Adapter;
import comuxi.example.administrator.panda_channel.moudel.China_Live.china_path_fragment.Path_Fragment;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.NonSwipeableViewPager;

/**
 * Created by Administrator on 2017/7/11.
 */

public class China_Live_Fragment extends BaseFragment implements China_Live_Contract.View {
    @BindView(R.id.china_live_tablayout)
    TabLayout chinaLiveTablayout;

    @BindView(R.id.china_live_viewpage)
    NonSwipeableViewPager chinaLiveViewpage;
    Unbinder unbinder;

    China_Live_Contract.presenter presenter;
    @BindView(R.id.chian_live_add)
    ImageView chianLiveAdd;
    @BindView(R.id.login_china_live_relation)
    RelativeLayout loginChinaLiveRelation;
    private PopupWindow popup;


    @BindView(R.id.china_live_tablayoutlin)
    LinearLayout chinaLiveTablayoutlin;


    private ArrayList<Fragment> fargmet_array = new ArrayList<>();

    private ArrayList<China_Live_Path_TextBean.TablistBean> tablistBeen_array = new ArrayList<>();
    private ArrayList<China_Live_Path_TextBean.AlllistBean> alllistBeen_aray = new ArrayList<>();
    China_Tablayout_PageAdapter tablay_adapter;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 300:
                    tablay_adapter.notifyDataSetChanged();
                    break;
            }

        }
    };


    @Override
    protected int getlayoutID() {
        return R.layout.login_china_nor_fragment;
    }

    @Override
    protected void init(View view) {


    }

    @Override
    protected void loadData() {
        presenter.start();
        tablay_adapter = new China_Tablayout_PageAdapter(getActivity().getSupportFragmentManager(), fargmet_array, tablistBeen_array);

        chinaLiveViewpage.setAdapter(tablay_adapter);

        chinaLiveTablayout.setupWithViewPager(chinaLiveViewpage);

        chinaLiveTablayout.setTabMode(TabLayout.MODE_FIXED);

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


    @Override
    public void getMessage(String message) {

    }

    @Override
    public void getNetData(China_Live_Path_TextBean china_live_path_textBean) {
//        这是 国家地址
        List<China_Live_Path_TextBean.TablistBean> tablist = china_live_path_textBean.getTablist();
        tablistBeen_array.addAll(tablist);
        Log.e("TAG", "默认 的  一些地址" + tablistBeen_array.size());
        List<China_Live_Path_TextBean.AlllistBean> alllist = china_live_path_textBean.getAlllist();
        alllistBeen_aray.addAll(alllist);
        Log.e("TAG", "后期添加 的  一些地址" + tablistBeen_array.size());

        for (int i = 0; i < tablist.size(); i++) {

            Path_Fragment path_fragment = new Path_Fragment(tablist.get(i).getUrl());

            fargmet_array.add(path_fragment);

        }

        handler.sendEmptyMessage(300);

    }


    @Override
    public void setPresenter(China_Live_Contract.presenter presenter) {
        this.presenter = presenter;
    }

    //弹出 Popwindow  动画
    private ImageView dele_imag;
    private GridView gridView, more_gridView;
    private TextView enit_text;

    @OnClick(R.id.chian_live_add)
    public void onViewClicked() {

        View layout = getActivity().getLayoutInflater().inflate(R.layout.popwindow_, null);

        gridView = (GridView) layout.findViewById(R.id.Switch_the_section_gridview);
        more_gridView = (GridView) layout.findViewById(R.id.more_Switch_the_section_gridview);


        GridView_Adapter gridView_adapter = new GridView_Adapter(getActivity(), tablistBeen_array);
        gridView.setAdapter(gridView_adapter);

        More_GridView_Adapter more_gridView_adapter = new More_GridView_Adapter(getActivity(), alllistBeen_aray);

        more_gridView.setAdapter(more_gridView_adapter);

        final PopupWindow pop = new PopupWindow(layout,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT, true);

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pop.setAnimationStyle(R.style.popupStyle);
                pop.showAtLocation(loginChinaLiveRelation, Gravity.NO_GRAVITY, 0, 0);
            }
        });

        dele_imag = (ImageView) layout.findViewById(R.id.live_china_delect);

        dele_imag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pop.dismiss();

            }
        });

    }

}

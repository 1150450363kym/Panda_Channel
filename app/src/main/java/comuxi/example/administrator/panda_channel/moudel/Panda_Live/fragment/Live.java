package comuxi.example.administrator.panda_channel.moudel.Panda_Live.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import comuxi.example.administrator.panda_channel.Base.BaseFragment;
import comuxi.example.administrator.panda_channel.R;
import comuxi.example.administrator.panda_channel.Utils.ACache;
import comuxi.example.administrator.panda_channel.Utils.Log_Utils;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.MLiveBean;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.adapter.TwoPageAdapter;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.contract.LiveContract;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.presenter.MoreLivePresenter;


/**
 * Created by lenovo on 2017/7/12.
 * 熊猫直播 --- 直播
 */

public class Live extends BaseFragment implements LiveContract.View {

    @BindView(R.id.live_center_blue_img)
    CheckBox liveCenterBlueImg;
    @BindView(R.id.live_center_linearlayout)
    LinearLayout liveCenterLinear;
    @BindView(R.id.live_center_tablayout)
    TabLayout liveCenterTablayout;
    @BindView(R.id.live_bottom_viewpager)
    ViewPager liveBottomViewpager;
    @BindView(R.id.live_top_img)
    ImageView liveTopImg;
    Unbinder unbinder;
    @BindView(R.id.radio_title)
    TextView radioTitle;
    @BindView(R.id.tv_visibility)
    TextView tvVisibility;

    private LiveContract.presenter presenter;
    private List<Fragment> list;
    private TwoPageAdapter twoPageAdapter;
    private MoreLive moreLive = new MoreLive();
    private WatchChat watchChat = new WatchChat();
    private List<MLiveBean.LiveBean> liveBeen;

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String title = intent.getStringExtra("title");
            radioTitle.setText(title);
        }
    };


    @Override
    protected int getlayoutID() {
        return R.layout.live;
    }

    @Override
    protected void init(View view) {

        list = new ArrayList<>();
        liveBeen = new ArrayList<>();
        list.add(moreLive);
        list.add(watchChat);

        getContext().registerReceiver(receiver, new IntentFilter("video_title"));
        twoPageAdapter = new TwoPageAdapter(getFragmentManager(), list);
        liveBottomViewpager.setAdapter(twoPageAdapter);
        liveBottomViewpager.setCurrentItem(2);
        liveCenterTablayout.setTabMode(TabLayout.MODE_FIXED);
        liveCenterTablayout.setTabTextColors(R.color.colorPrimaryDark, R.color.radio_black);
        liveCenterTablayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
        liveCenterTablayout.setupWithViewPager(liveBottomViewpager);

        new MoreLivePresenter(moreLive);

    }

    @Override
    protected void loadData() {

        presenter.start();

    }

    @OnClick(R.id.live_center_blue_img)
    public void onViewClicked() {


        liveCenterLinear.setVisibility(View.VISIBLE);

        if (!liveCenterBlueImg.isChecked()) {
            liveCenterLinear.setVisibility(View.GONE);
        }
    }


    @Override
    public void showData(MLiveBean moreLiveBean) {

        liveBeen.addAll(moreLiveBean.getLive());
        tvVisibility.setText(liveBeen.get(0).getBrief());
        radioTitle.setText(liveBeen.get(0).getTitle());


    }

    @Override
    public void showMsg(String msg) {

        ACache aCache =ACache.get(getContext());
        MLiveBean bean = (MLiveBean) aCache.getAsObject("MLiveBean");
        Log_Utils.log_d("TAG",liveBeen.size()+"");
        liveBeen.addAll(bean.getLive());
        tvVisibility.setText(liveBeen.get(0).getBrief());
        radioTitle.setText(liveBeen.get(0).getTitle());

    }

    @Override
    public void setPresenter(LiveContract.presenter presenter) {

        this.presenter = presenter;
    }

}

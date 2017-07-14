package comuxi.example.administrator.panda_channel.moudel.Panda_Live.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import comuxi.example.administrator.panda_channel.Base.BaseFragment;
import comuxi.example.administrator.panda_channel.R;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.adapter.TwoPageAdapter;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.presenter.MoreLivePresenter;


/**
 * Created by lenovo on 2017/7/12.
 * 熊猫直播 --- 直播
 */

public class Live extends BaseFragment {

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

    private List<Fragment> list;
    private TwoPageAdapter twoPageAdapter;
    private MoreLive moreLive = new MoreLive();
    private WatchChat watchChat = new WatchChat();


    @Override
    protected int getlayoutID() {
        return R.layout.live;
    }

    @Override
    protected void init(View view) {

        list = new ArrayList<>();
        list.add(moreLive);
        list.add(watchChat);

        twoPageAdapter = new TwoPageAdapter(getFragmentManager(), list);
        liveBottomViewpager.setAdapter(twoPageAdapter);
        liveCenterTablayout.setTabMode(TabLayout.MODE_FIXED);
        liveCenterTablayout.setTabTextColors(R.color.colorPrimaryDark, R.color.radio_black);
        liveCenterTablayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
        liveCenterTablayout.setupWithViewPager(liveBottomViewpager);

        new MoreLivePresenter(moreLive);
    }

    @Override
    protected void loadData() {

    }

    @OnClick(R.id.live_center_blue_img)
    public void onViewClicked() {


        liveCenterLinear.setVisibility(View.VISIBLE);

        if (!liveCenterBlueImg.isChecked()) {
            liveCenterLinear.setVisibility(View.GONE);
        }
    }



}

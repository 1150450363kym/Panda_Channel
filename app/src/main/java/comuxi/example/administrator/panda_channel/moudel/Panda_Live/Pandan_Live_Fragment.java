package comuxi.example.administrator.panda_channel.moudel.Panda_Live;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import comuxi.example.administrator.panda_channel.Base.BaseFragment;
import comuxi.example.administrator.panda_channel.R;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.adapter.PageAdapter;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.fragment.Live;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.fragment.Original_news;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.presenter.LivePresenter;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.presenter.OriginalnewsPresenter;


/**
 * Created by Administrator on 2017/7/11.
 * 熊猫直播
 */

public class Pandan_Live_Fragment extends BaseFragment {


    @BindView(R.id.live_fragment_tablayout)
    TabLayout liveFragmentTablayout;
    @BindView(R.id.live_fragment_viewpager)
    ViewPager liveFragmentViewpager;

    private Live live = new Live();

    private Original_news originalNewsnoe;
    private Original_news originalNewstwo;
    private Original_news originalNewstherr;
    private Original_news originalNewsfour;
    private Original_news originalNewsfive;
    private Original_news originalNewssix;
    private Original_news originalNewsseven;
    private Original_news originalNewseight;
    private List<Fragment> list;
    private PageAdapter adapter;



    @Override
    protected int getlayoutID() {
        return R.layout.login_live_fragment;
    }

    @Override
    protected void init(View view) {

        ButterKnife.bind(view);
        list = new ArrayList<>();
        list.add(live);

        originalNewsnoe = new Original_news("VSET100167216881");
        originalNewstwo = new Original_news("VSET100332640004");
        originalNewstherr = new Original_news("VSET100272959126");
        originalNewsfour = new Original_news("VSET100340574858");
        originalNewsfive = new Original_news("VSET100284428835");
        originalNewssix = new Original_news("VSET100237714751");
        originalNewsseven = new Original_news("VSET100167308855");
        originalNewseight = new Original_news("VSET100219009515");

        list.add(originalNewsnoe);
        list.add(originalNewstwo);
        list.add(originalNewstherr);
        list.add(originalNewsfour);
        list.add(originalNewsfive);
        list.add(originalNewssix);
        list.add(originalNewsseven);
        list.add(originalNewseight);


        adapter = new PageAdapter(getFragmentManager(),list);
        liveFragmentViewpager.setAdapter(adapter);
        liveFragmentViewpager.setCurrentItem(0);
        liveFragmentViewpager.setOffscreenPageLimit(9);
        liveFragmentTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        liveFragmentTablayout.setTabTextColors(R.color.cctv_tab_sel,R.color.radio_black);

        LinearLayout linearLayout = (LinearLayout) liveFragmentTablayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(getContext(),
                R.drawable.tablayout_fg));
        liveFragmentTablayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getContext(),R.color.cctv_tab_sel));
        liveFragmentTablayout.setupWithViewPager(liveFragmentViewpager);


        new OriginalnewsPresenter(originalNewsnoe);
        new OriginalnewsPresenter(originalNewstwo);
        new OriginalnewsPresenter(originalNewstherr);
        new OriginalnewsPresenter(originalNewsfour);
        new OriginalnewsPresenter(originalNewsfive);
        new OriginalnewsPresenter(originalNewssix);
        new OriginalnewsPresenter(originalNewsseven);
        new OriginalnewsPresenter(originalNewseight);
        new LivePresenter(live);
    }

    @Override
    protected void loadData() {

    }

}

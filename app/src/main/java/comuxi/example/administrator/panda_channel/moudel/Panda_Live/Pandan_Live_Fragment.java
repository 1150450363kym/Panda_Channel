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
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.fragment.Especially_program;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.fragment.LetPanda;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.fragment.Live;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.fragment.LovelyLive;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.fragment.Original_news;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.fragment.PandaTOP;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.fragment.PandaThing;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.fragment.Panda_archives;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.fragment.Wonderful_moment;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.presenter.EspeciallyprogramPresenter;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.presenter.LetPandaPresenter;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.presenter.LivePresenter;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.presenter.LovelyLivePresenter;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.presenter.OriginalnewsPresenter;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.presenter.PandaTOPPresenter;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.presenter.PandaThingPresenter;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.presenter.Panda_archivesPresenter;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.presenter.WonderfulOnePresenter;


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
    private Wonderful_moment wonderful_moment = new Wonderful_moment();
    private LetPanda letPanda = new LetPanda();
    private LovelyLive lovelyLive = new LovelyLive();
    private Panda_archives panda_archives = new Panda_archives();
    private PandaTOP pandaTOP = new PandaTOP();
    private PandaThing pandaThing = new PandaThing();
    private Especially_program especially_program = new Especially_program();
    private Original_news original_news = new Original_news();
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
        list.add(wonderful_moment);
        list.add(letPanda);
        list.add(lovelyLive);
        list.add(panda_archives);
        list.add(pandaTOP);
        list.add(pandaThing);
        list.add(especially_program);
        list.add(original_news);
        adapter = new PageAdapter(getFragmentManager(),list);
        liveFragmentViewpager.setAdapter(adapter);
        liveFragmentViewpager.setCurrentItem(9);
        liveFragmentTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        liveFragmentTablayout.setTabTextColors(R.color.cctv_tab_sel,R.color.radio_black);

        LinearLayout linearLayout = (LinearLayout) liveFragmentTablayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(getContext(),
                R.drawable.tablayout_fg));
        liveFragmentTablayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getContext(),R.color.cctv_tab_sel));
        liveFragmentTablayout.setupWithViewPager(liveFragmentViewpager);


        new WonderfulOnePresenter(wonderful_moment);
        new LetPandaPresenter(letPanda);
        new LovelyLivePresenter(lovelyLive);
        new Panda_archivesPresenter(panda_archives);
        new PandaTOPPresenter(pandaTOP);
        new PandaThingPresenter(pandaThing);
        new EspeciallyprogramPresenter(especially_program);
        new OriginalnewsPresenter(original_news);
        new LivePresenter(live);
    }

    @Override
    protected void loadData() {

    }

}

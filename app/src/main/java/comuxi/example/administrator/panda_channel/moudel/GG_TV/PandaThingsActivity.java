package comuxi.example.administrator.panda_channel.moudel.GG_TV;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import comuxi.example.administrator.panda_channel.Base.BaseActivity;
import comuxi.example.administrator.panda_channel.R;
import comuxi.example.administrator.panda_channel.moudel.GG_TV.fragment.HighcompleteFragment;
import comuxi.example.administrator.panda_channel.moudel.GG_TV.fragment.MarvellousWatchFragment;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.NonSwipeableViewPager;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.adapter.PageAdapter;


public class PandaThingsActivity extends BaseActivity {


    @BindView(R.id.live_center_blue_img)
    CheckBox liveCenterBlueImg;
    @BindView(R.id.panda_things_tv_pandacolumn)
    TextView pandaThingsTvPandacolumn;
    @BindView(R.id.panda_things_tablayout)
    TabLayout pandaThingsTablayout;
    @BindView(R.id.panda_things_viewpager)
    NonSwipeableViewPager pandaThingsViewpager;
    private List<Fragment> list;
    private PageAdapter adapter;
    private HighcompleteFragment highcompleteFragment = new HighcompleteFragment();
    private MarvellousWatchFragment marvellousWatchFragment = new MarvellousWatchFragment();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_panda_things;
    }

    @Override
    protected void initView() {

        list = new ArrayList<>();
        list.add(highcompleteFragment);
        list.add(marvellousWatchFragment);

        adapter = new PageAdapter(getSupportFragmentManager(),list);
        pandaThingsViewpager.setAdapter(adapter);

    }


}

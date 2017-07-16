package comuxi.example.administrator.panda_channel.moudel.GG_TV;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
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
    @BindView(R.id.gg_totop_title)
    TextView ggTotopTitle;
    @BindView(R.id.activity_panda_things)
    LinearLayout activityPandaThings;
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
        Intent intent = getIntent();
        String title = intent.getStringExtra("top_title");
        String content = intent.getStringExtra("top_content");
        ggTotopTitle.setText(title);
        pandaThingsTvPandacolumn.setText(content);
        list = new ArrayList<>();
        list.add(highcompleteFragment);
        list.add(marvellousWatchFragment);

        adapter = new PageAdapter(getSupportFragmentManager(), list);
        pandaThingsViewpager.setAdapter(adapter);

    }
    @OnClick(R.id.live_center_blue_img)
    public void onViewClicked() {

        pandaThingsTvPandacolumn.setVisibility(View.VISIBLE);
        if(!liveCenterBlueImg.isChecked()){

            pandaThingsTvPandacolumn.setVisibility(View.GONE);
        }
    }

}

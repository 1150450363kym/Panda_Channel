package comuxi.example.administrator.panda_channel;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import comuxi.example.administrator.panda_channel.Base.BaseActivity;
import comuxi.example.administrator.panda_channel.My_Collection_Fragment.MyCollcetion_Live_Fragment;
import comuxi.example.administrator.panda_channel.My_Collection_Fragment.MyCollection_Look_Fragment;
import comuxi.example.administrator.panda_channel.adapter_activity.My_Collecttion_View_Adapter;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.NonSwipeableViewPager;

//我的收藏
public class MyCollectionActivity extends BaseActivity {

//    tablayout
    @BindView(R.id.mycollection_tablayout)
    TabLayout mycollectionTablayout;
//    viewpager
    @BindView(R.id.mycollection_viewpager)
    NonSwipeableViewPager mycollectionViewpager;
    //返回按钮
    @BindView(R.id.shoucang_image)
    ImageView shoucangImage;
//    编辑按钮
    @BindView(R.id.shoucang_bianji)
    TextView shoucangBianji;

    @BindView(R.id.shoutoolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_my_collection)
    RelativeLayout activityMyCollection;


    private MyCollcetion_Live_Fragment live_fragment;
    private MyCollection_Look_Fragment look_fragment;
    ArrayList<Fragment> arrayList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_collection;
    }

    @Override
    protected void initView() {

        arrayList = new ArrayList<>();

        live_fragment = new MyCollcetion_Live_Fragment();
        look_fragment = new MyCollection_Look_Fragment();

        arrayList.add(live_fragment);
        arrayList.add(look_fragment);


        My_Collecttion_View_Adapter view_adapter = new My_Collecttion_View_Adapter(getSupportFragmentManager(), arrayList);

        mycollectionViewpager.setAdapter(view_adapter);

        mycollectionTablayout.setupWithViewPager(mycollectionViewpager);
        mycollectionTablayout.setTabMode(TabLayout.MODE_FIXED);


    }

    @OnClick(R.id.shoucang_image)
    public void onViewClicked() {

        this.finish();
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        // TODO: add setContentView(...) invocation
//        ButterKnife.bind(this);
//    }
}

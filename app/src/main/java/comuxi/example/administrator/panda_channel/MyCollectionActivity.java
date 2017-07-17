package comuxi.example.administrator.panda_channel;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.NonSwipeableViewPager;

//我的收藏
public class MyCollectionActivity extends Activity {

    @BindView(R.id.historical_image)
    ImageView historicalImage;
    @BindView(R.id.mycollection_tablayout)
    TabLayout mycollectionTablayout;
    @BindView(R.id.mycollection_viewpager)
    NonSwipeableViewPager mycollectionViewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection);
        ButterKnife.bind(this);

        
    }

    @OnClick(R.id.historical_image)
    public void onViewClicked() {

        this.finish();
    }
}

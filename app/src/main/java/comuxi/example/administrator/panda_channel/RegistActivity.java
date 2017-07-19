package comuxi.example.administrator.panda_channel;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import comuxi.example.administrator.panda_channel.Base.BaseActivity;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.NonSwipeableViewPager;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.adapter.RegistAdapter;

public class RegistActivity extends BaseActivity {

    @BindView(R.id.historical_image)
    ImageView historicalImage;
    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @BindView(R.id.framelayout_register_content)
    NonSwipeableViewPager framelayoutRegisterContent;
    @BindView(R.id.activity_regist)
    RelativeLayout activityRegist;
    @BindView(R.id.regist_tablayout)
    TabLayout registTablayout;
    private List<Fragment> list;
    private Personal_email_register_Fragment email_register_fragment;
    private Personal_phone_register_Fragment phone_register_fragment;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_regist;
    }

    @Override
    protected void initView() {

        email_register_fragment = new Personal_email_register_Fragment();
        phone_register_fragment = new Personal_phone_register_Fragment();
        list = new ArrayList<>();

        list.add(phone_register_fragment);
        list.add(email_register_fragment);
        RegistAdapter adapter = new RegistAdapter(getSupportFragmentManager(), list);
        framelayoutRegisterContent.setAdapter(adapter);


        LinearLayout linearLayout = (LinearLayout) registTablayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this,
                R.drawable.tablayout_fg));
        registTablayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.cctv_tab_sel));
        registTablayout.setTabTextColors(R.color.cctv_tab_sel,R.color.radio_black);
        registTablayout.setupWithViewPager(framelayoutRegisterContent);

    }

    @OnClick({R.id.historical_image, R.id.toolbar, R.id.framelayout_register_content, R.id.activity_regist})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.historical_image:

                this.finish();
                break;
            case R.id.toolbar:
                break;

            case R.id.framelayout_register_content:

                break;
            case R.id.activity_regist:
                break;
        }
    }

}

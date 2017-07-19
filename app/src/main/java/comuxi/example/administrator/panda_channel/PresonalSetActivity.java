package comuxi.example.administrator.panda_channel;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import comuxi.example.administrator.panda_channel.Base.BaseActivity;

public class PresonalSetActivity extends BaseActivity {


    @BindView(R.id.historical_image)
    ImageView historicalImage;
    @BindView(R.id.pe_set_push_view)
    CheckBox peSetPushView;
    @BindView(R.id.pe_set_play_view)
    CheckBox peSetPlayView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_qita)
    TextView tvQita;
    @BindView(R.id.set_cache_size_tv)
    TextView setCacheSizeTv;
    @BindView(R.id.personal_set_delete_cache_layout)
    RelativeLayout personalSetDeleteCacheLayout;
    @BindView(R.id.personal_set_fankui_layout)
    RelativeLayout personalSetFankuiLayout;
    @BindView(R.id.personal_set_udpate_layout)
    RelativeLayout personalSetUdpateLayout;
    @BindView(R.id.personal_set_ping_layout)
    RelativeLayout personalSetPingLayout;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.personal_set_about_layout)
    RelativeLayout personalSetAboutLayout;
    @BindView(R.id.activity_presonal_set)
    RelativeLayout activityPresonalSet;


    @Override
    protected int getLayoutId() {

        return R.layout.activity_presonal_set;
    }

    @Override
    protected void initView() {

    }


    @OnClick(R.id.historical_image)
    public void onViewClicked() {

        this.finish();
    }


    @OnClick({R.id.personal_set_delete_cache_layout, R.id.personal_set_fankui_layout, R.id.personal_set_udpate_layout, R.id.personal_set_ping_layout, R.id.personal_set_about_layout, R.id.activity_presonal_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.personal_set_delete_cache_layout:


                break;
            case R.id.personal_set_fankui_layout:

                startActivity(new Intent(this,PersonalFeedBackActivity.class));

                break;

            case R.id.personal_set_udpate_layout:
                break;
            case R.id.personal_set_ping_layout:
                break;
            case R.id.personal_set_about_layout:

                startActivity(new Intent(this,PersonAboutActivity.class));
                break;
            case R.id.activity_presonal_set:
                break;
        }
    }
}

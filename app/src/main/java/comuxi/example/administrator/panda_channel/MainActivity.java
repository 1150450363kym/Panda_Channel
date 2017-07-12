package comuxi.example.administrator.panda_channel;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import comuxi.example.administrator.panda_channel.Base.BaseActivity;
import comuxi.example.administrator.panda_channel.moudel.China_Live.China_Live_Fragment;
import comuxi.example.administrator.panda_channel.moudel.GG_TV.GG_TV_Fragment;
import comuxi.example.administrator.panda_channel.moudel.Home.HomeFragment;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.Pandan_Live_Fragment;
import comuxi.example.administrator.panda_channel.moudel.Pandan_Broadcast.Pandan_Broadcast_Fragment;

public class MainActivity extends BaseActivity {
    @BindView(R.id.login_home_button)
    RadioButton loginHomeButton;
    @BindView(R.id.login_live_button)
    RadioButton loginLiveButton;
    @BindView(R.id.login_ggtv_button)
    RadioButton loginGgtvButton;
    @BindView(R.id.login_Broadcast_button)
    RadioButton loginBroadcastButton;
    @BindView(R.id.login_china_button)
    RadioButton loginChinaButton;


    private HomeFragment homeFragment;//首页
    private China_Live_Fragment china_live_fragment;//直播中国
    private GG_TV_Fragment gg_tv_fragment;//滚滚视频
    private Pandan_Live_Fragment pandan_live_fragment;//熊猫直播
    private Pandan_Broadcast_Fragment pandan_broadcast_fragment;//熊猫播报

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        ButterKnife.bind(this);
        //设置一个初始的Fragment
        Setinitial();
        loginHomeButton.setBackgroundColor(getResources().getColor(R.color.radio_hui));


    }

    //设置一个初始的Fragment
    private void Setinitial() {
        homeFragment = new HomeFragment();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.login_fragment, homeFragment);
        transaction.commit();

    }


    @OnClick({R.id.login_home_button, R.id.login_live_button, R.id.login_ggtv_button, R.id.login_Broadcast_button, R.id.login_china_button})
    public void onViewClicked(View view) {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        //如果有了fragment就让他先隐藏
        hind_show(transaction);
        switch (view.getId()) {
            case R.id.login_home_button:

                loginHomeButton.setBackgroundColor(getResources().getColor(R.color.radio_hui));
                loginLiveButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginGgtvButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginBroadcastButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginChinaButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));


                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.login_fragment, homeFragment);
                } else {
                    transaction.show(homeFragment);

                }
                break;
            case R.id.login_live_button:

                loginHomeButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginLiveButton.setBackgroundColor(getResources().getColor(R.color.radio_hui));
                loginGgtvButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginBroadcastButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginChinaButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));


                if (pandan_live_fragment == null) {
                    pandan_live_fragment = new Pandan_Live_Fragment();
                    transaction.add(R.id.login_fragment, pandan_live_fragment);
                } else {
                    transaction.show(pandan_live_fragment);

                }
                break;
            case R.id.login_ggtv_button:
                loginHomeButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginLiveButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginGgtvButton.setBackgroundColor(getResources().getColor(R.color.radio_hui));
                loginBroadcastButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginChinaButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                if (gg_tv_fragment == null) {
                    gg_tv_fragment = new GG_TV_Fragment();
                    transaction.add(R.id.login_fragment, gg_tv_fragment);
                } else {
                    transaction.show(gg_tv_fragment);

                }
                break;
            case R.id.login_Broadcast_button:
                loginHomeButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginLiveButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginGgtvButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginBroadcastButton.setBackgroundColor(getResources().getColor(R.color.radio_hui));
                loginChinaButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                if (pandan_broadcast_fragment == null) {
                    pandan_broadcast_fragment = new Pandan_Broadcast_Fragment();
                    transaction.add(R.id.login_fragment, pandan_broadcast_fragment);
                } else {
                    transaction.show(pandan_broadcast_fragment);

                }
                break;
            case R.id.login_china_button:
                loginHomeButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginLiveButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginGgtvButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginBroadcastButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginChinaButton.setBackgroundColor(getResources().getColor(R.color.radio_hui));
                if (china_live_fragment == null) {
                    china_live_fragment = new China_Live_Fragment();
                    transaction.add(R.id.login_fragment, china_live_fragment);
                } else {
                    transaction.show(china_live_fragment);

                }
                break;

        }

        transaction.commit();
    }

    public void hind_show(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);

        }
        if (pandan_live_fragment != null) {
            transaction.hide(pandan_live_fragment);

        }
        if (gg_tv_fragment != null) {
            transaction.hide(gg_tv_fragment);

        }
        if (pandan_broadcast_fragment != null) {
            transaction.hide(pandan_broadcast_fragment);

        }
        if (china_live_fragment != null) {
            transaction.hide(china_live_fragment);

        }

    }


}

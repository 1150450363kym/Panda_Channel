package comuxi.example.administrator.panda_channel;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import comuxi.example.administrator.panda_channel.Base.BaseActivity;
import comuxi.example.administrator.panda_channel.moudel.China_Live.China_Live_Fragment;
import comuxi.example.administrator.panda_channel.moudel.GG_TV.GG_TV_Fragment;
import comuxi.example.administrator.panda_channel.moudel.Home.HomeFragment;
import comuxi.example.administrator.panda_channel.moudel.Home.HomePresenter;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.Pandan_Live_Fragment;
import comuxi.example.administrator.panda_channel.moudel.Pandan_Broadcast.Pandan_Broadcast_Fragment;

/**
 * Activity界面
 */

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
    @BindView(R.id.login_image)

    ImageView loginImage;
    @BindView(R.id.start_linear)
    RelativeLayout startLinear;


    private HomeFragment homeFragment;//首页
    private China_Live_Fragment china_live_fragment;//直播中国
    private GG_TV_Fragment gg_tv_fragment;//滚滚视频
    private Pandan_Live_Fragment pandan_live_fragment;//熊猫直播
    private Pandan_Broadcast_Fragment pandan_broadcast_fragment;//熊猫播报


    private Timer timer;
    private TimerTask task;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 300:
                    loginImage.setVisibility(View.GONE);

                    startLinear.setVisibility(View.VISIBLE);
                    task.cancel();

                    break;
            }


        }
    };


    //记录用户首次点击返回键的时间
    private long firstTime=0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        ButterKnife.bind(this);
        //设置一个初始的Fragment
        Setinitial();

        startLinear.setVisibility(View.GONE);
        loginHomeButton.setBackgroundColor(getResources().getColor(R.color.radio_hui));


        timer = new Timer();
       task= new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(300);

            }
        };
        timer.schedule(task, 3000, 3000);


    }

    //设置一个初始的Fragment
    private void Setinitial() {
        homeFragment = new HomeFragment();

        HomePresenter homePresenter = new HomePresenter(homeFragment);

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



    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                long secondTime=System.currentTimeMillis();
                if(secondTime-firstTime>2000){
                    Toast.makeText(MainActivity.this,"再按一次退出程序",Toast.LENGTH_SHORT).show();
                    firstTime=secondTime;
                    return true;
                }else{
                    System.exit(0);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

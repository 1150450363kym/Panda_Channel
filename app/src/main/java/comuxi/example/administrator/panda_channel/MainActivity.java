package comuxi.example.administrator.panda_channel;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import comuxi.example.administrator.panda_channel.Base.BaseActivity;
import comuxi.example.administrator.panda_channel.app.App;
import comuxi.example.administrator.panda_channel.moudel.China_Live.China_Live_Fragment;
import comuxi.example.administrator.panda_channel.moudel.China_Live.China_Live_Presenter;
import comuxi.example.administrator.panda_channel.moudel.GG_TV.GG_TV_Fragment;
import comuxi.example.administrator.panda_channel.moudel.GG_TV.GG_TV_Presenter;
import comuxi.example.administrator.panda_channel.moudel.Home.HomeFragment;
import comuxi.example.administrator.panda_channel.moudel.Home.HomePresenter;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.Pandan_Live_Fragment;
import comuxi.example.administrator.panda_channel.moudel.Pandan_Broadcast.Pandan_Broadcast_Fragment;
import comuxi.example.administrator.panda_channel.moudel.Pandan_Broadcast.Pandan_Broadcast_Presenter;

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

    //    熊猫频道的图片
    @BindView(R.id.login_panda_sign)
    ImageView loginPandaSign;

    //    互动的图片
    @BindView(R.id.login_hudong_sign)
    ImageView loginHudongSign;
    //    中间的文字
    @BindView(R.id.login_title)
    TextView loginTitle;
    //    互动旁边的文字
    @BindView(R.id.login_person_sign)
    ImageView loginPersonSign;


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
                    quitFullScreen();
                    startLinear.setVisibility(View.VISIBLE);
                    task.cancel();

                    break;
            }


        }
    };


    //记录用户首次点击返回键的时间
    private long firstTime = 0;

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


//        设置全屏方式

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(300);

            }
        };
        timer.schedule(task, 3000, 3000);

    }

    //设置一个初始的Fragment
    private void Setinitial() {

        homeFragment = (HomeFragment) changeFragment(HomeFragment.class, R.id.login_fragment, true, null, true);
        HomePresenter homePresenter = new HomePresenter(homeFragment);

    }


    @OnClick({R.id.login_home_button, R.id.login_live_button, R.id.login_ggtv_button, R.id.login_Broadcast_button, R.id.login_china_button,R.id.login_person_sign,R.id.login_hudong_sign})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_home_button:

                loginPandaSign.setVisibility(View.VISIBLE);
                loginTitle.setVisibility(View.GONE);
                loginHudongSign.setVisibility(View.VISIBLE);


                loginHomeButton.setBackgroundColor(getResources().getColor(R.color.radio_hui));
                loginLiveButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginGgtvButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginBroadcastButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginChinaButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));

                homeFragment = (HomeFragment) changeFragment(HomeFragment.class, R.id.login_fragment, true, null, false);
                HomePresenter homePresenter = new HomePresenter(homeFragment);

                break;
            case R.id.login_live_button:


                loginPandaSign.setVisibility(View.GONE);
                loginTitle.setVisibility(View.VISIBLE);
                loginTitle.setText("熊猫直播");
                loginHudongSign.setVisibility(View.GONE);


                loginHomeButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginLiveButton.setBackgroundColor(getResources().getColor(R.color.radio_hui));
                loginGgtvButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginBroadcastButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginChinaButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));

                pandan_live_fragment = (Pandan_Live_Fragment) changeFragment(Pandan_Live_Fragment.class, R.id.login_fragment, true, null, true);


                break;
            case R.id.login_ggtv_button:

                loginPandaSign.setVisibility(View.GONE);
                loginTitle.setVisibility(View.VISIBLE);
                loginTitle.setText("滚滚视频");
                loginHudongSign.setVisibility(View.GONE);

                loginHomeButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginLiveButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginGgtvButton.setBackgroundColor(getResources().getColor(R.color.radio_hui));
                loginBroadcastButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginChinaButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));


                gg_tv_fragment = (GG_TV_Fragment) changeFragment(GG_TV_Fragment.class, R.id.login_fragment, true, null, true);

                new GG_TV_Presenter(gg_tv_fragment);


                break;
            case R.id.login_Broadcast_button:


                loginPandaSign.setVisibility(View.GONE);
                loginTitle.setVisibility(View.VISIBLE);
                loginTitle.setText("熊猫播报");
                loginHudongSign.setVisibility(View.GONE);

                loginHomeButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginLiveButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginGgtvButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginBroadcastButton.setBackgroundColor(getResources().getColor(R.color.radio_hui));
                loginChinaButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));

                pandan_broadcast_fragment = (Pandan_Broadcast_Fragment) changeFragment(Pandan_Broadcast_Fragment.class, R.id.login_fragment, true, null, true);
                new Pandan_Broadcast_Presenter(pandan_broadcast_fragment);

                break;
            case R.id.login_china_button:


                loginPandaSign.setVisibility(View.GONE);
                loginTitle.setVisibility(View.VISIBLE);
                loginTitle.setText("直播中国");
                loginHudongSign.setVisibility(View.GONE);

                loginHomeButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginLiveButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginGgtvButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginBroadcastButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginChinaButton.setBackgroundColor(getResources().getColor(R.color.radio_hui));


                china_live_fragment = (China_Live_Fragment) changeFragment(China_Live_Fragment.class, R.id.login_fragment, true, null, true);

//我靠 全部上传 再次上传
                new China_Live_Presenter(china_live_fragment);

                break;
            case R.id.login_person_sign:

                startActivity(new Intent(App.content,PersonalCenterActivity.class));
                break;

            case R.id.login_hudong_sign:


                startActivity(new Intent(App.content,CehuaActivity.class));
                break;


        }

    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {
                    Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    firstTime = secondTime;
                    return true;
                } else {
                    System.exit(0);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        // TODO: add setContentView(...) invocation
//        ButterKnife.bind(this);
//    }


    private void quitFullScreen(){
        final WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setAttributes(attrs);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }
}

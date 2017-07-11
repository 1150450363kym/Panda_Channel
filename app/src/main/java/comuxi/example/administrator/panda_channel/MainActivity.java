package comuxi.example.administrator.panda_channel;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import comuxi.example.administrator.panda_channel.Fragment_.login_fragment.Culture_Fragment;
import comuxi.example.administrator.panda_channel.Fragment_.login_fragment.Eye_Pressed_Fragment;
import comuxi.example.administrator.panda_channel.Fragment_.login_fragment.Home_Fragment;
import comuxi.example.administrator.panda_channel.Fragment_.login_fragment.Live_China_Fragment;
import comuxi.example.administrator.panda_channel.Fragment_.login_fragment.Live_Fragment;

public class MainActivity extends AppCompatActivity {
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
    private Home_Fragment home_fragment;
    private Live_Fragment live_fragment;
    private Culture_Fragment culture_fragment;
    private Eye_Pressed_Fragment eye_pressed_fragment;
    private Live_China_Fragment live_china_fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //设置一个初始的Fragment
        Setinitial();
        loginHomeButton.setBackgroundColor(getResources().getColor(R.color.radio_hui));

    }


    //设置一个初始的Fragment
    private void Setinitial() {
        home_fragment = new Home_Fragment();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.login_fragment, home_fragment);
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


                if (home_fragment == null) {
                    home_fragment = new Home_Fragment();
                    transaction.add(R.id.login_fragment, home_fragment);
                } else {
                    transaction.show(home_fragment);

                }
                break;
            case R.id.login_live_button:

                loginHomeButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginLiveButton.setBackgroundColor(getResources().getColor(R.color.radio_hui));
                loginGgtvButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginBroadcastButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginChinaButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));


                if (live_fragment == null) {
                    live_fragment = new Live_Fragment();
                    transaction.add(R.id.login_fragment, live_fragment);
                } else {
                    transaction.show(live_fragment);

                }
                break;
            case R.id.login_ggtv_button:
                loginHomeButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginLiveButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginGgtvButton.setBackgroundColor(getResources().getColor(R.color.radio_hui));
                loginBroadcastButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginChinaButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                if (culture_fragment == null) {
                    culture_fragment = new Culture_Fragment();
                    transaction.add(R.id.login_fragment, culture_fragment);
                } else {
                    transaction.show(culture_fragment);

                }
                break;
            case R.id.login_Broadcast_button:
                loginHomeButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginLiveButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginGgtvButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginBroadcastButton.setBackgroundColor(getResources().getColor(R.color.radio_hui));
                loginChinaButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                if (eye_pressed_fragment == null) {
                    eye_pressed_fragment = new Eye_Pressed_Fragment();
                    transaction.add(R.id.login_fragment, eye_pressed_fragment);
                } else {
                    transaction.show(eye_pressed_fragment);

                }
                break;
            case R.id.login_china_button:
                loginHomeButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginLiveButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginGgtvButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginBroadcastButton.setBackgroundColor(getResources().getColor(R.color.radio_bai));
                loginChinaButton.setBackgroundColor(getResources().getColor(R.color.radio_hui));
                if (live_china_fragment == null) {
                    live_china_fragment = new Live_China_Fragment();
                    transaction.add(R.id.login_fragment, live_china_fragment);
                } else {
                    transaction.show(live_china_fragment);

                }
                break;

        }

        transaction.commit();
    }

    public void hind_show(FragmentTransaction transaction) {
        if (home_fragment != null) {
            transaction.hide(home_fragment);

        }
        if (live_fragment != null) {
            transaction.hide(live_fragment);

        }
        if (culture_fragment != null) {
            transaction.hide(culture_fragment);

        }
        if (eye_pressed_fragment != null) {
            transaction.hide(eye_pressed_fragment);

        }
        if (live_china_fragment != null) {
            transaction.hide(live_china_fragment);

        }

    }
}

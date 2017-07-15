package comuxi.example.administrator.panda_channel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lenovo on 2017/7/14.
 */

public class LoginActivity extends Activity {


    @BindView(R.id.personal_image)
    ImageView personalImage;
    @BindView(R.id.tv_toRegist)
    TextView tvToRegist;
    @BindView(R.id.llweixinlogin)
    LinearLayout llweixinlogin;
    @BindView(R.id.llqqlogin)
    LinearLayout llqqlogin;
    @BindView(R.id.llsinalogin)
    LinearLayout llsinalogin;
    @BindView(R.id.edit_account)
    EditText editAccount;
    @BindView(R.id.hint_account)
    TextView hintAccount;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.hint_password)
    TextView hintPassword;
    @BindView(R.id.personal_login_forget_pwd)
    TextView personalLoginForgetPwd;
    @BindView(R.id.loding_btn)
    TextView lodingBtn;
    @BindView(R.id.login_in_layout)
    LinearLayout loginInLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_toRegist, R.id.llweixinlogin, R.id.llqqlogin, R.id.llsinalogin, R.id.edit_account, R.id.hint_account, R.id.edit_password, R.id.hint_password, R.id.personal_login_forget_pwd, R.id.loding_btn, R.id.login_in_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_toRegist:

                startActivity(new Intent(this,RegistActivity.class));
                break;
            case R.id.llweixinlogin:
                break;
            case R.id.llqqlogin:
                break;
            case R.id.llsinalogin:
                break;
            case R.id.edit_account:
                break;
            case R.id.hint_account:
                break;
            case R.id.edit_password:
                break;
            case R.id.hint_password:
                break;
            case R.id.personal_login_forget_pwd:
                break;
            case R.id.loding_btn:
                break;
            case R.id.login_in_layout:
                break;
        }
    }
}

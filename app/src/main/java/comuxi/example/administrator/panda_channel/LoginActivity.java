package comuxi.example.administrator.panda_channel;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.SocializeUtils;

import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lenovo on 2017/7/14.
 * 登录页面
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
    public ArrayList<SnsPlatform> platforms = new ArrayList<SnsPlatform>();
    private SHARE_MEDIA[] list = {SHARE_MEDIA.QQ, SHARE_MEDIA.SINA};
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initPlatforms();
    }

    @OnClick({R.id.tv_toRegist, R.id.llweixinlogin, R.id.llqqlogin, R.id.llsinalogin, R.id.edit_account, R.id.hint_account, R.id.edit_password, R.id.hint_password, R.id.personal_login_forget_pwd, R.id.loding_btn, R.id.login_in_layout,R.id.personal_image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_toRegist:

                startActivity(new Intent(this,RegistActivity.class));
                break;
            case R.id.llweixinlogin:
                break;
            case R.id.llqqlogin:
                UMShareAPI.get(LoginActivity.this).doOauthVerify(LoginActivity.this, platforms.get(0).mPlatform, authListener);
                break;
            case R.id.llsinalogin:

                denglu();

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
            case R.id.personal_image:

                this.finish();
                break;
        }
    }
    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
            SocializeUtils.safeShowDialog(dialog);
        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(LoginActivity.this, "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(LoginActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(LoginActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };
    private void initPlatforms() {
        platforms.clear();
        for (SHARE_MEDIA e : list) {
            if (!e.toString().equals(SHARE_MEDIA.GENERIC.toString())) {
                platforms.add(e.toSnsPlatform());
            }
        }
    }
    //需回调此方法

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        UMShareAPI.get(LoginActivity.this).onActivityResult(requestCode, resultCode, data);
    }

    private void denglu() {

        UMShareAPI.get(LoginActivity.this).getPlatformInfo(LoginActivity.this, SHARE_MEDIA.SINA, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                Log.e("TAG", "onStart");
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {

                String uid = map.get("uid");
                String name = map.get("name");
                String gender = map.get("gender");
                String iconurl = map.get("iconurl");

                Log.e("TAG", uid + "---" + name + "---" + gender + "---" + iconurl);

                Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

                Log.e("TAG", "onError");
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {

                Log.e("TAG", "onCancel");
            }
        });
    }
}

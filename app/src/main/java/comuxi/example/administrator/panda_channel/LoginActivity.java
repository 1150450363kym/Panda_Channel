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
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import comuxi.example.administrator.panda_channel.Utils.ACache;
import comuxi.example.administrator.panda_channel.Utils.Log_Utils;
import comuxi.example.administrator.panda_channel.activity.WJMMActivity;
import comuxi.example.administrator.panda_channel.app.App;
import comuxi.example.administrator.panda_channel.mode.CallBack.MyHttpCallBack;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.LoginBean;
import comuxi.example.administrator.panda_channel.mode.biz.PandaItemMode;
import comuxi.example.administrator.panda_channel.mode.biz.PandaMode;

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
    public ArrayList<SnsPlatform> platforms = new ArrayList<SnsPlatform>();
    private SHARE_MEDIA[] list = {SHARE_MEDIA.QQ, SHARE_MEDIA.SINA};
    private ProgressDialog dialog;
    private String userName,psw,msg;
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

                startActivity(new Intent(LoginActivity.this, WJMMActivity.class));

                break;
            case R.id.loding_btn:

                userName = editAccount.getText().toString().trim();
                psw = editPassword.getText().toString().trim();
                checkEmpty(editAccount);
                checkEmpty(editPassword);

                PandaMode mode = new PandaItemMode();
                mode.getLogin(userName, psw, new MyHttpCallBack<LoginBean>() {
                    @Override
                    public void onSuccess(LoginBean loginBean) {

                        ACache aCache = ACache.get(App.content);
                        aCache.put("loginBean",loginBean);//

//                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        msg = loginBean.getErrMsg();
                        if (msg.equals("成功")) {

                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();


                            Intent intent = getIntent();

                            intent.putExtra("userid",loginBean.getUser_seq_id());
                            setResult(1000,intent);
                            finish();
//                            LoginActivity.this.finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(int errorCode, String errorMsg) {

                        Toast.makeText(LoginActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
                    }
                });




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

            Set<String> strings = data.keySet();
            Log_Utils.log_d("TAG",strings.toString());

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

//                Intent in =new Intent(LoginActivity.this,PersonalCenterActivity.class);
                Intent intent = getIntent();
                intent.putExtra("name",name);
                setResult(2000,intent);
                finish();


//                startActivity(in);

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        UMShareAPI.get(LoginActivity.this).onActivityResult(requestCode, resultCode, data);
    }
    /**
     * 检查是否为空
     *
     * @param editText
     * @return
     */
    private boolean checkEmpty(EditText editText) {
        String testTxt = editText.getText().toString();
        if (testTxt == null || "".equals(testTxt)) {
            return false;
        }
        return true;
    }
}

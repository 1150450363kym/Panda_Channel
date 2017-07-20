package comuxi.example.administrator.panda_channel;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import comuxi.example.administrator.panda_channel.Base.BaseFragment;
import comuxi.example.administrator.panda_channel.app.App;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by lenovo on 2017/7/15.
 * 注册页面
 */

public class Personal_phone_register_Fragment extends BaseFragment {
    //    手机号码
    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.hint_phone)
    TextView hintPhone;
    //    图形验证码编辑框
    @BindView(R.id.edit_imgyanzhengma)
    EditText editImgyanzhengma;
    //    图形验证码
    @BindView(R.id.personal_reg_imgcheck)
    ImageView personalRegImgcheck;
    @BindView(R.id.hint_imagecheck)
    TextView hintImagecheck;
    //    手机验证码
    @BindView(R.id.edit_phoneyanzhengma)
    EditText editPhoneyanzhengma;
    //    手机验证码按钮
    @BindView(R.id.personal_reg_phonecheck)
    TextView personalRegPhonecheck;
    @BindView(R.id.hint_phonecheck)
    TextView hintPhonecheck;
    //    密码
    @BindView(R.id.edit_inputpasswrod)
    EditText editInputpasswrod;
    @BindView(R.id.hint_password)
    TextView hintPassword;


    @BindView(R.id.xieyi_check)
    CheckBox xieyiCheck;
    @BindView(R.id.personal_reg_xieyi_view)
    TextView personalRegXieyiView;
    @BindView(R.id.hint_xieyi)
    TextView hintXieyi;
    @BindView(R.id.btn_register)
    TextView btnRegister;
    Unbinder unbinder;
    Unbinder unbinder1;
    private TextView xieyi;
    public String JSESSIONID = null;
    public String verifycode = null;
    public String uct = null;
    private byte[] mCaptchaBytes;
    //private String erroType = "网络异常";
    private String erroType = "移动网络环境下无法进行注册,请开启WIFI后进行注册";
    private TextView mCaptchaImageView;

    private String mUserSeqId;
    private String mUserId;
    private String mNickName;
    //获取验证码图片成功、失败
    private static final int IMG_GET_SUCCES = 101;
    private static final int IMG_GET_ERROR = 102;

    //获取手机验证码成功、失败
    private static final int MSG_GETTING_SUCCESS = 103;
    private static final int MSG_GETTING_ERROR = 104;
    //手机号注册成功、失败
    private static final int MSG_LOGIN_SUCCESS = 105;
    private static final int MSG_LOGIN_ERROR = 106;
    //获取用户ID、昵称
    private static final int MSG_LGOIN_IN_GET_NICKNAME = 107;
    private static final int MSG_LOGIN_IN_ERROR = 108;
    private static final int MSG_GET_NICKNAME_SUCCESS = 109;


//    private TextView hint_phone;
//    private ImageView hint_imagecheck;
//    private TextView hint_phonecheck;
//    private TextView hint_surepawd;
//    private TextView hint_xieyi;
//    private TextView btn_register;
//    private TextView personal_reg_phonecheck;
//
//    private EditText edit_phone;
//    private EditText edit_imgyanzhengma;
//    private EditText edit_phoneyanzhengma;
//    private EditText edit_password;

    //    private TimeCount mTime;
//    private LinearLayout email;

    //    private TextView phone;
    //输入框输入的验证码
    private String mCaptchaEditTextString;
    private View view;


    private String url = "http://reg.cntv.cn/simple/verificationCode.action";

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
//                这是获取验证码的
                case 300:

                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

                    Bitmap bitmap = BitmapFactory.decodeStream(byteArrayInputStream);

                    personalRegImgcheck.setImageBitmap(bitmap);

                    break;
            }

        }
    };


    @Override
    protected int getlayoutID() {
        return R.layout.fragment_personal_phone_register;
    }

    @Override
    protected void init(View view) {


    }

    @Override
    protected void loadData() {
//图形验证码
        sendCaptchaHttpMessage();

    }

    /**
     * 检查手机验证码
     */

    private boolean checkPhoneCheck() {
        String phonecheck = editPhoneyanzhengma.getText().toString().trim();

        if (TextUtils.isEmpty(phonecheck)) {
            hintPhonecheck.setText("验证码不能为空");
            return false;
        } else {
            hintPhonecheck.setText(" ");
            return true;
        }
    }

    //检查手机号
    private boolean checkPhone() {
        String phoneString = editPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phoneString)) {
            hintPhone.setText("手机号码不能为空");
            return false;
        }
        Pattern pattern = Pattern.compile("^1[3578]\\d{9}$");
        Matcher matcher = pattern.matcher(phoneString);
        if (matcher.matches()) {
            hintPhone.setText("");
            return true;
        } else {
            hintPhone.setText("手机格式不正确");
            return false;
        }
    }

    //图片验证码
    private boolean checkCaptcha() {

        mCaptchaEditTextString = editImgyanzhengma.getText().toString().trim();
        if (mCaptchaEditTextString.contains(" ")) {
            hintImagecheck.setText("验证码不正确");
            return false;
        }
        if (mCaptchaEditTextString == null || "".equals(mCaptchaEditTextString)) {
            hintImagecheck.setText("验证码不能为空");
            return false;
        } else {
            hintImagecheck.setText("");
            return true;
        }
    }

    //检查密码
    private boolean checkPasswork() {
        String editpasswordsString = editInputpasswrod.getText().toString();

        if (TextUtils.isEmpty(editpasswordsString)) {
            hintPassword.setText("密码不能为空");
            return false;
        } else if (editpasswordsString.length() < 6 || editpasswordsString.length() > 16) {
            hintPassword.setText("密码仅限6-16个字符");
            return false;
        } else {
            hintPassword.setText("");
            return true;
        }
    }


    /**
     * 获取图片验证码
     */
    private String jsonId;
    private byte[] bytes;
    OkHttpClient client;

    private void sendCaptchaHttpMessage() {

        client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Headers headers = response.headers();

                jsonId = headers.get("Set-Cookie");
                Log.e("TAG", "获取图片验证码时候 得到的 Cookie" + jsonId);

                bytes = response.body().bytes();

                handler.sendEmptyMessage(300);
            }
        });
    }

    private Request request_regis;

    @OnClick({R.id.personal_reg_phonecheck, R.id.btn_register, R.id.personal_reg_imgcheck})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.personal_reg_imgcheck:

                sendCaptchaHttpMessage();

                break;


//           获取手机验证码的 按钮
            case R.id.personal_reg_phonecheck:
                checkPhone();
                checkCaptcha();
                String url = "http://reg.cntv.cn/regist/getVerifiCode.action";
                String from = "http://cbox_mobile.regclientuser.cntv.cn";
//                    手机号
                String tPhoneNumber = editPhone.getText().toString().trim();
//                    图形验证码
                String imgyanzhengma = editImgyanzhengma.getText().toString().trim();
//                请求  获取验证码的 网络请求
//                post 请求体
                RequestBody body = new FormBody.Builder()
                        .add("method", "getRequestVerifiCodeM")
                        .add("mobile", tPhoneNumber)
                        .add("verfiCodeType", "1")
                        .add("verificationCode", imgyanzhengma)
                        .build();
                try {
//                    post  请求头
                    Request request = new Request.Builder().url(url)
                            .addHeader("Referer", URLEncoder.encode(from, "UTF-8"))
                            .addHeader("User-Agent", URLEncoder.encode("CNTV_APP_CLIENT_CBOX_MOBILE", "UTF-8"))
                            .addHeader("Cookie", jsonId)
                            .post(body).build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                            Log.e("TAG", e.getMessage().toString());

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {

                            String string = response.body().string();
                            Log.e("TAG", "手机验证码结果" + string);

                        }
                    });
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                break;

//            点击注册的按钮 网络请求
            case R.id.btn_register:

                checkPhoneCheck();
                boolean b = checkPasswork();
                if (!isConnected()) {
                    Toast.makeText(App.content, "请链接网络，你个穷逼", Toast.LENGTH_SHORT).show();
                    return;
                }
                String edit_passwrod = editInputpasswrod.getText().toString().trim();
                Log.e("TAg", "注册密码" + edit_passwrod);
                Log.e("TAg", "手机号码" + editPhone.getText().toString().trim());
                Log.e("TAg", "手机验证码" + editPhoneyanzhengma.getText().toString().trim());
//                  请求体
                if (b) {
                    try {
                        body = new FormBody.Builder()
                                .add("method", "saveMobileRegisterM")
                                .add("mobile", editPhone.getText().toString().trim())
                                .add("verfiCode", editPhoneyanzhengma.getText().toString().trim())
                                .add("passWd", URLEncoder.encode(edit_passwrod, "UTF-8"))
                                .add("verfiCodeType", "1")
                                .add("addons", URLEncoder.encode("http://cbox_mobile.regclientuser.cntv.cn", "UTF-8"))
                                .build();

                        Request request = new Request.Builder()
                                .url("https://reg.cntv.cn/regist/mobileRegist.do")
                                .addHeader("Referer", URLEncoder.encode("http://cbox_mobile.regclientuser.cntv.cn", "UTF-8"))
                                .addHeader("User-Agent", URLEncoder.encode("CNTV_APP_CLIENT_CBOX_MOBILE", "UTF-8"))
                                .post(body)
                                .build();


                        client.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                Log.e("TAG", e.getMessage().toString());
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {

                                String loginSate = response.body().string();

                                Log.e("TAG", "注册状态：：" + loginSate);
                                Toast.makeText(App.content, "注册成功", Toast.LENGTH_SHORT).show();

                            }
                        });

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    /**
     * 判断手机是否有网络
     *
     * @return true 有网络
     */
    public boolean isConnected() {
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        try {
            ConnectivityManager connectivity = (ConnectivityManager) getActivity()
                    .getSystemService(getActivity().CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                // 获取网络连接管理的对象
                NetworkInfo info = connectivity.getActiveNetworkInfo();

                if (info != null && info.isConnected()) {
                    // 判断当前网络是否已经连接
                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
        }
        return false;
    }


}

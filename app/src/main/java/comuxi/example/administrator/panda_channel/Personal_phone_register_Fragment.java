package comuxi.example.administrator.panda_channel;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import comuxi.example.administrator.panda_channel.Base.BaseFragment;

/**
 * Created by lenovo on 2017/7/15.
 */

public class Personal_phone_register_Fragment extends BaseFragment {
    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.hint_phone)
    TextView hintPhone;
    @BindView(R.id.edit_imgyanzhengma)
    EditText editImgyanzhengma;
    @BindView(R.id.personal_reg_imgcheck)
    ImageView personalRegImgcheck;
    @BindView(R.id.hint_imagecheck)
    TextView hintImagecheck;
    @BindView(R.id.edit_phoneyanzhengma)
    EditText editPhoneyanzhengma;
    @BindView(R.id.personal_reg_phonecheck)
    TextView personalRegPhonecheck;
    @BindView(R.id.hint_phonecheck)
    TextView hintPhonecheck;
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

    private TextView hint_phone;
    private ImageView hint_imagecheck;
    private TextView hint_phonecheck;
    private TextView hint_surepawd;
    private TextView hint_xieyi;
    private TextView btn_register;
    private TextView personal_reg_phonecheck;

    private EditText edit_phone;
    private EditText edit_imgyanzhengma;
    private EditText edit_phoneyanzhengma;
    private EditText edit_password;

//    private TimeCount mTime;
    private LinearLayout email;

    private TextView phone;
    //输入框输入的验证码
    private String mCaptchaEditTextString;
    private View view;

    private String url = "http://reg.cntv.cn/simple/verificationCode.action";
    @Override
    protected int getlayoutID() {
        return R.layout.fragment_personal_phone_register;
    }

    @Override
    protected void init(View view) {


    }

    @Override
    protected void loadData() {

    }

    /**
     * 检查手机验证码
     */

    private boolean checkPhoneCheck() {
        String phonecheck = edit_phoneyanzhengma.getText().toString().trim();

        if (TextUtils.isEmpty(phonecheck)) {
            hint_phonecheck.setText("验证码不能为空");
            return false;
        } else {
            hint_phonecheck.setText(" ");
            return true;
        }
    }

    /**
     * 获取图片验证码
     */
    private void sendCaptchaHttpMessage() {


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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.hint_phone, R.id.personal_reg_imgcheck, R.id.hint_imagecheck, R.id.personal_reg_phonecheck, R.id.hint_phonecheck, R.id.hint_password, R.id.xieyi_check, R.id.personal_reg_xieyi_view, R.id.hint_xieyi, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.hint_phone:
                break;
            case R.id.personal_reg_imgcheck:

                Glide.with(getContext()).load(url).into(personalRegImgcheck);
                break;
            case R.id.hint_imagecheck:
                break;
            case R.id.personal_reg_phonecheck:
                break;
            case R.id.hint_phonecheck:
                break;
            case R.id.hint_password:
                break;
            case R.id.xieyi_check:
                break;
            case R.id.personal_reg_xieyi_view:
                break;
            case R.id.hint_xieyi:
                break;
            case R.id.btn_register:
                break;
        }
    }
}

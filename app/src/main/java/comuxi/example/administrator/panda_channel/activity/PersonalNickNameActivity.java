package comuxi.example.administrator.panda_channel.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.UserManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import comuxi.example.administrator.panda_channel.Base.BaseActivity;
import comuxi.example.administrator.panda_channel.R;

public class PersonalNickNameActivity extends BaseActivity implements View.OnClickListener, TextWatcher {

    @BindView(R.id.edit_nickmane)
    EditText editNickmane;
    private TextView mRigthTitle, mCenterTitle;
    private TextView mTvNickName;
    private ImageView mLeftTitle;
    private UserManager mUserManager;
    private PopupWindow mTipPw;
    private String mOldName, mNewName;

    private int flag; //标识修改头像成功的提示框的显示样式


    @Override
    protected int getLayoutId() {
        return R.layout.personal_change_nickname;
    }

    @Override
    protected void initView() {



        init();
    }

    private void init() {

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        editNickmane.setText(name);
//        mUserManager = UserManager.getInstance();
        mLeftTitle = (ImageView) findViewById(R.id.common_title_left);
        mCenterTitle = (TextView) findViewById(R.id.common_title_center);
        mRigthTitle = (TextView) findViewById(R.id.common_title_right);
//        mTvNickName = (TextView) findViewById(R.id.edit_nickmane);
        mLeftTitle.setOnClickListener(this);


//        setNickName();

    }

    /**
     * 设置要修改的昵称 并且初始化光标位置
     */
//    private void setNickName() {
//
//        mOldName = getIntent().getStringExtra("nickname");
//        mTvNickName.setText(mOldName);
//        CharSequence text = mTvNickName.getText();
//
//        if (text instanceof Spannable) {
//            Spannable text1 = (Spannable) text;
//            Selection.setSelection(text1, text.length());
//        }
//    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.common_title_left:
                hideKeyBoard();
                finish();
                break;

            case R.id.common_title_right:

//                showLoadingDialog();
                changeNickName();

                break;

        }
    }


    /**
     * 修改用户昵称
     */
    private void changeNickName() {
//        final String userId = mUserManager.getUserId();
//
//        Call<NickName> call = PandaApi.alterNickName(userId,  mNewName);
//        call.enqueue(new Callback<NickName>() {
//            @Override
//            public void onResponse(Call<NickName> call, Response<NickName> response) {
//                NickName nickName = response.body();
//                int code = nickName.getCode();
//                if (code == Constants.CODE_SUCCEED) {
//                    flag = 1;//上传成功 显示“我知道啦”提示框
//                    showTipPop(findViewById(R.id.common_title_right), R.string.change_nickname_tips
//                            , R.string.change_nickname_sure, R.string.tip_null);
//                } else {
//                    //根据返回errcode 判断cookie失效 提示用户重新登录
//                    if (code == Constants.CODE_USER_NOT_LOGGED && userId != null) {
//                        showTipPop(findViewById(R.id.common_title_right), R.string.login_faile, R.string.sure,
//                                R.string.cancel);
//
//                    } else {
//                        //提示服务器返回的错误信息
//                        Toast toast = Toast.makeText(PersonalNickNameActivity.this, nickName.getError(), Toast.LENGTH_LONG);
//                        toast.setGravity(Gravity.CENTER, 0, 0);
//                        toast.show();
//                    }
//
//                }
//                dismissLoadDialog();
//
//            }
//
//            @Override
//            public void onFailure(Call<NickName> call, Throwable t) {
//                dismissLoadDialog();
//
//            }

//        });

    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {

        mNewName = mTvNickName.getText().toString().trim();

        if (mTvNickName != null && !mNewName.equals(mOldName)) {
            mRigthTitle.setTextColor(Color.WHITE);
            mRigthTitle.setOnClickListener(this);
        }

    }

    private void hideKeyBoard() {

        View tView = getWindow().peekDecorView();

        if (tView != null) {
            // 获取输入法接口
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            // 强制隐藏键盘
            imm.hideSoftInputFromWindow(tView.getWindowToken(), 0);
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

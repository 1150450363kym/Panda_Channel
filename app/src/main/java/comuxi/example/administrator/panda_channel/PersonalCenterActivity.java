package comuxi.example.administrator.panda_channel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import comuxi.example.administrator.panda_channel.Utils.ACache;
import comuxi.example.administrator.panda_channel.activity.PersonInfoActivity;
import comuxi.example.administrator.panda_channel.app.App;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.LoginBean;

/**
 * Created by lenovo on 2017/7/14.
 * 个人中心
 */

public class PersonalCenterActivity extends Activity {

    @BindView(R.id.personal_image)
    ImageView personalImage;
    @BindView(R.id.linear1)
    LinearLayout linear1;
    @BindView(R.id.linear2)
    LinearLayout linear2;
    @BindView(R.id.linear3)
    LinearLayout linear3;
    @BindView(R.id.linear4)
    LinearLayout linear4;
    @BindView(R.id.dianjilogin)
    TextView dianjilogin;
    private String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_personal_center);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        userid = intent.getStringExtra("userid");

    }

    @OnClick({R.id.personal_image, R.id.linear1, R.id.linear2, R.id.linear3, R.id.linear4,R.id.dianjilogin,R.id.user_headimg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.personal_image:

                //点击返回按钮
                this.finish();
                break;
            case R.id.linear1:


                break;
            case R.id.linear2:

                //跳转观看历史
                startActivity(new Intent(PersonalCenterActivity.this, Historical_recordActivity.class));
                break;
            case R.id.linear3:

                //跳转我的收藏
                startActivity(new Intent(PersonalCenterActivity.this, MyCollectionActivity.class));
                break;
            case R.id.linear4:

                //跳转设置
                startActivity(new Intent(PersonalCenterActivity.this, PresonalSetActivity.class));
                break;

            case R.id.dianjilogin:

                ACache aCache = ACache.get(App.content);
                LoginBean loginBean = (LoginBean) aCache.getAsObject("loginBean");
                if(loginBean == null){
                    //跳转登录页面
                    startActivityForResult(new Intent(PersonalCenterActivity.this, LoginActivity.class),0);
                }else{
                    //跳转个人信息
                    Intent intent = new Intent(PersonalCenterActivity.this,PersonInfoActivity.class);
                    intent.putExtra("username",dianjilogin.getText().toString());
                    startActivity(intent);
//                    startActivityForResult(intent,0);
                }
                break;

            case R.id.user_headimg:


//                startActivity(new Intent(PersonalCenterActivity.this,PersonInfoActivity.class));
                break;
        }




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        UMShareAPI.get(PersonalCenterActivity.this).onActivityResult(requestCode, resultCode, data);
//        if(requestCode == 0 && resultCode == 1000)
//        {
//
//        }

        switch (resultCode){
            case 1000:
                String result_value = data.getStringExtra("userid");
                dianjilogin.setText("央视网友"+result_value);
                break;
            case 2000:
                String name = data.getStringExtra("name");
                dianjilogin.setText(name);
                break;
            case 3000:

                dianjilogin.setText("点击登录");
                break;
        }
    }



}

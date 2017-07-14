package comuxi.example.administrator.panda_channel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_personal_center);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.personal_image, R.id.linear1, R.id.linear2, R.id.linear3, R.id.linear4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.personal_image:

                //点击返回按钮
                this.finish();
                break;
            case R.id.linear1:

                //跳转登录页面
                startActivity(new Intent(PersonalCenterActivity.this,LoginActivity.class));
                break;
            case R.id.linear2:

                //跳转观看历史
                startActivity(new Intent(PersonalCenterActivity.this,Historical_recordActivity.class));
                break;
            case R.id.linear3:

                //跳转我的收藏
                startActivity(new Intent(PersonalCenterActivity.this,MyCollectionActivity.class));
                break;
            case R.id.linear4:

                //跳转设置
                startActivity(new Intent(PersonalCenterActivity.this,PresonalSetActivity.class));
                break;
        }
    }
}

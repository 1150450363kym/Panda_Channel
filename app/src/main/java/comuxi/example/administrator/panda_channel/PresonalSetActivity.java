package comuxi.example.administrator.panda_channel;

import android.widget.ImageView;

import butterknife.BindView;
import butterknife.OnClick;
import comuxi.example.administrator.panda_channel.Base.BaseActivity;

public class PresonalSetActivity extends BaseActivity {


    @BindView(R.id.historical_image)
    ImageView historicalImage;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_presonal_set;
    }

    @Override
    protected void initView() {

    }


    @OnClick(R.id.historical_image)
    public void onViewClicked() {

        this.finish();
    }
}

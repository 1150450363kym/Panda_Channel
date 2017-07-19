package comuxi.example.administrator.panda_channel;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import comuxi.example.administrator.panda_channel.Base.BaseActivity;
import comuxi.example.administrator.panda_channel.Utils.ACache;

//历史记录
public class Historical_recordActivity extends BaseActivity {

    @BindView(R.id.historical_image)
    ImageView historicalImage;
    @BindView(R.id.historical_edit)
    TextView historicalEdit;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.historical_listview)
    ListView historicalListview;
    @BindView(R.id.activity_historical_record)
    RelativeLayout activityHistoricalRecord;




    @Override
    protected int getLayoutId() {
        return R.layout.activity_historical_record;
    }

    @Override
    protected void initView() {


        ACache aCache = ACache.get(this);
        aCache.getAsJSONObject("shoucang");
    }

    @OnClick({R.id.historical_image, R.id.historical_edit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.historical_image:

                this.finish();
                break;
            case R.id.historical_edit:
                break;

        }
    }
}

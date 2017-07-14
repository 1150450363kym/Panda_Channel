package comuxi.example.administrator.panda_channel;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//历史记录
public class Historical_recordActivity extends Activity {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historical_record);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.historical_image, R.id.historical_edit, R.id.historical_listview})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.historical_image:

                this.finish();
                break;
            case R.id.historical_edit:
                break;
            case R.id.historical_listview:
                break;
        }
    }
}

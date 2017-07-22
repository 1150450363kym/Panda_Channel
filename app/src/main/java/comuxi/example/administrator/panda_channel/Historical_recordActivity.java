package comuxi.example.administrator.panda_channel;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import comuxi.example.administrator.panda_channel.Base.BaseActivity;
import comuxi.example.administrator.panda_channel.HistroyGreeDao.DaoMaster;
import comuxi.example.administrator.panda_channel.HistroyGreeDao.DaoSession;
import comuxi.example.administrator.panda_channel.HistroyGreeDao.HistroyGreeDao;
import comuxi.example.administrator.panda_channel.HistroyGreeDao.HistroyGreeDaoDao;
import comuxi.example.administrator.panda_channel.adapter_activity.Historical_Adapter;
import comuxi.example.administrator.panda_channel.app.App;

import static com.umeng.socialize.utils.ContextUtil.getContext;

//历史记录
public class Historical_recordActivity extends BaseActivity {
    //    编辑返回 按钮
    @BindView(R.id.historical_image)
    ImageView historicalImage;
    //    编辑 按钮
    @BindView(R.id.historical_edit)
    TextView historicalEdit;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    //    数据显示
    @BindView(R.id.historical_listview)
    RecyclerView historicalListview;
    @BindView(R.id.activity_historical_record)
    RelativeLayout activityHistoricalRecord;

    @BindView(R.id.all_histor_button)
    TextView allHistorButton;

    @BindView(R.id.delete_histor_button)
    TextView deleteHistorButton;

    @BindView(R.id.all_delete_linear)
    LinearLayout allDeleteLinear;
    @BindView(R.id.his_image_wu)
    ImageView hisImageWu;

    private List<HistroyGreeDao> list;
    private ArrayList<HistroyGreeDao> his_array = new ArrayList<>();
    private Historical_Adapter h_adapter;
    private HistroyGreeDaoDao his_dp;
    private int number = 0;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 300:
                    h_adapter.notifyDataSetChanged();
                    break;

            }

        }
    };


    @Override
    protected int getLayoutId() {

        return R.layout.activity_historical_record;
    }

    @Override
    protected void initView() {

//
        his_dp = getHis_dp();

        list = his_dp.queryBuilder().list();

        his_array.addAll(list);

        Log.e("TAG", "GreeDao 数据  查选到的" + his_array.size());


        for (int i = 0; i < his_array.size(); i++) {
            Log.e("TAG", " 我查查GreeDao  查询到的" + list.get(i).getName());

        }

        LinearLayoutManager ma = new LinearLayoutManager(App.content);
        historicalListview.setLayoutManager(ma);

        h_adapter = new Historical_Adapter(App.content, his_array);
        historicalListview.setAdapter(h_adapter);

        h_adapter.set_Onclick(new Historical_Adapter.Onclick() {
            @Override
            public void get_Onclick(View view, int postion) {

                if (historicalEdit.getText().equals("取消")) {
                    if (his_array.get(postion).isFlg_bulen() == false) {
                        his_array.get(postion).setFlg_bulen(true);
                        number++;
                        deleteHistorButton.setText("删除" + number);
                    } else {
                        number--;
                        deleteHistorButton.setText("删除" + number);
                        his_array.get(postion).setFlg_bulen(false);
                    }
                    if (number == 0) {
                        deleteHistorButton.setText("删除");
                    }
                }
                handler.sendEmptyMessage(300);

            }
        });


    }

    @OnClick({R.id.historical_image, R.id.historical_edit, R.id.all_histor_button, R.id.delete_histor_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.historical_image:

                Historical_recordActivity.this.finish();

                break;

            case R.id.historical_edit:


                if (historicalEdit.getText().equals("编辑")) {

                    historicalEdit.setText("取消");
                    allDeleteLinear.setVisibility(View.VISIBLE);

                    for (int i = 0; i < his_array.size(); i++) {

                        his_array.get(i).setFlg(true);

                    }

                    handler.sendEmptyMessage(300);


                } else if (historicalEdit.getText().equals("取消")) {

                    historicalEdit.setText("编辑");
                    allDeleteLinear.setVisibility(View.GONE);
                    for (int i = 0; i < his_array.size(); i++) {

                        his_array.get(i).setFlg(false);

                    }

                    handler.sendEmptyMessage(300);

                }


                break;
            case R.id.all_histor_button:
                if (allHistorButton.getText().equals("全选")) {
                    allHistorButton.setText("取消全选");
                    if (historicalEdit.getText().equals("取消")) {
                        for (int i = 0; i < his_array.size(); i++) {
                            his_array.get(i).setFlg_bulen(true);
                        }
                        number = his_array.size();
                        deleteHistorButton.setText("删除" + number);
                        handler.sendEmptyMessage(300);
                    }
                } else {
                    for (int i = 0; i < his_array.size(); i++) {
                        his_array.get(i).setFlg_bulen(false);
                    }
                    number = 0;
                    deleteHistorButton.setText("删除");
                    allHistorButton.setText("全选");
                    handler.sendEmptyMessage(300);
                }


                break;
            case R.id.delete_histor_button:

                if (historicalEdit.getText().equals("取消")) {
                    Log.e("tag===list.size", his_array.size() + "");
                    for (int i = his_array.size() - 1; i >= 0; i--) {
                        if (his_array.get(i).isFlg_bulen()) {


                            his_dp.delete(his_array.get(i));

                            his_array.remove(his_array.get(i));
                        }
                    }
                    number = 0;
                    handler.sendEmptyMessage(300);
                    deleteHistorButton.setText("删除");


                    if (his_array.size() == 0) {
                        allDeleteLinear.setVisibility(View.GONE);
                        historicalEdit.setText("编辑");
                        historicalEdit.setVisibility(View.GONE);
                        hisImageWu.setVisibility(View.VISIBLE);
                    }
                }

                break;
        }
    }


    private HistroyGreeDaoDao getHis_dp() {
        DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(getContext(), "Histrogry.dp", null);
        SQLiteDatabase database = helper.getWritableDatabase();
        DaoMaster master = new DaoMaster(database);
        DaoSession session = master.newSession();
        HistroyGreeDaoDao histroyGreeDaoDao = session.getHistroyGreeDaoDao();
        return histroyGreeDaoDao;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

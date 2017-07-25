package comuxi.example.administrator.panda_channel;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import comuxi.example.administrator.panda_channel.Base.BaseActivity;
import comuxi.example.administrator.panda_channel.Utils.CleanMessageUtil;
import comuxi.example.administrator.panda_channel.app.App;

public class PresonalSetActivity extends BaseActivity {


    @BindView(R.id.person_set_image)
    ImageView historicalImage;
    @BindView(R.id.pe_set_push_view)
    CheckBox peSetPushView;
    @BindView(R.id.pe_set_play_view)
    CheckBox peSetPlayView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_qita)
    TextView tvQita;
    @BindView(R.id.set_cache_size_tv)
    TextView setCacheSizeTv;
    @BindView(R.id.personal_set_delete_cache_layout)
    RelativeLayout personalSetDeleteCacheLayout;
    @BindView(R.id.personal_set_fankui_layout)
    RelativeLayout personalSetFankuiLayout;
    @BindView(R.id.personal_set_udpate_layout)
    RelativeLayout personalSetUdpateLayout;
    @BindView(R.id.personal_set_ping_layout)
    RelativeLayout personalSetPingLayout;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.personal_set_about_layout)
    RelativeLayout personalSetAboutLayout;
    @BindView(R.id.activity_presonal_set)
    RelativeLayout activityPresonalSet;
    @BindView(R.id.tv_M)
    TextView tvM;
    private PopupWindow popupWindow;


    @Override
    protected int getLayoutId() {

        return R.layout.activity_presonal_set;
    }

    @Override
    protected void initView() {
        try {
            tvM.setText(CleanMessageUtil.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @OnClick(R.id.historical_image)
    public void onViewClicked() {

        this.finish();
    }


    @OnClick({R.id.personal_set_delete_cache_layout, R.id.personal_set_fankui_layout, R.id.personal_set_udpate_layout, R.id.personal_set_ping_layout, R.id.personal_set_about_layout, R.id.activity_presonal_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.personal_set_delete_cache_layout:

                onClickCleanCache();


                break;
            case R.id.personal_set_fankui_layout:

                startActivity(new Intent(this, PersonalFeedBackActivity.class));

                break;

            case R.id.personal_set_udpate_layout:

                Toast.makeText(PresonalSetActivity.this, "已经是最新版本了", Toast.LENGTH_SHORT).show();
                break;
            case R.id.personal_set_ping_layout:
                break;
            case R.id.personal_set_about_layout:

                startActivity(new Intent(this, PersonAboutActivity.class));
                break;
            case R.id.activity_presonal_set:
                break;
        }
    }
    private void onClickCleanCache() {
        getConfirmDialog(this, "是否清空缓存?", new DialogInterface.OnClickListener
                () {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                CleanMessageUtil.clearAllCache(App.content);
                tvM.setText("0.00MB");
            }
        }).show();
    }

    public static AlertDialog.Builder getConfirmDialog(Context context, String message, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = getDialog(context);
        builder.setMessage(Html.fromHtml(message));
        builder.setPositiveButton("确定", onClickListener);
        builder.setNegativeButton("取消", null);
        return builder;
    }

    public static AlertDialog.Builder getDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        return builder;
    }

}

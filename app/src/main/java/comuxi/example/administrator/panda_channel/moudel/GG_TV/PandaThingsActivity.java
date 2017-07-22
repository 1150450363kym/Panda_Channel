package comuxi.example.administrator.panda_channel.moudel.GG_TV;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;
import comuxi.example.administrator.panda_channel.Base.BaseActivity;
import comuxi.example.administrator.panda_channel.R;
import comuxi.example.administrator.panda_channel.mode.CallBack.MyHttpCallBack;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.XiquaimationBean;
import comuxi.example.administrator.panda_channel.mode.biz.PandaItemMode;
import comuxi.example.administrator.panda_channel.mode.biz.PandaMode;
import comuxi.example.administrator.panda_channel.moudel.GG_TV.adapter.XiquPulltoAdapter;

import static com.umeng.socialize.utils.ContextUtil.getContext;


public class PandaThingsActivity extends BaseActivity implements PullToRefreshListener {


    @BindView(R.id.live_center_blue_img)
    CheckBox liveCenterBlueImg;
    @BindView(R.id.panda_things_tv_pandacolumn)
    TextView pandaThingsTvPandacolumn;

    @BindView(R.id.gg_totop_title)
    TextView ggTotopTitle;
    @BindView(R.id.activity_panda_things)
    LinearLayout activityPandaThings;
    @BindView(R.id.panda_things_pullto)
    PullToRefreshRecyclerView pandaThingsPullto;
    @BindView(R.id.img_share)
    ImageView imgShare;
    private String n = "6";
    private String vsid = "VSET100311356635";
    private String p = "1";
    private String serviceId = "panda";
    private int em = 1;
    private List<XiquaimationBean.VideoBean> list;
    private XiquaimationBean.VideosetBean._$0Bean bean;

    private XiquPulltoAdapter adapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_panda_things;
    }

    @Override
    protected void initView() {

        list = new ArrayList<>();
        adapter = new XiquPulltoAdapter(this, list);

        pandaThingsPullto.setAdapter(adapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        pandaThingsPullto.setLayoutManager(manager);

        pandaThingsPullto.setLoadingMoreEnabled(true);
        pandaThingsPullto.setPullRefreshEnabled(true);
        pandaThingsPullto.setPullToRefreshListener(this);
        Intent intent = getIntent();
        String title = intent.getStringExtra("top_title");
        ggTotopTitle.setText(title);

        initData();

    }

    private void initData() {

        final PandaMode mode = new PandaItemMode();
        mode.getXiquanimation(n, vsid, p, serviceId, em, new MyHttpCallBack<XiquaimationBean>() {
            @Override
            public void onSuccess(XiquaimationBean xiquaimationBean) {
                list.addAll(xiquaimationBean.getVideo());
                 bean = xiquaimationBean.getVideoset().get_$0();
//                mlist.add(bean);
//                mlist.addAll(xiquaimationBean.getVideoset().get_$0().getDesc());
                pandaThingsTvPandacolumn.setText(bean.getDesc());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(int errorCode, String errorMsg) {

            }
        });
    }

    @Override
    public void onRefresh() {

        pandaThingsPullto.postDelayed(new Runnable() {
            @Override
            public void run() {
                list.clear();
                initData();
                adapter.notifyDataSetChanged();
                pandaThingsPullto.setRefreshComplete();
            }
        }, 1000);

    }

    @Override
    public void onLoadMore() {

        pandaThingsPullto.setLoadMoreComplete();
    }

    @OnClick({R.id.live_center_blue_img, R.id.img_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.live_center_blue_img:

                pandaThingsTvPandacolumn.setVisibility(View.VISIBLE);
                if (!liveCenterBlueImg.isChecked()) {

                    pandaThingsTvPandacolumn.setVisibility(View.GONE);
                }

                break;
            case R.id.img_share:

//                UMImage thumb = new UMImage(this, R.mipmap.ic_launcher);
//                UMWeb web = new UMWeb(bean.getUrl());
//                web.setTitle("This is a title");//标题
//                web.setThumb(thumb);  //缩略图
//                web.setDescription("my description");//描述
//
//                new ShareAction(PandaThingsActivity.this)
//                        .withMedia(web)
//                        .open();



                break;
        }
    }
}

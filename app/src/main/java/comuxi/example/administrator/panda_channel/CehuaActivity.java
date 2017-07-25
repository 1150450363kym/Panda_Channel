package comuxi.example.administrator.panda_channel;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import comuxi.example.administrator.panda_channel.Base.BaseActivity;
import comuxi.example.administrator.panda_channel.Utils.ACache;
import comuxi.example.administrator.panda_channel.mode.CallBack.MyHttpCallBack;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.CehuaBean;
import comuxi.example.administrator.panda_channel.mode.biz.PandaItemMode;
import comuxi.example.administrator.panda_channel.mode.biz.PandaMode;

/**
 * 原创互动 页面
 */

public class CehuaActivity extends BaseActivity implements PullToRefreshListener {

    @BindView(R.id.yuanchuan_image)
    ImageView historicalImage;
    @BindView(R.id.cehua_pullto)
    PullToRefreshRecyclerView cehuaPullto;
    private CehuaAdapter adapter;
    private List<CehuaBean.InteractiveBean> list;

    @Override
    protected int getLayoutId() {

        return R.layout.activity_cehua;
    }

    @Override
    protected void initView() {


        list = new ArrayList<>();
        adapter = new CehuaAdapter(this, list);
        cehuaPullto.setAdapter(adapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        cehuaPullto.setLayoutManager(manager);

        cehuaPullto.setLoadingMoreEnabled(true);
        cehuaPullto.setPullRefreshEnabled(true);
        cehuaPullto.setPullToRefreshListener(this);

        initData();


    }

    private void initData() {

        PandaMode mode = new PandaItemMode();
        mode.getCehua(new MyHttpCallBack<CehuaBean>() {
            @Override
            public void onSuccess(CehuaBean cehuaBean) {
                list.addAll(cehuaBean.getInteractive());
                Log.e("TAG",list.size()+"");
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(int errorCode, String errorMsg) {

                ACache aCache = ACache.get(CehuaActivity.this);
                CehuaBean bean = (CehuaBean) aCache.getAsObject("CehuaBean");
                list.addAll(bean.getInteractive());
                Log.e("TAG",list.size()+"");
                adapter.notifyDataSetChanged();

            }
        });
    }

    @OnClick(R.id.yuanchuan_image)
    public void onViewClicked() {
        this.finish();
    }

    @Override
    public void onRefresh() {

        cehuaPullto.postDelayed(new Runnable() {
            @Override
            public void run() {

                list.clear();
                initData();
                adapter.notifyDataSetChanged();
                cehuaPullto.setRefreshComplete();
            }
        },1000);
    }

    @Override
    public void onLoadMore() {

        cehuaPullto.setLoadMoreComplete();
    }
}

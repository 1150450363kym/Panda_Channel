package comuxi.example.administrator.panda_channel.moudel.Panda_Live.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;
import comuxi.example.administrator.panda_channel.Base.BaseFragment;
import comuxi.example.administrator.panda_channel.R;
import comuxi.example.administrator.panda_channel.Utils.ACache;
import comuxi.example.administrator.panda_channel.Utils.Log_Utils;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.WonderfulOneBean;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.adapter.WinderfulonePulltoAdapter;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.contract.OriginalnewsContract;


/**
 * Created by lenovo on 2017/7/12.
 * <p>
 * 熊猫直播 --- 原创新闻
 */

public class Original_news extends BaseFragment implements PullToRefreshListener,OriginalnewsContract.View {
    @BindView(R.id.original_news_pulltorefresh)
    PullToRefreshRecyclerView originalNewsPulltorefresh;
    Unbinder unbinder;
    private int i = 1;
    private   String  VSID;
    private static final String N="7";
    private static final String SERVICEID="panda";
    private static final String O="desc";
    private static final String OF="time";

    private boolean flag=false;
    public Original_news(String VSID) {
        this.VSID = VSID;
    }
    private OriginalnewsContract.presenter presenter ;
    private List<WonderfulOneBean.VideoBean> list;
    private WinderfulonePulltoAdapter adapter;
    @Override
    protected int getlayoutID() {
        return R.layout.original_news;
    }

    @Override
    protected void init(View view) {

        list = new ArrayList<>();
        adapter = new WinderfulonePulltoAdapter(getContext(),list);
        originalNewsPulltorefresh.setAdapter(adapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        originalNewsPulltorefresh.setLayoutManager(manager);

        originalNewsPulltorefresh.setLoadingMoreEnabled(true);
        originalNewsPulltorefresh.setPullRefreshEnabled(true);
        originalNewsPulltorefresh.setPullToRefreshListener(this);
    }

    @Override
    protected void loadData() {

        presenter.showData(VSID,N,SERVICEID,O,OF,i);
        presenter.start();
    }


    @Override
    public void onRefresh() {
        originalNewsPulltorefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                list.clear();
                loadData();
                adapter.notifyDataSetChanged();
                originalNewsPulltorefresh.setRefreshComplete();
            }
        },1000);
    }
    @Override
    public void onLoadMore() {

        originalNewsPulltorefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                i++;
                loadData();
                adapter.notifyDataSetChanged();
                originalNewsPulltorefresh.setLoadMoreComplete();

            }
        }, 1000);
    }

    @Override
    public void showData(WonderfulOneBean moreLiveBean) {

        list.addAll(moreLiveBean.getVideo());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMsg(String msg) {

        ACache aCache =ACache.get(getContext());
        WonderfulOneBean bean = (WonderfulOneBean) aCache.getAsObject("WonderfulOneBean");
        Log_Utils.log_d("TAG",bean.getVideo().size()+"");
        list.addAll(bean.getVideo());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(OriginalnewsContract.presenter presenter) {

        this.presenter = presenter;
    }
}

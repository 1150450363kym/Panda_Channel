package comuxi.example.administrator.panda_channel.moudel.Panda_Live.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import comuxi.example.administrator.panda_channel.Base.BaseFragment;
import comuxi.example.administrator.panda_channel.R;
import comuxi.example.administrator.panda_channel.Utils.ACache;
import comuxi.example.administrator.panda_channel.Utils.Log_Utils;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.WonderfulOneBean;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.adapter.WinderfulonePulltoAdapter;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.contract.PandaTOPContract;


/**
 * Created by lenovo on 2017/7/12.
 * 熊猫直播 --- 熊猫TOP榜
 */

public class PandaTOP extends BaseFragment implements PullToRefreshListener,PandaTOPContract.View {

    @BindView(R.id.pandatop_pulltorefresh)
    PullToRefreshRecyclerView pandatopPulltorefresh;
    private int i = 1;
    private PandaTOPContract.presenter presenter ;
    private List<WonderfulOneBean.VideoBean> list;
    private WinderfulonePulltoAdapter adapter;

    @Override
    protected int getlayoutID() {
        return R.layout.pandatop;
    }

    @Override
    protected void init(View view) {

        list = new ArrayList<>();
        adapter = new WinderfulonePulltoAdapter(getContext(),list);
        pandatopPulltorefresh.setAdapter(adapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        pandatopPulltorefresh.setLayoutManager(manager);

        pandatopPulltorefresh.setLoadingMoreEnabled(true);
        pandatopPulltorefresh.setPullRefreshEnabled(true);
        pandatopPulltorefresh.setPullToRefreshListener(this);
    }

    @Override
    protected void loadData() {


        presenter.start();
        presenter.showData("VSET100284428835","7","panda","desc","time",i);
    }

    @Override
    public void onRefresh() {
        pandatopPulltorefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                list.clear();
                loadData();
                adapter.notifyDataSetChanged();
                pandatopPulltorefresh.setRefreshComplete();
            }
        },1000);
    }
    @Override
    public void onLoadMore() {

        pandatopPulltorefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                i++;
                loadData();

                adapter.notifyDataSetChanged();
                pandatopPulltorefresh.setLoadMoreComplete();

            }
        },1000);

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
    public void setPresenter(PandaTOPContract.presenter presenter) {

        this.presenter = presenter;
    }
}

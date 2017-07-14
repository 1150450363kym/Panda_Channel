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
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.WonderfulOneBean;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.adapter.WinderfulonePulltoAdapter;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.contract.WonderfuloneContract;


/**
 * Created by lenovo on 2017/7/12.
 * 熊猫直播---精彩一刻
 */

public class Wonderful_moment extends BaseFragment implements PullToRefreshListener,WonderfuloneContract.View {


    @BindView(R.id.wonderful_woment_pulltorefresh)
    PullToRefreshRecyclerView wonderfulWomentPulltorefresh;
    private List<WonderfulOneBean.VideoBean> list;
    private WinderfulonePulltoAdapter adapter;

    private int i = 1;
    WonderfuloneContract.presenter presenter;

    @Override
    protected int getlayoutID() {
        return R.layout.wonderful_moment;
    }

    @Override
    protected void init(View view) {
        list = new ArrayList<>();
        adapter = new WinderfulonePulltoAdapter(getContext(),list);
        wonderfulWomentPulltorefresh.setAdapter(adapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        wonderfulWomentPulltorefresh.setLayoutManager(manager);

        wonderfulWomentPulltorefresh.setLoadingMoreEnabled(true);
        wonderfulWomentPulltorefresh.setPullRefreshEnabled(true);
        wonderfulWomentPulltorefresh.setPullToRefreshListener(this);
    }

    @Override
    protected void loadData() {
        presenter.start();
        presenter.showData("VSET100167216881","7","panda","desc","time",i);
    }

    @Override
    public void onRefresh() {
        wonderfulWomentPulltorefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                list.clear();
                loadData();
                adapter.notifyDataSetChanged();
                wonderfulWomentPulltorefresh.setRefreshComplete();
            }
        },1000);
    }

    @Override
    public void onLoadMore() {

        wonderfulWomentPulltorefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                i++;
                loadData();

                adapter.notifyDataSetChanged();
                wonderfulWomentPulltorefresh.setLoadMoreComplete();

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

    }

    @Override
    public void setPresenter(WonderfuloneContract.presenter presenter) {

        this.presenter = presenter;

    }
}

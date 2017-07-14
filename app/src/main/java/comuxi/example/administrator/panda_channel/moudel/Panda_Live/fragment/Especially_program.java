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
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.contract.EspeciallyprogramPresenterContract;


/**
 * Created by lenovo on 2017/7/12.
 * 熊猫直播 --- 特别节目
 */

public class Especially_program extends BaseFragment implements PullToRefreshListener,EspeciallyprogramPresenterContract.View {
    @BindView(R.id.especially_progress_pulltorefresh)
    PullToRefreshRecyclerView especiallyProgressPulltorefresh;
    private int i = 1;
    private EspeciallyprogramPresenterContract.presenter presenter ;
    private List<WonderfulOneBean.VideoBean> list;
    private WinderfulonePulltoAdapter adapter;

    @Override
    protected int getlayoutID() {
        return R.layout.especially_program;
    }

    @Override
    protected void init(View view) {

        list = new ArrayList<>();
        adapter = new WinderfulonePulltoAdapter(getContext(),list);
        especiallyProgressPulltorefresh.setAdapter(adapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        especiallyProgressPulltorefresh.setLayoutManager(manager);

        especiallyProgressPulltorefresh.setLoadingMoreEnabled(true);
        especiallyProgressPulltorefresh.setPullRefreshEnabled(true);
        especiallyProgressPulltorefresh.setPullToRefreshListener(this);
    }

    @Override
    protected void loadData() {

        presenter.start();
        presenter.showData("VSET100167308855","7","panda","desc","time",i);
    }

    @Override
    public void onRefresh() {

        especiallyProgressPulltorefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                list.clear();
                loadData();
                adapter.notifyDataSetChanged();
                especiallyProgressPulltorefresh.setRefreshComplete();
            }
        },1000);
    }
    @Override
    public void onLoadMore() {

        especiallyProgressPulltorefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                i++;
                loadData();

                adapter.notifyDataSetChanged();
                especiallyProgressPulltorefresh.setLoadMoreComplete();

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

    }

    @Override
    public void setPresenter(EspeciallyprogramPresenterContract.presenter presenter) {

        this.presenter = presenter;
    }
}

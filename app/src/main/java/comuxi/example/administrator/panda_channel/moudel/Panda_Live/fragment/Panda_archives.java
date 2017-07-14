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
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.WonderfulOneBean;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.adapter.WinderfulonePulltoAdapter;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.contract.Panda_archivesContract;


/**
 * Created by lenovo on 2017/7/12.
 * 熊猫直播---熊猫档案
 */

public class Panda_archives extends BaseFragment implements PullToRefreshListener,Panda_archivesContract.View {
    @BindView(R.id.panda_archives_pulltorefresh)
    PullToRefreshRecyclerView pandaArchivesPulltorefresh;
    Unbinder unbinder;
    private int i = 1;
    private Panda_archivesContract.presenter presenter ;
    private List<WonderfulOneBean.VideoBean> list;
    private WinderfulonePulltoAdapter adapter;
    @Override
    protected int getlayoutID() {

        return R.layout.panda_archives;
    }

    @Override
    protected void init(View view) {

        list = new ArrayList<>();
        adapter = new WinderfulonePulltoAdapter(getContext(),list);
        pandaArchivesPulltorefresh.setAdapter(adapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        pandaArchivesPulltorefresh.setLayoutManager(manager);

        pandaArchivesPulltorefresh.setLoadingMoreEnabled(true);
        pandaArchivesPulltorefresh.setPullRefreshEnabled(true);
        pandaArchivesPulltorefresh.setPullToRefreshListener(this);
    }

    @Override
    protected void loadData() {


        presenter.start();
        presenter.showData("VSET100340574858","7","panda","desc","time",i);
    }


    @Override
    public void onRefresh() {
        pandaArchivesPulltorefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                list.clear();
                loadData();
                adapter.notifyDataSetChanged();
                pandaArchivesPulltorefresh.setRefreshComplete();
            }
        },1000);
    }
    @Override
    public void onLoadMore() {

        pandaArchivesPulltorefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                i++;
                loadData();

                adapter.notifyDataSetChanged();
                pandaArchivesPulltorefresh.setLoadMoreComplete();

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
    public void setPresenter(Panda_archivesContract.presenter presenter) {

        this.presenter = presenter;
    }
}

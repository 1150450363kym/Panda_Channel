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
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.contract.PandaThingContract;


/**
 * Created by lenovo on 2017/7/12.
 * 熊猫直播 --- 熊猫那些事儿
 */

public class PandaThing extends BaseFragment implements PullToRefreshListener,PandaThingContract.View {

    @BindView(R.id.pandathing_pulltorefresh)
    PullToRefreshRecyclerView pandathingPulltorefresh;
    Unbinder unbinder;
    private int i = 1;
    private PandaThingContract.presenter presenter ;
    private List<WonderfulOneBean.VideoBean> list;
    private WinderfulonePulltoAdapter adapter;
    @Override
    protected int getlayoutID() {
        return R.layout.pandathing;
    }

    @Override
    protected void init(View view) {

        list = new ArrayList<>();
        adapter = new WinderfulonePulltoAdapter(getContext(),list);
        pandathingPulltorefresh.setAdapter(adapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        pandathingPulltorefresh.setLayoutManager(manager);

        pandathingPulltorefresh.setLoadingMoreEnabled(true);
        pandathingPulltorefresh.setPullRefreshEnabled(true);
        pandathingPulltorefresh.setPullToRefreshListener(this);
    }

    @Override
    protected void loadData() {

        presenter.start();
        presenter.showData("VSET100237714751","7","panda","desc","time",i);
    }


    @Override
    public void onRefresh() {

        pandathingPulltorefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                list.clear();
                loadData();
                adapter.notifyDataSetChanged();
                pandathingPulltorefresh.setRefreshComplete();
            }
        },1000);
    }
    @Override
    public void onLoadMore() {

        pandathingPulltorefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                i++;
                loadData();

                adapter.notifyDataSetChanged();
                pandathingPulltorefresh.setLoadMoreComplete();

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
    public void setPresenter(PandaThingContract.presenter presenter) {

        this.presenter = presenter;

    }
}

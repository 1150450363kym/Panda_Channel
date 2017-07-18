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
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.contract.LovelyLiveContract;


/**
 * Created by lenovo on 2017/7/12.
 * 熊猫直播--- 超萌滚滚秀
 */

public class LovelyLive extends BaseFragment implements PullToRefreshListener,LovelyLiveContract.View {
    @BindView(R.id.lovelylive_pulltorefresh)
    PullToRefreshRecyclerView lovelylivePulltorefresh;
    Unbinder unbinder;
    private int i = 1;
    private LovelyLiveContract.presenter presenter ;
    private List<WonderfulOneBean.VideoBean> list;
    private WinderfulonePulltoAdapter adapter;
    @Override
    protected int getlayoutID() {
        return R.layout.lovelyllive;
    }

    @Override
    protected void init(View view) {

        list = new ArrayList<>();
        adapter = new WinderfulonePulltoAdapter(getContext(),list);
        lovelylivePulltorefresh.setAdapter(adapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        lovelylivePulltorefresh.setLayoutManager(manager);

        lovelylivePulltorefresh.setLoadingMoreEnabled(true);
        lovelylivePulltorefresh.setPullRefreshEnabled(true);
        lovelylivePulltorefresh.setPullToRefreshListener(this);
    }

    @Override
    protected void loadData() {


        presenter.showData("VSET100272959126","7","panda","desc","time",i);
        presenter.start();
    }


    @Override
    public void onRefresh() {
        lovelylivePulltorefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                list.clear();
                loadData();
                adapter.notifyDataSetChanged();
                lovelylivePulltorefresh.setRefreshComplete();
            }
        },1000);
    }

    @Override
    public void onLoadMore() {

        lovelylivePulltorefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                i++;
                loadData();

                adapter.notifyDataSetChanged();

                //注意此方法!!!
                lovelylivePulltorefresh.setLoadMoreComplete();
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
    public void setPresenter(LovelyLiveContract.presenter presenter) {

        this.presenter = presenter;
    }
}

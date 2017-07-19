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
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.contract.LetPandaContract;


/**
 * Created by lenovo on 2017/7/12.
 * http://www.ipanda.com/kehuduan/PAGE14501772263221982/index.json
 *
 * 熊猫直播 --- 当熊不让
 */

public class LetPanda extends BaseFragment implements PullToRefreshListener,LetPandaContract.View {

    @BindView(R.id.letpanda_pulltorefresh)
    PullToRefreshRecyclerView letpandaPulltorefresh;
    Unbinder unbinder;
    private int i = 1;
    private LetPandaContract.presenter presenter ;
    private List<WonderfulOneBean.VideoBean> list;
    private WinderfulonePulltoAdapter adapter;

    @Override
    protected int getlayoutID() {
        return R.layout.letpanda;
    }

    @Override
    protected void init(View view) {

        list = new ArrayList<>();
        adapter = new WinderfulonePulltoAdapter(getContext(),list);
        letpandaPulltorefresh.setAdapter(adapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        letpandaPulltorefresh.setLayoutManager(manager);

        letpandaPulltorefresh.setLoadingMoreEnabled(true);
        letpandaPulltorefresh.setPullRefreshEnabled(true);
        letpandaPulltorefresh.setPullToRefreshListener(this);
    }

    @Override
    protected void loadData() {

        presenter.start();
        presenter.showData("VSET100332640004","7","panda","desc","time",i);

    }


    @Override
    public void onRefresh() {
        letpandaPulltorefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                list.clear();
                loadData();
                adapter.notifyDataSetChanged();
                letpandaPulltorefresh.setRefreshComplete();
            }
        },1000);
    }

    @Override
    public void onLoadMore() {

        letpandaPulltorefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                i++;
                loadData();

                adapter.notifyDataSetChanged();
                letpandaPulltorefresh.setLoadMoreComplete();

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
    public void setPresenter(LetPandaContract.presenter presenter) {

        this.presenter = presenter;
    }
}

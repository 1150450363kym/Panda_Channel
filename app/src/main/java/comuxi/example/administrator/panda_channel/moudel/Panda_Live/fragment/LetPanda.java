package comuxi.example.administrator.panda_channel.moudel.Panda_Live.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;

import butterknife.BindView;
import butterknife.Unbinder;
import comuxi.example.administrator.panda_channel.Base.BaseFragment;
import comuxi.example.administrator.panda_channel.R;


/**
 * Created by lenovo on 2017/7/12.
 * 熊猫直播 --- 当熊不让
 */

public class LetPanda extends BaseFragment implements PullToRefreshListener {
    @BindView(R.id.letpanda_pulltorefresh)
    PullToRefreshRecyclerView letpandaPulltorefresh;
    Unbinder unbinder;

    @Override
    protected int getlayoutID() {
        return R.layout.letpanda;
    }

    @Override
    protected void init(View view) {

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        letpandaPulltorefresh.setLayoutManager(manager);

        letpandaPulltorefresh.setLoadingMoreEnabled(true);
        letpandaPulltorefresh.setPullRefreshEnabled(true);
        letpandaPulltorefresh.setPullToRefreshListener(this);
    }

    @Override
    protected void loadData() {

    }


    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}

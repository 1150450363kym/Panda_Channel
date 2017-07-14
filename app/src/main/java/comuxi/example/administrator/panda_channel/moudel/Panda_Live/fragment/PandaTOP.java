package comuxi.example.administrator.panda_channel.moudel.Panda_Live.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;

import butterknife.BindView;
import comuxi.example.administrator.panda_channel.Base.BaseFragment;
import comuxi.example.administrator.panda_channel.R;


/**
 * Created by lenovo on 2017/7/12.
 * 熊猫直播 --- 熊猫TOP榜
 */

public class PandaTOP extends BaseFragment implements PullToRefreshListener {

    @BindView(R.id.pandatop_pulltorefresh)
    PullToRefreshRecyclerView pandatopPulltorefresh;


    @Override
    protected int getlayoutID() {
        return R.layout.pandatop;
    }

    @Override
    protected void init(View view) {

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        pandatopPulltorefresh.setLayoutManager(manager);

        pandatopPulltorefresh.setLoadingMoreEnabled(true);
        pandatopPulltorefresh.setPullRefreshEnabled(true);
        pandatopPulltorefresh.setPullToRefreshListener(this);
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

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
 * 熊猫直播 --- 特别节目
 */

public class Especially_program extends BaseFragment implements PullToRefreshListener {
    @BindView(R.id.especially_progress_pulltorefresh)
    PullToRefreshRecyclerView especiallyProgressPulltorefresh;


    @Override
    protected int getlayoutID() {
        return R.layout.especially_program;
    }

    @Override
    protected void init(View view) {

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        especiallyProgressPulltorefresh.setLayoutManager(manager);

        especiallyProgressPulltorefresh.setLoadingMoreEnabled(true);
        especiallyProgressPulltorefresh.setPullRefreshEnabled(true);
        especiallyProgressPulltorefresh.setPullToRefreshListener(this);
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

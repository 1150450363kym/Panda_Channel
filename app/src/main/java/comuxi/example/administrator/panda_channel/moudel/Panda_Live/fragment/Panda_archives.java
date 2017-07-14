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
 * 熊猫直播---熊猫档案
 */

public class Panda_archives extends BaseFragment implements PullToRefreshListener {
    @BindView(R.id.panda_archives_pulltorefresh)
    PullToRefreshRecyclerView pandaArchivesPulltorefresh;
    Unbinder unbinder;

    @Override
    protected int getlayoutID() {

        return R.layout.panda_archives;
    }

    @Override
    protected void init(View view) {

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        pandaArchivesPulltorefresh.setLayoutManager(manager);

        pandaArchivesPulltorefresh.setLoadingMoreEnabled(true);
        pandaArchivesPulltorefresh.setPullRefreshEnabled(true);
        pandaArchivesPulltorefresh.setPullToRefreshListener(this);
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

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
 * 熊猫直播--- 超萌滚滚秀
 */

public class LovelyLive extends BaseFragment implements PullToRefreshListener {
    @BindView(R.id.lovelylive_pulltorefresh)
    PullToRefreshRecyclerView lovelylivePulltorefresh;
    Unbinder unbinder;

    @Override
    protected int getlayoutID() {
        return R.layout.lovelyllive;
    }

    @Override
    protected void init(View view) {

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        lovelylivePulltorefresh.setLayoutManager(manager);

        lovelylivePulltorefresh.setLoadingMoreEnabled(true);
        lovelylivePulltorefresh.setPullRefreshEnabled(true);
        lovelylivePulltorefresh.setPullToRefreshListener(this);
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

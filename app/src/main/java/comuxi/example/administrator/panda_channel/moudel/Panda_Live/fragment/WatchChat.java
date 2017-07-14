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
 * <p>
 * 熊猫直播 ---直播 --- 边看边聊
 */

public class WatchChat extends BaseFragment implements PullToRefreshListener {

    @BindView(R.id.watchchat_pulltorefresh)
    PullToRefreshRecyclerView watchchatPulltorefresh;

    @Override
    protected int getlayoutID() {
        return R.layout.watchchat;
    }

    @Override
    protected void init(View view) {

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        watchchatPulltorefresh.setLayoutManager(manager);

        watchchatPulltorefresh.setLoadingMoreEnabled(true);
        watchchatPulltorefresh.setPullRefreshEnabled(true);
        watchchatPulltorefresh.setPullToRefreshListener(this);
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

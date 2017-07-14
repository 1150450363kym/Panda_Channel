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
 * <p>
 * 熊猫直播 --- 原创新闻
 */

public class Original_news extends BaseFragment implements PullToRefreshListener {
    @BindView(R.id.original_news_pulltorefresh)
    PullToRefreshRecyclerView originalNewsPulltorefresh;
    Unbinder unbinder;

    @Override
    protected int getlayoutID() {
        return R.layout.original_news;
    }

    @Override
    protected void init(View view) {

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        originalNewsPulltorefresh.setLayoutManager(manager);

        originalNewsPulltorefresh.setLoadingMoreEnabled(true);
        originalNewsPulltorefresh.setPullRefreshEnabled(true);
        originalNewsPulltorefresh.setPullToRefreshListener(this);
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

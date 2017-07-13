package comuxi.example.administrator.panda_channel.moudel.GG_TV;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;

import butterknife.BindView;
import comuxi.example.administrator.panda_channel.Base.BaseFragment;
import comuxi.example.administrator.panda_channel.R;

/**
 * Created by Administrator on 2017/7/11.
 */

public class GG_TV_Fragment extends BaseFragment implements PullToRefreshListener {
    @BindView(R.id.login_culture_fragment_pulltorefresh)
    PullToRefreshRecyclerView loginCultureFragmentPulltorefresh;

    @Override
    protected int getlayoutID() {
        return R.layout.login_culture_fragment;
    }

    @Override
    protected void init(View view) {


        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        loginCultureFragmentPulltorefresh.setLayoutManager(manager);
        loginCultureFragmentPulltorefresh.setLoadingMoreEnabled(true);
        loginCultureFragmentPulltorefresh.setPullRefreshEnabled(true);
        loginCultureFragmentPulltorefresh.setPullToRefreshListener(this);
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

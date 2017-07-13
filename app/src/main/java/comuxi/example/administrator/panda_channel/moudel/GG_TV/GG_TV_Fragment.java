package comuxi.example.administrator.panda_channel.moudel.GG_TV;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import comuxi.example.administrator.panda_channel.Base.BaseFragment;
import comuxi.example.administrator.panda_channel.R;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.GG_TV_TextBean;
import comuxi.example.administrator.panda_channel.moudel.GG_TV.adapter.PulltoAdapter;

/**
 * Created by Administrator on 2017/7/11.
 */

public class GG_TV_Fragment extends BaseFragment implements PullToRefreshListener,GG_TV_Contract.View{

    @BindView(R.id.login_culture_fragment_pulltorefresh)
    PullToRefreshRecyclerView loginCultureFragmentPulltorefresh;
    private List<GG_TV_TextBean.ListBean> list;
    private PulltoAdapter adapter;

    GG_TV_Contract.presenter presenter;

    @Override
    protected int getlayoutID() {
        return R.layout.login_culture_fragment;
    }

    @Override
    protected void init(View view) {

        list = new ArrayList<>();
        adapter = new PulltoAdapter(getContext(),list);
        loginCultureFragmentPulltorefresh.setAdapter(adapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        loginCultureFragmentPulltorefresh.setLayoutManager(manager);

        loginCultureFragmentPulltorefresh.setLoadingMoreEnabled(true);
        loginCultureFragmentPulltorefresh.setPullRefreshEnabled(true);
        loginCultureFragmentPulltorefresh.setPullToRefreshListener(this);



    }

    @Override
    protected void loadData() {

        presenter.start();

    }


    @Override
    public void onRefresh() {
        loginCultureFragmentPulltorefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                list.clear();
                loadData();
                adapter.notifyDataSetChanged();
                loginCultureFragmentPulltorefresh.setRefreshComplete();
            }
        },2000);
    }

    @Override
    public void onLoadMore() {

        loginCultureFragmentPulltorefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadData();
                adapter.notifyDataSetChanged();
            }
        },2000);

    }



    @Override
    public void showData(GG_TV_TextBean gg_tv_textBean) {

        list.addAll(gg_tv_textBean.getList());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMsg(String msg) {

    }


    @Override
    public void setPresenter(GG_TV_Contract.presenter presenter) {
        this.presenter = presenter;
    }
}

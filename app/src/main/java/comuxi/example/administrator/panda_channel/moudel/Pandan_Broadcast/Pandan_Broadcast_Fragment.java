package comuxi.example.administrator.panda_channel.moudel.Pandan_Broadcast;

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


/**
 * Created by Administrator on 2017/7/11.
 * 熊猫播报
 */

public class Pandan_Broadcast_Fragment extends BaseFragment implements PullToRefreshListener {

    @BindView(R.id.eyepress_fragment_pullto)
    PullToRefreshRecyclerView eyepressFragmentPullto;
    private List<String> list;
    private PulltoAdapter adapter;
    @Override
    protected int getlayoutID() {
        return R.layout.login_eyepress_fragment;
    }

    @Override
    protected void init(View view) {

        list = new ArrayList<>();
        adapter = new PulltoAdapter(getContext(),list);
        eyepressFragmentPullto.setAdapter(adapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        eyepressFragmentPullto.setLayoutManager(manager);

        eyepressFragmentPullto.setLoadingMoreEnabled(true);
        eyepressFragmentPullto.setPullRefreshEnabled(true);
        eyepressFragmentPullto.setPullToRefreshListener(this);
    }

    @Override
    protected void loadData() {


        for (int i=0;i<10;i++){

            list.add("测试数据"+i);
        }
    }


    @Override
    public void onRefresh() {
        eyepressFragmentPullto.postDelayed(new Runnable() {
            @Override
            public void run() {
                list.clear();
                loadData();
                adapter.notifyDataSetChanged();
                eyepressFragmentPullto.setRefreshComplete();
            }
        },2000);

    }

    @Override
    public void onLoadMore() {
        eyepressFragmentPullto.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadData();
                adapter.notifyDataSetChanged();
            }
        },2000);
    }

}

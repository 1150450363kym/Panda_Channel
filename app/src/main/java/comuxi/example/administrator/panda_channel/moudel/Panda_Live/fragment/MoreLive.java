package comuxi.example.administrator.panda_channel.moudel.Panda_Live.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import comuxi.example.administrator.panda_channel.Base.BaseFragment;
import comuxi.example.administrator.panda_channel.R;
import comuxi.example.administrator.panda_channel.Utils.ACache;
import comuxi.example.administrator.panda_channel.Utils.Log_Utils;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.MoreLiveBean;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.adapter.MoreLiveAdapter;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.contract.MoreLiveContract;


/**
 * Created by lenovo on 2017/7/12.
 * 熊猫直播 ---直播 --- 多视角直播
 */

public class MoreLive extends BaseFragment implements MoreLiveContract.View, MoreLiveAdapter.MyCall {
    @BindView(R.id.morelive_recyclerview)
    RecyclerView moreliveRecyclerview;

    private MoreLiveAdapter adapter;
    private List<MoreLiveBean.ListBean> list;
    MoreLiveContract.presenter presenter;


    @Override
    protected int getlayoutID() {
        return R.layout.morelive;
    }

    @Override
    protected void init(View view) {

        list = new ArrayList<>();
        adapter = new MoreLiveAdapter(list,getContext(),this);
        moreliveRecyclerview.setLayoutManager(new GridLayoutManager(getContext(),3));
        moreliveRecyclerview.setAdapter(adapter);

    }

    @Override
    protected void loadData() {

        presenter.start();

    }


    @Override
    public void showData(MoreLiveBean moreLiveBean) {

        list.addAll(moreLiveBean.getList());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMsg(String msg) {
        ACache aCache =ACache.get(getContext());
        MoreLiveBean bean = (MoreLiveBean) aCache.getAsObject("MoreLiveBean");
        Log_Utils.log_d("TAG",bean.getList().size()+"");
        list.addAll(bean.getList());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(MoreLiveContract.presenter presenter) {

        this.presenter = presenter;
    }

    @Override
    public void setOnItemClickListener(int position) {

        Intent intent = new Intent();
        intent.putExtra("title",list.get(position).getTitle());
        intent.setAction("video_title");
        getContext().sendBroadcast(intent);
    }
}

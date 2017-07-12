package comuxi.example.administrator.panda_channel.moudel.Home.Adapter;


import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.Home_Data_TextBean;

/**
 * Created by Administrator on 2017/7/12.
 */

public class Home_proRecycle_Adapter extends RecyclerView.Adapter {
    FragmentActivity activity;
    ArrayList<Home_Data_TextBean.DataBean> home_data;

    public Home_proRecycle_Adapter(FragmentActivity activity, ArrayList<Home_Data_TextBean.DataBean> home_data) {

        this.activity=activity;
        this.home_data = home_data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

package comuxi.example.administrator.panda_channel.moudel.Home.Adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import comuxi.example.administrator.panda_channel.R;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.Home_Data_TextBean;

/**
 * Created by Administrator on 2017/7/13.
 */

public class Home_Live_Show_Adapter extends RecyclerView.Adapter {
    FragmentActivity activity;
    List<Home_Data_TextBean.DataBean.AreaBean.ListscrollBean> listscroll;
    public Home_Live_Show_Adapter(FragmentActivity activity, List<Home_Data_TextBean.DataBean.AreaBean.ListscrollBean> listscroll) {
        this.activity =activity;
        this.listscroll = listscroll;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(activity).inflate(R.layout.login_home_live__show_item,null);

        return new My_View(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        My_View my_view = (My_View) holder;


        my_view.textView.setText(listscroll.get(position).getTitle());

        Glide.with(activity).load(listscroll.get(position).getImage()).into(my_view.imag);


    }

    @Override
    public int getItemCount() {
        return listscroll.size();
    }

    class My_View extends RecyclerView.ViewHolder {
        private ImageView imag;
        private TextView textView;

        public My_View(View itemView) {
            super(itemView);


            imag = (ImageView) itemView.findViewById(R.id.live_show_image);
            textView = (TextView) itemView.findViewById(R.id.live_show_text);


        }
    }
}

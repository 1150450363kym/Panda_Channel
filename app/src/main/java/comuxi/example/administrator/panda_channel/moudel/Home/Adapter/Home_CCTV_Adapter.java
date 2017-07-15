package comuxi.example.administrator.panda_channel.moudel.Home.Adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import comuxi.example.administrator.panda_channel.R;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.Home_CCTV_TextBean;

/**
 * Created by Administrator on 2017/7/13.
 */

public class Home_CCTV_Adapter extends RecyclerView.Adapter {

    public interface CCTV_live_Onclick{
        void  get_cctv_live(View view,int cctv_postion);
    }
    private CCTV_live_Onclick cctv_live_onclick;

    public void set_China_live_click(CCTV_live_Onclick cctv_live_onclick){
        this.cctv_live_onclick=cctv_live_onclick;
    }



    FragmentActivity activity;
    ArrayList<Home_CCTV_TextBean.ListBean> cctv_array;
    public Home_CCTV_Adapter(FragmentActivity activity, ArrayList<Home_CCTV_TextBean.ListBean> cctv_array) {
        this.activity=activity;
        this.cctv_array=cctv_array;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(activity).inflate(R.layout.login_cctv_recycle_item, null);
        return new My_View(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        My_View my_view = (My_View) holder;

        my_view.textView.setText(cctv_array.get(position).getTitle());
        Glide.with(activity).load(cctv_array.get(position).getImage()).placeholder(R.mipmap.umeng_socialize_share_pic).into(my_view.imag);


        my_view.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cctv_live_onclick.get_cctv_live(v,position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return cctv_array.size();
    }
    class My_View extends RecyclerView.ViewHolder {
        private ImageView imag;
        private TextView textView;

        public My_View(View itemView) {
            super(itemView);

            imag = (ImageView) itemView.findViewById(R.id.live_cctv_image);
            textView = (TextView) itemView.findViewById(R.id.live_cctv_text);

        }
    }
}

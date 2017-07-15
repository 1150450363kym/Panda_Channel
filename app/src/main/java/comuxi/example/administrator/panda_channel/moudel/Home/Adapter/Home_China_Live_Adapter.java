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

public class Home_China_Live_Adapter extends RecyclerView.Adapter {

    public interface China_live_Onclick{
        void  get_china_live(View view,int great_postion);
    }
    private China_live_Onclick china_live_onclick;

    public void set_China_live_click(China_live_Onclick china_live_onclick){
        this.china_live_onclick=china_live_onclick;
    }





    FragmentActivity activity;
    List<Home_Data_TextBean.DataBean.ChinaliveBean.ListBeanXX> list;

    public Home_China_Live_Adapter(FragmentActivity activity, List<Home_Data_TextBean.DataBean.ChinaliveBean.ListBeanXX> list) {

        this.activity = activity;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(activity).inflate(R.layout.login_home_live__show_item, null);


        return new My_View(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        My_View my_view = (My_View) holder;

        my_view.textView.setText(list.get(position).getTitle());
        Glide.with(activity).load(list.get(position).getImage()).placeholder(R.mipmap.umeng_socialize_share_pic).into(my_view.imag);

            int number=Integer.parseInt(list.get(position).getOrder());
            if(number==1||number==4||number==7) {
                ViewGroup.LayoutParams lp=my_view.yuan.getLayoutParams();
                lp.height=40;
                lp.width=40;
                my_view.yuan.setLayoutParams(lp);
            }
        my_view.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                china_live_onclick.get_china_live(v, position);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class My_View extends RecyclerView.ViewHolder {
        private ImageView imag;
        private TextView textView;
        private ImageView  yuan;
        public My_View(View itemView) {
            super(itemView);

            imag = (ImageView) itemView.findViewById(R.id.live_show_image);
            textView = (TextView) itemView.findViewById(R.id.live_show_text);
//
            yuan = (ImageView) itemView.findViewById(R.id.live_red_yuan);
//            int number=Integer.parseInt(listscroll.get(position).getOrder());
//            if(number==1||number==4||number==7) {
//                ViewGroup.LayoutParams lp=my_view.yuan.getLayoutParams();
//                lp.height=30;
//                lp.width=30;
//                my_view.yuan.setLayoutParams(lp);
//            }
        }
    }
}

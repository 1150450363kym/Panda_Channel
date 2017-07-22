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
 * <p>
 * 熊猫观察的Adapter
 */
//
public class Home_Live_Show_Adapter extends RecyclerView.Adapter {

    public interface panda_live_Onclick{
        void  get_panda_live(View view,int panda_postion);
    }
    private panda_live_Onclick panda_live_onclick;

    public void set_panda_live_click(panda_live_Onclick panda_live_onclick){
        this.panda_live_onclick=panda_live_onclick;
    }



    FragmentActivity activity;
    List<Home_Data_TextBean.DataBean.PandaliveBean.ListBean> listscroll;
    List<Home_Data_TextBean.DataBean.WallliveBean.ListBeanX> listBeanXes;
    public Home_Live_Show_Adapter(FragmentActivity activity, List<Home_Data_TextBean.DataBean.PandaliveBean.ListBean> list) {
        this.activity = activity;
        this.listscroll = list;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(activity).inflate(R.layout.login_home_live__show_item, null);

        return new My_View(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        My_View my_view = (My_View) holder;

        my_view.textView.setText(listscroll.get(position).getTitle());
        Glide.with(activity).load(listscroll.get(position).getImage()).placeholder(R.mipmap.umeng_socialize_share_pic).into(my_view.imag);

        my_view.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                panda_live_onclick.get_panda_live(v,position);
            }
        });

        int number=Integer.parseInt(listscroll.get(position).getOrder());
        if(number==1||number==4) {
            ViewGroup.LayoutParams lp=my_view.yuan.getLayoutParams();
            lp.height=40;
            lp.width=40;
            my_view.yuan.setLayoutParams(lp);
        }


    }

    @Override
    public int getItemCount() {



        return listscroll.size();
    }

    class My_View extends RecyclerView.ViewHolder {
        private ImageView imag;
        private TextView textView;
        private ImageView  yuan;

        public My_View(View itemView) {
            super(itemView);

            imag = (ImageView) itemView.findViewById(R.id.live_show_image);
            textView = (TextView) itemView.findViewById(R.id.live_show_text);
            yuan = (ImageView) itemView.findViewById(R.id.live_red_yuan);


        }
    }






}

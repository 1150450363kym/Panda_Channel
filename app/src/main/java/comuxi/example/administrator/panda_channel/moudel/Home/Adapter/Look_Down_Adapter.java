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
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.Look_Down_Text;

/**
 * Created by Administrator on 2017/7/13.
 * 熊猫观察 的Adapter
 */

public class Look_Down_Adapter extends RecyclerView.Adapter {
    public interface Look_dow_Onclick {

        void  get_look_dow_Onclick(View view, int lok_down_postion);

    }

    private Look_dow_Onclick look_dow_onclick;

    public void set_Look_dow_getOnclick(Look_dow_Onclick look_dow_onclick) {
        this.look_dow_onclick = look_dow_onclick;

    }


    FragmentActivity activity;
    ArrayList<Look_Down_Text.ListBean> look_down_array;

    public Look_Down_Adapter(FragmentActivity activity, ArrayList<Look_Down_Text.ListBean> look_down_array) {
        this.activity = activity;
        this.look_down_array = look_down_array;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(activity).inflate(R.layout.home_panda_look_down_item, null);


        return new My_View(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        My_View my_view = (My_View) holder;

        my_view.title.setText(look_down_array.get(position).getTitle());
        my_view.data.setText(look_down_array.get(position).getDaytime());
        my_view.time.setText(look_down_array.get(position).getVideoLength());
        Glide.with(activity).load(look_down_array.get(position).getImage()).placeholder(R.mipmap.umeng_socialize_share_pic).into(my_view.imageView);

        my_view.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                look_dow_onclick.get_look_dow_Onclick(v,position);

            }
        });




    }

    @Override
    public int getItemCount() {
        return look_down_array.size();
    }

    class My_View extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView title, data, time;

        public My_View(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.look_down_image);

            title = (TextView) itemView.findViewById(R.id.look_down_title);
            data = (TextView) itemView.findViewById(R.id.look_down_data);
            time = (TextView) itemView.findViewById(R.id.movie_time);


        }
    }
}

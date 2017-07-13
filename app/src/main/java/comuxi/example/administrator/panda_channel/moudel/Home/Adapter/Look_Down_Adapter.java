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
    FragmentActivity activity;
    ArrayList<Look_Down_Text.ListBean> look_down_array;
    public Look_Down_Adapter(FragmentActivity activity, ArrayList<Look_Down_Text.ListBean> look_down_array) {
        this.activity=activity;
        this.look_down_array = look_down_array;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(activity).inflate(R.layout.home_panda_look_down_item,null);


        return new My_View(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        My_View my_view = (My_View) holder;

        my_view.title.setText(look_down_array.get(position).getTitle());
        my_view.data.setText(look_down_array.get(position).getDaytime());

        Glide.with(activity).load(look_down_array.get(position).getImage()).placeholder(R.mipmap.umeng_socialize_share_pic).into(my_view.imageView);

    }

    @Override
    public int getItemCount() {
        return look_down_array.size();
    }
    class My_View extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView title,data;
        public My_View(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.look_down_image);

            title = (TextView) itemView.findViewById(R.id.look_down_title);
            data= (TextView) itemView.findViewById(R.id.look_down_data);

        }
    }
}

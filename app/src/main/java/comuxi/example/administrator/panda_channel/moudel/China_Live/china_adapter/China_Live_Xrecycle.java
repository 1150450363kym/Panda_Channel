package comuxi.example.administrator.panda_channel.moudel.China_Live.china_adapter;

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
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.Chian_item_Path_TextBean;

/**
 * Created by Administrator on 2017/7/17.
 */
////
public class China_Live_Xrecycle extends RecyclerView.Adapter {
    FragmentActivity activity;
    ArrayList<Chian_item_Path_TextBean.LiveBean> list_array;

    public China_Live_Xrecycle(FragmentActivity activity, ArrayList<Chian_item_Path_TextBean.LiveBean> list_array) {
        this.activity = activity;
        this.list_array = list_array;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(activity).inflate(R.layout.path_fragment_item, null);

        return new My_View(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final My_View my_view = (My_View) holder;

        my_view.title.setText(list_array.get(position).getTitle());
        my_view.content.setText(list_array.get(position).getBrief());

        Glide.with(activity).load(list_array.get(position).getImage()).placeholder(R.mipmap.umeng_socialize_share_pic).into(my_view.imag);


        my_view.down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                my_view.down.setVisibility(View.GONE);
                my_view.up.setVisibility(View.VISIBLE);
                my_view.content.setVisibility(View.VISIBLE);

            }
        });

        my_view.up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                my_view.down.setVisibility(View.VISIBLE);
                my_view.up.setVisibility(View.GONE);
                my_view.content.setVisibility(View.GONE);



            }
        });




    }

    @Override
    public int getItemCount() {
        return list_array.size();
    }

    class My_View extends RecyclerView.ViewHolder {

        private ImageView imag, down, up;
        private TextView title, content;

        public My_View(View itemView) {
            super(itemView);

            imag = (ImageView) itemView.findViewById(R.id.china_live_image);
            down = (ImageView) itemView.findViewById(R.id.china_live_detail_down);
            up = (ImageView) itemView.findViewById(R.id.china_live_detail_up);
            title = (TextView) itemView.findViewById(R.id.china_live_id);
            content = (TextView) itemView.findViewById(R.id.china_live_content);

        }
    }


}

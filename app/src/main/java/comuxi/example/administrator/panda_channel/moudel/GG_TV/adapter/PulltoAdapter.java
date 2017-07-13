package comuxi.example.administrator.panda_channel.moudel.GG_TV.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;

import java.util.List;

import comuxi.example.administrator.panda_channel.R;
import comuxi.example.administrator.panda_channel.VideoplayerActivity;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.GG_TV_TextBean;

/**
 * Created by lenovo on 2017/7/13.
 */

public class PulltoAdapter extends BaseAdapter<GG_TV_TextBean.ListBean> {


    private MyCall myCall;
    public PulltoAdapter(Context context, List<GG_TV_TextBean.ListBean> datas) {
        super(context, R.layout.item_pandalive_pullto, datas);



    }


    @Override
    public void convert(ViewHolder holder, GG_TV_TextBean.ListBean listBean) {

        holder.setText(R.id.item_pandalive_pullto_title,listBean.getTitle());
        holder.setText(R.id.item_pandalive_pullto_time,listBean.getBrief());
        holder.setText(R.id.item_pandalive_pullto_video_time,listBean.getVideoLength());
        ImageView imageView = (ImageView) holder.itemView.findViewById(R.id.item_pandalive_pullto_img);
        Glide.with(context).load(listBean.getImage()).into(imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,VideoplayerActivity.class);
                context.startActivity(intent);
            }
        });
    }


    interface MyCall{
        void setOnItemClintLinstener(int position);
    }
}

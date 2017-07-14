package comuxi.example.administrator.panda_channel.moudel.Panda_Live.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;

import java.util.List;

import comuxi.example.administrator.panda_channel.R;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.WonderfulOneBean;

/**
 * Created by lenovo on 2017/7/13.
 */

public class WinderfulonePulltoAdapter extends BaseAdapter<WonderfulOneBean.VideoBean> {


    public WinderfulonePulltoAdapter(Context context, List<WonderfulOneBean.VideoBean> datas) {
        super(context, R.layout.item_pandalive_pullto, datas);

    }

    @Override
    public void convert(ViewHolder holder, WonderfulOneBean.VideoBean listBean) {
        holder.setText(R.id.item_pandalive_pullto_title,listBean.getT());
        holder.setText(R.id.item_pandalive_pullto_time,listBean.getPtime());
        holder.setText(R.id.item_pandalive_pullto_video_time,listBean.getLen());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ImageView imageView = (ImageView) holder.itemView.findViewById(R.id.item_pandalive_pullto_img);
        Glide.with(context).load(listBean.getImg()).into(imageView);
    }
}

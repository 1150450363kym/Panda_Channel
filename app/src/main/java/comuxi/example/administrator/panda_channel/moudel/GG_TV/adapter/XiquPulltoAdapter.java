package comuxi.example.administrator.panda_channel.moudel.GG_TV.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;

import java.util.List;

import comuxi.example.administrator.panda_channel.R;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.XiquaimationBean;

/**
 * Created by lenovo on 2017/7/13.
 */

public class XiquPulltoAdapter extends BaseAdapter<XiquaimationBean.VideoBean> {


    public XiquPulltoAdapter(Context context, List<XiquaimationBean.VideoBean> datas) {
        super(context, R.layout.item_pandalive_pullto, datas);

    }


    @Override
    public void convert(ViewHolder holder, final XiquaimationBean.VideoBean videoBean) {

        holder.setText(R.id.item_pandalive_pullto_title,videoBean.getT());
//        holder.setText(R.id.item_pandalive_pullto_time,videoBean.getPtime());
        holder.setText(R.id.item_pandalive_pullto_video_time,videoBean.getLen());
        ImageView imageView = (ImageView) holder.itemView.findViewById(R.id.item_pandalive_pullto_img);
        Glide.with(context).load(videoBean.getImg()).into(imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, videoBean.getT(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

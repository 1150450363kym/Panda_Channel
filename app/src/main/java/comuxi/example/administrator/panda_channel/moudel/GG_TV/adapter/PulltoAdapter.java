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
import comuxi.example.administrator.panda_channel.Utils.ACache;
import comuxi.example.administrator.panda_channel.VideoplayerActivity;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.GG_TV_TextBean;
import comuxi.example.administrator.panda_channel.moudel.GG_TV.PandaThingsActivity;

/**
 * Created by lenovo on 2017/7/13.
 */

public class PulltoAdapter extends BaseAdapter<GG_TV_TextBean.ListBean> {


    public PulltoAdapter(Context context, List<GG_TV_TextBean.ListBean> datas) {
        super(context, R.layout.item_pandalive_pullto, datas);

    }
    @Override
    public void convert(ViewHolder holder, final GG_TV_TextBean.ListBean listBean) {

        holder.setText(R.id.item_pandalive_pullto_title,listBean.getTitle());
        holder.setText(R.id.item_pandalive_pullto_time,listBean.getBrief());
        holder.setText(R.id.item_pandalive_pullto_video_time,listBean.getVideoLength());
        ImageView imageView = (ImageView) holder.itemView.findViewById(R.id.item_pandalive_pullto_img);
        Glide.with(context).load(listBean.getImage()).into(imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //缓存
                ACache aCache = ACache.get(context);
                aCache.put("shoucang",listBean);

                if(listBean.getOrder().equals("1")){
                    Intent intent = new Intent(context,PandaThingsActivity.class);
                    intent.putExtra("top_title",listBean.getTitle());
                    context.startActivity(intent);
                }else{

                    Intent intent = new Intent(context,VideoplayerActivity.class);

                    intent.putExtra("pid",listBean.getId());
                    intent.putExtra("video_title",listBean.getTitle());
                    intent.putExtra("video_imag",listBean.getImage());
                    context.startActivity(intent);
                }
            }
        });
    }

}

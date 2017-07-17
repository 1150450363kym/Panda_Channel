package comuxi.example.administrator.panda_channel.moudel.Pandan_Broadcast;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;

import java.util.List;

import comuxi.example.administrator.panda_channel.R;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.PandaBroadCastListBean;

/**
 * Created by lenovo on 2017/7/13.
 */

public class PulltoAdapter extends BaseAdapter<PandaBroadCastListBean.ListBean> {


    public PulltoAdapter(Context context, List<PandaBroadCastListBean.ListBean> datas) {
        super(context, R.layout.item_pandalive_pullto, datas);
    }

    @Override
    public void convert(ViewHolder holder, final PandaBroadCastListBean.ListBean listBean) {

        holder.setText(R.id.item_pandalive_pullto_title,listBean.getTitle());
        holder.setText(R.id.item_pandabroadcast_pullto_time,listBean.getVideolength());
        ImageView img = (ImageView) holder.itemView.findViewById(R.id.item_pandalive_pullto_img);
        Glide.with(context).load(listBean.getPicurl()).into(img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context,listBean.getTitle(),Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(context, WebActivity.class);
//                intent.putExtra("url",listBean.getUrl());
//                context.startActivity(intent);

            }
        });
    }
}

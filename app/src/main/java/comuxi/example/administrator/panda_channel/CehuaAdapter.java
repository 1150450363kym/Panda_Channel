package comuxi.example.administrator.panda_channel;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;

import java.util.List;

import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.CehuaBean;

/**
 * Created by lenovo on 2017/7/15.
 * /qqqq
 */

public class CehuaAdapter extends BaseAdapter<CehuaBean.InteractiveBean> {


    public CehuaAdapter(Context context,List datas) {
        super(context, R.layout.cehua_item, datas);
    }


    @Override
    public void convert(ViewHolder holder, final CehuaBean.InteractiveBean interactiveBean) {

        holder.setText(R.id.cehua_item_tv,interactiveBean.getTitle());
        ImageView img = (ImageView) holder.itemView.findViewById(R.id.cehua_item_img);
        Glide.with(context).load(interactiveBean.getImage()).into(img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,interactiveBean.getTitle(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context,WebActivity.class);
                intent.putExtra("url",interactiveBean.getUrl());
                context.startActivity(intent);
            }
        });
    }
}

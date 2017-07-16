package comuxi.example.administrator.panda_channel;

import android.content.Context;
import android.widget.ImageView;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;

import java.util.List;

import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.CehuaBean;

/**
 * Created by lenovo on 2017/7/15.
 */

public class CehuaAdapter extends BaseAdapter<CehuaBean.InteractiveBean> {


    public CehuaAdapter(Context context,List datas) {
        super(context, R.layout.cehua_item, datas);
    }


    @Override
    public void convert(ViewHolder holder, CehuaBean.InteractiveBean interactiveBean) {

        holder.setText(R.id.cehua_item_tv,interactiveBean.getTitle());
        ImageView img = (ImageView) holder.itemView.findViewById(R.id.cehua_item_img);
        Glide.with(context).load(interactiveBean.getImage()).into(img);
    }
}

package comuxi.example.administrator.panda_channel.moudel.Pandan_Broadcast;

import android.content.Context;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;

import java.util.List;

import comuxi.example.administrator.panda_channel.R;

/**
 * Created by lenovo on 2017/7/13.
 */

public class PulltoAdapter extends BaseAdapter<String> {


    public PulltoAdapter(Context context, List<String> datas) {
        super(context, R.layout.item_pandalive_pullto, datas);
    }

    @Override
    public void convert(ViewHolder holder, String s) {
        holder.setText(R.id.item_pandalive_pullto_title,s);
    }
}

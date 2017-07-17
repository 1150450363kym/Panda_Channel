package comuxi.example.administrator.panda_channel.moudel.Panda_Live.adapter;

import android.content.Context;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;

import java.util.List;

import comuxi.example.administrator.panda_channel.R;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.WatchChatBean;

/**
 * Created by lenovo on 2017/7/13.
 */

public class WatchPulltoAdapter extends BaseAdapter<WatchChatBean.Bean> {


    public WatchPulltoAdapter(Context context, List<WatchChatBean.Bean> datas) {
        super(context, R.layout.watch_item, datas);

    }


    @Override
    public void convert(ViewHolder holder, WatchChatBean.Bean bean) {

        holder.setText(R.id.watch_item_netfriends,bean.getAuthor());
        holder.setText(R.id.watch_item_numlou,bean.getTid()+"楼");
        holder.setText(R.id.watch_item_content,bean.getMessage());
//        holder.setText(R.id.watch_item_time,bean.getDateline());
    }
}

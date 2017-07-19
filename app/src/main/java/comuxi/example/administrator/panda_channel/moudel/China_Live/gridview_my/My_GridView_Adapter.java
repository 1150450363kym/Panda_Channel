package comuxi.example.administrator.panda_channel.moudel.China_Live.gridview_my;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import comuxi.example.administrator.panda_channel.R;

/**
 * Created by Administrator on 2017/7/19.
 */

public class My_GridView_Adapter extends BaseAdapter {

    private Context context;
    public ArrayList<String> channels;

    public My_GridView_Adapter(Activity activity, ArrayList<String> channels) {
        context = activity;
        this.channels = channels;
    }
    @Override
    public int getCount() {
        return channels.size();
    }

    @Override
    public Object getItem(int position) {
        return channels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.gridview_item, null);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.gridview_item_content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(channels.get(position));
        return convertView;
    }
    public void swap(int i, int j) {
        if (j < i) {
            String s = channels.get(i);
            channels.add(j, s);
            channels.remove(i + 1);
        } else if (i < j) {
            String s = channels.get(i);
            channels.add(j + 1, s);
            channels.remove(i);
        }
    }

    static class ViewHolder {
        public TextView textView;
    }
}

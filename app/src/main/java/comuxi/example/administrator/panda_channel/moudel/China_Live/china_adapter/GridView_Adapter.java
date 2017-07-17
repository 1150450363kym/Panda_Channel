package comuxi.example.administrator.panda_channel.moudel.China_Live.china_adapter;

import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import comuxi.example.administrator.panda_channel.R;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.China_Live_Path_TextBean;

/**
 * Created by Administrator on 2017/7/17.
 */

public class GridView_Adapter extends BaseAdapter {
    FragmentActivity activity;
    ArrayList<China_Live_Path_TextBean.TablistBean> tablistBeen_array;
    public GridView_Adapter(FragmentActivity activity, ArrayList<China_Live_Path_TextBean.TablistBean> tablistBeen_array) {
        this.activity=activity;
        this.tablistBeen_array=tablistBeen_array;
    }

    @Override
    public int getCount() {
        return tablistBeen_array.size();
    }

    @Override
    public Object getItem(int position) {
        return tablistBeen_array.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        My_view my_view = null;
        if(convertView==null){
            convertView = LayoutInflater.from(activity).inflate(R.layout.gridview_item,null);

            my_view= new My_view();

            my_view.content = (TextView) convertView.findViewById(R.id.gridview_item_content);


            convertView.setTag(my_view);

        }else{
            my_view= (My_view) convertView.getTag();
        }

        my_view.content.setText(tablistBeen_array.get(position).getTitle());




        return convertView;
    }


    class My_view{
    private TextView content;

    }

}

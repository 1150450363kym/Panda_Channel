package comuxi.example.administrator.panda_channel.moudel.China_Live.china_adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;

import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.China_Live_Path_TextBean;

/**
 * Created by Administrator on 2017/7/17.
 */
////////////
public class China_Tablayout_PageAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> fargmet_array;
    ArrayList<China_Live_Path_TextBean.TablistBean> tablistBeen_array;



    public China_Tablayout_PageAdapter(FragmentManager fm, ArrayList<Fragment> fargmet_array, ArrayList<China_Live_Path_TextBean.TablistBean> tablistBeen_array) {
        super(fm);
        this.fargmet_array = fargmet_array;
        this.tablistBeen_array = tablistBeen_array;

        Log.e("TAG","点击删除 或者增加之后  Tablayout的  长度"+fargmet_array.size());

    }

    @Override
    public Fragment getItem(int position) {
        return fargmet_array.get(position);
    }

    @Override
    public int getCount() {
        return fargmet_array.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Log.e("TAG","点击删除 或者增加之后  Tablayout的  长度"+tablistBeen_array.size());
        return tablistBeen_array.get(position).getTitle();
    }


}

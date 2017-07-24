package comuxi.example.administrator.panda_channel.adapter_activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/7/24.
 * 我的收藏
 */

public class My_Collecttion_View_Adapter extends FragmentPagerAdapter {
    ArrayList<Fragment> arrayList;
    String[]  arr = {"直播","精彩看点"};
    public My_Collecttion_View_Adapter(FragmentManager fm, ArrayList<Fragment> arrayList) {
        super(fm);
        this.arrayList = arrayList;
    }

    @Override
    public Fragment getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return arr[position];

    }
}

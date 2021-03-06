package comuxi.example.administrator.panda_channel.moudel.Panda_Live.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by lenovo on 2017/7/12.
 */

public class RegistAdapter extends FragmentPagerAdapter {

    private List<Fragment> list;
    private String[] title = {"手机注册","邮箱注册"};

    public RegistAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}

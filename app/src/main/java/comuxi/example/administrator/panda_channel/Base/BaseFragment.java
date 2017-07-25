package comuxi.example.administrator.panda_channel.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/7/11.
 * Fragment基类
 */

public abstract class BaseFragment extends Fragment {
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(getlayoutID(), null);

        unbinder = ButterKnife.bind(this, view);

        init(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        loadData();

    }


    public void  setBundle (Bundle bundle){

    }



    //    加载布局的
    protected abstract int getlayoutID();

    //    初始化数据的
    protected abstract void init(View view);

    //    加载网络数据请求的
    protected abstract void loadData();

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(getContext());       //统计时长
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(getContext());
    }


}

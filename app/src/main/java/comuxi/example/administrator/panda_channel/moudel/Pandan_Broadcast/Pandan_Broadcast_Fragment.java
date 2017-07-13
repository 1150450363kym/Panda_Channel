package comuxi.example.administrator.panda_channel.moudel.Pandan_Broadcast;

import android.view.View;

import com.androidkun.PullToRefreshRecyclerView;

import butterknife.BindView;
import comuxi.example.administrator.panda_channel.Base.BaseFragment;
import comuxi.example.administrator.panda_channel.R;

/**
 * Created by Administrator on 2017/7/11.
 */

public class Pandan_Broadcast_Fragment extends BaseFragment {

    @BindView(R.id.eyepress_fragment_pullto)
    PullToRefreshRecyclerView eyepressFragmentPullto;

    @Override
    protected int getlayoutID() {
        return R.layout.login_eyepress_fragment;
    }

    @Override
    protected void init(View view) {

    }

    @Override
    protected void loadData() {

    }


}

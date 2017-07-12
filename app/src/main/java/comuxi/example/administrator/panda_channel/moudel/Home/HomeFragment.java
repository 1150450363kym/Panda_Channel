package comuxi.example.administrator.panda_channel.moudel.Home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import comuxi.example.administrator.panda_channel.Base.BaseFragment;
import comuxi.example.administrator.panda_channel.R;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.Home_Data_TextBean;
import comuxi.example.administrator.panda_channel.moudel.Home.Adapter.Home_proRecycle_Adapter;

/**
 * Created by Administrator on 2017/7/11.
 */

public class HomeFragment extends BaseFragment implements HomeContract.View {

    HomeContract.presenter home_present;

    Unbinder unbinder;
    @BindView(R.id.my_xrecycle_home)
    XRecyclerView myXrecycleHome;
    //        请求到的 首页所有的 数据
    private ArrayList<Home_Data_TextBean.DataBean> home_data = new ArrayList<>();


    private Home_proRecycle_Adapter home_adapter;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 300:


                    break;
            }


        }
    };


    @Override
    protected int getlayoutID() {
        return R.layout.login_home_fragment;
    }

    @Override
    protected void init(View view) {


    }


    @Override
    protected void loadData() {
        home_present.start();


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myXrecycleHome.setLayoutManager(layoutManager);

        home_adapter = new Home_proRecycle_Adapter(getActivity(), home_data);
        myXrecycleHome.setAdapter(home_adapter);


    }

    @Override
    public void shouProgress() {

    }

    @Override
    public void dissProgress() {

    }

    //展示轮播图
    @Override
    public void showCarousel(Home_Data_TextBean data_textBean) {

//        请求到的 首页所有的 数据
        Home_Data_TextBean.DataBean data = data_textBean.getData();
        home_data.add(data);


        handler.sendEmptyMessage(300);

//        List<Home_Data_TextBean.DataBean.BigImgBean> bigImg = data_textBean.getData().getBigImg();
//        for (int i = 0; i < bigImg.size(); i++) {
//            Log.e("TAG", "打印——首页——轮播图片——地址" + bigImg.get(i).getImage());
//
//        }

    }

    @Override
    public void showMessage(String s) {

    }


    @Override
    public void setPresenter(HomeContract.presenter presenter) {
        this.home_present = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

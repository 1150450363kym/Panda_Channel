package comuxi.example.administrator.panda_channel.moudel.Home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import comuxi.example.administrator.panda_channel.Base.BaseFragment;
import comuxi.example.administrator.panda_channel.R;
import comuxi.example.administrator.panda_channel.VideoplayerActivity;
import comuxi.example.administrator.panda_channel.app.App;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.Home_CCTV_TextBean;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.Home_China_Movie_Text;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.Home_Data_TextBean;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.Look_Down_Text;
import comuxi.example.administrator.panda_channel.moudel.Home.Adapter.Home_proRecycle_Adapter;
import comuxi.example.administrator.panda_channel.web_view.Home_Web_View_;

/**
 * Created by Administrator on 2017/7/11.
 */

public class HomeFragment extends BaseFragment implements HomeContract.View {

    HomeContract.presenter home_present;

    Unbinder unbinder;
    @BindView(R.id.my_xrecycle_home)
    XRecyclerView myXrecycleHome;
    //        请求到的 首页所有的 数据
    private ArrayList<Object> home_data_object = new ArrayList<>();
    private ArrayList<Home_Data_TextBean.DataBean> home_data = new ArrayList<>();

    private Home_proRecycle_Adapter home_adapter;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 300:

                    home_adapter.notifyDataSetChanged();
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

    }

    @Override
    public void showMessage(String s) {

    }

    //    设置数据
    @Override
    public void setResult(Home_Data_TextBean data_textBean) {
        Home_Data_TextBean.DataBean data = data_textBean.getData();

        home_data_object.clear();
        home_data_object.add(data.getBigImg().get(0));
        home_data_object.add(data.getArea());
        home_data_object.add(data.getPandaeye());
        home_data_object.add(data.getPandalive());
        home_data_object.add(data.getWalllive());
        home_data_object.add(data.getChinalive());
        home_data_object.add(data.getInteractive());
        home_data_object.add(data.getCctv());
        home_data_object.add(data.getList().get(0));

        home_data.add(data);

        handler.sendEmptyMessage(300);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myXrecycleHome.setLayoutManager(layoutManager);
        home_adapter = new Home_proRecycle_Adapter(getActivity(), home_data_object, home_data);
        myXrecycleHome.setAdapter(home_adapter);

        myXrecycleHome.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                home_adapter.notifyDataSetChanged();
                myXrecycleHome.refreshComplete();

            }

            @Override
            public void onLoadMore() {


            }
        });
        myXrecycleHome.setLoadingMoreEnabled(false);


        home_adapter.set_wonderful_Click(new Home_proRecycle_Adapter.x_Recy_Onclick() {

            //           轮播图的点击  事件
            @Override
            public void get_Ratation_Click(View v, Home_Data_TextBean.DataBean.BigImgBean bigImgBean) {
                if (bigImgBean.getOrder().equals("1")) {

                    Intent web_intent = new Intent(App.content, Home_Web_View_.class);
                    web_intent.putExtra("web_view_url", bigImgBean.getUrl());
                    startActivity(web_intent);

                } else {

                    Intent Video_intent = new Intent(App.content, VideoplayerActivity.class);
                    Video_intent.putExtra("pid", bigImgBean.getPid());
                    Video_intent.putExtra("video_title", bigImgBean.getTitle());
                    Video_intent.putExtra("video_imag", bigImgBean.getImage());
                    startActivity(Video_intent);

                }


            }

            @Override
//            精彩推荐的 点击事件
            public void get_wonderful_Click(Home_Data_TextBean.DataBean.AreaBean.ListscrollBean home_data) {
                Intent Video_intent = new Intent(App.content, VideoplayerActivity.class);
                Video_intent.putExtra("pid", home_data.getPid());
                Video_intent.putExtra("video_title", home_data.getTitle());
                Video_intent.putExtra("video_imag", home_data.getImage());
                startActivity(Video_intent);
            }

            //        熊猫观察的点击
            @Override
            public void get_pandan_loog_Click(View look_view, Home_Data_TextBean.DataBean.PandaeyeBean.ItemsBean itemsBean) {
                Intent Video_intent = new Intent(App.content, VideoplayerActivity.class);
                Video_intent.putExtra("pid", itemsBean.getPid());
                Video_intent.putExtra("video_title", itemsBean.getTitle());
                startActivity(Video_intent);
            }

            @Override
            public void get_pandan_loog_second_Click(View look_view, Home_Data_TextBean.DataBean.PandaeyeBean.ItemsBean second_itemsBean) {
                Intent Video_intent = new Intent(App.content, VideoplayerActivity.class);
                Video_intent.putExtra("pid", second_itemsBean.getPid());
                Video_intent.putExtra("video_title", second_itemsBean.getTitle());
                startActivity(Video_intent);


            }

            @Override
            public void get_pandan_look_down_Click(Look_Down_Text.ListBean look_down_text) {
                Intent Video_intent = new Intent(App.content, VideoplayerActivity.class);
                Video_intent.putExtra("pid", look_down_text.getPid());
                Video_intent.putExtra("video_title", look_down_text.getTitle());
                Video_intent.putExtra("video_imag", look_down_text.getImage());
                startActivity(Video_intent);


            }

            //熊猫直播
            @Override
            public void get_Panda_live_Click(Home_Data_TextBean.DataBean.PandaliveBean.ListBean pandalivebean) {
                Toast.makeText(App.content, "" + pandalivebean.getTitle(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void get_great_live_Click(Home_Data_TextBean.DataBean.WallliveBean.ListBeanX listBeanX) {
                Toast.makeText(App.content, "" + listBeanX.getTitle(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void get_china_live_Click(Home_Data_TextBean.DataBean.ChinaliveBean.ListBeanXX listBeanXX) {
                Toast.makeText(App.content, "" + listBeanXX.getTitle(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void get_special_planning_Click(View v, Home_Data_TextBean.DataBean.InteractiveBean.InteractiveoneBean interactiveoneBean) {

                Intent special_intent = new Intent(App.content, Home_Web_View_.class);
                special_intent.putExtra("special_url", interactiveoneBean.getUrl());
                startActivity(special_intent);

            }

            @Override
            public void get_cctv_live_Click(Home_CCTV_TextBean.ListBean listBean) {
                Intent Video_intent = new Intent(App.content, VideoplayerActivity.class);
                Video_intent.putExtra("pid", listBean.getPid());
                Log.e("TAG","首页pid"+listBean.getPid());
                Video_intent.putExtra("video_title", listBean.getTitle());
                Video_intent.putExtra("video_imag", listBean.getImage());
                startActivity(Video_intent);
            }

            @Override
            public void get_movie_live_Click(Home_China_Movie_Text.ListBean listBean) {
                Intent Video_intent = new Intent(App.content, VideoplayerActivity.class);
                Video_intent.putExtra("pid", listBean.getPid());
                Video_intent.putExtra("video_title", listBean.getTitle());
                Video_intent.putExtra("video_imag", listBean.getImage());
                startActivity(Video_intent);
            }
        });

    }

    //熊猫播报方法
    @Override
    public void showPandaBroadcast(Home_Data_TextBean data_textBean) {


    }

    //展示xRecycleView
    @Override
    public void showXrecycleView(final Home_Data_TextBean data_textBean) {


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

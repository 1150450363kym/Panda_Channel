package comuxi.example.administrator.panda_channel.moudel.GG_TV;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import comuxi.example.administrator.panda_channel.Base.BaseFragment;
import comuxi.example.administrator.panda_channel.R;
import comuxi.example.administrator.panda_channel.Utils.ACache;
import comuxi.example.administrator.panda_channel.Utils.Log_Utils;
import comuxi.example.administrator.panda_channel.WebActivity;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.GG_TV_TextBean;
import comuxi.example.administrator.panda_channel.moudel.GG_TV.adapter.PulltoAdapter;
import comuxi.example.administrator.panda_channel.moudel.Home.Adapter.Home_page_Rotation;

/**
 * Created by Administrator on 2017/7/11.
 */

public class GG_TV_Fragment extends BaseFragment implements PullToRefreshListener,GG_TV_Contract.View,ViewPager.OnPageChangeListener{

    @BindView(R.id.login_culture_fragment_pulltorefresh)
    PullToRefreshRecyclerView loginCultureFragmentPulltorefresh;
    private List<GG_TV_TextBean.ListBean>  list = new ArrayList<>();;
    private PulltoAdapter adapter;
    private ArrayList<GG_TV_TextBean.BigImgBean> bigbim_array = new ArrayList<>();
    private ArrayList<View> rotation_array  = new ArrayList<>();
    private   View topviewpager;
    private LinearLayout point_ratio=null;
    private ImageView imag;
    private TextView textView;
    private Home_page_Rotation home_page_rotation;
    private boolean time_flg  = false;
    private Timer timer;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 300:

                    pager.setCurrentItem(pager.getCurrentItem() + 1);

                    break;
                case 400:
                    adapter.notifyDataSetChanged();


                    break;
                case 555:
                    login_home_rotation();
                    break;

            }
        }
    };

    GG_TV_Contract.presenter presenter;
    private ViewPager pager;
    @Override
    protected int getlayoutID() {
        return R.layout.login_culture_fragment;
    }

    @Override
    protected void init(View view) {


        Log_Utils.log_d("TAG",GG_TV_Fragment.class.getSimpleName());
        adapter = new PulltoAdapter(getContext(),list);
        loginCultureFragmentPulltorefresh.setAdapter(adapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        loginCultureFragmentPulltorefresh.setLayoutManager(manager);

        loginCultureFragmentPulltorefresh.setLoadingMoreEnabled(true);
        loginCultureFragmentPulltorefresh.setPullRefreshEnabled(true);
        loginCultureFragmentPulltorefresh.setPullToRefreshListener(this);
        topviewpager = LayoutInflater.from(getContext()).inflate(R.layout.top_viewpager,null);
        loginCultureFragmentPulltorefresh.addHeaderView(topviewpager);



    }

    @Override
    protected void loadData() {

        presenter.start();


// 调用轮播图的方法

    }


    @Override
    public void onRefresh() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loginCultureFragmentPulltorefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list.clear();
                        loadData();
                        adapter.notifyDataSetChanged();
                        loginCultureFragmentPulltorefresh.setRefreshComplete();
                    }
                },1000);
            }
        });


    }

    @Override
    public void onLoadMore() {

        loginCultureFragmentPulltorefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadData();
                adapter.notifyDataSetChanged();
            }
        },1000);

    }

    @Override
    public void showData(GG_TV_TextBean gg_tv_textBean) {

        list.addAll(gg_tv_textBean.getList());

        bigbim_array.addAll(gg_tv_textBean.getBigImg());


        handler.sendEmptyMessage(400);
        handler.sendEmptyMessage(555);

    }

    @Override
    public void showMsg(String msg) {

        ACache aCache =ACache.get(getContext());
        GG_TV_TextBean bean = (GG_TV_TextBean) aCache.getAsObject("GG_TV_TextBean");
        Log_Utils.log_d("TAG",bean.getList().size()+"");
        list.addAll(bean.getList());
        bigbim_array.addAll(bean.getBigImg());
        handler.sendEmptyMessage(400);
        handler.sendEmptyMessage(555);

    }

    @Override
    public void setPresenter(GG_TV_Contract.presenter presenter) {
        this.presenter = presenter;

    }
//    轮播图的方法

    public void login_home_rotation() {
        point_ratio = (LinearLayout) topviewpager.findViewById(R.id.top_login_home_rotation_point);
        rotation_array.clear();
        point_ratio.removeAllViews();

        for (int i = 0; i <bigbim_array.size(); i++) {

            View page_item = LayoutInflater.from(getActivity()).inflate(R.layout.login_home_rotation_item, null);
//            轮播图的图片
            imag = (ImageView) page_item.findViewById(R.id.home_rotation_item_imag);
//            轮播图的文字
            textView = (TextView) page_item.findViewById(R.id.home_rotation_item_text);
            textView.setText(bigbim_array.get(i).getTitle());
            Glide.with(getActivity()).load(bigbim_array.get(i).getImage()).into(imag);
            rotation_array.add(page_item);

//            储存俩个点
            View vvv = new View(getActivity());
            vvv.setBackgroundResource(R.drawable.rotation_point_write);
            LinearLayout.LayoutParams LP = new LinearLayout.LayoutParams(20, 20);
            LP.setMargins(20, 0, 0, 0);
            point_ratio.addView(vvv, LP);

            final int finalI = i;
            imag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ACache aCache = ACache.get(getContext());
                    aCache.put("cache_url",bigbim_array.get(finalI).getUrl());
                    Intent intent = new Intent(getContext(), WebActivity.class);
                    intent.putExtra("url",bigbim_array.get(finalI).getUrl());
                    startActivity(intent);
                }
            });

        }
        pager= (ViewPager) topviewpager.findViewById(R.id.top_viewpager);

        point_ratio.getChildAt(0).setBackgroundResource(R.drawable.rotation_point_blue);

        home_page_rotation = new Home_page_Rotation(rotation_array);

        pager.setAdapter(home_page_rotation);
        pager.setOnPageChangeListener(this);
        pager.setCurrentItem(10000);

        if (time_flg == false) {

            timer = new Timer();
            timer.schedule(task, 4000, 4000);

            time_flg = true;
        }


    }



    private TimerTask task = new TimerTask() {
        @Override
        public void run() {

            handler.sendEmptyMessage(300);

        }
    };

    //    轮播图的 点击事件
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        int newPosition = position % rotation_array.size();
        for (int i = 0; i < bigbim_array.size(); i++) {
            if (i == newPosition) {
                // 就将i对应的点设置为选中状态，其他的点都设置成未选中状态
                point_ratio.getChildAt(i).setBackgroundResource(
                        R.drawable.rotation_point_blue);
            } else {
                point_ratio.getChildAt(i).setBackgroundResource(
                        R.drawable.rotation_point_write);
            }

        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


}

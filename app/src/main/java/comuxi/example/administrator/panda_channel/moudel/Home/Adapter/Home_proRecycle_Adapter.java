package comuxi.example.administrator.panda_channel.moudel.Home.Adapter;


import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import comuxi.example.administrator.panda_channel.R;
import comuxi.example.administrator.panda_channel.app.App;
import comuxi.example.administrator.panda_channel.mode.CallBack.MyHttpCallBack;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.Home_CCTV_TextBean;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.Home_China_Movie_Text;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.Home_Data_TextBean;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.Look_Down_Text;
import comuxi.example.administrator.panda_channel.mode.biz.PandaItemMode;

/**
 * Created by Administrator on 2017/7/12.
 * 首页适配器
 */

public class Home_proRecycle_Adapter extends RecyclerView.Adapter implements ViewPager.OnPageChangeListener {

    public interface x_Recy_Onclick{
//        精彩推荐  监听的方法
        void get_wonderful_Click(Home_Data_TextBean.DataBean.AreaBean.ListscrollBean home_data);
//        熊猫观察第一条 监听的方法
        void get_pandan_loog_Click(View look_view, Home_Data_TextBean.DataBean.PandaeyeBean.ItemsBean itemsBean);
//       熊猫观察第 二条 监听的方法
        void get_pandan_loog_second_Click(View look_view, Home_Data_TextBean.DataBean.PandaeyeBean.ItemsBean second_itemsBean);

    }

    private x_Recy_Onclick recy_onclick;

    public void set_wonderful_Click(x_Recy_Onclick recy_onclick){
        this.recy_onclick=recy_onclick;
    };


    private FragmentActivity activity;
    private ArrayList<Home_Data_TextBean.DataBean> home_data;
    //    存放滚动轮播的 集合
    private ArrayList<View> rotation_array = new ArrayList<>();
    //    滚动轮播的图片
    private ImageView imag;
    //    滚动轮播图片上的文字
    private TextView textView;
    //    滚动轮播的ViewPage
    private ViewPager viewPager;
    //    Viewpage 的 适配器
    private Home_page_Rotation home_page_rotation;
    //    时间2类
    private Timer timer;
    //    轮播的四个小点
    LinearLayout point_ratio = null;
    private boolean time_flg = false;

    private ArrayList<Look_Down_Text.ListBean> Look_Down_Array = new ArrayList();
    private ArrayList<Home_CCTV_TextBean.ListBean> cctv_Array = new ArrayList();
    private ArrayList<Home_China_Movie_Text.ListBean> movie_Array = new ArrayList<>();

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 300:
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    break;
            }

        }
    };

    public Home_proRecycle_Adapter(FragmentActivity activity, ArrayList<Home_Data_TextBean.DataBean> home_data) {

        this.activity = activity;
        this.home_data = home_data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(activity).inflate(R.layout.login_xrecycleview_item, null);
        return new My_View(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        final My_View my_view = (My_View) holder;
        login_home_rotation(my_view);

//        设置 熊猫观察的 数据
        my_view.broad_title_one.setText(home_data.get(0).getPandaeye().getItems().get(0).getBrief());
        my_view.broad_title_two.setText(home_data.get(0).getPandaeye().getItems().get(1).getBrief());
        my_view.broad_content_one.setText(home_data.get(0).getPandaeye().getItems().get(0).getTitle());
        my_view.broad_content_two.setText(home_data.get(0).getPandaeye().getItems().get(1).getTitle());
        Glide.with(activity).load(home_data.get(0).getPandaeye().getPandaeyelogo()).into(my_view.broad_imag);

        my_view.broad_content_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recy_onclick.get_pandan_loog_Click(v,home_data.get(0).getPandaeye().getItems().get(0));
            }
        });

        my_view.broad_content_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recy_onclick.get_pandan_loog_second_Click(v,home_data.get(0).getPandaeye().getItems().get(1));
            }
        });


//        精彩推荐的数据
        LinearLayoutManager linmanage = new LinearLayoutManager(activity);
        linmanage.setOrientation(LinearLayoutManager.HORIZONTAL);
        my_view.wonderful_recycel.setLayoutManager(linmanage);
        Home_Wonderful_Adapter wonderful_adapter = new Home_Wonderful_Adapter(activity, home_data.get(0).getArea().getListscroll());
        my_view.wonderful_recycel.setAdapter(wonderful_adapter);
        wonderful_adapter.notifyDataSetChanged();
        Glide.with(activity).load(home_data.get(0).getArea().getImage()).placeholder(R.mipmap.umeng_socialize_share_pic).into(my_view.wonderful_image);
//        精彩推荐 的 点击事件
        wonderful_adapter.Wonder_setOnclick(new Home_Wonderful_Adapter.Wonder_Onclick() {
            @Override
            public void Wonder_getOnclick(View view, int pos) {

                recy_onclick.get_wonderful_Click(home_data.get(0).getArea().getListscroll().get(pos));

            }
        });


//        精彩推荐 下面 的数据
        LinearLayoutManager down_linmanage = new LinearLayoutManager(activity);
        down_linmanage.setOrientation(LinearLayoutManager.VERTICAL);
        my_view.look_down_recycle.setLayoutManager(down_linmanage);

        PandaItemMode.I_HTTP.get(home_data.get(0).getPandaeye().getPandaeyelist(), null, new MyHttpCallBack<Look_Down_Text>() {
            @Override
            public void onSuccess(Look_Down_Text look_down_text) {
                List<Look_Down_Text.ListBean> list = look_down_text.getList();

                Look_Down_Array.clear();
                Look_Down_Array.addAll(list);
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
            }
        });
        Look_Down_Adapter down_adapter = new Look_Down_Adapter(activity, Look_Down_Array);
        my_view.look_down_recycle.setAdapter(down_adapter);
        down_adapter.notifyDataSetChanged();


        //        设置 熊猫直播 数据
        GridLayoutManager manager = new GridLayoutManager(activity, 3);
        my_view.live_show_recy.setLayoutManager(manager);
        Home_Live_Show_Adapter show_adapter = new Home_Live_Show_Adapter(activity, home_data.get(0).getPandalive().getList());
        my_view.live_show_recy.setAdapter(show_adapter);

        show_adapter.notifyDataSetChanged();


//    设置  长城直播  数据
        GridLayoutManager manager_great = new GridLayoutManager(activity, 3);

        my_view.Great_Wall_recycle.setLayoutManager(manager_great);
        Home_Great_Wall_Adapter great_wall_adapter = new Home_Great_Wall_Adapter(activity, home_data.get(0).getWalllive().getList());

        my_view.Great_Wall_recycle.setAdapter(great_wall_adapter);


//        设置直播中国的数据
        GridLayoutManager manager_china = new GridLayoutManager(activity, 3);

        my_view.Live_China_Recycle.setLayoutManager(manager_china);
        Home_China_Live_Adapter wall_adapter = new Home_China_Live_Adapter(activity, home_data.get(0).getChinalive().getList());

        my_view.Live_China_Recycle.setAdapter(wall_adapter);

//      设置特别策划  的数据

        my_view.Special_planning_title.setText(home_data.get(0).getInteractive().getInteractiveone().get(0).getTitle());

        Glide.with(activity).load(home_data.get(0).getInteractive().getInteractiveone().get(0).getImage()).placeholder(R.mipmap.umeng_socialize_share_pic).into(my_view.Special_planning_Imagee);

//        设置CCTV里面的数据
        PandaItemMode.I_HTTP.get(home_data.get(0).getCctv().getListurl(), null, new MyHttpCallBack<Home_CCTV_TextBean>() {
            @Override
            public void onSuccess(Home_CCTV_TextBean look_down_text) {
                List<Home_CCTV_TextBean.ListBean> list = look_down_text.getList();
                cctv_Array.clear();
                cctv_Array.addAll(list);
                App.content.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        GridLayoutManager manager_cctv = new GridLayoutManager(activity, 2);
                        my_view.CCTV_recycle.setLayoutManager(manager_cctv);
                        Home_CCTV_Adapter cctv_adapter = new Home_CCTV_Adapter(activity, cctv_Array);
                        my_view.CCTV_recycle.setAdapter(cctv_adapter);
                    }
                });
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
            }
        });


//     设置供应中国的  数据
        PandaItemMode.I_HTTP.get(home_data.get(0).getList().get(0).getListUrl(), null, new MyHttpCallBack<Home_China_Movie_Text>() {
            @Override
            public void onSuccess(Home_China_Movie_Text look_down_text) {

                List<Home_China_Movie_Text.ListBean> list = look_down_text.getList();
                movie_Array.clear();
                movie_Array.addAll(list);
                App.content.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        LinearLayoutManager china_movie_mange = new LinearLayoutManager(activity);
                        china_movie_mange.setOrientation(LinearLayoutManager.VERTICAL);
                        my_view.china_movie_recyclee.setLayoutManager(china_movie_mange);

                        Home_China_Moive_Adapter moive_adapter = new Home_China_Moive_Adapter(activity, movie_Array);
                        my_view.china_movie_recyclee.setAdapter(moive_adapter);
                    }
                });
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
            }
        });

    }

    class My_View extends RecyclerView.ViewHolder {

        private ImageView broad_imag, wonderful_image, Special_planning_Imagee;
        private TextView broad_title_one, broad_title_two, broad_content_one, broad_content_two, Special_planning_title;//熊猫播报 内容
        private RecyclerView live_show_recy, wonderful_recycel, look_down_recycle, Great_Wall_recycle, Live_China_Recycle, CCTV_recycle, china_movie_recyclee;

        public My_View(View itemView) {
            super(itemView);
//        设置 熊猫观察的 数据
            broad_imag = (ImageView) itemView.findViewById(R.id.broad_image);
            broad_title_one = (TextView) itemView.findViewById(R.id.panda_broadcast_text_one);
            broad_title_two = (TextView) itemView.findViewById(R.id.panda_broadcast_text_two);
            broad_content_one = (TextView) itemView.findViewById(R.id.panda_broadcast_content_one);
            broad_content_two = (TextView) itemView.findViewById(R.id.panda_broadcast_content_two);

//             精彩推荐的数据
            wonderful_recycel = (RecyclerView) itemView.findViewById(R.id.home_Wonderful_recommendation_recycle);
            wonderful_image = (ImageView) itemView.findViewById(R.id.home_Wonderful_recommendation_image);

//        精彩推荐 下面 的数据
            look_down_recycle = (RecyclerView) itemView.findViewById(R.id.home_panda_look_two_recycle);

//               设置 熊猫直播 数据
            live_show_recy = (RecyclerView) itemView.findViewById(R.id.home_live_show_recycle);

//            设置 长城直播  数  据
            Great_Wall_recycle = (RecyclerView) itemView.findViewById(R.id.Home_Great_wall_recycle);

//            设置直播中国的数据
            Live_China_Recycle = (RecyclerView) itemView.findViewById(R.id.Home_Live_China_recycle);

//      设置特别策划  的数据
            Special_planning_Imagee = (ImageView) itemView.findViewById(R.id.Special_planning_Image);
            Special_planning_title = (TextView) itemView.findViewById(R.id.Special_planning_text);

//     设置 CCTV  里面 的  数据
            CCTV_recycle = (RecyclerView) itemView.findViewById(R.id.home_CCTV_recycle);

//            设置供应中国 的 数据
            china_movie_recyclee = (RecyclerView) itemView.findViewById(R.id.home_china_movie_recycle);

        }
    }

    @Override
    public int getItemCount() {
        return home_data.size();
    }

    //    设置轮播图的方法
    public void login_home_rotation(My_View my_view) {
        point_ratio = (LinearLayout) my_view.itemView.findViewById(R.id.my_login_home_rotation_point);
        rotation_array.clear();
        point_ratio.removeAllViews();
        for (int i = 0; i < home_data.get(0).getBigImg().size(); i++) {

            View page_item = LayoutInflater.from(activity).inflate(R.layout.login_home_rotation_item, null);
//            轮播图的图片
            imag = (ImageView) page_item.findViewById(R.id.home_rotation_item_imag);
//            轮播图的文字
            textView = (TextView) page_item.findViewById(R.id.home_rotation_item_text);
            textView.setText(home_data.get(0).getBigImg().get(i).getTitle());
            Glide.with(activity).load(home_data.get(0).getBigImg().get(i).getImage()).into(imag);
            rotation_array.add(page_item);

//            储存俩个点
            View v_point = new View(activity);
            v_point.setBackgroundResource(R.drawable.rotation_point_blue);
            LinearLayout.LayoutParams LP = new LinearLayout.LayoutParams(20, 20);
            LP.setMargins(20, 0, 0, 0);
            point_ratio.addView(v_point, LP);

        }

        viewPager = (ViewPager) my_view.itemView.findViewById(R.id.my_login_home_rotation);
        point_ratio.getChildAt(0).setBackgroundResource(
                R.drawable.rotation_point_write);
        home_page_rotation = new Home_page_Rotation(rotation_array);

        viewPager.setAdapter(home_page_rotation);
        viewPager.setOnPageChangeListener(this);
        viewPager.setCurrentItem(100000000);

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
        for (int i = 0; i < home_data.get(0).getBigImg().size(); i++) {
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

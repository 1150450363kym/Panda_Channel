package comuxi.example.administrator.panda_channel.moudel.Home.Adapter;


import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import comuxi.example.administrator.panda_channel.R;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.Home_Data_TextBean;

/**
 * Created by Administrator on 2017/7/12.
 * 首页适配器
 */

public class Home_proRecycle_Adapter extends RecyclerView.Adapter implements ViewPager.OnPageChangeListener {
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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        My_View my_view = (My_View) holder;
        login_home_rotation(my_view);

//        设置 熊猫播报的 数据
        my_view.broad_title_one.setText(home_data.get(0).getPandaeye().getItems().get(0).getBrief());
        my_view.broad_title_two.setText(home_data.get(0).getPandaeye().getItems().get(1).getBrief());
        my_view.broad_content_one.setText(home_data.get(0).getPandaeye().getItems().get(0).getTitle());
        my_view.broad_content_two.setText(home_data.get(0).getPandaeye().getItems().get(1).getTitle());
        Glide.with(activity).load(home_data.get(0).getPandaeye().getPandaeyelogo()).into(my_view.broad_imag);

//        设置 直播秀场 数据

        GridLayoutManager manager = new GridLayoutManager(activity, 3);

        my_view.live_show_recy.setLayoutManager(manager);

        Home_Live_Show_Adapter show_adapter = new Home_Live_Show_Adapter(activity, home_data.get(0).getArea().getListscroll());

        my_view.live_show_recy.setAdapter(show_adapter);
        show_adapter.notifyDataSetChanged();


    }

    class My_View extends RecyclerView.ViewHolder {

        private ImageView broad_imag;//熊猫播报 图片
        private TextView broad_title_one, broad_title_two, broad_content_one, broad_content_two;//熊猫播报 内容
        private RecyclerView live_show_recy;

        public My_View(View itemView) {
            super(itemView);
//        设置 熊猫播报的 数据
            broad_imag = (ImageView) itemView.findViewById(R.id.broad_image);
            broad_title_one = (TextView) itemView.findViewById(R.id.panda_broadcast_text_one);
            broad_title_two = (TextView) itemView.findViewById(R.id.panda_broadcast_text_two);
            broad_content_one = (TextView) itemView.findViewById(R.id.panda_broadcast_content_one);
            broad_content_two = (TextView) itemView.findViewById(R.id.panda_broadcast_content_two);

//               设置 直播秀场 数据
            live_show_recy = (RecyclerView) itemView.findViewById(R.id.home_live_show_recycle);


        }
    }

    @Override
    public int getItemCount() {
        return home_data.size();
    }


    //    设置轮播图的方法
//
    public void login_home_rotation(My_View my_view) {


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
            point_ratio = (LinearLayout) my_view.itemView.findViewById(R.id.my_login_home_rotation_point);
            point_ratio.addView(v_point, LP);

        }

        viewPager = (ViewPager) my_view.itemView.findViewById(R.id.my_login_home_rotation);

        point_ratio.getChildAt(0).setBackgroundResource(
                R.drawable.rotation_point_write);
        home_page_rotation = new Home_page_Rotation(rotation_array);

        viewPager.setAdapter(home_page_rotation);
        viewPager.setOnPageChangeListener(this);
        viewPager.setCurrentItem(100000000);

        timer = new Timer();
        timer.schedule(task, 4000, 4000);


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

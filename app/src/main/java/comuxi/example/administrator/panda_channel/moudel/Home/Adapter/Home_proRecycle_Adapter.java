package comuxi.example.administrator.panda_channel.moudel.Home.Adapter;

//我认为自己是外向的人   大惊小怪  话多 要求多的人  勤劳  慷慨  小心谨慎  令人愉快 不受传统 亲和力 效力
// 2 3 7 11 5
//
//
//

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
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
//
public class Home_proRecycle_Adapter extends RecyclerView.Adapter implements ViewPager.OnPageChangeListener {

    public interface x_Recy_Onclick {

//        轮播图   监听方法

        void get_Ratation_Click(View v, Home_Data_TextBean.DataBean.BigImgBean bigImgBean);


        //        精彩推荐  监听的方法
        void get_wonderful_Click(Home_Data_TextBean.DataBean.AreaBean.ListscrollBean home_data);

        //        熊猫观察第一条 监听的方法
        void get_pandan_loog_Click(View look_view, Home_Data_TextBean.DataBean.PandaeyeBean.ItemsBean itemsBean);

        //       熊猫观察第 二条 监听的方法
        void get_pandan_loog_second_Click(View look_view, Home_Data_TextBean.DataBean.PandaeyeBean.ItemsBean second_itemsBean);

        //        熊猫观察下面的点击事件
        void get_pandan_look_down_Click(Look_Down_Text.ListBean look_down_text);

        //熊猫直播的点击事件
        void get_Panda_live_Click(Home_Data_TextBean.DataBean.PandaliveBean.ListBean pandalivebean);

//        //        长城直播的点击事件
        void get_great_live_Click(Home_Data_TextBean.DataBean.WallliveBean.ListBeanX listBeanX);
//
//        //        直播中国
        void get_china_live_Click(Home_Data_TextBean.DataBean.ChinaliveBean.ListBeanXX listBeanXX);
//
//        //        特别策划
        void get_special_planning_Click(View v, Home_Data_TextBean.DataBean.InteractiveBean.InteractiveoneBean interactiveoneBean);
//
//        //        CCTV 点击事件
        void get_cctv_live_Click(Home_CCTV_TextBean.ListBean listBean);
//
//        //        公映中国
        void get_movie_live_Click(Home_China_Movie_Text.ListBean listBean);

    }

    private x_Recy_Onclick recy_onclick;

    public void set_wonderful_Click(x_Recy_Onclick recy_onclick) {
        this.recy_onclick = recy_onclick;
    }

    ;


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
    public static final int ratation_type = 1;
    public static final int wonderful_type = 2;
    public static final int panda_look_type = 3;
    public static final int panda_live_type = 4;
    public static final int great_wall_type = 5;
    public static final int lice_china_type = 6;
    public static final int special_planning_type = 7;
    public static final int cctv_type = 8;
    public static final int movie_china_type = 9;
    private FragmentActivity activity;
    //        private ArrayList<Home_Data_TextBean.DataBean> home_data;
    private ArrayList<Object> datas;
    private ArrayList<Home_Data_TextBean.DataBean> home_data;

    public Home_proRecycle_Adapter(FragmentActivity activity, ArrayList<Object> datas, ArrayList<Home_Data_TextBean.DataBean> home_data) {

        this.activity = activity;
        this.datas = datas;
        this.home_data = home_data;
    }

    //    加载不同的布局
    @Override
    public int getItemViewType(int position) {
        Object o = datas.get(position);
//轮播图
        if (o instanceof Home_Data_TextBean.DataBean.BigImgBean) {
            return ratation_type;
        }
//        精彩推荐
        if (o instanceof Home_Data_TextBean.DataBean.AreaBean) {
            return wonderful_type;
        }
//熊猫观察
        if (o instanceof Home_Data_TextBean.DataBean.PandaeyeBean) {
            return panda_look_type;
        }
//熊猫直播
        if (o instanceof Home_Data_TextBean.DataBean.PandaliveBean) {
            return panda_live_type;
        }
//长城直播
        if (o instanceof Home_Data_TextBean.DataBean.WallliveBean) {
            return great_wall_type;
        }
//直播中国
        if (o instanceof Home_Data_TextBean.DataBean.ChinaliveBean) {
            return lice_china_type;
        }
//特别策划
        if (o instanceof Home_Data_TextBean.DataBean.InteractiveBean) {
            return special_planning_type;
        }
//CCTV
        if (o instanceof Home_Data_TextBean.DataBean.CctvBean) {
            return cctv_type;
        }
//公映中国
        if (o instanceof Home_Data_TextBean.DataBean.ListBeanXXX) {
            return movie_china_type;
        }
        return 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            //            轮播图
            case 1:

                View raio_view = LayoutInflater.from(activity).inflate(R.layout.login_home_rotation, null);

                return new Ration_viewHolder(raio_view);
            //            精彩推荐
            case 2:

                View wonder_view = LayoutInflater.from(activity).inflate(R.layout.home_wonderful_recommenda, null);

                return new Wondelful_viewHolder(wonder_view);
            //            熊猫观察
            case 3:

                View pande_look_view = LayoutInflater.from(activity).inflate(R.layout.home_panda_look, null);

                return new Panda_look_viewHolder(pande_look_view);

            //            熊猫直播
            case 4:
                View panda_live_view = LayoutInflater.from(activity).inflate(R.layout.home_panda_live_recy, null);

                return new Panda_live_viewHolder(panda_live_view);
            //            长城直播
            case 5:
                View great_wall_view = LayoutInflater.from(activity).inflate(R.layout.home_great_wall2_recy, null);

                return new Great_wall_viewHolder(great_wall_view);
            //            直播中国
            case 6:
                View live_china_view = LayoutInflater.from(activity).inflate(R.layout.home_live_china_recy, null);

                return new Live_china_viewHolder(live_china_view);
            //            特别策划
            case 7:
                View special_planning_view = LayoutInflater.from(activity).inflate(R.layout.home_special_planning, null);

                return new Simping_viewHolder(special_planning_view);
            //            CCTV
            case 8:
                View cctv_view = LayoutInflater.from(activity).inflate(R.layout.login_cctv_recycle, null);

                return new Cctv_viewHolder(cctv_view);
            //            公映中国
            case 9:
                View movie_china_view = LayoutInflater.from(activity).inflate(R.layout.home_movie_china, null);

                return new Movie_china_viewHolder(movie_china_view);
        }

        return null;

    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Object o = datas.get(position);
        switch (getItemViewType(position)) {
            case ratation_type:
                Ration_viewHolder ration_view = (Ration_viewHolder) holder;


                Home_Data_TextBean.DataBean.BigImgBean bigImgBean = (Home_Data_TextBean.DataBean.BigImgBean) o;

                login_home_rotation(ration_view);

                break;
            //        精彩推荐的数据
            case wonderful_type:
                Home_Data_TextBean.DataBean.AreaBean areaBean = (Home_Data_TextBean.DataBean.AreaBean) o;

                Wondelful_viewHolder wonde_holder = (Wondelful_viewHolder) holder;
                LinearLayoutManager linmanage = new LinearLayoutManager(activity);
                linmanage.setOrientation(LinearLayoutManager.HORIZONTAL);
                wonde_holder.wonderful_recycel.setLayoutManager(linmanage);
                Home_Wonderful_Adapter wonderful_adapter = new Home_Wonderful_Adapter(activity, areaBean.getListscroll());
                wonde_holder.wonderful_recycel.setAdapter(wonderful_adapter);
                wonderful_adapter.notifyDataSetChanged();
                Glide.with(activity).load(areaBean.getImage()).placeholder(R.mipmap.umeng_socialize_share_pic).into(wonde_holder.wonderful_image);
                //       精彩推荐 的 点击事件
                wonderful_adapter.Wonder_setOnclick(new Home_Wonderful_Adapter.Wonder_Onclick() {
                    @Override
                    public void Wonder_getOnclick(View view, int pos) {

                        recy_onclick.get_wonderful_Click(home_data.get(0).getArea().getListscroll().get(pos));

                    }
                });
                break;
            case panda_look_type:
                Panda_look_viewHolder look_viewholder = (Panda_look_viewHolder) holder;

                Home_Data_TextBean.DataBean.PandaeyeBean pandaeyeBean = (Home_Data_TextBean.DataBean.PandaeyeBean) o;

                look_viewholder.broad_title_one.setText(pandaeyeBean.getItems().get(0).getBrief());
                look_viewholder.broad_title_two.setText(pandaeyeBean.getItems().get(1).getBrief());
                look_viewholder.broad_content_one.setText(pandaeyeBean.getItems().get(0).getTitle());
                look_viewholder.broad_content_two.setText(pandaeyeBean.getItems().get(1).getTitle());
                Glide.with(activity).load(pandaeyeBean.getPandaeyelogo()).into(look_viewholder.broad_imag);
                LinearLayoutManager down_linmanage = new LinearLayoutManager(activity);
                down_linmanage.setOrientation(LinearLayoutManager.VERTICAL);
                look_viewholder.look_down_recycle.setLayoutManager(down_linmanage);
                PandaItemMode.I_HTTP.get(pandaeyeBean.getPandaeyelist(), null, new MyHttpCallBack<Look_Down_Text>() {
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
                look_viewholder.look_down_recycle.setAdapter(down_adapter);
                down_adapter.notifyDataSetChanged();

                look_viewholder.broad_content_one.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        recy_onclick.get_pandan_loog_Click(v, home_data.get(0).getPandaeye().getItems().get(0));
                    }
                });

                look_viewholder.broad_content_two.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        recy_onclick.get_pandan_loog_second_Click(v, home_data.get(0).getPandaeye().getItems().get(1));
                    }
                });
                down_adapter.set_Look_dow_getOnclick(new Look_Down_Adapter.Look_dow_Onclick() {
                    @Override
                    public void get_look_dow_Onclick(View view, int lok_down_postion) {

                        recy_onclick.get_pandan_look_down_Click(Look_Down_Array.get(lok_down_postion));
                    }
                });


                break;
            case panda_live_type:
                final Home_Data_TextBean.DataBean.PandaliveBean pandaliveBean = (Home_Data_TextBean.DataBean.PandaliveBean) o;
                Panda_live_viewHolder live_holder = (Panda_live_viewHolder) holder;
                GridLayoutManager manager = new GridLayoutManager(activity, 3);
                live_holder.live_show_recy.setLayoutManager(manager);
                Home_Live_Show_Adapter show_adapter = new Home_Live_Show_Adapter(activity, pandaliveBean.getList());
                live_holder.live_show_recy.setAdapter(show_adapter);
                show_adapter.notifyDataSetChanged();



                show_adapter.set_panda_live_click(new Home_Live_Show_Adapter.panda_live_Onclick() {
                    @Override
                    public void get_panda_live(View view, int panda_postion) {

                        recy_onclick.get_Panda_live_Click(pandaliveBean.getList().get(panda_postion));
                    }
                });

                break;
            case great_wall_type:
                final Home_Data_TextBean.DataBean.WallliveBean wallliveBean = (Home_Data_TextBean.DataBean.WallliveBean) o;
                Great_wall_viewHolder wall_viewHolder = (Great_wall_viewHolder) holder;
                GridLayoutManager manager_great = new GridLayoutManager(activity, 3);
                wall_viewHolder.Great_Wall_recycle.setLayoutManager(manager_great);
                Home_Great_Wall_Adapter great_wall_adapter = new Home_Great_Wall_Adapter(activity, wallliveBean.getList());
                wall_viewHolder.Great_Wall_recycle.setAdapter(great_wall_adapter);


                great_wall_adapter.set_Great_live_click(new Home_Great_Wall_Adapter.Great_live_Onclick() {
                    @Override
                    public void get_Great_live(View view, int great_postion) {

                        recy_onclick.get_great_live_Click(wallliveBean.getList().get(great_postion));
                    }
                });



                break;
            case lice_china_type:
                final Home_Data_TextBean.DataBean.ChinaliveBean chinaliveBean = (Home_Data_TextBean.DataBean.ChinaliveBean) o;
                Live_china_viewHolder china_holder = (Live_china_viewHolder) holder;
                GridLayoutManager manager_china = new GridLayoutManager(activity, 3);
                china_holder.Live_China_Recycle.setLayoutManager(manager_china);
                Home_China_Live_Adapter wall_adapter = new Home_China_Live_Adapter(activity, chinaliveBean.getList());
                china_holder.Live_China_Recycle.setAdapter(wall_adapter);

                wall_adapter.set_China_live_click(new Home_China_Live_Adapter.China_live_Onclick() {
                    @Override
                    public void get_china_live(View view, int great_postion) {
                        recy_onclick.get_china_live_Click(chinaliveBean.getList().get(great_postion));
                    }
                });


                break;
            case special_planning_type:
                final Home_Data_TextBean.DataBean.InteractiveBean interactiveBean = (Home_Data_TextBean.DataBean.InteractiveBean) o;
                Simping_viewHolder sim_viewHolder = (Simping_viewHolder) holder;
                sim_viewHolder.Special_planning_title.setText(interactiveBean.getInteractiveone().get(0).getTitle());
                Glide.with(activity).load(interactiveBean.getInteractiveone().get(0).getImage()).placeholder(R.mipmap.umeng_socialize_share_pic).into(sim_viewHolder.Special_planning_Imagee);

                sim_viewHolder.Special_planning_Imagee.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        recy_onclick.get_special_planning_Click(v,interactiveBean.getInteractiveone().get(0));

                    }
                });

                break;
            case cctv_type:
                final Cctv_viewHolder cctv_viewholder = (Cctv_viewHolder) holder;
////        设置CCTV里面的数据
                Home_Data_TextBean.DataBean.CctvBean cctvBean = (Home_Data_TextBean.DataBean.CctvBean) o;
                PandaItemMode.I_HTTP.get(cctvBean.getListurl(), null, new MyHttpCallBack<Home_CCTV_TextBean>() {
                    @Override
                    public void onSuccess(Home_CCTV_TextBean look_down_text) {
                        List<Home_CCTV_TextBean.ListBean> list = look_down_text.getList();
                        cctv_Array.clear();
                        cctv_Array.addAll(list);
                        App.content.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                GridLayoutManager manager_cctv = new GridLayoutManager(activity, 2);
                                cctv_viewholder.CCTV_recycle.setLayoutManager(manager_cctv);
                                Home_CCTV_Adapter cctv_adapter = new Home_CCTV_Adapter(activity, cctv_Array);
                                cctv_viewholder.CCTV_recycle.setAdapter(cctv_adapter);

                                cctv_adapter.set_China_live_click(new Home_CCTV_Adapter.CCTV_live_Onclick() {
                                    @Override
                                    public void get_cctv_live(View view, int cctv_postion) {
                                        recy_onclick.get_cctv_live_Click(cctv_Array.get(cctv_postion));
                                    }
                                });

                            }
                        });
                    }

                    @Override
                    public void onError(int errorCode, String errorMsg) {
                    }
                });




                break;
            case movie_china_type:
                final Movie_china_viewHolder chian_viewholder = (Movie_china_viewHolder) holder;
////     设置供应中国的  数据

                Home_Data_TextBean.DataBean.ListBeanXXX listBeanXXX = (Home_Data_TextBean.DataBean.ListBeanXXX) o;
                PandaItemMode.I_HTTP.get(listBeanXXX.getListUrl(), null, new MyHttpCallBack<Home_China_Movie_Text>() {
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
                                chian_viewholder.china_movie_recyclee.setLayoutManager(china_movie_mange);
                                Home_China_Moive_Adapter moive_adapter = new Home_China_Moive_Adapter(activity, movie_Array);
                                chian_viewholder.china_movie_recyclee.setAdapter(moive_adapter);


                                moive_adapter.set_China_movie_click(new Home_China_Moive_Adapter.Movie_live_Onclick() {
                                    @Override
                                    public void get_movie_live(View view, int movie_postion) {

                                       recy_onclick.get_movie_live_Click(movie_Array.get(movie_postion));

                                    }
                                });



                            }
                        });
                    }

                    @Override
                    public void onError(int errorCode, String errorMsg) {
                    }
                });

                break;

        }




    }


    @Override
    public int getItemCount() {
        return datas.size();
    }

    //    写  9  个ViewHolder
    class Ration_viewHolder extends RecyclerView.ViewHolder {
        public Ration_viewHolder(View itemView) {
            super(itemView);
        }
    }

    class Wondelful_viewHolder extends RecyclerView.ViewHolder {
        private RecyclerView wonderful_recycel;
        private ImageView wonderful_image;

        public Wondelful_viewHolder(View itemView) {
            super(itemView);
            wonderful_recycel = (RecyclerView) itemView.findViewById(R.id.home_Wonderful_recommendation_recycle);
            wonderful_image = (ImageView) itemView.findViewById(R.id.home_Wonderful_recommendation_image);
        }
    }

    class Panda_look_viewHolder extends RecyclerView.ViewHolder {
        private RecyclerView look_down_recycle;
        private TextView broad_content_two, broad_content_one, broad_title_two, broad_title_one;
        private ImageView broad_imag;

        public Panda_look_viewHolder(View itemView) {
            super(itemView);
            broad_imag = (ImageView) itemView.findViewById(R.id.broad_image);
            broad_title_one = (TextView) itemView.findViewById(R.id.panda_broadcast_text_one);
            broad_title_two = (TextView) itemView.findViewById(R.id.panda_broadcast_text_two);
            broad_content_one = (TextView) itemView.findViewById(R.id.panda_broadcast_content_one);
            broad_content_two = (TextView) itemView.findViewById(R.id.panda_broadcast_content_two);
            look_down_recycle = (RecyclerView) itemView.findViewById(R.id.home_panda_look_two_recycle);


        }
    }

    class Panda_live_viewHolder extends RecyclerView.ViewHolder {
        private RecyclerView live_show_recy;


        public Panda_live_viewHolder(View itemView) {
            super(itemView);
            live_show_recy = (RecyclerView) itemView.findViewById(R.id.home_live_show_recycle);


        }
    }
    class Great_wall_viewHolder extends RecyclerView.ViewHolder {
        private RecyclerView Great_Wall_recycle;


        public Great_wall_viewHolder(View itemView) {
            super(itemView);

            Great_Wall_recycle = (RecyclerView) itemView.findViewById(R.id.Home_Great_wall_recycle);


        }
    }

    class Live_china_viewHolder extends RecyclerView.ViewHolder {
        private RecyclerView Live_China_Recycle;

        public Live_china_viewHolder(View itemView) {
            super(itemView);
            Live_China_Recycle = (RecyclerView) itemView.findViewById(R.id.Home_Live_China_recycle);
        }
    }

    class Simping_viewHolder extends RecyclerView.ViewHolder {

        private TextView Special_planning_title;
        private ImageView Special_planning_Imagee;

        public Simping_viewHolder(View itemView) {
            super(itemView);

            Special_planning_Imagee = (ImageView) itemView.findViewById(R.id.Special_planning_Image);
            Special_planning_title = (TextView) itemView.findViewById(R.id.Special_planning_text);
        }
    }

    class Cctv_viewHolder extends RecyclerView.ViewHolder {
        private RecyclerView CCTV_recycle;

        public Cctv_viewHolder(View itemView) {
            super(itemView);
            CCTV_recycle = (RecyclerView) itemView.findViewById(R.id.home_CCTV_recycle);


        }
    }

    class Movie_china_viewHolder extends RecyclerView.ViewHolder {
        private RecyclerView china_movie_recyclee;

        public Movie_china_viewHolder(View itemView) {
            super(itemView);
            china_movie_recyclee = (RecyclerView) itemView.findViewById(R.id.home_china_movie_recycle);

        }
    }


    //    //    设置轮播图的方法
    public void login_home_rotation(Ration_viewHolder my_view) {
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


            final int finalI = i;
            imag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    recy_onclick.get_Ratation_Click(v,home_data.get(0).getBigImg().get(finalI));

                }
            });


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

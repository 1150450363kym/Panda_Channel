package comuxi.example.administrator.panda_channel.moudel.China_Live;

import android.animation.ValueAnimator;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import comuxi.example.administrator.panda_channel.Base.BaseFragment;
import comuxi.example.administrator.panda_channel.R;
import comuxi.example.administrator.panda_channel.app.App;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.China_Live_Path_TextBean;
import comuxi.example.administrator.panda_channel.moudel.China_Live.china_adapter.China_Tablayout_PageAdapter;
import comuxi.example.administrator.panda_channel.moudel.China_Live.china_adapter.GridView_Adapter;
import comuxi.example.administrator.panda_channel.moudel.China_Live.china_adapter.More_GridView_Adapter;
import comuxi.example.administrator.panda_channel.moudel.China_Live.china_path_fragment.Path_Fragment;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.NonSwipeableViewPager;

/**
 * Created by Administrator on 2017/7/11.
 */

public class China_Live_Fragment extends BaseFragment implements China_Live_Contract.View {
    @BindView(R.id.china_live_tablayout)
    TabLayout chinaLiveTablayout;

    @BindView(R.id.china_live_viewpage)
    NonSwipeableViewPager chinaLiveViewpage;
    Unbinder unbinder;

    China_Live_Contract.presenter presenter;
    @BindView(R.id.chian_live_add)
    ImageView chianLiveAdd;
    @BindView(R.id.login_china_live_relation)
    RelativeLayout loginChinaLiveRelation;
    @BindView(R.id.china_live_tablayoutlin)
    LinearLayout chinaLiveTablayoutlin;

    private ArrayList<Fragment> fargmet_array = new ArrayList<>();
    private ArrayList<China_Live_Path_TextBean.TablistBean> tablistBeen_array = new ArrayList<>();
    private ArrayList<China_Live_Path_TextBean.AlllistBean> alllistBeen_aray = new ArrayList<>();


    China_Tablayout_PageAdapter tablay_adapter;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 300:
                    tablay_adapter.notifyDataSetChanged();
                    break;
                case 400:

                    gridView_adapter.notifyDataSetChanged();
                    more_gridView_adapter.notifyDataSetChanged();

                    break;
            }
        }
    };

    @Override
    protected int getlayoutID() {
        return R.layout.login_china_nor_fragment;
    }

    @Override
    protected void init(View view) {
    }

    @Override
    protected void loadData() {
        presenter.start();
        tablay_adapter = new China_Tablayout_PageAdapter(getActivity().getSupportFragmentManager(), fargmet_array, tablistBeen_array);
        chinaLiveViewpage.setAdapter(tablay_adapter);
        chinaLiveTablayout.setupWithViewPager(chinaLiveViewpage);
        chinaLiveTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void getMessage(String message) {
    }

    @Override
    public void getNetData(China_Live_Path_TextBean china_live_path_textBean) {
//        这是 tablayout地址
        List<China_Live_Path_TextBean.TablistBean> tablist = china_live_path_textBean.getTablist();
        tablistBeen_array.addAll(tablist);

//        这是  popwindow  里面的 地址
        List<China_Live_Path_TextBean.AlllistBean> alllist = china_live_path_textBean.getAlllist();

        alllistBeen_aray.addAll(alllist);
        Log.e("TAG", "点击 添加 以前 下面的长度 是" + alllistBeen_aray.size());
//        Fragment  数量
        for (int i = 0; i < tablistBeen_array.size(); i++) {
            Path_Fragment path_fragment = new Path_Fragment(tablist.get(i).getUrl());
            fargmet_array.add(path_fragment);
        }

        handler.sendEmptyMessage(300);

    }

    @Override
    public void setPresenter(China_Live_Contract.presenter presenter) {
        this.presenter = presenter;
    }

    //弹出 Popwindow  动画
    private ImageView dele_imag;
    private GridView gridView, more_gridView;
    private TextView enit_text;
    private GridView_Adapter gridView_adapter;
    private More_GridView_Adapter more_gridView_adapter;
    private RelativeLayout popwindow_parent;
    // 贝塞尔曲线中间过程点坐标
    private float[] mCurrentPosition = new float[2];

    @OnClick(R.id.chian_live_add)
    public void onViewClicked() {
        View layout = getActivity().getLayoutInflater().inflate(R.layout.popwindow_, null);
        enit_text = (TextView) layout.findViewById(R.id.edtix_button);
        gridView = (GridView) layout.findViewById(R.id.Switch_the_section_gridview);
        more_gridView = (GridView) layout.findViewById(R.id.more_Switch_the_section_gridview);
        popwindow_parent = (RelativeLayout) layout.findViewById(R.id.popwindow_parent);

        enit_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < tablistBeen_array.size(); i++) {
                    tablistBeen_array.get(i).setFlg(true);
                }
                if (enit_text.getText().equals("编辑")) {
                    enit_text.setText("完成");
//                    切换内容的点击事件
                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            if (tablistBeen_array.size() > 4) {
//
                                China_Live_Path_TextBean.AlllistBean down_array = new China_Live_Path_TextBean.AlllistBean();
                                down_array.setTitle(tablistBeen_array.get(position).getTitle());
                                down_array.setOrder(tablistBeen_array.get(position).getOrder());
                                down_array.setType(tablistBeen_array.get(position).getType());
                                down_array.setUrl(tablistBeen_array.get(position).getUrl());

                                alllistBeen_aray.add(down_array);
                                tablistBeen_array.remove(position);

                                handler.sendEmptyMessage(400);

                            }

                            gridView_adapter.notifyDataSetChanged();

                        }
                    });


                    more_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View Qview, int DOposition, long id) {

                            China_Live_Path_TextBean.TablistBean UP_array = new China_Live_Path_TextBean.TablistBean();
                            UP_array.setTitle(alllistBeen_aray.get(DOposition).getTitle());
                            UP_array.setOrder(alllistBeen_aray.get(DOposition).getOrder());
                            UP_array.setFlg(true);
                            UP_array.setType(alllistBeen_aray.get(DOposition).getType());
                            UP_array.setUrl(alllistBeen_aray.get(DOposition).getUrl());
                            tablistBeen_array.add(UP_array);

                            // 创造出执行动画的主题goodsImg
                            final ImageView goods = new ImageView(App.content);
                            goods.setImageDrawable(Qview.getBackground());
                            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100, 100);
                            popwindow_parent.addView(goods, params);

                            // 得到父布局的起始点坐标（用于辅助计算动画开始/结束时的点的坐标）
                            int[] parentLocation = new int[2];
                            popwindow_parent.getLocationInWindow(parentLocation);
                            // 得到商品图片的坐标（用于计算动画开始的坐标）
                            int startLoc[] = new int[2];
                            goods.getLocationInWindow(startLoc);

                            // 得到购物车图片的坐标(用于计算动画结束后的坐标)
                            int endLoc[] = new int[2];
                            gridView.getLocationInWindow(endLoc);

                            // 开始掉落的商品的起始点：商品起始点-父布局起始点+该商品图片的一半
                            float startX = startLoc[0] - parentLocation[0] + goods.getWidth() / 2;
                            float startY = startLoc[1] - parentLocation[1] + goods.getHeight() / 2;

                            // 商品掉落后的终点坐标：购物车起始点-父布局起始点+购物车图片的1/5
                            float toX = endLoc[0] - parentLocation[0] + gridView.getWidth() / 5;
                            float toY = endLoc[1] - parentLocation[1];
                            // 开始绘制贝塞尔曲线
                            Path path = new Path();
                            // 移动到起始点（贝塞尔曲线的起点）
                            path.moveTo(startX, startY);
                            // 使用二阶贝塞尔曲线：注意第一个起始坐标越大，贝塞尔曲线的横向距离就会越大，一般按照下面的式子取即可
                            path.quadTo((startX + toX) / 2, startY, toX, toY);
                            // mPathMeasure用来计算贝塞尔曲线的曲线长度和贝塞尔曲线中间插值的坐标，如果是true，path会形成一个闭环
                            final PathMeasure mPathMeasure = new PathMeasure(path, false);

                            // 属性动画实现（从0到贝塞尔曲线的长度之间进行插值计算，获取中间过程的距离值）
                            ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, mPathMeasure.getLength());
                            valueAnimator.setDuration(500);

                            // 属性动画实现（从0到贝塞尔曲线的长度之间进行插值计算，获取中间过程的距离值）
                            valueAnimator = ValueAnimator.ofFloat(0, mPathMeasure.getLength());
                            valueAnimator.setDuration(500);

                            // 匀速线性插值器
                            valueAnimator.setInterpolator(new LinearInterpolator());
                            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                @Override
                                public void onAnimationUpdate(ValueAnimator animation) {
                                    // 当插值计算进行时，获取中间的每个值，
                                    // 这里这个值是中间过程中的曲线长度（下面根据这个值来得出中间点的坐标值）
                                    float value = (Float) animation.getAnimatedValue();
                                    // 获取当前点坐标封装到mCurrentPosition
                                    // boolean getPosTan(float distance, float[] pos, float[] tan) ：
                                    // 传入一个距离distance(0<=distance<=getLength())，然后会计算当前距离的坐标点和切线，pos会自动填充上坐标，这个方法很重要。
                                    // mCurrentPosition此时就是中间距离点的坐标值
                                    mPathMeasure.getPosTan(value, mCurrentPosition, null);
                                    // 移动的商品图片（动画图片）的坐标设置为该中间点的坐标
                                    goods.setTranslationX(mCurrentPosition[0]);
                                    goods.setTranslationY(mCurrentPosition[1]);
                                }
                            });

                            // 开始执行动画
                            valueAnimator.start();

                            alllistBeen_aray.remove(DOposition);
                            handler.sendEmptyMessage(400);


                        }
                    });

//不可编辑
                } else {

                    enit_text.setText("编辑");
                    for (int i = 0; i < tablistBeen_array.size(); i++) {
                        tablistBeen_array.get(i).setFlg(false);
                    }

//                        重新 NEW  Fragment
                    fargmet_array.clear();
                    for (int i = 0; i < tablistBeen_array.size(); i++) {
                        Path_Fragment path_fragment = new Path_Fragment(tablistBeen_array.get(i).getUrl());
                        fargmet_array.add(path_fragment);
                    }
                    tablay_adapter.notifyDataSetChanged();

                    handler.sendEmptyMessage(400);

                }
            }
        });


        gridView_adapter = new GridView_Adapter(getActivity(), tablistBeen_array);
        gridView.setAdapter(gridView_adapter);


        more_gridView_adapter = new More_GridView_Adapter(getActivity(), alllistBeen_aray);
        more_gridView.setAdapter(more_gridView_adapter);

        final PopupWindow pop = new PopupWindow(layout,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT, true);

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pop.setAnimationStyle(R.style.popupStyle);
                pop.showAtLocation(loginChinaLiveRelation, Gravity.NO_GRAVITY, 0, 0);
            }
        });

        dele_imag = (ImageView) layout.findViewById(R.id.live_china_delect);

        dele_imag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pop.dismiss();

            }
        });

    }

}

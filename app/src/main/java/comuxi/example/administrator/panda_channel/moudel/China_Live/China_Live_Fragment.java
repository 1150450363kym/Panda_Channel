package comuxi.example.administrator.panda_channel.moudel.China_Live;

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
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import comuxi.example.administrator.panda_channel.Base.BaseFragment;
import comuxi.example.administrator.panda_channel.R;
import comuxi.example.administrator.panda_channel.Utils.ACache;
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
    @BindView(R.id.progress_bar_id)
    RelativeLayout progressBarId;
    Unbinder unbinder1;

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
        progressBarId.setVisibility(View.VISIBLE);

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
    public void getMessage(String message) {

        ACache aCache = ACache.get(App.content);
        China_Live_Path_TextBean china_live_path_textBean = (China_Live_Path_TextBean) aCache.getAsObject("China_Live_Path_TextBean");

        if (china_live_path_textBean != null) {

            tablistBeen_array.addAll(china_live_path_textBean.getTablist());

            alllistBeen_aray.addAll(china_live_path_textBean.getAlllist());

            for (int i = 0; i < tablistBeen_array.size(); i++) {
                Path_Fragment path_fragment = new Path_Fragment(china_live_path_textBean.getTablist().get(i).getUrl());
                fargmet_array.add(path_fragment);
            }

            handler.sendEmptyMessage(300);

        } else {
            Toast.makeText(App.content, "缓存直播中国", Toast.LENGTH_SHORT).show();
        }


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

        progressBarId.setVisibility(View.GONE);

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
    private int mMargin = 6;


    @OnClick(R.id.chian_live_add)
    public void onViewClicked() {
        View layout = getActivity().getLayoutInflater().inflate(R.layout.popwindow_, null);
        enit_text = (TextView) layout.findViewById(R.id.edtix_button);
        gridView = (GridView) layout.findViewById(R.id.Switch_the_section_gridview);
        more_gridView = (GridView) layout.findViewById(R.id.more_Switch_the_section_gridview);
        popwindow_parent = (RelativeLayout) layout.findViewById(R.id.popwindow_parent);
        gridView.setEnabled(false);
        enit_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                for (int i = 0; i < tablistBeen_array.size(); i++) {
                    tablistBeen_array.get(i).setFlg(true);
                }
                handler.sendEmptyMessage(400);

                if (enit_text.getText().equals("编辑")) {
                    enit_text.setText("完成");

                    gridView.setEnabled(true);
//                    切换内容的点击事件
                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            if (tablistBeen_array.size() > 4) {
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

                            alllistBeen_aray.remove(DOposition);
                            handler.sendEmptyMessage(400);

                        }
                    });


//不可编辑
                } else {

                    enit_text.setText("编辑");

                    gridView.setEnabled(false);

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

        handler.sendEmptyMessage(400);
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

//        点击 X 号  Popwindow  消失
        dele_imag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
            }
        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }
}

package comuxi.example.administrator.panda_channel.moudel.Home;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import comuxi.example.administrator.panda_channel.Base.BaseFragment;
import comuxi.example.administrator.panda_channel.HistroyGreeDao.DaoMaster;
import comuxi.example.administrator.panda_channel.HistroyGreeDao.DaoSession;
import comuxi.example.administrator.panda_channel.HistroyGreeDao.HistroyGreeDao;
import comuxi.example.administrator.panda_channel.HistroyGreeDao.HistroyGreeDaoDao;
import comuxi.example.administrator.panda_channel.R;
import comuxi.example.administrator.panda_channel.Utils.ACache;
import comuxi.example.administrator.panda_channel.VideoplayerActivity;
import comuxi.example.administrator.panda_channel.app.App;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.Home_CCTV_TextBean;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.Home_China_Movie_Text;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.Home_Data_TextBean;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.Look_Down_Text;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.UpDateLoading;
import comuxi.example.administrator.panda_channel.moudel.Home.Adapter.Home_proRecycle_Adapter;
import comuxi.example.administrator.panda_channel.web_view.Home_Web_View_;


/**
 * Created by Administrator on 2017/7/11.
 */

public class HomeFragment extends BaseFragment implements HomeContract.View {
    HomePresenter homePresenter;
    HomeContract.presenter home_present;
    //    private ProgressDialog dialog;
    Unbinder unbinder;
    @BindView(R.id.my_xrecycle_home)
    XRecyclerView myXrecycleHome;
    @BindView(R.id.progress_bar_id)
    RelativeLayout progressBarId;
    private String vsinurl;
    //        请求到的 首页所有的 数据
    private ArrayList<Object> home_data_object = new ArrayList<>();
    private ArrayList<Home_Data_TextBean.DataBean> home_data = new ArrayList<>();

    private static int versionCode;
    private Home_proRecycle_Adapter home_adapter;
    //     历史记录 数据库
    private HistroyGreeDaoDao getdp;

    //     数据库的实体类
    int total = 0;
    //    查询数据库 里面的  集合
    private List<HistroyGreeDao> histroTextBeen_list;


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

/////////
    private boolean flag = false;
    @Override
    protected int getlayoutID() {
        return R.layout.login_home_fragment;
    }

    @Override
    protected void init(View view) {

        shouProgress();
    }


    @Override
    protected void loadData() {

        getAppVersionName(App.content);

        home_present.start();

//历史记录的 数据库
        getdp = getdp();
//        数据库扫除
//        getdp.deleteAll();
// 查询 数据库 中的 内容
        histroTextBeen_list = selectHieGreedao();

//历史记录 的  实体类

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

//                    轮播图视频 存入 历史记录的数据库
                    if (histroTextBeen_list.size() == 0) {
                        HistroyGreeDao      histroTextBean = new HistroyGreeDao();
                        histroTextBean.setName(bigImgBean.getTitle());
                        histroTextBean.setData("00");
                        histroTextBean.setImagpath(bigImgBean.getUrl() + "?pid=" + bigImgBean.getPid());
                        histroTextBean.setMoviepath(bigImgBean.getImage());
                        getdp.insert(histroTextBean);
                    } else {
                        for (int i = 0; i < histroTextBeen_list.size(); i++) {
                            if (bigImgBean.getTitle().equals(getdp.queryBuilder().build().list().get(i).getName())) {
                                flag = true;
                                return;
                            }
                        }
                        if (flag == true) {
                            flag = false;
                        } else {
                            HistroyGreeDao      histroTextBean = new HistroyGreeDao();
                            histroTextBean.setName(bigImgBean.getTitle());
                            histroTextBean.setData("00");
                            histroTextBean.setImagpath(bigImgBean.getUrl() + "?pid=" + bigImgBean.getPid());
                            histroTextBean.setMoviepath(bigImgBean.getImage());
                            getdp.insert(histroTextBean);
                        }
                    }

//

                }

            }


            @Override
//            精彩推荐的 点击事件
            public void get_wonderful_Click(Home_Data_TextBean.DataBean.AreaBean.ListscrollBean home_data) {
                Intent Video_intent = new Intent(App.content, VideoplayerActivity.class);
//                Video_intent.putExtra("pid", home_data.getPid());
//                Video_intent.putExtra("video_title", home_data.getTitle());
//                Video_intent.putExtra("video_imag", home_data.getImage());
                startActivity(Video_intent);


                //                    精彩推荐 存入 历史记录的数据库
                if (histroTextBeen_list.size() == 0) {
                    HistroyGreeDao      histroTextBean = new HistroyGreeDao();
                    histroTextBean.setName(home_data.getTitle());
                    histroTextBean.setData("00");
                    histroTextBean.setImagpath(home_data.getUrl() + "?pid=" + home_data.getPid());
                    histroTextBean.setMoviepath(home_data.getImage());
                    getdp.insert(histroTextBean);
                } else {
                    for (int i = 0; i < histroTextBeen_list.size(); i++) {
                        if (home_data.getTitle().equals(getdp.queryBuilder().build().list().get(i).getName())) {
                            flag = true;
                            return;
                        }
                    }
                    if (flag == true) {
                        flag = false;
                    } else {
                        HistroyGreeDao      histroTextBean = new HistroyGreeDao();
                        histroTextBean.setName(home_data.getTitle());
                        histroTextBean.setData("00");
                        histroTextBean.setImagpath(home_data.getUrl() + "?pid=" + home_data.getPid());
                        histroTextBean.setMoviepath(home_data.getImage());
                        getdp.insert(histroTextBean);
                    }
                }


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
                Log.e("TAG", "首页pid" + listBean.getPid());
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


    @Override
    public void showMessage(String s) {

        ACache aCache = ACache.get(App.content);
        Home_Data_TextBean home_data_textBean_aCache = (Home_Data_TextBean) aCache.getAsObject("Home_Data_TextBean");
        if (home_data_textBean_aCache != null) {
            home_data_object.add(home_data_textBean_aCache.getData().getBigImg().get(0));
            home_data_object.add(home_data_textBean_aCache.getData().getArea());
            home_data_object.add(home_data_textBean_aCache.getData().getPandaeye());
            home_data_object.add(home_data_textBean_aCache.getData().getPandalive());
            home_data_object.add(home_data_textBean_aCache.getData().getWalllive());
            home_data_object.add(home_data_textBean_aCache.getData().getChinalive());
            home_data_object.add(home_data_textBean_aCache.getData().getInteractive());
            home_data_object.add(home_data_textBean_aCache.getData().getCctv());
            home_data_object.add(home_data_textBean_aCache.getData().getList().get(0));
            home_data.add(home_data_textBean_aCache.getData());
            handler.sendEmptyMessage(300);


        } else {

        }

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
        dissProgress();
    }


    @Override
    public void shouProgress() {

        progressBarId.setVisibility(View.VISIBLE);

    }

    @Override
    public void dissProgress() {

        progressBarId.setVisibility(View.GONE);

    }

    //展示轮播图
    @Override
    public void showCarousel(Home_Data_TextBean data_textBean) {

    }

    //熊猫播报方法
    @Override
    public void showPandaBroadcast(Home_Data_TextBean data_textBean) {


    }

    //展示xRecycleView
    @Override
    public void showXrecycleView(final Home_Data_TextBean data_textBean) {


    }

    //    调用  数据库 的
    @Override
    public HistroyGreeDaoDao getdp() {

        DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(getContext(), "Histrogry.dp", null);
        SQLiteDatabase database = helper.getWritableDatabase();
        DaoMaster master = new DaoMaster(database);
        DaoSession daoSession = master.newSession();
        HistroyGreeDaoDao histroyGreeDaoDao = daoSession.getHistroyGreeDaoDao();


        return histroyGreeDaoDao;


    }

    //    查询数据库 中数据
    @Override
    public List<HistroyGreeDao> selectHieGreedao() {

        HistroyGreeDaoDao getdp = getdp();

        List<HistroyGreeDao> list = getdp.queryBuilder().list();

        Log.e("TAG", "刚开始进去看看" + list.size());

        return list;

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

    public static String getAppVersionName(Context context) {
        String versionName = "";

        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = packageInfo.versionName;
            versionCode = packageInfo.versionCode;
            Log.i("aaa", versionCode + "");
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.i("aaa", versionName);
        }
        return versionName;

    }

    /**
     * 提示版本更新的对话框
     */
    private void showDialogUpdate() {
        // 这里的属性可以一直设置，因为每次设置后返回的是一个builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // 设置提示框的标题
        builder.setTitle("版本升级").
                // 设置提示框的图标
                        setIcon(R.mipmap.ic_launcher).
                // 设置要显示的信息
                        setMessage("发现新版本！请及时更新").
                // 设置确定按钮
                        setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(MainActivity.this, "选择确定哦", 0).show();
                        dialog.dismiss();
                        loadNewVersionProgress();//下载最新的版本程序

                    }
                }).

                // 设置取消按钮,null是什么都不做，并关闭对话框
                        setNegativeButton("取消", null);
        // 生产对话框
        AlertDialog  alertDialog = builder.create();
        // 显示对话框
        alertDialog.show();
    }


    /**
     * 下载新版本程序，需要子线程
     */
    private void loadNewVersionProgress() {
        final String uri = vsinurl;
        final ProgressDialog pd;    //进度条对话框
        pd = new ProgressDialog(getActivity());
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMessage("正在下载更新");
        pd.show();
        //启动子线程下载任务
        new Thread() {
            @Override
            public void run() {
                try {
                    File file = getFileFromServer(uri, pd);
                    sleep(3000);
                    installApk(file);
                    pd.dismiss(); //结束掉进度条对话框
                } catch (Exception e) {
                    //下载apk失败
                    Log.i("abc", "下载失败");
//                    Toast.makeText(getActivity(), "下载新版本失败", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        }.start();
    }


    /**
     * 从服务器获取apk文件的代码
     * 传入网址uri，进度条对象即可获得一个File文件
     * （要在子线程中执行哦）
     */
    public File getFileFromServer(String uri, final ProgressDialog pd) throws Exception {
        //如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            //获取到文件的大小
            pd.setMax(conn.getContentLength());
            InputStream is = conn.getInputStream();
            long time = System.currentTimeMillis();//当前时间的毫秒数
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), time + "updata.apk");
            if (!file.exists())
                file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len;

            while ((len = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                total += len;
                //获取当前下载量
                pd.setProgress(total);
            }
            fos.close();
            bis.close();
            is.close();
            return file;
        } else {
            return null;
        }
    }

    /**
     * 安装apk
     */
    protected void installApk(File file) {
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        //执行的数据类型
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        startActivity(intent);
    }

    @Override
    public void getVersion(UpDateLoading upDateLoading) {
        String versionsNum = upDateLoading.getData().getVersionsNum();
        vsinurl = upDateLoading.getData().getVersionsUrl();
        int versionsInt = Integer.parseInt(versionsNum);
        if (versionCode < versionsInt) {
            getShowDialog();
        } else {
            Toast.makeText(getActivity(), "已经是最新版本", Toast.LENGTH_LONG).show();
        }
    }
    public void getShowDialog() {
        new AlertDialog.Builder(getActivity()).setTitle("版本升级")//设置对话框标题

                .setMessage("检测到最新版本，新版本对系统做了更好的优化")//设置显示的内容

                .setPositiveButton("立即跟新", new DialogInterface.OnClickListener() {//添加确定按钮


                    @Override

                    public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件

                        // TODO Auto-generated method stub
                        dialog.dismiss();
                        showDialogUpdate();
                        dialog.dismiss();

                    }

                }).setNegativeButton("稍后再说", new DialogInterface.OnClickListener() {//添加返回按钮


            @Override

            public void onClick(DialogInterface dialog, int which) {//响应事件

                // TODO Auto-generated method stub

                dialog.dismiss();

            }

        }).show();//在按键响应事件中显示此对话框
    }


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // TODO: inflate a fragment view
//        View rootView = super.onCreateView(inflater, container, savedInstanceState);
//        unbinder = ButterKnife.bind(this, rootView);
//        return rootView;
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        unbinder.unbind();
//    }

}

package comuxi.example.administrator.panda_channel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import comuxi.example.administrator.panda_channel.Base.BaseActivity;
import comuxi.example.administrator.panda_channel.app.App;
import comuxi.example.administrator.panda_channel.mode.CallBack.MyHttpCallBack;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.VideoplayerBean;
import comuxi.example.administrator.panda_channel.mode.Url_Path.Url;
import comuxi.example.administrator.panda_channel.mode.biz.PandaItemMode;
import comuxi.example.administrator.panda_channel.mode.biz.PandaMode;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class VideoplayerActivity extends BaseActivity {
    /**
     * 视频播放的 类
     * @return
     */
    String video_title, pid, video_imag;
    @BindView(R.id.jiecao_tv)
    fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard jcVideo;
    @BindView(R.id.activity_videoplayer)
    RelativeLayout activityVideoplayer;
//    @BindView(R.id.jc_video)
//    JCVideoPlayerStandard jcVideo;

    private ArrayList<VideoplayerBean.VideoBean.Chapters4Bean> biaoqing_array = new ArrayList();
    private ArrayList<VideoplayerBean.VideoBean.ChaptersBean> gaoqing_array = new ArrayList();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_videoplayer;
    }

    @Override
    protected void initView() {
//
//
////        JCVideoPlayerStandard.startFullscreen(
////                VideoplayerActivity.this,JCVideoPlayerStandard.class,
////               "http://202.108.17.101/v.cctv.com/flash/mp4video60/TMS/2017/06/12/d2a828ca5cd0400f82c2adee5581fc17_h2642000000nero_aac16.mp4"
////                ,"为什么播放不了");
//
//
//        jcVideo.setUp("http://vod.cntv.lxdns.com/flash/mp4video60/TMS/2017/06/12/d2a828ca5cd0400f82c2adee5581fc17_h264418000nero_aac32.mp4"
//                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,"就只为了测试");

        Intent intent = getIntent();

//        跳转   接受  数值
        pid = intent.getStringExtra("pid");
        video_title = intent.getStringExtra("video_title");
        video_imag = intent.getStringExtra("video_imag");

        PandaMode pandaMode = new PandaItemMode();

        pandaMode.getVideoplayer(pid, new MyHttpCallBack<VideoplayerBean>() {
            @Override
            public void onSuccess(final VideoplayerBean videoplayerBean) {

//            标清   地址
                final List<VideoplayerBean.VideoBean.Chapters4Bean> chapters4 = videoplayerBean.getVideo().getChapters4();
                biaoqing_array.addAll(chapters4);

                App.content.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("TAG", "我点击 播放视频的地址是" + biaoqing_array.get(0).getUrl());

                        jcVideo.setUp( biaoqing_array.get(0).getUrl()
                                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, videoplayerBean.getTitle());

                        Glide.with(VideoplayerActivity.this)
                                .load(biaoqing_array.get(0).getImage())
                                .into(jcVideo.thumbImageView);
                    }
                });

//             高清地址
                List<VideoplayerBean.VideoBean.ChaptersBean> chapters = videoplayerBean.getVideo().getChapters();
                gaoqing_array.addAll(chapters);

//                各种 按钮

//                分享监听
                jcVideo.set_Viewo_Click(new JCVideoPlayerStandard.Video_Click() {
                    @Override
                    public void Shar_Video(View view) {
                        share();
                    }

                    @Override
                    public void add_cang_Video(CompoundButton compoundButton, boolean b) {

                        boolean checked = compoundButton.isChecked();

                        if(checked == true) {
                            Toast.makeText(VideoplayerActivity.this, "已添加至收藏", Toast.LENGTH_SHORT).show();


                        }else{
                            Toast.makeText(VideoplayerActivity.this, "已取消至收藏", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void WatchthelistMonitor(View view) {
                        Toast.makeText(VideoplayerActivity.this, "列表", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void setgq() {
                        jcVideo.setUp(gaoqing_array.get(0).getUrl()
                                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, videoplayerBean.getTitle());
                        Glide.with(VideoplayerActivity.this)
                                .load(gaoqing_array.get(0).getImage())
                                .into(jcVideo.thumbImageView);
                    }

                    @Override
                    public void setbq() {

                        jcVideo.setUp(biaoqing_array.get(0).getUrl()
                                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, videoplayerBean.getTitle());
                        Glide.with(VideoplayerActivity.this)
                                .load(biaoqing_array.get(0).getImage())
                                .into(jcVideo.thumbImageView);
                    }
                });

            }

            @Override
            public void onError(int errorCode, String errorMsg) {

            Log.e("TAG","....................................");

            }
        });

    }

    @Override
    public void onBackPressed() {
        if (jcVideo.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        jcVideo.releaseAllVideos();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        jcVideo.clearSavedProgress(this, "");
    }


    private void shoucang() {

    }

    private void share() {

        UMImage image = new UMImage(VideoplayerActivity.this, video_imag);

        new ShareAction(VideoplayerActivity.this).withText(video_title + "!" + Url.TV_Url + pid)
                .withMedia(image)
                .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
                .setCallback(new UMShareListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {
                        Log.e("TAG", "onStart");
                    }

                    @Override
                    public void onResult(SHARE_MEDIA share_media) {

                        Log.e("TAG", "onResult");
///
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {

                        Log.e("TAG", "onError");
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media) {

                        Log.e("TAG", "onCancel");
                    }
                }).open();
    }

    //需回调此方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        UMShareAPI.get(VideoplayerActivity.this).onActivityResult(requestCode, resultCode, data);

    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        // TODO: add setContentView(...) invocation
//        ButterKnife.bind(this);
//    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        VideoplayerActivity.this.finish();
        return super.onKeyDown(keyCode, event);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

}

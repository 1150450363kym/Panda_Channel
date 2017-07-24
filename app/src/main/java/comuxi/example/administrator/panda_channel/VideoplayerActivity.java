package comuxi.example.administrator.panda_channel;

import android.content.Intent;
import android.util.Log;
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
import comuxi.example.administrator.panda_channel.Base.BaseActivity;
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

    private ArrayList<VideoplayerBean.VideoBean.Chapters4Bean> biaoqing_array = new ArrayList();
    private ArrayList<VideoplayerBean.VideoBean.ChaptersBean> gaoqing_array = new ArrayList();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_videoplayer;
    }

    @Override
    protected void initView() {

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

                Log.e("TAG", "我点击 播放视频的地址是" + biaoqing_array.get(0).getUrl());

                jcVideo.setUp(biaoqing_array.get(0).getUrl()
                        , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, video_title);

                        Glide.with(VideoplayerActivity.this)
                                .load(video_title)
                                .into(jcVideo.thumbImageView);

//             高清地址
                List<VideoplayerBean.VideoBean.ChaptersBean> chapters = videoplayerBean.getVideo().getChapters();
                gaoqing_array.addAll(chapters);


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



}

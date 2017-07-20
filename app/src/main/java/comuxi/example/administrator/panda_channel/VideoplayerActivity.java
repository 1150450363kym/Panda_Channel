package comuxi.example.administrator.panda_channel;

import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import comuxi.example.administrator.panda_channel.Base.BaseActivity;
import comuxi.example.administrator.panda_channel.Utils.Log_Utils;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.VideoplayerBean;
import comuxi.example.administrator.panda_channel.mode.Url_Path.Url;
import fm.jiecao.jcvideoplayer_lib.JCFullScreenActivity;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.PandaVedioPlayer;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class VideoplayerActivity extends BaseActivity {

//    @BindView(R.id.my_videoplayer)
//    JCVideoPlayerStandard myJiecaoVideoPlay;
//    @BindView(R.id.activity_videoplayer)
//    RelativeLayout activityVideoplayer;
//    @BindView(R.id.videoplayer_share)
//    ImageView videoplayerShare;
//    @BindView(R.id.videoplayer_shoucang)
//    CheckBox videoplayerShoucang;
    String video_title,pid,video_imag,url;

    private List<VideoplayerBean.VideoBean.ChaptersBean> list;

    /**
     * 视频播放的 类
     *
     * @return
     */
//
    @Override
    protected int getLayoutId() {
        return R.layout.activity_videoplayer;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        Intent intent = getIntent();

        pid = intent.getStringExtra("pid");
        video_title = intent.getStringExtra("video_title");
        video_imag = intent.getStringExtra("video_imag");

        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(Url.TV_Url+pid).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String s = response.body().string();
                Gson gson = new Gson();
                VideoplayerBean bean = gson.fromJson(s, VideoplayerBean.class);
                list.addAll(bean.getVideo().getChapters());

                for (int i=0;i<list.size();i++){

                    url = list.get(i).getUrl();

                }
                Log_Utils.log_d("TAG",url);
                JCFullScreenActivity.toActivity(VideoplayerActivity.this,
                        url,null,
                        PandaVedioPlayer.class, "...");

            }
        });



        if (video_imag == null) {
            video_imag = "http://img4.jiecaojingxuan.com/2016/11/23/00b026e7-b830-4994-bc87-38f4033806a6.jpg@!640_360";
        }

//        myJiecaoVideoPlay.setUp(Url.TV_Url + pid
//                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, video_title);
//        Glide.with(this)
//                .load(list.get(0).getImage())
//                .into(myJiecaoVideoPlay.ivThumb);
        String s = "http://vod.cntv.lxdns.com/flash/mp4video61/TMS/2017/07/14/c39ef06c39314cb6a9d6c25f6527c095_h264418000nero_aac32.mp4";




    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        myJiecaoVideoPlay.clearSavedProgress(this, "");
    }


//    @OnClick({R.id.videoplayer_share, R.id.videoplayer_shoucang})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.videoplayer_share:
//
//                share();
//
//                break;
//            case R.id.videoplayer_shoucang:
//
//
//
//                if(videoplayerShoucang.isChecked()){
//
//                    Toast.makeText(VideoplayerActivity.this, "已收藏，请到[我的收藏]查看", Toast.LENGTH_SHORT).show();
//                }else{
//
//                    Toast.makeText(VideoplayerActivity.this, "已取消收藏", Toast.LENGTH_SHORT).show();
//                }
//
//                shoucang();
//                break;
//        }
//    }

    private void shoucang() {


    }

    private void share() {

        UMImage image = new UMImage(VideoplayerActivity.this,video_imag);

        new ShareAction(VideoplayerActivity.this).withText(video_title+"!"+Url.TV_Url + pid)
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

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//
//        VideoplayerActivity.this.finish();
//        return super.onKeyDown(keyCode, event);
//
//    }
}

package comuxi.example.administrator.panda_channel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import comuxi.example.administrator.panda_channel.Base.BaseActivity;
import comuxi.example.administrator.panda_channel.mode.Url_Path.Url;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class VideoplayerActivity extends BaseActivity {

    @BindView(R.id.my_videoplayer)
    fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard myJiecaoVideoPlay;
    @BindView(R.id.activity_videoplayer)
    RelativeLayout activityVideoplayer;

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

        Intent intent = getIntent();

        String pid = intent.getStringExtra("pid");
        String video_title = intent.getStringExtra("video_title");
        String video_imag = intent.getStringExtra("video_imag");

        if (video_imag == null) {
            video_imag = "http://img4.jiecaojingxuan.com/2016/11/23/00b026e7-b830-4994-bc87-38f4033806a6.jpg@!640_360";
        }

        myJiecaoVideoPlay.setUp(Url.TV_Url + pid
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, video_title);
        Glide.with(this)
                .load(video_imag)
                .into(myJiecaoVideoPlay.thumbImageView);


    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myJiecaoVideoPlay.clearSavedProgress(this, "");
    }
}

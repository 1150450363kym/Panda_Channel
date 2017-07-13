package comuxi.example.administrator.panda_channel;

import comuxi.example.administrator.panda_channel.Base.BaseActivity;

public class VideoplayerActivity extends BaseActivity {

//    @BindView(R.id.jiecaovideoplayer)
//    JCVideoPlayer jiecaovideoplayer;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_videoplayer;
    }

    @Override
    protected void initView() {

//        jiecaovideoplayer.setUp("http://tv.cctv.com/2016/10/10/VIDEqlbYiOXMR1idfKoFeHBw161010.shtml","复原失传600年的珠光青瓷");

    }

    @Override
    protected void onPause() {
        super.onPause();

//        jiecaovideoplayer.releaseAllVideos();
    }
}

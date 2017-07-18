package comuxi.example.administrator.panda_channel;

import android.webkit.WebSettings;
import android.webkit.WebView;

import butterknife.BindView;
import comuxi.example.administrator.panda_channel.Base.BaseActivity;
import comuxi.example.administrator.panda_channel.Utils.ACache;

public class WebActivity extends BaseActivity {

    private WebSettings settings;
    private String stringurl;
    @BindView(R.id.webview)
    WebView webview;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected void initView() {

        settings =webview.getSettings();

        if(stringurl == null){


            stringurl = getIntent().getStringExtra("url");

        }else{

            ACache aCache = ACache.get(this);
            stringurl = aCache.getAsString("cache_url");

        }


        //      可以与什么交互
        settings.setJavaScriptEnabled(true);
//        将图片控制到适合webview的大小
        settings.setUseWideViewPort(true);

//        缩放至屏幕大小
        settings.setLoadWithOverviewMode(true);
        webview.loadUrl(stringurl);
    }


}

package comuxi.example.administrator.panda_channel.web_view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import comuxi.example.administrator.panda_channel.R;

/**
 * Created by Administrator on 2017/7/15.
 */

public class Home_Web_View_ extends Activity {
    @BindView(R.id.my_home_webview)
    WebView myHomeWebview;
    @BindView(R.id.web_view_back)
    ImageView webViewBack;
    @BindView(R.id.web_view_text)
    TextView webViewText;
    @BindView(R.id.web_view_fenxiang)
    ImageView webViewFenxiang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_web_view);
        ButterKnife.bind(this);
//        首页轮播图  的  WebView
        Intent intent = getIntent();
        String web_view_ration = intent.getStringExtra("web_view_url");
        if (web_view_ration != null) {

            myHomeWebview.loadUrl(web_view_ration);
            myHomeWebview.setWebViewClient(new WebViewClient());

            return;
        }


//        二十四节气————芒种


        String special_url = intent.getStringExtra("special_url");

        if (special_url != null) {
            webViewText.setVisibility(View.VISIBLE);
            myHomeWebview.loadUrl(special_url);
            myHomeWebview.setWebViewClient(new WebViewClient());
            return;
        }


    }
}

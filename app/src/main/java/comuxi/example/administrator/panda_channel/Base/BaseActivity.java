package comuxi.example.administrator.panda_channel.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import comuxi.example.administrator.panda_channel.app.App;

/**
 * Created by Administrator on 2017/7/11.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());
        App.content = this;
        ButterKnife.bind(this);
        initView();


    }

    //加载 Layout ID
    protected abstract int getLayoutId();

    // 初始化 Activity 数据
    protected abstract void initView();
}

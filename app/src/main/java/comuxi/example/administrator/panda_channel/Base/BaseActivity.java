package comuxi.example.administrator.panda_channel.Base;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;
import comuxi.example.administrator.panda_channel.app.App;

/**
 * Created by Administrator on 2017/7/11.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private BaseFragment lastFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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


//    抽取 切换Fagment 方法

    public BaseFragment changeFragment(Class<? extends BaseFragment> fragmentclass, int layoutId, boolean isHidden, Bundle bundle, boolean isBack) {

        FragmentManager fragmentmanager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentmanager.beginTransaction();
//        获取Fragment的类名，当做Tag;
        String simpleName = fragmentclass.getSimpleName();
//        根据tag来查找当前的fragment，如果为null，加载过一次
        BaseFragment currentFragment = (BaseFragment) fragmentmanager.findFragmentByTag(simpleName);

        if (currentFragment == null) {
            try {
//                通过Java 动态代理处理创建的对象
                currentFragment = fragmentclass.newInstance();
//                添加到FragmentragmentMange中
                transaction.add(layoutId, currentFragment, simpleName);

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
//显示  隐藏 方法
        if (isHidden) {
//            隐藏上一个Fragment；
            if (lastFragment != null) {
                transaction.hide(lastFragment);
            }
            transaction.show(currentFragment);
        } else {
            transaction.replace(layoutId, currentFragment, simpleName);
        }

        if (bundle != null) {
            currentFragment.setBundle(bundle);
        }
//会退栈
        if (isBack) {
            transaction.addToBackStack(simpleName);

        }

        transaction.show(currentFragment);
//        将当前的framgment 付给上一个
        lastFragment = currentFragment;

        transaction.commit();

        return lastFragment;

    }

    public void onResume() {
        super.onResume();

        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();

        MobclickAgent.onPause(this);
    }


}

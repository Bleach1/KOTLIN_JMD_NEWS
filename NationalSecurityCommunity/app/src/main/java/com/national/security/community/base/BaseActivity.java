package com.national.security.community.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.WindowManager;

import com.national.security.community.App;
import com.national.security.community.injection.component.ActivityComponent;
import com.national.security.community.injection.component.DaggerActivityComponent;
import com.national.security.community.injection.module.ActivityModule;
import com.national.security.community.mvp.IPresenter;
import com.national.security.community.mvp.IView;
import com.national.security.community.utils.Immersionutil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @ description:  基类Activity
 * @ author:  ljn
 * @ time:  2018/1/2
 */
public abstract class BaseActivity<T extends IPresenter> extends RxAppCompatActivity implements IView {

    @Inject
    protected T mPresenter;
    protected RxAppCompatActivity mContext;
    private Unbinder mUnBinder;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //禁止截屏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        //屏幕常量
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(getLayout());
        if (Build.VERSION.SDK_INT >= 22) {
            Immersionutil.setTitleBarColorFive(this);
        } else {
            Immersionutil.setTitleBarColor(this);
        }
        mUnBinder = ButterKnife.bind(this);
        mContext = this;
        initInject();

        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        App.getInstance().addActivity(this);
        initEventAndData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        mUnBinder.unbind();
        App.getInstance().removeActivity(this);
    }


    protected abstract void initInject();

    protected abstract int getLayout();

    protected abstract void initEventAndData();

}

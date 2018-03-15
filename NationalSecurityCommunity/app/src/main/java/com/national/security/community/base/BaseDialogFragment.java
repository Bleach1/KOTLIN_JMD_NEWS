package com.national.security.community.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.national.security.community.App;
import com.national.security.community.injection.component.DaggerFragmentComponent;
import com.national.security.community.injection.component.FragmentComponent;
import com.national.security.community.injection.module.FragmentModule;
import com.national.security.community.mvp.IPresenter;
import com.national.security.community.mvp.IView;

import javax.inject.Inject;

/**
 * @ description:  MVP模式的Base Dialog fragment
 * @ author:  ljn
 * @ time:  2018/3/13
 */

public abstract class BaseDialogFragment<T extends IPresenter> extends AbstractSimpleDialogFragment implements IView {

    @Inject
    protected T mPresenter;

    @SuppressWarnings("unchecked")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initInject();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroyView();
    }

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(App.getAppComponent())
                .fragmentModule(new FragmentModule(this))
                .build();
    }


    protected abstract void initInject();

}

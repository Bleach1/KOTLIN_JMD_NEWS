package com.national.security.community.base;

import com.national.security.community.mvp.IPresenter;
import com.national.security.community.mvp.IView;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @ description:  Presenter基类
 * @ author:  ljn
 * @ time:  2018/1/2
 */
public class BasePresenter<T extends IView> implements IPresenter<T> {

    protected RxAppCompatActivity mActivity;
    protected T mView;
    private CompositeDisposable mCompositeDisposable;

    public BasePresenter(RxAppCompatActivity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
        unSubscribe();
    }


    private void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    protected void addSubscribe(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }
}

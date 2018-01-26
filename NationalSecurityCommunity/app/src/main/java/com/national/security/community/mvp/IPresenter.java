package com.national.security.community.mvp;

/**
 * @ description: Presenter 基类
 * @ author: ljn
 * @ time: 2017/12/7
 */
public interface IPresenter<T extends IView> {
    void attachView(T view);

    void detachView();
}

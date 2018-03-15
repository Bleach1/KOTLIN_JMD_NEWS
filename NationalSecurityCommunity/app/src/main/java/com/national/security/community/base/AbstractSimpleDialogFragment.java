package com.national.security.community.base;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;


/**
 * Common simple dialog fragment
 *
 * @author quchao
 * @date 2017/11/28
 */

public abstract class AbstractSimpleDialogFragment extends DialogFragment {

    private Unbinder unBinder;
    public View mRootView;
    private CompositeDisposable mCompositeDisposable;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(), container, false);
        unBinder = ButterKnife.bind(this, mRootView);
        mCompositeDisposable = new CompositeDisposable();
        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        initEventAndData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
        unBinder.unbind();
    }

    protected abstract int getLayoutId();

    protected abstract void initEventAndData();
}

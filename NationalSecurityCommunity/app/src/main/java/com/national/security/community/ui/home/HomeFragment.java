package com.national.security.community.ui.home;

import android.os.Bundle;

import com.national.security.community.R;
import com.national.security.community.base.BaseFragment;

public class HomeFragment extends BaseFragment {
    @Override
    public void showMsg(String msg) {

    }

    @Override
    protected void initInject() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initEventAndData() {

    }

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }
}

package com.national.security.community.ui.mine;

import android.os.Bundle;

import com.national.security.community.R;
import com.national.security.community.base.BaseFragment;

public class MineFragment extends BaseFragment {
    @Override
    public void showMsg(String msg) {

    }

    @Override
    protected void initInject() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initEventAndData() {

    }

    public static MineFragment newInstance() {
        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }
}

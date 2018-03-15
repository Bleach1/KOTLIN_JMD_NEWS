package com.national.security.community.ui.msg;

import android.os.Bundle;

import com.national.security.community.R;
import com.national.security.community.base.BaseFragment;
import com.national.security.community.ui.home.HomeFragment;

public class MsgFragment extends BaseFragment {
    @Override
    public void showMsg(String msg) {

    }

    @Override
    protected void initInject() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_msg;
    }

    @Override
    protected void initEventAndData() {

    }


    public static MsgFragment newInstance() {
        Bundle args = new Bundle();
        MsgFragment fragment = new MsgFragment();
        fragment.setArguments(args);
        return fragment;
    }
}

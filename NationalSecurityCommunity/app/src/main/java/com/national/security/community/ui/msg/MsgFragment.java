package com.national.security.community.ui.msg;

import android.os.Bundle;
import android.util.Log;

import com.national.security.community.Config;
import com.national.security.community.R;
import com.national.security.community.base.BaseFragment;

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
        Log.i(Config.TAG, "initEventAndData: ");
    }


    public static MsgFragment newInstance() {
        Bundle args = new Bundle();
        MsgFragment fragment = new MsgFragment();
        fragment.setArguments(args);
        return fragment;
    }
}

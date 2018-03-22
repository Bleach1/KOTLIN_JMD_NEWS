package com.national.security.community.ui.msg;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.View;

import com.national.security.community.Config;
import com.national.security.community.R;
import com.national.security.community.base.BaseFragment;

import butterknife.BindView;

public class MsgFragment extends BaseFragment implements AppBarLayout.OnOffsetChangedListener {


    @BindView(R.id.fake_status_bar)
    View fake_status_bar;

    @BindView(R.id.appBar)
    AppBarLayout appBarLayout;

    @BindView(R.id.tl_active_tab)
    TabLayout tabLayout;

    @Override
    public void showMsg(String msg) {

    }

    @Override
    protected void initInject() {

    }

    public void setStatusBarBackgroundColor(String color) {
        fake_status_bar.setBackgroundColor(Color.parseColor(color));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_msg;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        appBarLayout.addOnOffsetChangedListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        appBarLayout.removeOnOffsetChangedListener(this);
    }

    public static MsgFragment newInstance() {
        Bundle args = new Bundle();
        MsgFragment fragment = new MsgFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

        if (tabLayout.getHeight() - appBarLayout.getHeight() == verticalOffset) {
            Log.i(Config.TAG, "onOffsetChanged: ********" + verticalOffset);
            fake_status_bar.setBackgroundColor(Color.parseColor("#ff0ff0"));
        } else {
            fake_status_bar.setBackgroundColor(Color.parseColor("#3ac569"));
        }
    }
}

package com.national.security.community.ui;

import android.util.Log;

import com.national.security.community.R;
import com.national.security.community.base.BaseActivity;
import com.national.security.community.utils.network.NetWorkUtil;

/**
 * @description: 商品详情页 Test
 * @author: ljn
 * @time: 2018/10/22
 */
public class GoodsActivity extends BaseActivity {
    @Override
    protected void onNetworkConnected(NetWorkUtil.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    protected void initInject() {

    }

    @Override
    protected int getLayout() {
        return R.layout.goods_details;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void showMsg(String msg) {

    }
}

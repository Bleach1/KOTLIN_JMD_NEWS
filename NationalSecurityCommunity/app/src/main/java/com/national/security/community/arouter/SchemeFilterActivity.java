package com.national.security.community.arouter;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @ description:  H5外部跳转
 * @ author:  ljn
 * @ time:  2018/2/5
 */
public class SchemeFilterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Uri uri = getIntent().getData();
        assert uri != null;
        ARouter.getInstance().build(uri).navigation();
        finish();
    }
}

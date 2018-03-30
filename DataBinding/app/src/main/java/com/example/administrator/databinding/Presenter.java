package com.example.administrator.databinding;

import android.view.View;

/**
 * 接着在activity/fragment中来实现Presenter接口，处理点击事件
 */
public interface Presenter extends View.OnClickListener {
    @Override
    void onClick(View v);
}

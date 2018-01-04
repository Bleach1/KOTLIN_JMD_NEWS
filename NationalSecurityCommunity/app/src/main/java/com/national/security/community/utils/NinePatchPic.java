package com.national.security.community.utils;

import android.util.Log;

import com.national.security.community.Config;

import javax.inject.Inject;

/**
 * @ description: .9 picture
 * @ author: ljn
 * @ time: 2017/12/12
 */
public class NinePatchPic {
    @Inject
    public NinePatchPic() {
    }

    //顶部：在水平拉伸的时候，保持其他位置不动，只在这个点的区域做无限的延伸
    //左边：在竖直拉伸的时候，保持其他位置不动，只在这个点的区域做无限的延伸
    //底部：在水平拉伸的时候，指定图片里的内容显示的区域
    //右边：在竖直拉伸的时候，指定图片里的内容显示的区域
    public void printWord() {
        Log.i(Config.TAG, "printWord: ");
    }
}

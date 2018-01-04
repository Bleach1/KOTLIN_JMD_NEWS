package com.national.security.community.utils;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class EnumUtil {
    // @KEEP 被这个注解修饰的函数或者类，在代码混淆进行压缩时会被保持住。
    public static final int ONE = 1;
    public static final int TWO = 2;

    @IntDef({ONE, TWO})//@StringDef
    @Retention(RetentionPolicy.SOURCE)
    public @interface Num {
    }

    private int num;

    @Num
    public int getNum() {
        return num;
    }

    public void setNum(@Num int num) {
        this.num = num;
    }
}

package com.national.security.community.widgets;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

public class GoodsBehavior extends CoordinatorLayout.Behavior<View> {
    //https://www.jianshu.com/p/b987fad8fcb4
    //https://www.jianshu.com/p/0fc1d9223d3d
    //https://blog.csdn.net/xiangzhihong8/article/details/54669277
    //https://github.com/cnbleu/SlideDetailsLayout

    private float deltaY;

    public GoodsBehavior() {
    }

    public GoodsBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        return dependency instanceof RecyclerView;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        if (deltaY == 0) {
            deltaY = dependency.getY() - child.getHeight();
        }

        float dy = dependency.getY() - child.getHeight();
        dy = dy < 0 ? 0 : dy;
        /*位置变化*/
        //float y = -(dy / deltaY) * child.getHeight();
        //child.setTranslationY(y);
        /*透明度变化*/
        float alpha = 1 - (dy / deltaY);
        child.setAlpha(alpha);
        return true;
    }
}

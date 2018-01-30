package com.national.security.community.widgets;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

public class ViewBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {

    // 列表顶部和title底部重合时，列表的滑动距离。
    private float deltaY;

    public ViewBehavior() {
    }

    public ViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     * @param parent     CoordinatorLayout
     * @param child      使用该Behavior的View
     * @param dependency 要监听的View,这里要监听RecyclerView
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, V child, View dependency) {
        return dependency instanceof RecyclerView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, V child, View dependency) {

        if (deltaY == 0) {
            deltaY = dependency.getY() - child.getHeight();
        }

        float dy = dependency.getY() - child.getHeight();
        dy = dy < 0 ? 0 : dy;

       /* float alpha = 1 - (dy / deltaY);  透明度变化
        child.setAlpha(alpha);*/

        float y = -(dy / deltaY) * child.getHeight();
        child.setTranslationY(y);
        return true;
    }
}

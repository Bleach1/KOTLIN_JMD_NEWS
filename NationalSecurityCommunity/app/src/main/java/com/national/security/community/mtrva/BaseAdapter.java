package com.national.security.community.mtrva;

import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.crazysunj.multitypeadapter.helper.RecyclerViewAdapterHelper;

/**
 * @description:
 * @author: ljn
 * @time: 2018/4/23
 */

public abstract class BaseAdapter<T extends MultiTypeTitleEntity,
        K extends BaseViewHolder, H extends RecyclerViewAdapterHelper<T, BaseAdapter>> extends BaseQuickAdapter<T, K> {

    protected H mHelper;

    public BaseAdapter(H helper) {
        super(helper.getData());
        mHelper = helper;
        mHelper.bindAdapter(this);
    }

    @Override
    protected K onCreateDefViewHolder(ViewGroup parent, int viewType) {
        return createBaseViewHolder(parent, mHelper.getLayoutId(viewType));
    }

    @Override
    protected int getDefItemViewType(int position) {
        return mHelper.getItemViewType(position);
    }

    public H getHelper() {
        return mHelper;
    }
}

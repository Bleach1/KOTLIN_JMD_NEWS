package com.national.security.community.adapter;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.national.security.community.R;
import com.national.security.community.data.model.ContentBean;
import com.oushangfeng.pinnedsectionitemdecoration.utils.FullSpanUtil;

import java.util.List;

public class ContentAdapter extends BaseMultiItemQuickAdapter<ContentBean, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ContentAdapter(List<ContentBean> data) {
        super(data);
        addItemType(ContentBean.TYPE_HEADER, R.layout.sticky_header);
        addItemType(ContentBean.TYPE_DATA, R.layout.content_grid_item);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        FullSpanUtil.onAttachedToRecyclerView(recyclerView, this, ContentBean.TYPE_HEADER);
    }

    @Override
    public void onViewAttachedToWindow(BaseViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        FullSpanUtil.onViewAttachedToWindow(holder, this, ContentBean.TYPE_HEADER);
    }

    @Override
    protected void convert(BaseViewHolder helper, ContentBean item) {
        switch (helper.getItemViewType()) {
            case ContentBean.TYPE_HEADER:
                helper.setText(R.id.sticky_title, item.getPinnedHeaderName());
                break;
            case ContentBean.TYPE_DATA:
                break;
            default:
                break;
        }
    }
}

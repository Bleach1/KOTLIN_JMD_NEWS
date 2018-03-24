package com.national.security.community.ui.Classification;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.national.security.community.R;
import com.national.security.community.adapter.ContentAdapter;
import com.national.security.community.base.BaseFragment;
import com.national.security.community.data.model.ContentBean;
import com.oushangfeng.pinnedsectionitemdecoration.PinnedHeaderItemDecoration;
import com.oushangfeng.pinnedsectionitemdecoration.callback.OnHeaderClickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
/**
 @ description:  海底月是天上月，眼前人是新上人  海底月捞不起，心上人不可及
 https://github.com/wjie2014/DoubleListViewLinkage
 https://github.com/ysnows/DoubleScrollVIew

 @ author:  ljn
 @ time:  2018/3/23 
 */
public class ClassificationFragment extends BaseFragment {

    @BindView(R.id.rv_content)
    RecyclerView rv_content;
    private ArrayList<String> strings;
    private List<ContentBean> itemDatas = new ArrayList<>();
    private GridLayoutManager gridLayoutManager;
    private ContentAdapter adapter;

    @Override
    public void showMsg(String msg) {

    }

    @Override
    protected void initInject() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_classification;
    }

    public static ClassificationFragment newInstance(ArrayList<String> datas) {
        Bundle args = new Bundle();
        args.putStringArrayList("datas", datas);
        ClassificationFragment fragment = new ClassificationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void setCheckPosition(int position) {
        mIndex = position;
        rv_content.stopScroll();
        smoothMoveToPosition(position);
    }


    private void smoothMoveToPosition(int position) {
        int firstItem = gridLayoutManager.findFirstVisibleItemPosition();
        int lastItem = gridLayoutManager.findLastVisibleItemPosition();
        if (position <= firstItem) {
            rv_content.scrollToPosition(position);
        } else if (position <= lastItem) {
            int top = rv_content.getChildAt(position - firstItem).getTop();
            rv_content.scrollBy(0, top);
        } else {
            rv_content.scrollToPosition(position);
            move = true;
        }
    }

    private boolean move = false;
    private int mIndex = 0;

    @Override
    protected void initEventAndData() {

        Bundle arguments = getArguments();
        if (arguments != null) {
            strings = arguments.getStringArrayList("datas");
        }

        for (int i = 0; i < strings.size(); i++) {
            itemDatas.add(new ContentBean(ContentBean.TYPE_HEADER, strings.get(i)));
            for (int i1 = 0; i1 < 10; i1++) {
                itemDatas.add(new ContentBean(ContentBean.TYPE_DATA));
            }

        }

        gridLayoutManager = new GridLayoutManager(getContext(), 3);
        rv_content.setLayoutManager(gridLayoutManager);
        rv_content.addItemDecoration(new PinnedHeaderItemDecoration.Builder(ContentBean.TYPE_HEADER).enableDivider(false)
                .setHeaderClickListener(new OnHeaderClickAdapter() {
                    @Override
                    public void onHeaderClick(View view, int id, int position) {
                        super.onHeaderClick(view, id, position);
                    }
                }).create());


        rv_content.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (move && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    move = false;
                    int n = mIndex - gridLayoutManager.findFirstVisibleItemPosition();
                    if (0 <= n && n < rv_content.getChildCount()) {
                        int top = rv_content.getChildAt(n).getTop();
                        rv_content.smoothScrollBy(0, top);
                    }
                }
            }


            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // strings.size()/gridLayoutManager.findFirstVisibleItemPosition();
                if (move) {
                    move = false;
                    int n = mIndex - gridLayoutManager.findFirstVisibleItemPosition();
                    if (0 <= n && n < rv_content.getChildCount()) {
                        int top = rv_content.getChildAt(n).getTop();
                        rv_content.scrollBy(0, top);
                    }
                }
            }

        });
        adapter = new ContentAdapter(itemDatas);
        rv_content.setAdapter(adapter);
    }
}

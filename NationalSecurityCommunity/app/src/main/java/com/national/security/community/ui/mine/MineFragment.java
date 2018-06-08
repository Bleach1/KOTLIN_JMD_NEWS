package com.national.security.community.ui.mine;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.national.security.community.R;
import com.national.security.community.base.BaseFragment;
import com.national.security.community.ui.Classification.ClassificationFragment;

import java.util.ArrayList;

import butterknife.BindView;
import me.yokeyword.fragmentation.ISupportFragment;

public class MineFragment extends BaseFragment {

    @BindView(R.id.rv_menu)
    RecyclerView mMenuRv;

    private ArrayList<String> datas = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;

    @Override
    public void showMsg(String msg) {

    }

    @Override
    protected void initInject() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }


    @Override
    protected void initEventAndData() {


        ISupportFragment classificationFragment = ClassificationFragment.newInstance(datas);
        if (findChildFragment(ClassificationFragment.class) == null) {
            loadRootFragment(R.id.content_Fl, classificationFragment);
        } else {
            classificationFragment = findChildFragment(ClassificationFragment.class);
        }
        for (int i = 0; i < 30; i++) {
            datas.add("狗不理包子");
        }
        linearLayoutManager = new LinearLayoutManager(getContext());
        mMenuRv.setLayoutManager(linearLayoutManager);
        mMenuRv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        BaseQuickAdapter adapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.menu_item, datas) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.tv_title, item);
            }
        };
        ClassificationFragment contentFragment = (ClassificationFragment) classificationFragment;
        adapter.setOnItemClickListener((adapter1, view, position) -> {
            int count = 0;
            for (int i = 0; i < position; i++) {
                count += 10;
            }
            count += position;
            contentFragment.setCheckPosition(count);

        });

        mMenuRv.setAdapter(adapter);


    }

    //将当前选中的item居中
    public void moveToCenter(int position) {
        //将点击的position转换为当前屏幕上可见的item的位置以便于计算距离顶部的高度，从而进行移动居中
        View childAt = mMenuRv.getChildAt(position - linearLayoutManager.findFirstVisibleItemPosition());
        if (childAt != null) {
            int y = (childAt.getTop() - mMenuRv.getHeight() / 2);
            mMenuRv.smoothScrollBy(0, y);
        }

    }

    public static MineFragment newInstance() {
        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }
}

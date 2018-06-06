package com.national.security.community.ui.home;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelperEx;
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.chad.library.adapter.base.BaseViewHolder;
import com.national.security.community.R;
import com.national.security.community.adapter.BaseDelegateAdapter;
import com.national.security.community.adapter.GlideImageLoader;
import com.national.security.community.base.BaseFragment;
import com.national.security.community.utils.StatusBarUtil;
import com.scwang.smartrefresh.header.DeliveryHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;

/**
 * @ description:  VLayout使用 https://www.jianshu.com/p/43d654e253bd
 * @ author:  ljn
 * @ time:  2018/3/19
 */
public class HomeFragment extends BaseFragment {


    @BindView(R.id.rv_container)
    RecyclerView containerRv;

    @BindView(R.id.refreshLayout_srl)
    SmartRefreshLayout smartRefreshLayoutSrl;

    @BindView(R.id.header)
    DeliveryHeader header;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private List<String> datas = new ArrayList<>();
    private Banner banner;

    @Override
    public void showMsg(String msg) {

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void initInject() {
        StatusBarUtil.setTranslucentForImageView(getActivity(), 0, smartRefreshLayoutSrl);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (banner != null) {
            banner.startAutoPlay();
        }
        //开始轮播
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }

    private int mDistance = 0;
    private int maxDistance = 255;//当距离在[0,255]变化时，透明度在[0,255之间变化]

    /**
     * 设置标题栏背景透明度
     *
     * @param alpha 透明度
     */
    private void setSystemBarAlpha(int alpha) {
        if (alpha >= 200) {
            alpha = 200;
        }
        toolbar.getBackground().setAlpha(alpha);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void initEventAndData() {

        for (int i = 0; i < 4; i++) {
            datas.add("http://img0.imgtn.bdimg.com/it/u=3565185884,2248353566&fm=27&gp=0.jpg");
        }
        setSystemBarAlpha(0);
        //noinspection ConstantConditions
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        containerRv.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 20);
        @SuppressWarnings("ConstantConditions") final VirtualLayoutManager layoutManager = new VirtualLayoutManager(getActivity());
        containerRv.setLayoutManager(layoutManager);
        containerRv.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mDistance += dy;
                float percent = mDistance * 1f / maxDistance;
                int alpha = (int) (percent * 255);
                setSystemBarAlpha(alpha);
            }
        });


        final DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, true);
        containerRv.setAdapter(delegateAdapter);
        List<DelegateAdapter.Adapter> adapters = new LinkedList<>();
        //banner
        adapters.add(new BaseDelegateAdapter(getActivity(), new LinearLayoutHelper(), R.layout.banner_ljn, 1, 1) {
            @Override
            public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                if (viewType == 1) {
                    banner = (Banner) LayoutInflater.from(getActivity())
                            .inflate(R.layout.banner_ljn, parent, false);

                    return new BaseViewHolder(banner);
                }

                return super.onCreateViewHolder(parent, viewType);
            }

            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {

                banner.setImageLoader(new GlideImageLoader());
                //设置图片集合
                banner.setImages(datas);
                banner.start();
            }
        });

//网格布局
        GridLayoutHelper layoutHelper = new GridLayoutHelper(4);
        layoutHelper.setMargin(0, 10, 0, 10);
        layoutHelper.setPadding(0, 20, 0, 10);

        // 控制子元素之间的垂直间距
        layoutHelper.setHGap(3);
        // 控制子元素之间的水平间距
        layoutHelper.setHGap(0);
        //权重
        layoutHelper.setWeights(new float[]{25, 25, 25, 25});
        layoutHelper.setAspectRatio(4f);
        adapters.add(new BaseDelegateAdapter(getActivity(), layoutHelper, R.layout.item, 8, 2) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                holder.setText(R.id.title, "Mine");
            }
        });

//一托N布局
        OnePlusNLayoutHelper helper = new OnePlusNLayoutHelper();
        helper.setBgColor(0xff876384);
        helper.setMargin(10, 0, 10, 0);
        helper.setPadding(10, 10, 10, 10);
        adapters.add(new BaseDelegateAdapter(getActivity(), helper, R.layout.item, 4, 3) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                holder.itemView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
                holder.setText(R.id.title, "Their");
            }
        });

        OnePlusNLayoutHelperEx onePlusHelper = new OnePlusNLayoutHelperEx();
        onePlusHelper.setBgColor(0xff876384);
        onePlusHelper.setMargin(0, 10, 0, 10);
        // onePlusHelper.setColWeights(new float[]{40f, 45f, 15f, 60f, 0f});
        adapters.add(new BaseDelegateAdapter(getActivity(), onePlusHelper, R.layout.item, 5, 4) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                holder.itemView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
                holder.setText(R.id.title, "Your");
            }
        });

//吸X布局
        StickyLayoutHelper stickyHelper = new StickyLayoutHelper();
        stickyHelper.setAspectRatio(4);
        adapters.add(new BaseDelegateAdapter(getActivity(), stickyHelper, R.layout.item, 1, 5) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                holder.itemView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100));
                holder.setText(R.id.title, "StickyLayoutHelper");
            }
        });
//固定布局，但之后当页面滑动到该图片区域才显示, 可以用来做返回顶部或其他书签等
        ScrollFixLayoutHelper scrollFixlayoutHelper = new ScrollFixLayoutHelper(FixLayoutHelper.BOTTOM_RIGHT, 100, 100);
        scrollFixlayoutHelper.setShowType(ScrollFixLayoutHelper.SHOW_ON_LEAVE);
        adapters.add(new BaseDelegateAdapter(getActivity(), scrollFixlayoutHelper, R.layout.item, 1, 6) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                holder.setText(R.id.title, "UP");
            }
        });

//瀑布流
        StaggeredGridLayoutHelper staggeredGridLayoutHelper = new StaggeredGridLayoutHelper(3, 20);
        staggeredGridLayoutHelper.setBgColor(0xff876384);
        adapters.add(new BaseDelegateAdapter(getActivity(), staggeredGridLayoutHelper, R.layout.item, 20, 7) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.height = 260 + position % 7 * 20;
                holder.itemView.setLayoutParams(layoutParams);
                holder.setText(R.id.title, "Stagger");
            }
        });
//栏格布局  显示在一行
        ColumnLayoutHelper columnLayoutHelper = new ColumnLayoutHelper();
        columnLayoutHelper.setWeights(new float[]{20, 20, 20, 20, 20});
        columnLayoutHelper.setMarginBottom(20);
        adapters.add(new BaseDelegateAdapter(getActivity(), columnLayoutHelper, R.layout.item, 5, 8) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                holder.setText(R.id.title, "Column");
            }
        });
//通栏布局
        SingleLayoutHelper singleLayoutHelper = new SingleLayoutHelper();
        //设置间距
        singleLayoutHelper.setMargin(10, 10, 10, 10);
        adapters.add(new BaseDelegateAdapter(getActivity(), singleLayoutHelper, R.layout.item, 1, 9) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                holder.setText(R.id.title, "Single");
            }
        });

        delegateAdapter.setAdapters(adapters);


    }

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }
}

package com.national.security.community.ui.main;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.kennyc.view.MultiStateView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.national.security.community.App;
import com.national.security.community.Config;
import com.national.security.community.R;
import com.national.security.community.architecture_components.LifeListener;
import com.national.security.community.base.BaseActivity;
import com.national.security.community.event.MessageEvent;
import com.national.security.community.ui.CustomDialog;
import com.national.security.community.ui.home.HomeFragment;
import com.national.security.community.ui.mine.MineFragment;
import com.national.security.community.ui.msg.MsgFragment;
import com.national.security.community.utils.JNIUtil;
import com.national.security.community.utils.NinePatchPic;
import com.national.security.community.utils.UniqueIDUtil;
import com.national.security.community.utils.network.NetWorkUtil;
import com.national.security.community.widgets.BottomBar;
import com.national.security.community.widgets.BottomBarTab;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import hugo.weaving.DebugLog;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * @ description:  https://github.com/alibaba/ARouter
 * https://www.cnblogs.com/wjtaigwh/p/6689684.html 购物车动画
 * @ author:  ljn
 * @ time:  2018/2/5
 */
@Route(path = "/main/MainActivity")
public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @Inject
    NinePatchPic ninePatchPic;

    @BindView(R.id.multiStateView)
    MultiStateView multiStateView;

    private long mExitTime = 0;
    private ISupportFragment[] mFragments = new ISupportFragment[3];

    @BindView(R.id.bottomBar)
    BottomBar bottomBar;

    /**
     * 测试 Lifecycle
     *
     * @param savedInstanceState
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLifecycle().addObserver(new LifeListener());
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    // @OnClick(R.id.sample_text)
    // void onClick() {

        /*ARouter.getInstance().build("/login/LoginActivity")
                .withLong("key1", 666L)
                .withString("key3", "888")
                .withSerializable("key4", new TestBean("Jack"))
                .navigation();*/

    //PhotoUtil.openAlbum(this, Config.SAVE_PHOTO_PATH);
    //  PhotoUtil.openCamera(this);
    // mPresenter.requestPermission();

       /* Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
        openAlbumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "file*//*");
        startActivityForResult(openAlbumIntent, 0);*/
    // }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    Log.i(Config.TAG, "onActivityResult: " + selectList.size());
                    break;
            }
        }
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }


    @SuppressLint("CheckResult")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @DebugLog
    @Override
    protected void initEventAndData() {
        Log.i(Config.TAG, "initEventAndData2: " + JNIUtil.show());
        Log.i(Config.TAG, "initEventAndData: " + UniqueIDUtil.getUniqueID());
        CustomDialog customDialog = new CustomDialog();
        customDialog.show(getSupportFragmentManager(), "");
        if (findFragment(HomeFragment.class) == null) {
            mFragments[0] = HomeFragment.newInstance();
            mFragments[1] = MsgFragment.newInstance();
            mFragments[2] = MineFragment.newInstance();
            loadMultipleRootFragment(R.id.container_fl,
                    0,
                    mFragments[0],
                    mFragments[1],
                    mFragments[2]);
        } else {
            mFragments[0] = findFragment(HomeFragment.class);
            mFragments[1] = findFragment(MsgFragment.class);
            mFragments[2] = findFragment(MineFragment.class);
        }
        // mPresenter.loadHome();
        // mPresenter.requestPermission();
        ninePatchPic.printWord();
        multiStateView.setViewState(0);
//调用Room数据库
       /* AppDatabase.getDatabase(this).getUserEntityDao().getUserList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userEntities -> {
                });*/
        bottomBar
                .addItem(new BottomBarTab(this, R.drawable.homepage, "首页"))
                .addItem(new BottomBarTab(this, R.drawable.activity, "活动"))
                .addItem(new BottomBarTab(this, R.drawable.personal, "我的"));

        // 模拟未读消息
        bottomBar.getItem(0).setUnreadCount(9);
        bottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position], mFragments[prePosition]);
                if (mFragments[position] instanceof MsgFragment) {

                    ((MsgFragment) mFragments[position]).setStatusBarBackgroundColor("#3ac569");
                }
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
                // 在FirstPagerFragment,FirstHomeFragment中接收, 因为是嵌套的Fragment
                // 主要为了交互: 重选tab 如果列表不在顶部则移动到顶部,如果已经在顶部,则刷新

            }
        });


        /*Map遍历建议*/
        Map<String, String> map = new HashMap<>();
        map.put("1", "3");
        map.put("2", "2");
        map.put("3", "1");
//key
        for (String key : map.keySet()) {
            Log.i(Config.TAG, "initEventAndData: " + key);
        }
//values
        for (String value : map.values()) {
            Log.i(Config.TAG, "initEventAndData: " + value);
        }
//all
        for (Map.Entry<String, String> entry : map.entrySet()) {
            Log.i(Config.TAG, "initEventAndData: " + "key" + entry.getKey() + "value" + entry.getValue());
        }
    }

   /* private int whichDay(@EnumUtil.Num int day) {
        return 0;
    }*/

    @Override
    public void showMsg(String msg) {
        Toast.makeText(this, "弹一个", Toast.LENGTH_LONG).show();
        EventBus.getDefault().post(new MessageEvent("Hello !....."));
    }

    @Override
    public void showProgress() {
    }

    @Override
    public void hideProgress() {

    }

    @SuppressLint("MissingPermission")
    @Override
    public void granted() {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "17615013866"));
        startActivity(intent);
    }

    @Override
    public void denied_never_ask_again() {

    }

    @Override
    public void denied() {

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            if (System.currentTimeMillis() - mExitTime > 2000) {
                Toast.makeText(MainActivity.this, R.string.exit_app,
                        Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                App.getInstance().exitApp();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    public void viewUrl(String url, String mimeType) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(url), mimeType);
        if (getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
            try {
                startActivity(intent);
            } catch (ActivityNotFoundException ignored) {

            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void createThread() {

       /* int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
        int KEEP_ALIVE_TIME = 1;
        TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
        BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();

        ExecutorService executorService = new ThreadPoolExecutor(NUMBER_OF_CORES,
                NUMBER_OF_CORES * 2,
                KEEP_ALIVE_TIME,
                KEEP_ALIVE_TIME_UNIT,
                taskQueue,
                new BackgroundThreadFactory(),
                new DefaultRejectedExecutionHandler()); //执行任务
        executorService.execute(() -> {
//doSomething...
            Log.i(Config.TAG, "createThread: " + Thread.currentThread().getName());
        });*/


        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                3,
                5,
                1,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(128));
        threadPoolExecutor.execute(() -> Log.i(Config.TAG, "run: " + Thread.currentThread().getName()));
        threadPoolExecutor.shutdown();
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }

    @Override
    protected void onNetworkConnected(NetWorkUtil.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    /**
     * 屏幕常量处理
     * android:keepScreenOn="true"
     */
    @Override
    protected void onResume() {
        super.onResume();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
}

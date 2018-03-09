package com.national.security.community.ui.main;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.kennyc.view.MultiStateView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.national.security.community.App;
import com.national.security.community.Config;
import com.national.security.community.R;
import com.national.security.community.base.BaseActivity;
import com.national.security.community.event.MessageEvent;
import com.national.security.community.ui.login.LoginActivity;
import com.national.security.community.utils.JNIUtil;
import com.national.security.community.utils.NinePatchPic;
import com.national.security.community.utils.SharedPreferencesUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import hugo.weaving.DebugLog;

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
    @BindView(R.id.sample_text)
    TextView textView;
    @BindView(R.id.multiStateView)
    MultiStateView multiStateView;
    private long mExitTime = 0;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @OnClick(R.id.sample_text)
    void onClick() {
        startActivity(new Intent(this, LoginActivity.class));

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
    }

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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @DebugLog
    @Override
    protected void initEventAndData() {

        long startTime = System.currentTimeMillis();
        SharedPreferencesUtil.save("ljn", "sds");
        textView.setText(R.string.hello_world);
        mPresenter.loadHome();
        ninePatchPic.printWord();
        multiStateView.setViewState(0);
//调用Room数据库
       /* AppDatabase.getDatabase(this).getUserEntityDao().getUserList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userEntities -> {
                });*/
        Log.i(Config.TAG, "initEventAndData: " + JNIUtil.show());
        createThread();
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

}

package com.national.security.community.ui.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kennyc.view.MultiStateView;
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

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @Inject
    NinePatchPic ninePatchPic;
    @BindView(R.id.sample_text)
    TextView textView;
    @BindView(R.id.iv_test)
    ImageView imageView;
    @BindView(R.id.multiStateView)
    MultiStateView multiStateView;
    private long mExitTime = 0;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @OnClick(R.id.sample_text)
    void onClick() {
        startActivity(new Intent(this, LoginActivity.class));
        // mPresenter.requestPermission();
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {
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
    }


   /* private int whichDay(@EnumUtil.Num int day) {
        return 0;
    }*/

    @Override
    public void showMsg(String msg) {
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
}

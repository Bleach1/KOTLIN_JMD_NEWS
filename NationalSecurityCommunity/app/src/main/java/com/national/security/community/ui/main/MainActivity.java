package com.national.security.community.ui.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.ImageView;
import android.widget.TextView;

import com.kennyc.view.MultiStateView;
import com.national.security.community.R;
import com.national.security.community.base.BaseActivity;
import com.national.security.community.event.MessageEvent;
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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @OnClick(R.id.sample_text)
    void onClick() {
        mPresenter.requestPermission();
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
}

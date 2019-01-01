package com.national.security.community.ui.main;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.national.security.community.Config;
import com.national.security.community.base.BasePresenter;
import com.national.security.community.data.model.HomeBean;
import com.national.security.community.data.model.TestBean;
import com.national.security.community.helper.RetrofitHelper;
import com.national.security.community.utils.MyObserver;
import com.national.security.community.utils.RxUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @ description:回家洗干净  https://www.zhihu.com/question/34907211/answer/60369202(Python)
 * @ author: ljn
 * @ time: 2017/12/8
 */
public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private RetrofitHelper mRetrofitHelper;
    private RxAppCompatActivity mActivity;

    @Inject
    MainPresenter(RetrofitHelper mRetrofitHelper, RxAppCompatActivity mActivity) {
        super(mActivity);
        this.mRetrofitHelper = mRetrofitHelper;
        this.mActivity = mActivity;
    }

    @Override
    public void loadData(String url) {
        DisposableSubscriber<TestBean> disposableSubscriber = mRetrofitHelper.fetchDailyListInfo2()
                .compose(mActivity.bindToLifecycle())
                .compose(RxUtil.handleResult())
                .subscribeWith(new DisposableSubscriber<TestBean>() {
                    @Override
                    public void onNext(TestBean baseBean) {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        addSubscribe(disposableSubscriber);

    }

    @SuppressLint({"CheckResult", "MissingPermission"})
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void requestPermission() {
        RxPermissions rxPermission = new RxPermissions(mActivity);
        rxPermission
                .requestEach(Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_CALENDAR,
                        Manifest.permission.READ_CALL_LOG,
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.READ_SMS,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA,
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.SEND_SMS)
                .subscribe(permission -> {
                    if (permission.granted) {
                        // 用户已经同意该权限
                        Log.d(Config.TAG, permission.name + " is granted.");
                        Log.i("ljn", "onCreate: \n"
                               /* + "设备系统版本号:" + DeviceUtils.getSDKVersionName() + "\n"
                                + "设备系统版本码:" + DeviceUtils.getSDKVersionCode() + "\n"
                                + "设备 AndroidID:" + DeviceUtils.getAndroidID() + "\n"
                                + "设备 MAC 地址:" + DeviceUtils.getMacAddress() + "\n"
                                + "设备厂商:" + DeviceUtils.getManufacturer() + "\n"
                                + "设备型号:" + DeviceUtils.getModel() + "\n"
                                + "设备码:" + PhoneUtils.getDeviceId() + "\n"
                                + "IMEI 码:" + PhoneUtils.getIMEI() + "\n"
                                + "移动终端类型:" + PhoneUtils.getPhoneType() + "\n"
                                + "IMSI 码:" + PhoneUtils.getIMSI() + "\n"*/
                        );
                        mView.granted();
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                        mView.denied_never_ask_again();
                        Log.d(Config.TAG, permission.name + " is denied. More info should be provided.");
                    } else {
                        // 用户拒绝了该权限，并且选中『不再询问』
                        mView.denied();
                        Log.d(Config.TAG, permission.name + " is denied.");
                    }
                });
        Log.i(Config.TAG, "requestPermission: fuck");
    }

    @Override
    public void loadHome() {
        mRetrofitHelper.loadHome()
                .compose(mActivity.bindToLifecycle())
                .compose(RxUtil.handleObservableResult())
                .subscribe(new MyObserver<HomeBean>() {

                    @Override
                    protected void _onNext(HomeBean homeBean) {
                        mView.showMsg("");
                    }

                    @Override
                    protected void _onError(String msg) {
                        mView.showMsg(msg);
                        Log.i(Config.TAG, "_onError: " + msg);
                    }
                });
    }

}

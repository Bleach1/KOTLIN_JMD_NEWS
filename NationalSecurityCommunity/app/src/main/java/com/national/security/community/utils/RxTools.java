package com.national.security.community.utils;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxCompoundButton;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * x
 *
 * @description: RxJava+RxBinding实际应用
 * @author: ljn
 * @time: 2018/6/27
 */
public class RxTools {

    /**
     * 优化搜索功能
     */
    @SuppressLint("CheckResult")
    public static void search(TextView view) {
        RxTextView.textChanges(view)
                // 跳过一开始et内容为空时的搜索
                .skip(1)
                //debounce 在一定的时间内没有操作就会发送事件
                .debounce(1000, TimeUnit.MILLISECONDS)
                //下面这两个都是数据转换
                //flatMap：当同时多个网络请求访问的时候，前面的网络数据会覆盖后面的网络数据
                //switchMap：当同时多个网络请求访问的时候，会以最后一个发送请求为准，前面网路数据会被最后一个覆盖
                .switchMap((Function<CharSequence, ObservableSource<List<String>>>) charSequence -> {
                    String searchKey = charSequence.toString();
                    System.out.println("binding=======搜索内容:" + searchKey);
                    //这里执行网络操作，获取数据
                    List<String> list = new ArrayList<>();
                    list.add("小刘哥");
                    list.add("可爱多");

                    return Observable.just(list);
                })
                // .onErrorResumeNext()
                //网络操作，获取我们需要的数据
                .subscribeOn(Schedulers.io())
                //界面更新在主线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(strings -> System.out.println("binding=======搜索到" + strings.size() + "条数据"));

    }

    /**
     * 连续点击
     */
    @SuppressLint("CheckResult")
    public static void multipleClicks(View view) {
        RxView.clicks(view)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(c -> System.out.println("binding=======点击了按钮"));

    }

    /**
     * 长按事件
     */
    @SuppressLint("CheckResult")
    public static void longClick(View view) {
        RxView.longClicks(view)
                .subscribe(c -> System.out.println("binding=======长按了按钮"));
    }


    /**
     * View的选中状态
     */

    @SuppressLint("CheckResult")
    public static void isChecked(CheckBox checkBox) {
        RxCompoundButton.checkedChanges(checkBox)
                .subscribe(aBoolean -> {

                });

    }

    /**
     * 获取验证码 倒计时
     */
    public static void getCode(Button button) {

        final int count = 60;
        Observable.interval(0, 1, TimeUnit.SECONDS)//设置0延迟，每隔一秒发送一条数据
                .take(count + 1)//设置循环次数
                .map(aLong -> count - aLong)
                .doOnSubscribe(disposable -> {
                    button.setEnabled(false);
                    button.setBackgroundColor(Color.parseColor("#39c6c1"));
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long value) {
                        button.setText(String.valueOf(value));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        button.setText("重新获取");
                        button.setEnabled(true);
                        button.setBackgroundColor(Color.parseColor("#d1d1d1"));
                    }
                });

    }

    /**
     * 延迟执行
     */

    public static void delayExecution() {
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long value) {
                        System.out.println("binding=======value:" + value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    /**
     * 组合校验 使用两个  三个 Function3 ...
     */
    @SuppressLint("CheckResult")
    public static void jointCheck(EditText editText, EditText editText2, Button button) {
        Observable<CharSequence> name = RxTextView.textChanges(editText).skip(1);
        Observable<CharSequence> age = RxTextView.textChanges(editText2).skip(1);
        Observable.combineLatest(name, age, (charSequence, charSequence2) -> {
            boolean isNameEmpty = TextUtils.isEmpty(editText.getText());
            boolean isAgeEmpty = TextUtils.isEmpty(editText2.getText());
            return !isNameEmpty && !isAgeEmpty;
        }).subscribe(button::setEnabled);
    }

}

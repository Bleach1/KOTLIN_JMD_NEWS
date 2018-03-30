package com.example.administrator.databinding;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewStub;
import android.widget.Toast;

import com.example.administrator.databinding.base.BaseActivity;
import com.example.administrator.databinding.bean.UserBean;
import com.example.administrator.databinding.databinding.ActivityMainBinding;
import com.example.administrator.databinding.databinding.ViewStubBinding;

import java.util.Objects;

/**
 * @description: DataBinding Start
 * @author: ljn
 * @time: 2018/3/28
 */
public class MainActivity extends BaseActivity<ActivityMainBinding> implements Presenter {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void initView() {
        final UserBean userBean = new UserBean("L", "JN");
        mBinding.setVariable(com.example.administrator.databinding.BR.UserBean, userBean);
        mBinding.setVariable(com.example.administrator.databinding.BR.presenter, new Presenter());
        Objects.requireNonNull(mBinding.viewStub.getViewStub()).inflate();
        mBinding.viewStub.setOnInflateListener(new ViewStub.OnInflateListener() {
            @Override
            public void onInflate(ViewStub stub, View inflated) {
                ViewStubBinding binding = DataBindingUtil.bind(inflated);
                //绑定属性
                assert binding != null;
                binding.setVariable(com.example.administrator.databinding.BR.UserBean, userBean);
            }
        });
    }

    @SingleClick
    @Override
    public void onClick(View v) {

    }

    /**
     * @description: 绑定事件的一种(方法引用 需要与原方法对应 ex : onClick)
     * @author: ljn
     * @time: 2018/3/28
     */
    public class Presenter {

        //方法引用
        public void onClick(View view) {
            Toast.makeText(MainActivity.this, "绑定点击事件", Toast.LENGTH_LONG).show();
        }

        //监听器
        public void onClickListenerBinding(UserBean userBean) {
            Toast.makeText(MainActivity.this, userBean.lastName, Toast.LENGTH_LONG).show();
        }


    }


}

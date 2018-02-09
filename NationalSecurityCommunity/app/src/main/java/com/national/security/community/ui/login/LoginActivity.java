package com.national.security.community.ui.login;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.national.security.community.Config;
import com.national.security.community.R;
import com.national.security.community.data.model.TestBean;

@Route(path = "/login/LoginActivity")
public class LoginActivity extends AppCompatActivity {

    private ConstraintLayout constraintLayout;
    private ConstraintSet constraintSet;

    @Autowired(name = "key3")
    public String name;
    @Autowired(name = "key1")
    Long age;
    @Autowired(name = "key4")
    TestBean obj;
    private ImageView icon;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
        setContentView(R.layout.login_activity);
        constraintSet = new ConstraintSet();
        constraintLayout = findViewById(R.id.container_cl);
        icon = findViewById(R.id.xiaodai_icon);
        constraintSet.clone(constraintLayout);
        Log.i(Config.TAG, "onCreate: " + age + "----" + name + "---");
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void click(View v) {
        TransitionManager.beginDelayedTransition(constraintLayout);
        constraintSet.setMargin(R.id.editText2, ConstraintSet.START, 8);
        constraintSet.applyTo(constraintLayout);

    }


}

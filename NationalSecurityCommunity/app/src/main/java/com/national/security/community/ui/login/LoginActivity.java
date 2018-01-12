package com.national.security.community.ui.login;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionManager;
import android.view.View;

import com.national.security.community.R;

public class LoginActivity extends AppCompatActivity {

    private ConstraintLayout constraintLayout;
    private ConstraintSet constraintSet;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_acyivity);
        constraintSet = new ConstraintSet();
        constraintLayout = findViewById(R.id.container_cl);
        constraintSet.clone(constraintLayout);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void click(View v) {
        TransitionManager.beginDelayedTransition(constraintLayout);
        constraintSet.setMargin(R.id.editText2, ConstraintSet.START, 8);
        constraintSet.applyTo(constraintLayout);

    }
}

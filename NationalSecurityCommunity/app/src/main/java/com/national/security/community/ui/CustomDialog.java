package com.national.security.community.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.national.security.community.R;

import java.util.Objects;

public class CustomDialog extends DialogFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        assert container != null;
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        //显示自定义圆角
        Objects.requireNonNull(getDialog().getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //getDialog().xxxxx  属性设置
        getDialog().setCanceledOnTouchOutside(false);
        return inflater.inflate(R.layout.dialog_custom, container, false);
    }

   /* @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = Objects.requireNonNull(getActivity()).getLayoutInflater();
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.dialog_custom, null);
        builder.setView(view).setPositiveButton("Sign In", (dialog, which) -> {

        }).setNegativeButton("Cancel", null);
        return builder.create();
    }*/
}

package com.example.administrator.databinding.adapter;

import android.annotation.SuppressLint;
import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class CustomAttributeAdapter {

    @SuppressLint("CheckResult")
    @BindingAdapter({"app:imageUrl", "app:placeholder"})
    public static void loadImageFromUrl(ImageView imageView, String url, Drawable drawable) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(drawable);
        Glide.with(imageView.getContext()).load(url).apply(requestOptions).into(imageView);
    }

    //类型转换
    @BindingConversion
    public static ColorDrawable convertColorToDrawable(int color) {
        return new ColorDrawable(color);
    }


}

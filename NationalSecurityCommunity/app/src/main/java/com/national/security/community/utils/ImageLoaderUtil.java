package com.national.security.community.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestFutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.national.security.community.R;

import java.io.File;
import java.text.Format;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class ImageLoaderUtil {

    public static void loadImg(String url, Context context, ImageView imageView) {

        SimpleTarget<Drawable> simpleTarget = new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                imageView.setImageDrawable(resource);
            }
        };

        RequestOptions options = new RequestOptions()
                .transform(new BlurTransformation())//变换 圆角 模糊等 https://github.com/wasabeef/glide-transformations
                .diskCacheStrategy(DiskCacheStrategy.NONE)//缓存
                .skipMemoryCache(true)//禁用内存缓存
                .override(100, 200)//指定大小   Target.SIZE_ORIGINAL原始尺寸
                .error(R.mipmap.ic_launcher)//异常占位图
                .placeholder(R.mipmap.ic_launcher);//占位图

        Glide.with(context)
                .asBitmap()//静态图片  asGif()
                .load(url)
                .listener(new RequestListener<Bitmap>() {//加载监听
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .apply(options)
                .into(imageView);


        Glide.with(context)
                .load(url)
                .apply(options)
                .preload();//预加载


//子线程中执行
        FutureTarget<File> target = Glide.with(context.getApplicationContext())
                .asFile()
                .load(url)
                .submit();//只是下载
        try {
            File imageFile = target.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

/*
package com.national.security.community.utils.glide;

import android.app.ActivityManager;
import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

import java.io.InputStream;

*/
/**
 * @description: GlideModule
 * @author: ljn
 * @time: 2018/10/29
 *//*

@com.bumptech.glide.annotation.GlideModule
public class GlideModule extends AppGlideModule {
    */
/**
     * 如果是处于 lowMemory 的时候，将图片的 DecodeFormat 设置为 RGB_565 ，
     * RGB_565 和默认的 ARGB_8888 比，每个像素会少 2 个byte
     *
     * @param context
     * @param builder
     *//*

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (activityManager != null) {
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            builder.setDefaultRequestOptions(new RequestOptions().format(memoryInfo.lowMemory ? DecodeFormat.PREFER_RGB_565 : DecodeFormat.PREFER_ARGB_8888));
            //builder.setDecodeFormat(memoryInfo.lowMemory ? DecodeFormat.PREFER_RGB_565 : DecodeFormat.PREFER_ARGB_8888); 4.7以下
        }
    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        super.registerComponents(context, glide, registry);
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory());
    }
}
*/

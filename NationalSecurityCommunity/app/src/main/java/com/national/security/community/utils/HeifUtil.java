package com.national.security.community.utils;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@SuppressWarnings("ALL")
public class HeifUtil {
    /**
     * 是否支持HEIF格式图片
     *
     * @return
     */
    public static boolean isSupportHeif() {
        Log.e("ljn", "Build.MANUFACTURER:" + Build.MANUFACTURER
                + ", Build.VERSION.SDK_INT:" + Build.VERSION.SDK_INT);
        return "HUAWEI".equals(Build.MANUFACTURER) && Build.VERSION.SDK_INT > 27;
    }

    /**
     * 读取 显示
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static Drawable getHeifImageFromSdcardUseImageDecoder(String path) throws IOException {
        File file = new File(path);
        ImageDecoder.Source source = ImageDecoder.createSource(file);
        return ImageDecoder.decodeDrawable(source);
    }

    public static Bitmap getHeifImageFromSdcardUseBitmapFactory(String path) {
        return BitmapFactory.decodeFile(path);
    }

    /**
     * 扫描
     *
     * @param context
     */
    public static void getAllImagesLocal(final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Cursor cursor = context.getContentResolver().query(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
                while (cursor.moveToNext()) {
                    String path = cursor.getString(cursor
                            .getColumnIndex(MediaStore.Images.Media.DATA));
                    Log.e("ljn", "image path:" + path);
                }
            }
        }).start();
    }

    /**
     * HEIF转换JPEG
     */
    public static void HeifToJPEG() {
        try {
            Bitmap bmp = getHeifImageFromSdcardUseBitmapFactory("/sdcard/bird_burst.heic");
            File file = new File("/sdcard/download/bird_burst1.jpg");
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            Uri uri = Uri.fromFile(file);
            //sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

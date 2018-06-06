package com.national.security.community.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.storage.StorageManager;

import com.national.security.community.App;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FileUtil {

    /**
     * 打开指定文件
     *
     * @param file
     */
    private void openFileKinds(File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(file);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
//0.打开文件
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(uri, "file/*");
//1.打开图片
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(uri, "image/*");
//2.打开PDF文件
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(uri, "application/pdf");
//3.打开文本
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(uri, "text/plain");
//4.打开音频
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        intent.setDataAndType(uri, "audio/*");
//5.打开视频
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        intent.setDataAndType(uri, "video/*");
//6.打开CHM
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(uri, "application/x-chm");
//7.打开apk
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
//8.打开PPT
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
//9.打开excel
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(uri, "application/vnd.ms-excel");
//10.打开word
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(uri, "application/msword");
    }


    /**
     * 由于这里使用的是Android 手机内置存储，
     * 如果手机没有获得root权限的话文件浏览器是无法访问的，同样这种存储也会随之app被删除而被删除。
     *
     * @param context
     */
    private void BuiltInStorage(Context context) {
        //获取内置存储下的文件目录，可以用来保存不能公开给其他应用的一些敏感数据如用户个人信息
        //path:/data/data/应用包名/files/
        File filesDir = context.getFilesDir();
        //获取内置存储下的缓存目录，可以用来保存一些缓存文件如图片，当内置存储的空间不足时将系统自动被清除
        //path:/data/data/应用包名/cache/
        File cacheDir = context.getCacheDir();
        //其实是内部存储的根目录，在ES文件浏览器对应的是根目录
        File parentFile = Environment.getDataDirectory().getParentFile();
        //path:/data
        File dataDirectory = Environment.getDataDirectory();
        //path:/cache
        File downloadCacheDirectory = Environment.getDownloadCacheDirectory();
        //path:/system
        File rootDirectory = Environment.getRootDirectory();


    }

    /**
     * SD卡
     * 由于存储在sdcard上所以尽量不要存在敏感数据比如用户信息等，这里的文件也会随着app 被删除而被删除。
     *
     * @param context
     */
    private void ExtendedStorage(Context context) {
        //获取SD卡上的文件目录 ex:picture
        //path:SDCard/Android/data/应用包名/files/
        File externalFilesDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        //获取SD卡上的缓存目录，可以用来保存一些缓存文件如图片
        //path: SDCard/Android/data/应用包名/cache/
        File externalCacheDir = context.getExternalCacheDir();
    }

    /**
     * 公共存储 SD卡
     * 有时我们也是需要存储一些公共文件，而且希望这些文件能够不随着App被删除而被删除，
     * 例如我们录制的视频或者下载的音乐等。由于这个目录可以被任何app访问，
     * 所以我们在使用的时候是需要申请权限的。
     */
    private void PublicStorage() {
        //获取sdcard根目录
        //path:SDCard/xxx文件夹名字/
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
    }


    /**
     * 判断外置sdcard是否可以正常使用
     *
     * @return
     */
    private static Boolean existsSdcard() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable();
    }

    /**
     * 获取app的根目录
     *
     * @return 文件缓存根路径
     */
    public static String getDiskCacheRootDir() {
        File diskRootFile;
        if (existsSdcard()) {
            diskRootFile = App.getInstance().getExternalCacheDir();
        } else {
            diskRootFile = App.getInstance().getCacheDir();
        }
        String cachePath;
        if (diskRootFile != null) {
            cachePath = diskRootFile.getPath();
        } else {
            throw new IllegalArgumentException("disk is invalid");
        }
        return cachePath;
    }

    /**
     * 扩展卡内存
     *
     * @param mContext
     * @return
     */
    @SuppressWarnings("JavaReflectionMemberAccess")
    private static String getExtendedMemoryPath(Context mContext) {
        StorageManager mStorageManager = (StorageManager) mContext.getSystemService(Context.STORAGE_SERVICE);
        Class<?> storageVolumeClazz;
        try {
            storageVolumeClazz = Class.forName("android.os.storage.StorageVolume");
            assert mStorageManager != null;
            Method getVolumeList = mStorageManager.getClass().getMethod("getVolumeList");
            Method getPath = storageVolumeClazz.getMethod("getPath");
            Method isRemovable = storageVolumeClazz.getMethod("isRemovable");
            Object result = getVolumeList.invoke(mStorageManager);
            final int length = Array.getLength(result);
            for (int i = 0; i < length; i++) {
                Object storageVolumeElement = Array.get(result, i);
                String path = (String) getPath.invoke(storageVolumeElement);
                boolean removable = (Boolean) isRemovable.invoke(storageVolumeElement);
                if (removable) {
                    return path;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}

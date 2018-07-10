package com.national.security.community.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import com.national.security.community.App;
import com.national.security.community.Config;

public class SharedPreferencesUtil {

    private static final SharedPreferences preferences = App.instance.getSharedPreferences(Config.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);

    //保存
    public static void save(String key, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void save(String key, int value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static void save(String key, float value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public static void save(String key, long value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public static void save(String key, boolean value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    //获取
    public static String getString(String key, String defValue) {

        return preferences.getString(key, defValue);
    }

    public static int getInt(String key, int defValue) {

        return preferences.getInt(key, defValue);
    }

    public static float getFloat(String key, float defValue) {

        return preferences.getFloat(key, defValue);
    }

    public static long getLong(String key, int defValue) {

        return preferences.getLong(key, defValue);
    }

    public static Boolean getBoolean(String key, Boolean defValue) {
        return preferences.getBoolean(key, defValue);
    }
}

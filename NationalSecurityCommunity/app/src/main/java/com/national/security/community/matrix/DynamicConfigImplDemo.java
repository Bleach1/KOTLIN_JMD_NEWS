package com.national.security.community.matrix;

import com.tencent.mrs.plugin.IDynamicConfig;

public class DynamicConfigImplDemo implements IDynamicConfig {
    public DynamicConfigImplDemo() {
    }

    public boolean isFPSEnable() {
        return true;
    }

    public boolean isTraceEnable() {
        return true;
    }

    public boolean isMatrixEnable() {
        return true;
    }

    public boolean isDumpHprof() {
        return false;
    }

    @Override
    public String get(String key, String defStr) {
        //hook to change default values
        return defStr;
    }

    @Override
    public int get(String key, int defInt) {
        //hook to change default values
        return defInt;
    }

    @Override
    public long get(String key, long defLong) {
        //hook to change default values
        return defLong;
    }

    @Override
    public boolean get(String key, boolean defBool) {
        //hook to change default values
        return defBool;
    }

    @Override
    public float get(String key, float defFloat) {
        //hook to change default values
        return defFloat;
    }
}
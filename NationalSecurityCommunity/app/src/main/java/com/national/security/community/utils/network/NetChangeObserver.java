package com.national.security.community.utils.network;

public interface NetChangeObserver {
    /**
     * 网络连接回调 type为网络类型
     */
    void onNetConnected(NetWorkUtil.NetType type);

    /**
     * 没有网络
     */
    void onNetDisConnect();
}

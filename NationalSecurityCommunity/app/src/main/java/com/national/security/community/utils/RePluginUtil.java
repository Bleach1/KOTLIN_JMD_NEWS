package com.national.security.community.utils;

/**
 * l
 *
 * @ description:  RePlugin帮助类
 * @ author:  ljn
 * @ time:  2018/1/18
 */
public class RePluginUtil {
    /**
     * 下载插件
     *
     * @param url
     */
    public static void downLoadPlugin(String url) {

    }
    /*
      外置插件：
      1.download 插件 存到SD卡中
      2.安装RePlugin.install("/sdcard/exam.apk");
      3.升级插件同安装
      4.可进行预加载
        PluginInfo pi = RePlugin.install("/sdcard/exam_new.apk");
             if (pi != null) {
	         RePlugin.preload(pi);
            }
      5.写在插件 RePlugin.uninstall("exam"); exam插件名


      内置插件：
      1.将APK改名为：[插件名].jar
        放入主程序的assets/plugins目录
      2.删除内置插件：删除内置插件非常简单，直接移除相应的Jar文件，其余均交给RePlugin来自动化完成。
      3.升级：主程序随包升级   通过install方法升级
        只要APK的时间戳和大小发生变化就升级，若两者均无变化，则不会升级

   */

}

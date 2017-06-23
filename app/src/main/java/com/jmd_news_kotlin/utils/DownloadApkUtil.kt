package com.jmd_news_kotlin.utils

import android.app.AlertDialog
import android.app.DownloadManager
import android.content.*
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.Settings
import android.support.v4.content.FileProvider
import android.widget.Toast
import java.io.File

class DownloadApkUtil(val context: Context) {

    companion object {
        val packageName = "com.android.providers.downloads"
    }

    private var mDownloadManager: DownloadManager? = null
    private var mContext: Context = context
    private var downLoadId: Long = 0
    private var apkName: String? = null
    fun downLoad(url: String, name: String) {
        val state = mContext.packageManager.getApplicationEnabledSetting(packageName)
        if (state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED
                || state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED_USER
                || state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED_UNTIL_USED) {
            val builder = AlertDialog.Builder(mContext)
                    .setTitle("温馨提示")
                    .setMessage("系统下载管理器被禁止，需手动打开")
                    .setPositiveButton("确定", { dialog, _ ->
                        dialog.dismiss()
                        try {
                            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                            intent.data = Uri.parse("package:" + packageName)
                            mContext.startActivity(intent)
                        } catch (e: ActivityNotFoundException) {
                            val intent = Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS)
                            mContext.startActivity(intent)
                        }
                    })
                    .setNegativeButton("取消", { dialog, _ -> dialog.dismiss() })
            builder.create().show()
        } else {
            //正常下载流程
            apkName = name
            val request = DownloadManager.Request(Uri.parse(url))
            request.setAllowedOverRoaming(false)
            //通知栏显示
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            request.setTitle("过啦自考")
            request.setDescription("正在下载中...")
            request.setVisibleInDownloadsUi(true)
            //设置下载的路径
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, apkName)
            //获取DownloadManager
            mDownloadManager = mContext.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            downLoadId = (mDownloadManager as DownloadManager).enqueue(request)
            mContext.registerReceiver(mReceiver, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
        }
    }

    private val mReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            checkStatus()
        }
    }

    private fun checkStatus() {
        val query = DownloadManager.Query()
        query.setFilterById(downLoadId)
        val cursor = mDownloadManager?.query(query)
        if (cursor!!.moveToFirst()) {
            val status = cursor?.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))
            when (status) {
            //下载暂停
                DownloadManager.STATUS_PAUSED -> {
                }
            //下载延迟
                DownloadManager.STATUS_PENDING -> {
                }
            //正在下载
                DownloadManager.STATUS_RUNNING -> {
                }
            //下载完成
                DownloadManager.STATUS_SUCCESSFUL -> installAPK()
            //下载失败
                DownloadManager.STATUS_FAILED -> Toast.makeText(mContext, "下载失败", Toast.LENGTH_SHORT).show()
            }
        }
        cursor.close()
    }

    private fun installAPK() {
        val apkFile = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), apkName)
        val intent = Intent(Intent.ACTION_VIEW)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        if (Build.VERSION.SDK_INT >= 24) {
            val apkUri = FileProvider.getUriForFile(mContext, mContext.packageName + ".provider", apkFile)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive")
        } else {
            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive")
        }
        mContext.startActivity(intent)
    }

    fun unRegister() {
        mContext.unregisterReceiver(mReceiver)
    }
}
package com.will.habit.http

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import com.will.habit.http.download.ProgressCallBack
import com.will.habit.utils.ToastUtils
import java.io.File


object VideoDownLoadManager {
    @JvmStatic
    fun downloadVideo(url: String, downloadProgress: DownloadProgress, context: Context) {
        val fileName = "${System.currentTimeMillis()}" + url.subSequence(url.lastIndexOf("."), url.length)
        val fileUrl = Environment.getExternalStorageDirectory().absolutePath + File.separator + Environment.DIRECTORY_DCIM + File.separator + "Camera" + File.separator
        DownLoadManager.instance.load(url, progressCallBack(fileUrl, fileName, downloadProgress, context))
    }

    private fun progressCallBack(fileUrl: String, fileName: String, downloadProgress: DownloadProgress, context: Context) = object : ProgressCallBack<Any>(fileUrl, fileName) {
        override fun onSuccess(t: Any?) {
            if (t != null) {
                ToastUtils.showShort("下载完成已保存到图库")
                val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
                intent.data = Uri.fromFile(File(fileUrl + fileName))
                context.sendBroadcast(intent)
            }
            downloadProgress.onSuccess(fileUrl + fileName)
        }

        override fun progress(progress: Long, total: Long) {

        }

        override fun onError(e: Throwable?) {
            downloadProgress.onFail()
        }

    }
}

abstract class DownloadProgress {
    abstract fun onSuccess(url:String)
    abstract fun onFail()
}
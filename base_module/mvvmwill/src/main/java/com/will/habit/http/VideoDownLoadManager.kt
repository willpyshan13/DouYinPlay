package com.will.habit.http

import android.os.Environment
import com.will.habit.http.download.ProgressCallBack
import com.will.habit.http.download.ProgressResponseBody
import com.will.habit.utils.ToastUtils
import com.will.habit.utils.Utils

object VideoDownLoadManager  {
    @JvmStatic
    fun downloadVideo(url:String,downloadProgress: DownloadProgress){
        val fileName = "${System.currentTimeMillis()}"+url.subSequence(url.lastIndexOf("."),url.length)
        val fileUrl = Utils.getContext().getExternalFilesDir(Environment.DIRECTORY_MOVIES)?.absolutePath
        if (fileUrl!=null) {
            DownLoadManager.instance.load(url, progressCallBack(fileUrl, fileName,downloadProgress))
        }
    }

    private fun progressCallBack(fileUrl:String,fileName:String,downloadProgress: DownloadProgress) = object : ProgressCallBack<Any>(fileUrl,fileName){
        override fun onSuccess(t: Any?) {
            if (t!=null) {
                ToastUtils.showShort("下载完成已保存到图库")
            }
            downloadProgress.onSuccess()
        }

        override fun progress(progress: Long, total: Long) {

        }

        override fun onError(e: Throwable?) {
            downloadProgress.onFail()
        }

    }
}

abstract class DownloadProgress{
    abstract fun onSuccess()
    abstract fun onFail()
}
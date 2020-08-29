package com.will.habit.http

import android.os.Environment
import com.will.habit.http.download.ProgressCallBack
import com.will.habit.utils.ToastUtils
import com.will.habit.utils.Utils

object VideoDownLoadManager  {
    @JvmStatic
    fun downloadVideo(url:String){
        val fileName = "${System.currentTimeMillis()}"
        val fileUrl = Utils.getContext().getExternalFilesDir(Environment.DIRECTORY_MOVIES)?.absolutePath
        if (fileUrl!=null) {
            DownLoadManager.instance.load(url, progressCallBack(fileUrl, fileName))
        }
    }

    private fun progressCallBack(fileUrl:String,fileName:String) = object : ProgressCallBack<String>(fileUrl,fileName){
        override fun onSuccess(t: String?) {
            if (t!=null) {
                ToastUtils.showShort(t)
            }
        }

        override fun progress(progress: Long, total: Long) {

        }

        override fun onError(e: Throwable?) {

        }

    }
}
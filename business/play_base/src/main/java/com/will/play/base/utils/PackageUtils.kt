package com.will.play.base.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
import com.will.habit.utils.ToastUtils


object PackageUtils {
    @JvmStatic
    fun openDouyin(context: Context){
        val b: Boolean = checkAppInstalled(context, "com.ss.android.ugc.aweme")
        if (b) {
            val intent = context.packageManager.getLaunchIntentForPackage("com.ss.android.ugc.aweme")
            context.startActivity(intent)
        } else {
            ToastUtils.showShort("未安装此应用")
        }
    }

    private fun checkAppInstalled(context: Context, pkgName: String?): Boolean {
        if (pkgName == null || pkgName.isEmpty()) {
            return false
        }
        val packageManager: PackageManager = context.packageManager
        val info: List<PackageInfo> = packageManager.getInstalledPackages(0)
        if (info == null || info.isEmpty()) return false
        for (i in info.indices) {
            if (pkgName == info[i].packageName) {
                return true
            }
        }
        return false
    }

}
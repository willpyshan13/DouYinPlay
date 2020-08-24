package com.will.play.app

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import androidx.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
import com.will.habit.base.BaseApplication
import com.will.habit.constant.ConstantConfig
import com.will.habit.crash.CaocConfig
import com.will.habit.crash.CaocConfig.Builder.Companion.create
import com.will.habit.utils.KLog.init
import com.will.habit.utils.SPUtils
import com.will.play.BuildConfig
import com.will.play.R
import com.will.play.aop.login.core.ILogin
import com.will.play.aop.login.core.LoginSDK
import com.will.play.mine.ui.activity.MineLoginActivity

/**
 * will
 */
class AppApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        //是否开启打印日志
        init(BuildConfig.DEBUG)
        MultiDex.install(this)
        //初始化全局异常崩溃
        initCrash()
        initLogin()
        ARouter.init(this);
    }

    private fun initLogin(){
        LoginSDK.instance?.init(this,object :ILogin{
            override fun login(applicationContext: Context?, userDefine: Int) {
                //登录状态 loginStatusCode
                //通过  LoginAssistant.getInstance().serverTokenInvalidation(); 来设置具体状态
                //目前默认只有未登录状态0直接跳转到登录页面
                if (userDefine == 0) {
                    applicationContext?.startActivity(Intent(applicationContext,MineLoginActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
                }
            }

            override fun isLogin(applicationContext: Context?): Boolean {
                //返回登录状态(true 已登录：false 未登录)
                return !TextUtils.isEmpty(SPUtils.instance.getString(ConstantConfig.TOKEN))
            }

            override fun clearLoginStatus(applicationContext: Context?) {

            }

        })
    }

    private fun initCrash() {
        create()
                .backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT) //背景模式,开启沉浸式
                .enabled(true) //是否启动全局异常捕获
                .showErrorDetails(true) //是否显示错误详细信息
                .showRestartButton(true) //是否显示重启按钮
                .trackActivities(true) //是否跟踪Activity
                .minTimeBetweenCrashesMs(2000) //崩溃的间隔时间(毫秒)
                .errorDrawable(R.mipmap.ic_launcher) //错误图标
                .restartActivity(MineLoginActivity::class.java) //重新启动后的activity
                //                .errorActivity(YourCustomErrorActivity.class) //崩溃后的错误activity
                //                .eventListener(new YourCustomEventListener()) //崩溃后的错误监听
                .apply()
    }
}
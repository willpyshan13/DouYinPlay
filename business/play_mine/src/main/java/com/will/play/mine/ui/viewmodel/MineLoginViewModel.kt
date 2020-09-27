package com.will.play.mine.ui.viewmodel

import android.app.Application
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.viewModelScope
import com.alibaba.android.arouter.launcher.ARouter
import com.will.habit.base.BaseViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.habit.binding.command.BindingConsumer
import com.will.habit.bus.event.SingleLiveEvent
import com.will.habit.constant.ConstantConfig
import com.will.habit.extection.launch
import com.will.habit.extection.toJson
import com.will.habit.http.RetrofitClient
import com.will.habit.utils.SPUtils
import com.will.habit.utils.StringUtils
import com.will.habit.utils.ToastUtils
import com.will.play.base.web.WebViewActivity
import com.will.play.base.web.WebViewPath
import com.will.play.mine.R
import com.will.play.mine.BR
import com.will.play.mine.repository.MineLoginRepository

/**
 * Desc:登陆
 *
 * Date: 2020-06-22
 * Copyright: Copyright (c) 2018-2020
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class MineLoginViewModel(application: Application) : BaseViewModel<MineLoginRepository>(application) {
    val userAccount = ObservableField("")
    val userPassword = ObservableField("")
    val verifyBtnVisible = ObservableInt(View.VISIBLE)

    val privateCheck = ObservableBoolean(false)
    val verifyHint = ObservableField(StringUtils.getStringResource(R.string.mine_douyin_verify_title_password))

    val verifyText = ObservableField(StringUtils.getStringResource(R.string.mine_douyin_verify_hint_verify))
    val showPhoneCallDialog = SingleLiveEvent<Void>()
    val douyinLogin = SingleLiveEvent<Void>()
    val verifyTitleCLick = BindingCommand<Any>(object : BindingAction {
        override fun call() {
            if (verifyBtnVisible.get() == View.VISIBLE) {
                verifyBtnVisible.set(View.GONE)
                verifyText.set(StringUtils.getStringResource(R.string.mine_douyin_verify_title_password))
                verifyHint.set(StringUtils.getStringResource(R.string.mine_douyin_verify_hint_password))
            } else {
                verifyBtnVisible.set(View.VISIBLE)
                verifyText.set(StringUtils.getStringResource(R.string.mine_douyin_verify_title_phone))
                verifyHint.set(StringUtils.getStringResource(R.string.mine_douyin_verify_hint_verify))
            }
        }
    })

    val onLoginClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {
            if(!privateCheck.get()){
                showPhoneCallDialog.call()
            }else {
                if (!TextUtils.isEmpty(userAccount.get()) || !TextUtils.isEmpty(userPassword.get())) {
                    launch({
                        showDialog()
                        if (verifyBtnVisible.get() == View.VISIBLE) {
                            val data = model.checkVerifyCode(userAccount.get(), userPassword.get())
                            SPUtils.instance.put(ConstantConfig.TOKEN, data.Token)
                        } else {
                            val data = model.login(userAccount.get(), userPassword.get())
                            SPUtils.instance.put(ConstantConfig.USER_INFO, data.userInfo.toJson())
                            SPUtils.instance.put(ConstantConfig.TOKEN, data.Token)
                        }
                        dismissDialog()
                        finish()
                        ARouter.getInstance().build("/app/home").navigation()
                    }, {
                        dismissDialog()
                    })
                } else {
                    ToastUtils.showShort("用户名或密码不能为空")
                }
            }
        }
    })


    val onWechatClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {

        }
    })

    val onCheckChange = BindingCommand<Boolean>(object :BindingConsumer<Boolean>{
        override fun call(t: Boolean) {

        }

    })
    val onPrivateClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {
            val bundle = Bundle().apply {
                putString(WebViewPath.URL,"http://test.weizhiyx.com/api.php/Webpage/privacy")
            }
            startActivity(WebViewActivity::class.java,bundle)
        }
    })

    val onBackClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {
            finish()
        }
    })

    val onDouyinClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {
            douyinLogin.call()
        }
    })

    val onVerifyClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {
            launch({
                val data = model.getVerifyCode(userAccount.get())
                SPUtils.instance.put(ConstantConfig.TOKEN, data.Token)
                ToastUtils.showShort("发送验证码成功")
            })
        }
    })

}
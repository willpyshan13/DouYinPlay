package com.will.play.mine.ui.viewmodel

import android.app.Application
import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.will.habit.base.BaseListViewModel
import com.will.habit.base.BaseViewModel
import com.will.habit.base.ItemViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.habit.constant.ConstantConfig
import com.will.habit.extection.launch
import com.will.habit.utils.SPUtils
import com.will.habit.utils.StringUtils
import com.will.habit.widget.recycleview.paging.LoadCallback
import com.will.play.mine.R
import com.will.play.mine.BR
import com.will.play.mine.repository.MineLoginRepository
import com.will.play.mine.repository.MineRepository
import me.tatarka.bindingcollectionadapter2.ItemBinding

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
class MineLoginViewModel(application: Application) :BaseViewModel<MineLoginRepository>(application) {
    val userAccount = ObservableField("")
    val userPassword = ObservableField("")
    val verifyBtnVisible = ObservableInt(View.GONE)

    val verifyText = ObservableField(StringUtils.getStringResource(R.string.mine_douyin_verify_title_phone))
    val verifyTitleCLick = BindingCommand<Any>(object : BindingAction {
        override fun call() {
            if (verifyBtnVisible.get() == View.VISIBLE){
                verifyBtnVisible.set(View.GONE)
                verifyText.set(StringUtils.getStringResource(R.string.mine_douyin_verify_title_password))
            }else{
                verifyBtnVisible.set(View.VISIBLE)
                verifyText.set(StringUtils.getStringResource(R.string.mine_douyin_verify_title_phone))
            }
        }
    })

    val onLoginClick = BindingCommand<Any>(object :BindingAction{
        override fun call() {
            launch({
                if (verifyBtnVisible.get() == View.VISIBLE){
                    val data = model.checkVerifyCode(userAccount.get(),userPassword.get())
                }else{
                    val data = model.login(userAccount.get(),userPassword.get())
                    Log.d("","")
                }

            })
        }
    })

    val onWechatClick = BindingCommand<Any>(object :BindingAction{
        override fun call() {

        }
    })

    val onBackClick = BindingCommand<Any>(object :BindingAction{
        override fun call() {
            finish()
        }
    })

    val onDouyinClick = BindingCommand<Any>(object :BindingAction{
        override fun call() {

        }
    })

    val onVerifyClick = BindingCommand<Any>(object :BindingAction{
        override fun call() {
            launch({
                val data = model.getVerifyCode(userAccount.get())
                SPUtils.instance.put(ConstantConfig.TOKEN,data?.Token)
            })
        }
    })

}
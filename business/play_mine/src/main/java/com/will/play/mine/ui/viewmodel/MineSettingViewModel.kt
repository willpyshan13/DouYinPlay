package com.will.play.mine.ui.viewmodel

import android.app.Application
import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.launcher.ARouter
import com.will.habit.base.BaseListViewModel
import com.will.habit.base.BaseViewModel
import com.will.habit.base.ItemViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.habit.constant.ConstantConfig
import com.will.habit.extection.launch
import com.will.habit.http.RetrofitClient
import com.will.habit.utils.SPUtils
import com.will.habit.utils.StringUtils
import com.will.habit.widget.recycleview.paging.LoadCallback
import com.will.play.base.web.WebViewActivity
import com.will.play.base.web.WebViewPath
import com.will.play.mine.R
import com.will.play.mine.BR
import com.will.play.mine.repository.MineRepository
import com.will.play.mine.ui.activity.MineLoginActivity
import com.will.play.mine.ui.activity.MineSettingVolumeActivity
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * Desc:我的地址页面
 *
 * Date: 2020-06-22
 * Copyright: Copyright (c) 2018-2020
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class MineSettingViewModel(application: Application) :BaseViewModel<MineRepository>(application) {

    override fun onCreate() {
        super.onCreate()
        setTitleText(StringUtils.getStringResource(R.string.mine_setting_title))
    }

    val onVolumeClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {
            startActivity(MineSettingVolumeActivity::class.java)
        }
    })

    val onLogoutClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {
            SPUtils.instance.put(ConstantConfig.TOKEN,"")
            startActivity(MineLoginActivity::class.java)
            finish()
        }
    })

    val onCheckUpdateClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {

        }
    })

    val onPrivateClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {
            val bundle = Bundle().apply {
                putString(WebViewPath.URL,"${RetrofitClient.baseUrl}/api.php/Webpage/privacy")
            }
            startActivity(WebViewActivity::class.java,bundle)
        }
    })

    val onUseCaseClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {
            val bundle = Bundle().apply {
                putString(WebViewPath.URL,"${RetrofitClient.baseUrl}/api.php/Webpage/argument")
            }
            startActivity(WebViewActivity::class.java,bundle)
        }
    })

    val onAboutClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {
            val bundle = Bundle().apply {
                putString(WebViewPath.URL,"${RetrofitClient.baseUrl}/api.php/Webpage/about")
            }
            startActivity(WebViewActivity::class.java,bundle)
        }
    })
}
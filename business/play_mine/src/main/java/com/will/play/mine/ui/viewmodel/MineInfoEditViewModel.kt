package com.will.play.mine.ui.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.huantansheng.easyphotos.EasyPhotos
import com.will.habit.base.BaseListViewModel
import com.will.habit.base.BaseViewModel
import com.will.habit.base.ItemViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.habit.bus.event.SingleLiveEvent
import com.will.habit.extection.launch
import com.will.habit.utils.StringUtils
import com.will.habit.widget.recycleview.paging.LoadCallback
import com.will.play.mine.R
import com.will.play.mine.BR
import com.will.play.mine.repository.MineRepository
import com.will.play.mine.ui.activity.MineAddressActivity
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * Desc:我的信息界面
 *
 * Date: 2020-06-22
 * Copyright: Copyright (c) 2018-2020
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class MineInfoEditViewModel(application: Application) :BaseViewModel<MineRepository>(application) {
    init {
        setTitleText(StringUtils.getStringResource(R.string.mine_info_edit))
    }

    val userHeader = ObservableField("")
    val userName = ObservableField("")
    val userGender = ObservableField("请选择性别")
    val userArea = ObservableField("请选择地区")
    val userQQ = ObservableField("")
    val userWechat = ObservableField("")

    val takePhone = SingleLiveEvent<Boolean>()

    private fun getUserInfo(){
        launch({
            val data = model.getUserIndex()
            userHeader.set(data?.userInfo?.avatar)
            userName.set(data?.userInfo?.nickname)
            userGender.set(if (data?.userInfo?.sex_id.equals("1")) "男" else "女")
            userArea.set(data?.userInfo?.area_id)
            userQQ.set(data?.userInfo?.qq)
            userWechat.set(data?.userInfo?.wechat_no)
        })
    }

    override fun onCreate() {
        super.onCreate()
        getUserInfo()
    }

    val onAreaClick = BindingCommand<Any>(object :BindingAction{
        override fun call() {
            startActivity(MineAddressActivity::class.java)
        }
    })

    val onHeadClick = BindingCommand<Any>(object :BindingAction{
        override fun call() {
            if (takePhone.value == null){
                takePhone.value = true
            }else{
                takePhone.value = !takePhone.value!!
            }
        }

    })


}
package com.will.play.mine.ui.viewmodel

import android.app.Application
import android.graphics.Color
import android.widget.Toast
import androidx.databinding.ObservableField
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.bigkoo.pickerview.view.OptionsPickerView
import com.google.gson.Gson
import com.will.habit.base.BaseViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.habit.bus.event.SingleLiveEvent
import com.will.habit.utils.StringUtils
import com.will.habit.utils.Utils
import com.will.play.mine.R
import com.will.play.mine.entity.JsonBean
import com.will.play.mine.repository.MineRepository
import com.will.play.mine.utils.JsonParseUtils
import org.json.JSONArray

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
class MineAddressViewModel(application: Application) : BaseViewModel<MineRepository>(application) {
    val showPickerView = SingleLiveEvent<Void>()
    val userName = ObservableField("")
    val userPhone = ObservableField("")
    val userArea = ""
    val userAddress = ObservableField("")


    init {
        setTitleText(StringUtils.getStringResource(R.string.mine_address))

    }

    val onAddressClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {
            showPickerView.call()
        }

    })



}
package com.will.play.third.ui.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import com.will.habit.base.BaseViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.habit.extection.launch
import com.will.play.third.repository.ThirdRepository

/**
 * Desc:添加抖音账号
 *
 * Date: 2020-06-22
 * Copyright: Copyright (c) 2018-2020
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class ThirdDouyinInfoViewModel(application: Application) :BaseViewModel<ThirdRepository>(application) {

    val imageUrl = ObservableField("")
    val follow = ObservableField("")
    val praise = ObservableField("")
    val video = ObservableField("")
    val valueOne = ObservableField("")

    val valueTwo = ObservableField("")

    val valueThree = ObservableField("")

    val value4 = ObservableField("")

    val value5 = ObservableField("")

    val value6 = ObservableField("")

    val value7 = ObservableField("")

    val value8 = ObservableField("")

    val value9 = ObservableField("")

    val onSubmit = BindingCommand<Any>(object :BindingAction{
        override fun call() {
            submit()
        }
    })

    private fun getDouyinInfo(){
        launch({
            val data = model.getUserIndex()

        })
    }

    private fun submit(){
        launch({
            showDialog()
            model.submitDouyin(value1 = valueOne.get(),value2 = valueTwo.get(),value3 = valueThree.get(),
            value4 = value4.get(),value5 = value5.get(),value6 = value5.get(),value7 = value6.get(),
            value8 = value7.get(),value9 = value8.get(),value10 = value9.get(),value11 = value9.get())
            dismissDialog()
            finish()
        },{
            dismissDialog()
        })

    }

}
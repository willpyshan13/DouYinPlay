package com.will.play.mine.ui.viewmodel

import android.app.Application
import com.will.habit.base.BaseViewModel
import com.will.play.mine.R
import com.will.play.mine.BR
import com.will.play.mine.repository.MineRepository

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
class MineVipViewModel(application: Application) :BaseViewModel<MineRepository>(application) {
    val vipLayout = MineVipLayoutItem(this)
}
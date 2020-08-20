package com.will.play.mine.ui.viewmodel

import android.os.Bundle
import androidx.core.content.ContextCompat
import com.will.habit.base.BaseViewModel
import com.will.habit.base.ItemViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.habit.constant.ConstantConfig
import com.will.habit.utils.Utils
import com.will.play.mine.entity.UpgradeLists
import com.will.play.mine.ui.activity.MineVipPayActivity

/**
 * 我的会员服务item
 */
class MineVipDetailItem(viewModel: BaseViewModel<*>,val data: UpgradeLists) :ItemViewModel<BaseViewModel<*>>(viewModel) {


    val vipServiceOneOpen = BindingCommand<Any>(object : BindingAction {
        override fun call() {
            viewModel.startActivity(MineVipPayActivity::class.java, Bundle().apply { putParcelable(ConstantConfig.VIP_PAY_MONEY,data) })
        }

    })

}
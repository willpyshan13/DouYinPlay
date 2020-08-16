package com.will.play.mine.ui.viewmodel

import androidx.databinding.ObservableField
import com.will.habit.base.ItemViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.play.mine.entity.MineUserInfo
import com.will.play.mine.ui.activity.MineInfoEditActivity
import com.will.play.mine.ui.activity.MineLoginActivity
import com.will.play.mine.ui.activity.MineVipActivity
import okhttp3.internal.notifyAll

/**
 * @author will
 */
class MineHomeHeaderItem(viewModel:MineViewModel,userInfo: MineUserInfo?) :ItemViewModel<MineViewModel>(viewModel) {

    val userName = ObservableField(userInfo?.userInfo?.username)
    val userVideo = ObservableField("")
    val userThing = ObservableField("")
    val userMoney = ObservableField("")

    val onMineInfoEditClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {
            viewModel.startActivity(MineInfoEditActivity::class.java)
        }
    })

    val onLoginClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {
            viewModel.startActivity(MineLoginActivity::class.java)
        }
    })

    val onVipClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {
            viewModel.startActivity(MineVipActivity::class.java)
        }
    })
}
package com.will.play.mine.ui.viewmodel

import androidx.databinding.ObservableField
import com.will.habit.base.ItemViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.play.mine.entity.MineUserInfo
import com.will.play.mine.ui.activity.MineInfoEditActivity
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
}
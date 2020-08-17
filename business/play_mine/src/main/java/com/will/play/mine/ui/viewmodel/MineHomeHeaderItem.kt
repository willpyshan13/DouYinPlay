package com.will.play.mine.ui.viewmodel

import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
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
class MineHomeHeaderItem(viewModel:MineViewModel) :ItemViewModel<MineViewModel>(viewModel) {



    val showLogin = ObservableInt(View.INVISIBLE)

    val userName = ObservableField("")
    val userVideo = ObservableField("")
    val userThing = ObservableField("")
    val userMoney = ObservableField("")

    fun updateUserInfo(userInfo: MineUserInfo?){
        showLogin.set(if (userInfo != null) View.INVISIBLE else View.VISIBLE)
        userName.set(userInfo?.userInfo?.username)
        userVideo.set("${userInfo?.userInfo?.download_total}")
        userThing.set("${userInfo?.userInfo?.download_month_use}")
        userMoney.set("${userInfo?.userInfo?.download_point_total}")
    }

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
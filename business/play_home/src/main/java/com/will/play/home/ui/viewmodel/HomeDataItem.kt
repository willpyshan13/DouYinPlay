package com.will.play.home.ui.viewmodel

import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.alibaba.android.arouter.launcher.ARouter
import com.will.habit.base.ItemViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.play.base.constant.Constants
import com.will.play.base.entity.HomeFilterDataList
import com.will.play.home.R
import com.will.play.home.entity.DataLists

class HomeDataItem(viewModel:FragmentHomeViewModel, val data: HomeFilterDataList, val index:Int) :ItemViewModel<FragmentHomeViewModel>(viewModel) {
    val positionDrawable = ObservableField<Drawable>()
    val positionVisible = ObservableInt(View.VISIBLE)
    init {
        when (index) {
            0 -> {
                positionVisible.set(View.VISIBLE)
                positionDrawable.set(ContextCompat.getDrawable(viewModel.getApplication(), R.mipmap.base_icon_one))
            }
            1 -> {
                positionVisible.set(View.VISIBLE)
                positionDrawable.set(ContextCompat.getDrawable(viewModel.getApplication(), R.mipmap.base_icon_two))
            }
            2 -> {
                positionVisible.set(View.VISIBLE)
                positionDrawable.set(ContextCompat.getDrawable(viewModel.getApplication(), R.mipmap.base_icon_three))
            }
            else -> {
                positionVisible.set(View.GONE)
            }
        }
    }

    val onItemClick = BindingCommand<Any>(object :BindingAction{
        override fun call() {
            ARouter.getInstance().build("/mine/talentinfo")
                    .withString(Constants.COMMON_ID,"${data.id}")
                    .navigation()
        }

    })

    val onApplyClick = BindingCommand<Any>(object :BindingAction{
        override fun call() {
            viewModel.updateMerchant("${data.id}")
        }

    })
}
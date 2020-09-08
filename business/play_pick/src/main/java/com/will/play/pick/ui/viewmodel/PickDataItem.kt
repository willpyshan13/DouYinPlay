package com.will.play.pick.ui.viewmodel

import android.os.Bundle
import androidx.databinding.ObservableField
import com.will.habit.base.BaseViewModel
import com.will.habit.base.ItemViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.habit.binding.command.BindingConsumer
import com.will.habit.constant.ConstantConfig
import com.will.play.base.entity.DataRecommendDataEntity
import com.will.play.pick.entity.TaskLists
import com.will.play.pick.ui.activity.PickGoodsDetailActivity

class PickDataItem(viewModel: BaseViewModel<*>, val data: DataRecommendDataEntity) : ItemViewModel<BaseViewModel<*>>(viewModel) {

    val videoCount = ObservableField("${data.total_download}个种草视频")

    val videoLeft = ObservableField("视频剩余${data.remain_download / data.total_download}")

    val onItemClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {
            val bundle = Bundle()
            bundle.putString(ConstantConfig.GOOD_DETAIL_ID, "${data.id}")
            viewModel.startActivity(PickGoodsDetailActivity::class.java, bundle)
        }
    })
}
package com.will.play.data.ui.viewmodel

import com.alibaba.android.arouter.launcher.ARouter
import com.will.habit.base.ItemViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.habit.constant.ConstantConfig
import com.will.play.data.entity.DataRecommendEntity

class DataItem(viewModel: DataViewModel,val data: DataRecommendEntity) :ItemViewModel<DataViewModel>(viewModel) {

    var imageUrl = data.taskLists.firstOrNull()?.pict_url

    val goodsName = data.taskLists.firstOrNull()?.title

    val salePrice = "售价:${data.taskLists.firstOrNull()?.price_text}"

    val recommendCount = "推广人数:${data.taskLists.firstOrNull()?.user_download}"

    val money = "佣金:${data.taskLists.firstOrNull()?.price_text}"

    val moneyPercent = "佣金比例:${data.taskLists.firstOrNull()?.price_text}"

    val saleCount = "销售人数:${data.taskLists.firstOrNull()?.user_download}"

    val saleMoney = "销售金额:${data.taskLists.firstOrNull()?.user_download}"

    val leftVideo = "剩余视频数:${data.taskLists.firstOrNull()?.user_download}"
    
    val onNextClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {
            viewModel.callReload(false)
        }
    })

    val onVideoClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {
            if (data.taskLists.firstOrNull()!=null) {
                ARouter.getInstance().build("/pick/collect").withInt(ConstantConfig.RECOMMEND_ID, data.taskLists.firstOrNull()!!.id).navigation()
            }
        }
    })
}
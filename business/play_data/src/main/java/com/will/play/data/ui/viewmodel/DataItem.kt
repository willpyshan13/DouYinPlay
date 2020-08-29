package com.will.play.data.ui.viewmodel

import android.os.Environment
import com.alibaba.android.arouter.launcher.ARouter
import com.will.habit.base.ItemViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.habit.constant.ConstantConfig
import com.will.habit.http.DownLoadManager
import com.will.habit.http.VideoDownLoadManager
import com.will.habit.http.download.DownLoadSubscriber
import com.will.habit.http.download.ProgressCallBack
import com.will.habit.utils.ToastUtils
import com.will.habit.utils.Utils
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

    val progressCallBack = object :ProgressCallBack<String>("",""){
        override fun onSuccess(t: String?) {
            if (t!=null) {
                ToastUtils.showShort(t)
            }
        }

        override fun progress(progress: Long, total: Long) {

        }

        override fun onError(e: Throwable?) {

        }

    }

    val onVideoClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {

        }
    })
}
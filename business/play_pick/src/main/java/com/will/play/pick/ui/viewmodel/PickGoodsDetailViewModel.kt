package com.will.play.pick.ui.viewmodel

import android.app.Application
import androidx.recyclerview.widget.DiffUtil
import com.will.habit.base.BaseViewModel
import com.will.habit.binding.collection.DiffObservableArrayList
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.habit.bus.event.SingleLiveEvent
import com.will.habit.utils.StringUtils
import com.will.play.pick.R
import com.will.play.pick.BR
import com.will.play.pick.repository.PickRepository
import com.will.play.pick.ui.activity.PickSearchActivity
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * Desc:商品详情页
 *
 * Date: 2020-07-11 18:03
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: zhuanghongzhan
 */
class PickGoodsDetailViewModel(application: Application) : BaseViewModel<PickRepository>(application) {

    val uiChange = UiChangeObservable()

    val itemList = DiffObservableArrayList(object : DiffUtil.ItemCallback<PickDataItem>() {
        override fun areItemsTheSame(oldItem: PickDataItem, newItem: PickDataItem): Boolean {
            return true
        }

        override fun areContentsTheSame(oldItem: PickDataItem, newItem: PickDataItem): Boolean {
            return true
        }
    })


    val itemBinding = ItemBinding.of<PickDataItem>(BR.viewModel, R.layout.fragment_pick_item)


    /**
     * 测试跳转到搜索页面
     */
    val onTestClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {
            startActivity(PickSearchActivity::class.java)
        }
    })

    /**
     * 开通vip的对话框
     */
    val onVipDialogClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {
            uiChange.vipDialog.call()
        }
    })

    init {
        val list = mutableListOf<PickDataItem>()
        for (i in 1..2) {
            list.add(PickDataItem(this))
        }
        itemList.submit(list, false)
    }

    override fun onCreate() {
        super.onCreate()
        setTitleText(StringUtils.getStringResource(R.string.pick_good_detail_title))

    }


    class UiChangeObservable {
        val vipDialog = SingleLiveEvent<Unit>()
    }


}
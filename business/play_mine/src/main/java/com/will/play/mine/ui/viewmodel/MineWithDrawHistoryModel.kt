package com.will.play.mine.ui.viewmodel

import android.app.Application
import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.will.habit.base.BaseListViewModel
import com.will.habit.base.BaseViewModel
import com.will.habit.base.ItemViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.habit.bus.event.SingleLiveEvent
import com.will.habit.constant.ConstantConfig
import com.will.habit.extection.launch
import com.will.habit.extection.toJson
import com.will.habit.utils.SPUtils
import com.will.habit.utils.StringUtils
import com.will.habit.widget.recycleview.paging.LoadCallback
import com.will.play.mine.R
import com.will.play.mine.BR
import com.will.play.mine.repository.MineRepository
import com.will.play.mine.ui.activity.MineChangeRoleActivity
import com.will.play.mine.ui.activity.MineSettingActivity
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * Desc:提现记录
 *
 * Date: 2020-06-22
 * Copyright: Copyright (c) 2018-2020
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class MineWithDrawHistoryModel(application: Application) : BaseListViewModel<MineRepository, ItemViewModel<*>>(application) {

    override fun getDiffItemCallback(): DiffUtil.ItemCallback<ItemViewModel<*>> {
        return object : DiffUtil.ItemCallback<ItemViewModel<*>>() {
            override fun areItemsTheSame(oldItem: ItemViewModel<*>, newItem: ItemViewModel<*>): Boolean {
                return false
            }

            override fun areContentsTheSame(oldItem: ItemViewModel<*>, newItem: ItemViewModel<*>): Boolean {
                return false
            }

        }
    }

    init {
        setTitleText("提现记录")
        loadInit()
    }

    override fun onResume() {
        super.onResume()
        callReload(false)
    }

    val leftClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {
            startActivity(MineChangeRoleActivity::class.java)
        }

    })

    val rightCLick = BindingCommand<Any>(object : BindingAction {
        override fun call() {
            startActivity(MineSettingActivity::class.java)
        }

    })

    override fun showEmptyState() {
    }

    override fun getItemBinding(): ItemBinding<ItemViewModel<*>> {
        return ItemBinding.of { binding, _, item ->
            when (item) {
                is MineWithDrawHistoryItemViewModel -> binding.set(BR.viewModel, R.layout.mine_activity_with_draw_history_item_layout)
            }
        }
    }

    override fun getItemDecoration(): RecyclerView.ItemDecoration? {
        return null
    }

    override fun loadData(pageIndex: Int, loadCallback: LoadCallback<ItemViewModel<*>>) {
        launch({
            val data = model.getPointApplyIndex(pageIndex)
            val dataList = data.dataLists.map { MineWithDrawHistoryItemViewModel(this,it) }
            loadCallback.onSuccess(dataList,pageIndex,data.total)
        }, {

        })
    }
}
package com.will.play.mine.ui.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.will.habit.base.BaseListViewModel
import com.will.habit.base.ItemViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.habit.bus.event.SingleLiveEvent
import com.will.habit.extection.launch
import com.will.habit.widget.recycleview.paging.LoadCallback
import com.will.play.mine.R
import com.will.play.mine.BR
import com.will.play.mine.repository.MineRepository
import me.tatarka.bindingcollectionadapter2.ItemBinding
import java.text.SimpleDateFormat
import java.util.*

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

    private val simpleYearFormat = SimpleDateFormat("yyyy 年")
    private val simpleMonthFormat = SimpleDateFormat("MM 月")
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    var time = dateFormat.format(System.currentTimeMillis())
    val year = ObservableField("")
    val month = ObservableField("")
    val money = ObservableField("")

    val showDatePicker = SingleLiveEvent<Void>()

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

    fun onTimeSelect(date: Date){
        time = dateFormat.format(date.time)
        year.set(simpleYearFormat.format(date.time))
        month.set(simpleMonthFormat.format(date.time))
        callReload(false)
    }

    init {
        setTitleText("提现记录")
        loadInit()
    }

    override fun onCreate() {
        super.onCreate()
        year.set(simpleYearFormat.format(System.currentTimeMillis()))
        month.set(simpleMonthFormat.format(System.currentTimeMillis()))
    }

    override fun onResume() {
        super.onResume()
        callReload(false)
    }

    val leftClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {
            showDatePicker.call()
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
            val data = model.getPointApplyIndex(pageIndex,dateFrom = time)
            val dataList = data.dataLists.map { MineWithDrawHistoryItemViewModel(this,it) }
            money.set(data.sum_amount+"元")
            loadCallback.onSuccess(dataList,pageIndex,data.total)
        }, {

        })
    }
}
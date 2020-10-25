package com.will.play.mine.ui.viewmodel

import android.app.Application
import android.graphics.Color
import android.view.View
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.bigkoo.pickerview.view.OptionsPickerView
import com.google.gson.Gson
import com.will.habit.base.BaseListViewModel
import com.will.habit.base.BaseViewModel
import com.will.habit.base.ItemViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.habit.bus.event.SingleLiveEvent
import com.will.habit.extection.launch
import com.will.habit.utils.StringUtils
import com.will.habit.utils.Utils
import com.will.habit.widget.recycleview.paging.LoadCallback
import com.will.play.mine.BR
import com.will.play.mine.R
import com.will.play.mine.entity.JsonBean
import com.will.play.mine.repository.MineRepository
import com.will.play.mine.ui.activity.MineDouyinBindingActivity
import com.will.play.mine.utils.JsonParseUtils
import me.tatarka.bindingcollectionadapter2.ItemBinding
import org.json.JSONArray

/**
 * Desc:我的地址页面
 *
 * Date: 2020-06-22
 * Copyright: Copyright (c) 2018-2020
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class MineSaleRecordViewModel(application: Application,val talentId:String) : BaseListViewModel<MineRepository, ItemViewModel<*>>(application) {

    init {
        setRightIconVisible(View.VISIBLE)
        loadInit()
    }

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

    val onBackClick = BindingCommand<Any>(object :BindingAction{
        override fun call() {
            finish()
        }

    })

    override fun rightIconOnClick() {
        startActivity(MineDouyinBindingActivity::class.java)
    }

    override fun showEmptyState() {
    }

    override fun getItemBinding(): ItemBinding<ItemViewModel<*>> {
        return ItemBinding.of { binding, _, item ->
            when (item) {
                is MineSaleRecordListItem -> binding.set(BR.viewModel, R.layout.mine_activity_sale_record_item)
                is MineSaleRecordHeaderViewModel -> binding.set(BR.viewModel, R.layout.mine_activity_sale_header)
            }
        }
    }

    override fun getItemDecoration(): RecyclerView.ItemDecoration? {
        return null
    }

    override fun loadData(pageIndex: Int, loadCallback: LoadCallback<ItemViewModel<*>>) {
        launch({
            val data = model.getDarenOrderInfo(pageIndex,talentId)
            val itemList = mutableListOf<ItemViewModel<*>>()
            if (pageIndex ==1) {
                itemList.add(MineSaleRecordHeaderViewModel(this, data.darenInfo))
            }
            val recordList = data.dataLists.map { MineSaleRecordListItem(this,it) }
            itemList.addAll(recordList)
            loadCallback.onSuccess(itemList,pageIndex,data.total)
        })
    }

}
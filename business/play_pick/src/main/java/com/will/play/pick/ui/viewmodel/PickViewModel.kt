package com.will.play.pick.ui.viewmodel

import android.app.Application
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.will.habit.base.BaseListViewModel
import com.will.habit.base.ItemViewModel
import com.will.habit.extection.launch
import com.will.habit.widget.recycleview.paging.LoadCallback
import com.will.play.pick.R
import com.will.play.pick.BR
import com.will.play.pick.repository.PickRepository
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * Desc:首页
 *
 * Date: 2020-06-22
 * Copyright: Copyright (c) 2018-2020
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class PickViewModel(application: Application) : BaseListViewModel<PickRepository, ItemViewModel<*>>(application) {
    val headerItem = PickHeaderItem(this)
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
        loadInit()
    }

    override fun showEmptyState() {
    }

    override fun getItemBinding(): ItemBinding<ItemViewModel<*>> {
        return ItemBinding.of { binding, _, item ->
            when (item) {
                is PickDataItem -> binding.set(BR.viewModel, R.layout.fragment_pick_item)
                is PickHeaderItem -> binding.set(BR.viewModel, R.layout.fragment_pick_header)
            }
        }
    }

    override fun getItemDecoration(): RecyclerView.ItemDecoration? {
        return null
    }

    override fun loadData(pageIndex: Int, loadCallback: LoadCallback<ItemViewModel<*>>) {
        launch({
            showDialog()
            val viewModels = mutableListOf<ItemViewModel<*>>()
            if (pageIndex ==1) {
                val banner = model.getHomeBanner()
                val type = model.getGoodsType()
                headerItem.updateBanner(banner,type)
            }
            val pickData = model.getTaskIndex(pageIndex)
            val pickList = pickData.taskLists.map { PickDataItem(this,it) }.orEmpty()
            viewModels.addAll(pickList)
            items.addAll(viewModels)
            dismissDialog()
        }, {
            dismissDialog()
        })
    }
}
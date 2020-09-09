package com.will.play.pick.ui.viewmodel

import android.app.Application
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.will.habit.base.BaseListViewModel
import com.will.habit.base.ItemViewModel
import com.will.habit.extection.launch
import com.will.habit.widget.recycleview.paging.LoadCallback
import com.will.play.base.entity.ShopInfo
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
class PickStoreDetailViewModel(application: Application,val storeId:String?) : BaseListViewModel<PickRepository, ItemViewModel<*>>(application) {
     var headerItem:PickStoreDetailHeaderItem = PickStoreDetailHeaderItem(this, ShopInfo("",""))

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
        setTitleText("商家详情")
    }

    override fun showEmptyState() {
    }

    override fun getItemBinding(): ItemBinding<ItemViewModel<*>> {
        return ItemBinding.of { binding, _, item ->
            when (item) {
                is PickDataItem -> binding.set(BR.viewModel, R.layout.fragment_pick_item)
            }
        }
    }

    override fun getItemDecoration(): RecyclerView.ItemDecoration? {
        return null
    }

    override fun loadData(pageIndex: Int, loadCallback: LoadCallback<ItemViewModel<*>>) {
        launch({
            val data = model.getTaskIndex(0,userId = storeId)
            headerItem.updateShopInfo(data.shopInfo)
            val listItem = data.taskLists.map {
                PickDataItem(this,it)
            }
            loadCallback.onSuccess(listItem,pageIndex,1)
        }, {

        })
    }
}
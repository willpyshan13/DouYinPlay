package com.will.play.home.ui.viewmodel

import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.will.habit.base.BaseListViewModel
import com.will.habit.base.ItemViewModel
import com.will.habit.extection.launch
import com.will.habit.widget.recycleview.paging.LoadCallback
import com.will.play.home.R
import com.will.play.home.BR
import com.will.play.home.repository.HomeRepository
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
class FragmentHomeViewModel(application: Application) :BaseListViewModel<HomeRepository, ItemViewModel<*>>(application) {

    private lateinit var headerItem: HomeHeaderItem

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
            when(item){
                is HomeDataItem -> binding.set(BR.viewModel, R.layout.fragment_home_item)
                is HomeHeaderItem -> binding.set(BR.viewModel, R.layout.fragment_home_header)
            }
        }
    }

    override fun getItemDecoration(): RecyclerView.ItemDecoration? {
        return null
    }

    override fun loadData(pageIndex: Int, loadCallback: LoadCallback<ItemViewModel<*>>) {
        launch({
            val viewModels = mutableListOf<ItemViewModel<*>>()
            if (pageIndex ==1){
                viewModels.add(headerItem)
                val data = model.getHomeData()
                val bannerData = model.getHomeBanner()
                headerItem = HomeHeaderItem(this,data,bannerData)
            }

            val listData = model.getHomeList(pageIndex)
            val dataList = listData?.dataLists?.map { HomeDataItem(this,it) }.orEmpty()
            viewModels.addAll(dataList)

            loadCallback.onSuccess(viewModels,pageIndex,1)
        },{

        })
    }
}
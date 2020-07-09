package com.will.play.mine.ui.viewmodel

import android.app.Application
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.will.habit.base.BaseListViewModel
import com.will.habit.base.ItemViewModel
import com.will.habit.extection.launch
import com.will.habit.widget.recycleview.paging.LoadCallback
import com.will.play.mine.R
import com.will.play.mine.BR
import com.will.play.mine.repository.MineRepository
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
class MineViewModel(application: Application) :BaseListViewModel<MineRepository, ItemViewModel<*>>(application) {
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
                is MineDataItem -> binding.set(BR.viewModel, R.layout.fragment_mine_item)
                is MineHeaderItem -> binding.set(BR.viewModel, R.layout.fragment_mine_header)
            }
        }
    }

    override fun getItemDecoration(): RecyclerView.ItemDecoration? {
        return null
    }

    override fun loadData(pageIndex: Int, loadCallback: LoadCallback<ItemViewModel<*>>) {
        launch({
            val viewModels = mutableListOf<ItemViewModel<*>>()
            viewModels.add(MineHeaderItem(this))
            viewModels.add(MineDataItem(this,R.mipmap.base_mine_wallet,"我的钱包"))
            viewModels.add(MineDataItem(this,R.mipmap.base_mine_course,"我的消息"))
            viewModels.add(MineDataItem(this,R.mipmap.base_mine_taobao,"绑定淘宝"))
            viewModels.add(MineDataItem(this,R.mipmap.base_mine_douyin,"绑定抖音"))
            viewModels.add(MineDataItem(this,R.mipmap.base_mine_course,"我的课程"))
            viewModels.add(MineDataItem(this,R.mipmap.base_mine_guide,"新手指引"))
            viewModels.add(MineDataItem(this,R.mipmap.base_mine_contact,"联系我们"))
            viewModels.add(MineDataItem(this,R.mipmap.base_mine_custom,"工人客服"))
            items.addAll(viewModels)
        },{

        })
    }
}
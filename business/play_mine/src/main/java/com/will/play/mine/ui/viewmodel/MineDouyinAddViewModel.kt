package com.will.play.mine.ui.viewmodel

import android.app.Application
import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.will.habit.base.BaseListViewModel
import com.will.habit.base.BaseViewModel
import com.will.habit.base.ItemViewModel
import com.will.habit.extection.launch
import com.will.habit.utils.StringUtils
import com.will.habit.widget.recycleview.paging.LoadCallback
import com.will.play.mine.R
import com.will.play.mine.BR
import com.will.play.mine.repository.MineRepository
import com.will.play.mine.ui.activity.MineDouyinBindingActivity
import me.tatarka.bindingcollectionadapter2.ItemBinding

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
class MineDouyinAddViewModel(application: Application) :BaseListViewModel<MineRepository,MineAddDouyinItem>(application) {

    init {
        setRightIconVisible(View.VISIBLE)
        setTitleText(StringUtils.getStringResource(R.string.mine_douyin_add))
    }

    override fun getDiffItemCallback(): DiffUtil.ItemCallback<MineAddDouyinItem> {
        return object : DiffUtil.ItemCallback<MineAddDouyinItem>() {
            override fun areItemsTheSame(oldItem: MineAddDouyinItem, newItem: MineAddDouyinItem): Boolean {
                return false
            }

            override fun areContentsTheSame(oldItem: MineAddDouyinItem, newItem: MineAddDouyinItem): Boolean {
                return false
            }

        }
    }

    override fun rightIconOnClick() {
        startActivity(MineDouyinBindingActivity::class.java)
    }

    override fun showEmptyState() {
    }

    override fun getItemBinding(): ItemBinding<MineAddDouyinItem> {
        return ItemBinding.of { binding, _, _ ->
            binding.set(BR.viewModel, R.layout.mine_activity_douyin_add)
        }
    }

    override fun getItemDecoration(): RecyclerView.ItemDecoration? {
        return null
    }

    override fun loadData(pageIndex: Int, loadCallback: LoadCallback<MineAddDouyinItem>) {
        launch({
            items.add(MineAddDouyinItem(this))
            items.add(MineAddDouyinItem(this))
            items.add(MineAddDouyinItem(this))
        })
    }

}
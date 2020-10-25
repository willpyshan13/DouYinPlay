package com.will.play.mine.ui.viewmodel

import android.app.Application
import android.view.View
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableInt
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.will.habit.base.BaseListViewModel
import com.will.habit.base.ItemViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.habit.extection.launch
import com.will.habit.utils.StringUtils
import com.will.habit.widget.recycleview.paging.LoadCallback
import com.will.play.mine.R
import com.will.play.mine.BR
import com.will.play.mine.repository.MineRepository
import com.will.play.mine.ui.activity.MineDouyinBindingActivity
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * Desc:添加抖音账号
 *
 * Date: 2020-06-22
 * Copyright: Copyright (c) 2018-2020
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class MineTalentListViewModel(application: Application) :BaseListViewModel<MineRepository,MineTalentListItem>(application) {

    val showMore = ObservableInt(View.GONE)

    init {
        setTitleText(StringUtils.getStringResource(R.string.mine_talent_title))
        loadInit()
    }

    override fun getDiffItemCallback(): DiffUtil.ItemCallback<MineTalentListItem> {
        return object : DiffUtil.ItemCallback<MineTalentListItem>() {
            override fun areItemsTheSame(oldItem: MineTalentListItem, newItem: MineTalentListItem): Boolean {
                return false
            }

            override fun areContentsTheSame(oldItem: MineTalentListItem, newItem: MineTalentListItem): Boolean {
                return false
            }

        }
    }

    /**
     * 过滤
     */
    val filterItemBinding = ItemBinding.of<Any> { itemBinding, _, item ->
        when (item) {
//            is DataDouyinItem -> itemBinding.set(BR.viewModel, R.layout.fragment_data_douyin_item)
//            is DataTaobaoItem -> itemBinding.set(BR.viewModel, R.layout.fragment_data_taobao_item)
        }
    }

    val filterItems = ObservableArrayList<ItemViewModel<*>>()


    val onMoreClick = BindingCommand<Any>(object :BindingAction{
        override fun call() {
            if (showMore.get() == View.VISIBLE){
                showMore.set(View.GONE)
            }else{
                showMore.set(View.VISIBLE)
            }
        }

    })

    override fun rightIconOnClick() {
        startActivity(MineDouyinBindingActivity::class.java)
    }

    override fun showEmptyState() {
    }

    override fun getItemBinding(): ItemBinding<MineTalentListItem> {
        return ItemBinding.of { binding, _, _ ->
            binding.set(BR.viewModel, R.layout.mine_activity_talent_item)
        }
    }

    override fun getItemDecoration(): RecyclerView.ItemDecoration? {
        return null
    }

    override fun loadData(pageIndex: Int, loadCallback: LoadCallback<MineTalentListItem>) {
        launch({
            val listData = model.getHomeList(pageIndex)
            val dataList = listData.dataLists.map { MineTalentListItem(this,it) }
            loadCallback.onSuccess(dataList,pageIndex,listData.total)
        })
    }

}
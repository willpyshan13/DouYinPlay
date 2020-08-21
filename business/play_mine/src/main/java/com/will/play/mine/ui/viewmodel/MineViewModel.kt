package com.will.play.mine.ui.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.will.habit.base.BaseListViewModel
import com.will.habit.base.ItemViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.habit.constant.ConstantConfig
import com.will.habit.extection.launch
import com.will.habit.extection.parse
import com.will.habit.utils.SPUtils
import com.will.habit.widget.recycleview.paging.LoadCallback
import com.will.play.mine.R
import com.will.play.mine.BR
import com.will.play.mine.entity.MineUserInfo
import com.will.play.mine.repository.MineRepository
import com.will.play.mine.ui.activity.MineChangeRoleActivity
import com.will.play.mine.ui.activity.MineSettingActivity
import com.will.play.mine.ui.activity.MineWalletActivity
import com.will.play.mine.ui.viewmodel.MineHomeDataItem.Companion.item_type_binding_douyin
import com.will.play.mine.ui.viewmodel.MineHomeDataItem.Companion.item_type_binding_taobao
import com.will.play.mine.ui.viewmodel.MineHomeDataItem.Companion.item_type_contract
import com.will.play.mine.ui.viewmodel.MineHomeDataItem.Companion.item_type_course
import com.will.play.mine.ui.viewmodel.MineHomeDataItem.Companion.item_type_custom_service
import com.will.play.mine.ui.viewmodel.MineHomeDataItem.Companion.item_type_guide
import com.will.play.mine.ui.viewmodel.MineHomeDataItem.Companion.item_type_message
import com.will.play.mine.ui.viewmodel.MineHomeDataItem.Companion.item_type_vip
import com.will.play.mine.ui.viewmodel.MineHomeDataItem.Companion.item_type_wallet
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
class MineViewModel(application: Application) : BaseListViewModel<MineRepository, ItemViewModel<*>>(application) {
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
                is MineHomeDataItem -> binding.set(BR.viewModel, R.layout.fragment_mine_item)
                is MineHomeHeaderItem -> binding.set(BR.viewModel, R.layout.fragment_mine_header)
            }
        }
    }

    override fun getItemDecoration(): RecyclerView.ItemDecoration? {
        return null
    }

    override fun loadData(pageIndex: Int, loadCallback: LoadCallback<ItemViewModel<*>>) {
        launch({
            val viewModels = mutableListOf<ItemViewModel<*>>()
            viewModels.add(MineHomeHeaderItem(this))
            viewModels.add(MineHomeDataItem(this, R.mipmap.base_mine_vip_icon, "我的会员", item_type_vip))
            viewModels.add(MineHomeDataItem(this, R.mipmap.base_mine_wallet, "我的钱包", item_type_wallet))
            viewModels.add(MineHomeDataItem(this, R.mipmap.base_mine_course, "我的消息", item_type_message))
            viewModels.add(MineHomeDataItem(this, R.mipmap.base_mine_taobao, "绑定淘宝", item_type_binding_taobao))
            viewModels.add(MineHomeDataItem(this, R.mipmap.base_mine_douyin, "绑定抖音", item_type_binding_douyin))
            viewModels.add(MineHomeDataItem(this, R.mipmap.base_mine_course, "我的课程", item_type_course))
            viewModels.add(MineHomeDataItem(this, R.mipmap.base_mine_guide, "新手指引", item_type_guide))
            viewModels.add(MineHomeDataItem(this, R.mipmap.base_mine_contact, "联系我们", item_type_contract))
            viewModels.add(MineHomeDataItem(this, R.mipmap.base_mine_custom, "工人客服", item_type_custom_service))
            items.submit(viewModels,false)
            val data = model.getUserIndex()
            (viewModels[0] as MineHomeHeaderItem).updateUserInfo(data)
        }, {
            (items[0]as MineHomeHeaderItem).updateUserInfo(null)
        })
    }
}
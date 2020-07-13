package com.will.play.mine.ui.viewmodel

import androidx.recyclerview.widget.DiffUtil
import com.will.habit.base.ItemViewModel
import com.will.habit.binding.collection.DiffObservableArrayList
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.play.mine.BR
import com.will.play.mine.R
import com.will.play.mine.ui.activity.MineInfoEditActivity
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * @author will
 */
class MineVipLayoutItem(viewModel:MineVipViewModel) :ItemViewModel<MineVipViewModel>(viewModel) {
    val itemBinding: ItemBinding<MineVipServiceItem> = ItemBinding.of { itemBinding, _, _ ->
        itemBinding.set(BR.viewModel, R.layout.mine_activity_vip_service_item)
    }

    val items = DiffObservableArrayList(object : DiffUtil.ItemCallback<MineVipServiceItem>() {
        override fun areItemsTheSame(oldItem: MineVipServiceItem, newItem: MineVipServiceItem): Boolean {
            return true
        }

        override fun areContentsTheSame(oldItem: MineVipServiceItem, newItem: MineVipServiceItem): Boolean {
            return false
        }
    })

    init {
        val itemList = ArrayList<MineVipServiceItem>()
        itemList.add(MineVipServiceItem(viewModel,R.mipmap.base_vip_service_1,"监控分析"))
        itemList.add(MineVipServiceItem(viewModel,R.mipmap.base_vip_service_2,"四维分析"))
        itemList.add(MineVipServiceItem(viewModel,R.mipmap.base_vip_service_3,"商品链接"))
        itemList.add(MineVipServiceItem(viewModel,R.mipmap.base_vip_service_4,"商品素材"))
        itemList.add(MineVipServiceItem(viewModel,R.mipmap.base_vip_service_5,"商品视频"))
        itemList.add(MineVipServiceItem(viewModel,R.mipmap.base_vip_service_6,"商品实物"))
        itemList.add(MineVipServiceItem(viewModel,R.mipmap.base_vip_service_7,"视频监控"))
        itemList.add(MineVipServiceItem(viewModel,R.mipmap.base_vip_service_8,"评论监控"))
        itemList.add(MineVipServiceItem(viewModel,R.mipmap.base_vip_service_9,"净赚ROI"))
        itemList.add(MineVipServiceItem(viewModel,R.mipmap.base_vip_service_10,"实时ROI"))
        itemList.add(MineVipServiceItem(viewModel,R.mipmap.base_vip_service_11,"DOU+ 投放"))
        itemList.add(MineVipServiceItem(viewModel,R.mipmap.base_vip_service_12,"挂车验证"))
        items.submit(itemList,false)
    }
}
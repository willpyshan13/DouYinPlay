package com.will.play.data.ui.viewmodel

import androidx.databinding.ObservableArrayList
import com.will.habit.base.ItemViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.play.base.BannerEntity
import com.will.play.data.R
import com.will.play.data.BR
import me.tatarka.bindingcollectionadapter2.ItemBinding

class DataHeaderItem(viewModel: DataViewModel,banner: BannerEntity?) :ItemViewModel<DataViewModel>(viewModel) {
    /**
     * banner列表
     */
    val bannerItemBinding = ItemBinding.of<DataBannerItem>(BR.viewModel, R.layout.fragment_data_banner_item)
    val bannerItems = ObservableArrayList<DataBannerItem>()

    init {
        val bannerList = banner?.swiperLists?.map { DataBannerItem(viewModel,it) }.orEmpty()
        bannerItems.addAll(bannerList)
    }

    val onTaobaoClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {

        }
    })

}
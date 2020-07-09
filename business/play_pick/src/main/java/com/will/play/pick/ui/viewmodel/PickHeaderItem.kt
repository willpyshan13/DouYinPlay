package com.will.play.pick.ui.viewmodel

import androidx.databinding.ObservableArrayList
import com.will.habit.base.ItemViewModel
import com.will.play.pick.R
import com.will.play.pick.BR
import me.tatarka.bindingcollectionadapter2.ItemBinding

class PickHeaderItem(viewModel: PickViewModel) :ItemViewModel<PickViewModel>(viewModel) {
    /**
     * banner列表
     */
    val itemBinding = ItemBinding.of<PickTopItem>(BR.viewModel, R.layout.fragment_pick_top_item)
    val items = ObservableArrayList<PickTopItem>()

    /**
     * banner列表
     */
    val bannerItemBinding = ItemBinding.of<PickBannerItem>(BR.viewModel, R.layout.fragment_pick_banner_item)
    val bannerItems = ObservableArrayList<PickBannerItem>()


    init {
        for ( i in 0..9) {
            items.add(PickTopItem(viewModel))
        }

        for ( i in 0..3) {
            bannerItems.add(PickBannerItem(viewModel))
        }
    }

}
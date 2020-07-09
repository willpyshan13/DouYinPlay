package com.will.play.data.ui.viewmodel

import androidx.databinding.ObservableArrayList
import com.will.habit.base.ItemViewModel
import com.will.play.data.R
import com.will.play.data.BR
import me.tatarka.bindingcollectionadapter2.ItemBinding

class DataHeaderItem(viewModel: DataViewModel) :ItemViewModel<DataViewModel>(viewModel) {
    /**
     * banner列表
     */
    val bannerItemBinding = ItemBinding.of<DataBannerItem>(BR.viewModel, R.layout.fragment_data_banner_item)
    val bannerItems = ObservableArrayList<DataBannerItem>()

    init {
        bannerItems.add(DataBannerItem(viewModel))
    }

}
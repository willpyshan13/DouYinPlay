package com.will.play.pick.ui.viewmodel

import androidx.databinding.ObservableArrayList
import com.will.play.pick.R
import com.will.play.pick.BR
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * Desc:搜索页面item ViewModel
 *
 * Date: 2020-07-12 15:33
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: zhuanghongzhan
 */
class PickSearchItemViewModel {

    val items = ObservableArrayList<PickSearchItemTagItemViewModel>()

    val itemBinding: ItemBinding<PickSearchItemTagItemViewModel> = ItemBinding.of(BR.viewModel, R.layout.item_pick_search_tag_item_layout)



    init {
        items.add(PickSearchItemTagItemViewModel("可领取视频"))
        items.add(PickSearchItemTagItemViewModel("可领取实物"))

    }

}

/**
 * Desc:搜索页面item的
 * <p>
 * Date: 2020-07-23
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * Author: zhuanghongzhan
 */
class PickSearchItemTagItemViewModel(val tagText: String)
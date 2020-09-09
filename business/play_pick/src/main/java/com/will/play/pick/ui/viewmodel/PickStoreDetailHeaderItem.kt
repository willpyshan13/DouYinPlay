package com.will.play.pick.ui.viewmodel

import androidx.databinding.ObservableField
import com.will.habit.base.ItemViewModel
import com.will.play.base.entity.ShopInfo
import java.util.*

class PickStoreDetailHeaderItem(viewModel: PickStoreDetailViewModel,val shopInfo:ShopInfo) :ItemViewModel<PickStoreDetailViewModel>(viewModel) {
    val storeName = ObservableField(shopInfo.nickname)
    val sellNum = ObservableField(shopInfo.sell_total+"件")

    fun updateShopInfo(shopInfo:ShopInfo){
        storeName.set(shopInfo.nickname)
        sellNum.set(shopInfo.sell_total+"件")
    }

}
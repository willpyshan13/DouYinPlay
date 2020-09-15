package com.will.play.data.ui.viewmodel

import com.will.habit.base.ItemViewModel
import com.will.play.base.entity.DataLists
import com.will.play.base.entity.DouyinVideoLists

class DataTaobaoItem(viewModel: DataViewModel, val data: DataLists) :ItemViewModel<DataViewModel>(viewModel) {

    var imageUrl = data.item_img

    val goodsName = data.item_title

    val recommendCount = "0获赞"

    val saleCount = "0评论"

    val saleMoney = "0播放"

    val leftVideo = "0转发"
}
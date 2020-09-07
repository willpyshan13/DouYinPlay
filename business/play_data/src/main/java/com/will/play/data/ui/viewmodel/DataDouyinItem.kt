package com.will.play.data.ui.viewmodel

import com.will.habit.base.ItemViewModel
import com.will.play.base.entity.DouyinVideoLists

class DataDouyinItem(viewModel: DataViewModel, val data: DouyinVideoLists) :ItemViewModel<DataViewModel>(viewModel) {

    var imageUrl = data.cover

    val goodsName = data.title

    val recommendCount = "0获赞"

    val saleCount = "0评论"

    val saleMoney = "0播放"

    val leftVideo = "0转发"
}
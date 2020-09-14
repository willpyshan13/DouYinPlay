package com.will.play.pick.ui.viewmodel

import android.app.Application
import androidx.databinding.ObservableArrayList
import com.will.habit.base.BaseDialogViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.habit.extection.launch
import com.will.habit.utils.ToastUtils
import com.will.play.pick.BR
import com.will.play.pick.R
import com.will.play.base.entity.DouyinVideoLists
import com.will.play.base.entity.PickDouyinEntity
import com.will.play.pick.entity.VideoLists
import com.will.play.pick.repository.PickRepository
import com.will.play.pick.ui.activity.PickCollectionVideoActivity
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * Desc:视频关联viewmodel
 *
 * Date: 2020-07-12 13:25
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class PickCollectDialogViewModel(application: Application,val data: VideoLists,videoList: PickDouyinEntity) : BaseDialogViewModel<PickRepository>(application) {

    /**
     * banner列表
     */
    val itemBinding = ItemBinding.of<PickCollectVideoDialogItem>(BR.viewModel, R.layout.fragment_pick_video_collect_item)
    val items = ObservableArrayList<PickCollectVideoDialogItem>()

    /**
     * 左边按钮点击事件
     */
    val onClick=BindingCommand<Any>(object : BindingAction {
        override fun call() {
            launch({

            })
        }
    })

    fun bindVideo(douyinData: DouyinVideoLists){
        launch({
            val data = model.getVideoBind("${data.id}",douyinVideoId = "${douyinData.id}")
            ToastUtils.showShort("关联成功")
            PickCollectionVideoActivity.collectSingleLiveEvent.call()
            finish()
        })
    }


    init {
        val data = videoList.douyinVideoLists.map { PickCollectVideoDialogItem(this,it) }
        items.addAll(data)
    }

}
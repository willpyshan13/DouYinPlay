package com.will.play.pick.ui.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.DiffUtil
import com.will.habit.binding.collection.DiffObservableArrayList
import com.will.play.pick.R
import com.will.play.pick.entity.TaskInfo
import me.tatarka.bindingcollectionadapter2.ItemBinding
import com.will.play.pick.BR


/**
 * Desc: 商品详情页数据viewModel  用于处理数据
 *
 * @Author: zhuanghongzhan
 */
class PickGoodsDetailDataViewModel(val application: Application) {

    var detailInfo = ObservableField<TaskInfo>()

    /**
     * 月销量
     */
    val monthlySales = ObservableField<String>(application.resources.getString(R.string.pick_good_detail_sales, "0"))

    /**
     * 剩余视频数/总视频数
     */
    val videosText = ObservableField<String>(application.resources.getString(R.string.pick_good_detail_video_surplus, "0/0"))

    /**
     * 评论数
     */
    val commentText = ObservableField<String>(application.resources.getString(R.string.pick_good_detail_product_comment, "0"))

    /**
     * 第一条评论的用户名
     */
    val commentFirstName = ObservableField<String>()

    /**
     * 第一条评论的内容
     */
    val commentFirstText = ObservableField<String>()


    val descItemList = DiffObservableArrayList(object : DiffUtil.ItemCallback<PickDetailDescItemViewModel>() {
        override fun areItemsTheSame(oldItem: PickDetailDescItemViewModel, newItem: PickDetailDescItemViewModel): Boolean {
            return true
        }

        override fun areContentsTheSame(oldItem: PickDetailDescItemViewModel, newItem: PickDetailDescItemViewModel): Boolean {
            return true
        }
    })


    val descItemBinding = ItemBinding.of<PickDetailDescItemViewModel>(BR.viewModel, R.layout.fragment_pick_item)


    //设置数据
    fun setData(detailInfo: TaskInfo?) {
        detailInfo ?: return
        this.detailInfo.set(detailInfo)
        monthlySales.set(application.resources.getString(R.string.pick_good_detail_sales, detailInfo.volume))
        videosText.set(application.resources.getString(R.string.pick_good_detail_video_surplus, "${detailInfo.remain_download}/${detailInfo.total_download}"))
        commentText.set(application.resources.getString(R.string.pick_good_detail_product_comment, (detailInfo.feedbackLists?.size ?: 0).toString()))
        commentFirstName.set(detailInfo.feedbackLists?.firstOrNull()?.userNick)
        commentFirstText.set(detailInfo.feedbackLists?.firstOrNull()?.feedback)

        createDescItem(detailInfo)
    }


    /**
     * Desc:创建商品详情
     * <p>
     * Author: zhuanghongzhan
     * Date: 2020-08-25
     * @param detailInfo TaskInfo
     */
    private fun createDescItem(detailInfo: TaskInfo?) {
        if (detailInfo!=null) {
            val itemViewModel = detailInfo.desc_images.map {
                PickDetailDescItemViewModel(it)
            }
            descItemList.submit(itemViewModel, false)
        }
    }


}

class PickDetailDescItemViewModel(val imgUrl: String) {


}
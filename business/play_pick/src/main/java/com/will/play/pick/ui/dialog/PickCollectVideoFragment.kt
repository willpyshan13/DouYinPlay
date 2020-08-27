package com.will.play.pick.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.will.habit.base.BaseApplication
import com.will.habit.base.BaseDialogFragment
import com.will.habit.base.BaseDialogViewModel
import com.will.habit.utils.Utils
import com.will.play.base.web.WebViewModel
import com.will.play.pick.BR
import com.will.play.pick.R
import com.will.play.pick.databinding.DialogPickCollectVideoLayoutBinding
import com.will.play.pick.databinding.DialogPickVipLayoutBinding
import com.will.play.pick.entity.PickDouyinEntity
import com.will.play.pick.entity.VideoLists
import com.will.play.pick.ui.viewmodel.PickCollectDialogViewModel
import com.will.play.pick.ui.viewmodel.PickVipDialogViewModel

/**
 * Desc:视频关联对话框
 *
 * Date: 2020-07-12 11:55
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class PickCollectVideoFragment(val data: VideoLists,val videoList: PickDouyinEntity) :BaseDialogFragment<DialogPickCollectVideoLayoutBinding, PickCollectDialogViewModel>(){
    override fun initContentView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): Int {
        return  R.layout.dialog_pick_collect_video_layout
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun <T : BaseDialogViewModel<*>> createViewModel(activity: Fragment, cls: Class<T>): T {
        return ViewModelProvider(activity, object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return PickCollectDialogViewModel(BaseApplication.instance, data,videoList) as T
            }
        })[cls]
    }


    override fun onStart() {
        super.onStart()
        //重新计算视图宽高  充满屏幕
        val win = dialog?.window
        if (win!=null){
            // 一定要设置Background，如果不设置，window属性设置无效
            win.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            win.attributes.apply {
                gravity = Gravity.CENTER
                width = activity!!.resources.getDimensionPixelSize(R.dimen.dp300)
                height = activity!!.resources.getDimensionPixelSize(R.dimen.dp450)
            }.let {
                win.attributes = it
            }
        }

    }
}
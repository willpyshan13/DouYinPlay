package com.will.play.pick.ui.activity

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.will.play.pick.R
import com.will.play.pick.BR
import com.will.play.pick.ui.viewmodel.PickSearchViewModel
import com.will.habit.base.BaseActivity
import com.will.habit.constant.ConstantConfig
import com.will.play.pick.databinding.ActivityPickSearchBinding
import com.will.play.pick.ui.viewmodel.PickCollectionViewModel

/**
 * Desc:搜索页面
 *
 * Date: 2020-07-12
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @author: zhuanghongzhan
 */
class PickSearchActivity : BaseActivity<ActivityPickSearchBinding, PickSearchViewModel>() {

    override fun initContentView(savedInstanceState: Bundle?) = R.layout.activity_pick_search

    override fun initVariableId() = BR.viewModel


    override fun needToolBar(): Boolean = false

    override fun <T : ViewModel> createViewModel(activity: FragmentActivity, cls: Class<T>): T {
        return ViewModelProvider(activity, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                val typeId=intent.getStringExtra(ConstantConfig.TYPE_ID)?:""
                //todo
                return PickSearchViewModel(activity.application, typeId) as T
            }
        }).get(cls)
    }

}

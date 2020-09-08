package com.will.play.pick.ui.activity

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.will.habit.base.BaseActivity
import com.will.habit.constant.ConstantConfig
import com.will.play.pick.BR
import com.will.play.pick.R
import com.will.play.pick.databinding.ActivityPickStoreDetailBinding
import com.will.play.pick.ui.viewmodel.PickCollectionViewModel
import com.will.play.pick.ui.viewmodel.PickStoreDetailViewModel

/**
 * Desc:商家详情页
 *
 * Date: 2020-06-30
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class PickStoreDetailActivity : BaseActivity<ActivityPickStoreDetailBinding, PickStoreDetailViewModel>() {
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_pick_store_detail
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun <T : ViewModel> createViewModel(activity: FragmentActivity, cls: Class<T>): T {
        return ViewModelProvider(activity, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                val storeId=intent.getStringExtra(ConstantConfig.STORE_ID)?:""
                return PickStoreDetailViewModel(activity.application, storeId) as T
            }
        }).get(cls)
    }

}
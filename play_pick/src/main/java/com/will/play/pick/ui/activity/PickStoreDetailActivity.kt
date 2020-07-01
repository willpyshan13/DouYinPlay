package com.will.play.pick.ui.activity

import android.os.Bundle
import com.will.habit.base.BaseActivity
import com.will.play.pick.BR
import com.will.play.pick.R
import com.will.play.pick.databinding.ActivityPickStoreDetailBinding
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
}
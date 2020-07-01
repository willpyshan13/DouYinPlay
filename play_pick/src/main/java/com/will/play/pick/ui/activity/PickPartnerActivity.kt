package com.will.play.pick.ui.activity

import android.os.Bundle
import com.will.habit.base.BaseActivity
import com.will.play.pick.BR
import com.will.play.pick.R
import com.will.play.pick.databinding.ActivityPickStoreDetailBinding
import com.will.play.pick.ui.viewmodel.PickStoreDetailViewModel


/**
 * Desc:注册商家
 *
 * Date: 2020-06-30
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class PickPartnerActivity : BaseActivity<ActivityPickStoreDetailBinding, PickStoreDetailViewModel>() {
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_pick_register_partner
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }
}
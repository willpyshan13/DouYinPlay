package com.will.play.pick.ui.activity

import android.os.Bundle
import com.will.habit.base.BaseActivity
import com.will.play.pick.BR
import com.will.play.pick.R
import com.will.play.pick.databinding.ActivityPickGoodsDetailBinding
import com.will.play.pick.ui.viewmodel.PickStoreDetailViewModel

/**
 * Desc:商品详情页
 *
 * Date: 2020-07-05
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class PickGoodsDetailActivity : BaseActivity<ActivityPickGoodsDetailBinding, PickStoreDetailViewModel>() {
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_pick_goods_detail
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }
}
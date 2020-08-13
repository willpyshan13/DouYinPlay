package com.will.play.pick.ui.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import com.will.habit.base.BaseActivity
import com.will.play.pick.BR
import com.will.play.pick.R
import com.will.play.pick.databinding.ActivityPickGoodsDetailBinding
import com.will.play.pick.ui.dialog.PickVipDialogFragment
import com.will.play.pick.ui.viewmodel.PickGoodsDetailViewModel

/**
 * Desc:商品详情页
 *
 * Date: 2020-07-05
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class PickGoodsDetailActivity : BaseActivity<ActivityPickGoodsDetailBinding, PickGoodsDetailViewModel>() {
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_pick_goods_detail
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initViewObservable() {
        super.initViewObservable()
        viewModel.uiChange.vipDialog.observe(this, Observer {
            showVipDialog()
        })
    }

    private fun showVipDialog() {
        PickVipDialogFragment().show(supportFragmentManager, "")
    }


}
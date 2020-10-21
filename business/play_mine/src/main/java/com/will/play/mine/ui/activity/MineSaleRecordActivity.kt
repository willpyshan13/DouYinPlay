package com.will.play.mine.ui.activity

import android.os.Bundle
import com.will.habit.base.BaseActivity
import com.will.play.mine.BR
import com.will.play.mine.R
import com.will.play.mine.databinding.MineActivitySaleRecordBinding
import com.will.play.mine.databinding.MineActivityTalentBinding
import com.will.play.mine.ui.viewmodel.MineSaleRecordViewModel
import com.will.play.mine.ui.viewmodel.MineTalentViewModel

/**
 * Desc:销售记录
 *
 * Date: 2020-10-19
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class MineSaleRecordActivity : BaseActivity<MineActivitySaleRecordBinding, MineSaleRecordViewModel>() {

    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.mine_activity_sale_record
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun needToolBar(): Boolean {
        return false
    }

}
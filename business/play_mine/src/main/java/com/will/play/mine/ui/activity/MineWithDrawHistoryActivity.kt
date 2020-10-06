package com.will.play.mine.ui.activity

import android.os.Bundle
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.will.habit.base.BaseActivity
import com.will.habit.utils.KLog
import com.will.play.mine.BR
import com.will.play.mine.R
import com.will.play.mine.databinding.MineActivityWithDrawHistoryBinding
import com.will.play.mine.ui.viewmodel.MineWithDrawHistoryModel


/**
 * Desc:提现
 *
 * Date: 2020-06-30
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class MineWithDrawHistoryActivity : BaseActivity<MineActivityWithDrawHistoryBinding, MineWithDrawHistoryModel>() {
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.mine_activity_with_draw_history
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initViewObservable() {
        super.initViewObservable()
        viewModel.showDatePicker.observe(this, {
            TimePickerBuilder(this@MineWithDrawHistoryActivity) { date, v ->
                viewModel.onTimeSelect(date)
            }.build().show()
        })
    }
}
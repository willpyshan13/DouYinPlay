package com.will.play.mine.ui.activity

import android.os.Bundle
import com.will.habit.base.BaseActivity
import com.will.play.mine.R
import com.will.play.mine.BR
import com.will.play.mine.databinding.MineActivityRegisterPartnerBinding
import com.will.play.mine.ui.viewmodel.MineRegisterPartnerViewModel


/**
 * Desc:注册商家
 *
 * Date: 2020-06-30
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class MInePartnerActivity : BaseActivity<MineActivityRegisterPartnerBinding, MineRegisterPartnerViewModel>() {
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.mine_activity_register_partner
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }
}
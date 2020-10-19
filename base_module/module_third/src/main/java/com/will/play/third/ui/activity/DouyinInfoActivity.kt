package com.will.play.third.ui.activity

import android.os.Bundle
import com.will.habit.base.BaseActivity
import com.will.play.third.R
import com.will.play.third.BR
import com.will.play.third.databinding.ThirdActivityDouyinInfoBinding
import com.will.play.third.ui.viewmodel.ThirdDouyinInfoViewModel


class DouyinInfoActivity : BaseActivity<ThirdActivityDouyinInfoBinding, ThirdDouyinInfoViewModel>() {
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.third_activity_douyin_info
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initData() {
        super.initData()

    }
}
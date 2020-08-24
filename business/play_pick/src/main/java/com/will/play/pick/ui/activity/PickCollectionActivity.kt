package com.will.play.pick.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.will.habit.base.BaseActivity
import com.will.play.pick.BR
import com.will.play.pick.R
import com.will.play.pick.databinding.ActivityPickCollectionBinding
import com.will.play.pick.ui.viewmodel.PickCollectionViewModel

/**
 * Desc:实物领取
 *
 * Date: 2020-07-01
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
@Route(path = "/pick/collect")
class PickCollectionActivity : BaseActivity<ActivityPickCollectionBinding, PickCollectionViewModel>() {
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_pick_collection
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }
}
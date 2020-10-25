package com.will.play.mine.ui.activity

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.will.habit.base.BaseActivity
import com.will.play.base.constant.Constants
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

    override fun <T : ViewModel> createViewModel(activity: FragmentActivity, cls: Class<T>): T {
        return ViewModelProvider(activity, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                val talentId=intent.getStringExtra(Constants.COMMON_ID)?:""
                //todo
                return MineSaleRecordViewModel(activity.application, talentId) as T
            }
        }).get(cls)
    }


}
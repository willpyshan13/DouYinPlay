package com.will.play.mine.ui.activity

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.will.habit.base.BaseActivity
import com.will.habit.constant.ConstantConfig
import com.will.play.base.constant.Constants
import com.will.play.mine.BR
import com.will.play.mine.R
import com.will.play.mine.databinding.MineActivityTalentBinding
import com.will.play.mine.ui.viewmodel.MineTalentViewModel

/**
 * Desc:达人信息
 *
 * Date: 2020-10-19
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
@Route(path = "/mine/talentinfo")
class MineTalentInfoActivity : BaseActivity<MineActivityTalentBinding, MineTalentViewModel>() {

    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.mine_activity_talent
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
                return MineTalentViewModel(activity.application, talentId) as T
            }
        }).get(cls)
    }

}
package com.will.play.mine.ui.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import com.chenenyu.router.annotation.Route
import com.will.habit.base.BaseActivity
import com.will.play.mine.BR
import com.will.play.mine.R
import com.will.play.mine.databinding.MineActivityAddressBinding
import com.will.play.mine.ui.viewmodel.MineLoginViewModel
import com.will.play.third.DouyinLogin
import com.will.play.third.ThirdInit

/**
 * Desc:登陆页面
 *
 * Date: 2020-06-30
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
@Route(value = ["/mine/LoginActivity"])
class MineLoginActivity : BaseActivity<MineActivityAddressBinding, MineLoginViewModel>() {
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.mine_activity_login
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun needToolBar(): Boolean {
        return false
    }

    override fun initData() {
        super.initData()
        ThirdInit.initShare()
    }

    override fun initViewObservable() {
        super.initViewObservable()
        viewModel.douyinLogin.observe(this, Observer {
            DouyinLogin.login(this)
        })

        DouyinLogin.authSuccess.observe(this, Observer {
            if (it!=null) {
                viewModel.getDouyinUserinfo(it)
            }
        })
    }
}
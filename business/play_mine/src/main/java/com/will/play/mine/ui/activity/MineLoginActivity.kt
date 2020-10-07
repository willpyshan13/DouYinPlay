package com.will.play.mine.ui.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import com.will.habit.base.BaseActivity
import com.will.habit.widget.dialog.ConfirmDialog
import com.will.play.mine.BR
import com.will.play.mine.R
import com.will.play.mine.databinding.MineActivityAddressBinding
import com.will.play.mine.ui.viewmodel.MineLoginViewModel
import com.will.play.third.DouyinLogin
import kotlinx.android.synthetic.main.mine_activity_login.*

/**
 * Desc:登陆页面
 *
 * Date: 2020-06-30
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
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
    }

    override fun initViewObservable() {
        super.initViewObservable()
        viewModel.douyinLogin.observe(this, Observer {
            DouyinLogin.login(this)
        })

        viewModel.showPhoneCallDialog.observe(this, {
            ConfirmDialog(this, true)
                    .setTtitle("服务协议和隐私政策")
                    .setTitleSize(15F)
                    .setMessage("\n" +
                            "请你务必审慎，充分理解“服务协议”和“隐私政策”各条款，包括但不限于：为了向你提供即时通讯、内容分享等服务，我们需要手机你的" +
                            "设备信息、操作日志等个人信息。你可以在“我的”中查看相关条款说明" +
                            "你可阅读" +
                            "《服务协议和隐私政策》了解详情。如你同意，请点击\"同意\"开始接受我们的服务")
                    .setNegativeButton("暂不使用") { p0, p1 -> }
                    .setPositiveButton("同意") { p0, p1 ->
                        mine_check.isChecked = true
                    }.create().show();
        })
    }
}
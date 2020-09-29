package com.will.play.mine.ui.activity

import android.os.Bundle
import com.will.habit.base.BaseActivity
import com.will.habit.utils.ToastUtils
import com.will.play.mine.BR
import com.will.play.mine.R
import com.will.play.mine.databinding.MineActivityAddressBinding
import com.will.play.mine.databinding.MineActivityWechatAuthBinding
import com.will.play.mine.ui.viewmodel.MineAddressViewModel
import com.will.play.mine.ui.viewmodel.MineChangeRoleViewModel
import com.will.play.mine.ui.viewmodel.MineLoginViewModel
import com.will.play.mine.ui.viewmodel.MineWechatAuthViewModel
import com.will.play.pay.WillPay
import com.will.play.pay.callback.IPayCallback
import com.will.play.pay.wechat.wxpay.WXPay

/**
 * Desc:微信授权
 *
 * Date: 2020-06-30
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class MineWechatAuthActivity : BaseActivity<MineActivityWechatAuthBinding, MineWechatAuthViewModel>() {
    private val wxPay: WXPay = WXPay.instance
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.mine_activity_wechat_auth
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initViewObservable() {
        super.initViewObservable()
        viewModel.payClick.observe(this,  {
            wxPay.auth(this,object :IPayCallback{
                override fun success() {

                }

                override fun failed(code: Int, message: String?) {

                }

                override fun cancel() {

                }

            })
        })
    }
}
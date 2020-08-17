package com.will.play.mine.ui.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import com.will.habit.base.BaseActivity
import com.will.play.mine.BR
import com.will.play.mine.R
import com.will.play.mine.databinding.MineActivityVipDetailBinding
import com.will.play.mine.databinding.MineActivityVipPayBinding
import com.will.play.mine.ui.viewmodel.MineVipDetailViewModel
import com.will.play.mine.ui.viewmodel.MineVipPayViewModel
import com.will.play.pay.WillPay
import com.will.play.pay.callback.IPayCallback
import com.will.play.pay.wechat.wxpay.WXPay
import com.will.play.pay.wechat.wxpay.WXPayInfoImpl

/**
 * Desc:vip详情
 *
 * Date: 2020-07-02
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class MineVipPayActivity : BaseActivity<MineActivityVipPayBinding, MineVipPayViewModel>() {
    private val wxPay: WXPay = WXPay.instance
    private val wxPayInfoImpl  = WXPayInfoImpl()
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.mine_activity_vip_pay
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initViewObservable() {
        super.initViewObservable()
        viewModel.payClick.observe(this, Observer {
          WillPay.pay(wxPay,this,wxPayInfoImpl,object :IPayCallback{
              override fun failed(code: Int, message: String?) {
              }

              override fun cancel() {
              }

              override fun success() {
              }

          })
        })
    }
}
package com.will.play.pay

import android.app.Activity
import com.will.play.pay.base.IPayInfo
import com.will.play.pay.base.IPayStrategy
import com.will.play.pay.callback.IPayCallback

object WillPay {
    fun <T : IPayInfo> pay(payWay: IPayStrategy<T>, mActivity: Activity, payinfo: T, callback: IPayCallback?) {
        payWay.pay(mActivity, payinfo, callback)
    }
}
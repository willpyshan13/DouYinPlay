package com.will.play.pay.base

import android.app.Activity
import com.will.play.pay.callback.IPayCallback

interface IPayStrategy<T : IPayInfo> {
    fun pay(activity: Activity, payInfo: T, payCallback: IPayCallback?)
}
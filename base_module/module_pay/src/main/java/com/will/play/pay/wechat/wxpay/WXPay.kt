package com.will.play.pay.wechat.wxpay

import android.app.Activity
import android.content.Context
import android.text.TextUtils
import com.tencent.mm.opensdk.constants.Build
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.modelmsg.SendAuth
import com.tencent.mm.opensdk.modelpay.PayReq
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import com.will.play.pay.base.IPayStrategy
import com.will.play.pay.callback.IPayCallback

class WXPay private constructor() : IPayStrategy<WXPayInfoImpl> {
    private var payInfoImpli: WXPayInfoImpl? = null
    var wXApi: IWXAPI? = null
        private set
    private var initializated = false

    private fun initWXApi(context: Context, appId: String?) {
        wXApi = WXAPIFactory.createWXAPI(context.applicationContext, appId)
        wXApi?.registerApp(appId)
        initializated = true
    }

    fun auth(activity: Activity, payCallback: IPayCallback?){
        if (!initializated) {
            initWXApi(activity.applicationContext, "wxb262b19645856466")
        }
        val req = SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "douqupai_api";
        wXApi?.sendReq(req);
    }

    override fun pay(activity: Activity, payInfo: WXPayInfoImpl, payCallback: IPayCallback?) {
        payInfoImpli = payInfo
        sPayCallback = payCallback
        if (payInfoImpli == null || TextUtils.isEmpty(payInfoImpli!!.appid) || TextUtils.isEmpty(payInfoImpli!!.partnerid)
                || TextUtils.isEmpty(payInfoImpli!!.prepayId) || TextUtils.isEmpty(payInfoImpli!!.packageValue) ||
                TextUtils.isEmpty(payInfoImpli!!.nonceStr) || TextUtils.isEmpty(payInfoImpli!!.timestamp) ||
                TextUtils.isEmpty(payInfoImpli!!.sign)) {
            payCallback?.failed(WXErrCodeEx.CODE_ILLEGAL_ARGURE, WXErrCodeEx.getMessageByCode(WXErrCodeEx.CODE_ILLEGAL_ARGURE))
            return
        }
        if (!initializated) {
            initWXApi(activity.applicationContext, payInfoImpli!!.appid)
        }
        if (!check()) {
            payCallback?.failed(WXErrCodeEx.CODE_UNSUPPORT, WXErrCodeEx.getMessageByCode(WXErrCodeEx.CODE_UNSUPPORT))
            return
        }
        val req = PayReq()
        req.appId = payInfoImpli!!.appid
        req.partnerId = payInfoImpli!!.partnerid
        req.prepayId = payInfoImpli!!.prepayId
        req.packageValue = payInfoImpli!!.packageValue
        req.nonceStr = payInfoImpli!!.nonceStr
        req.timeStamp = payInfoImpli!!.timestamp
        req.sign = payInfoImpli!!.sign
        wXApi!!.sendReq(req)
    }

    /**
     * 支付回调响应
     */
    fun onResp(errorCode: Int, errorMsg: String?) {
        if (sPayCallback == null) {
            return
        }
        when (errorCode) {
            BaseResp.ErrCode.ERR_OK -> {
                sPayCallback!!.success()
            }
            BaseResp.ErrCode.ERR_COMM -> {
                sPayCallback!!.failed(errorCode, errorMsg)
            }
            BaseResp.ErrCode.ERR_USER_CANCEL -> {
                sPayCallback!!.cancel()
            }
            else -> {
                sPayCallback!!.failed(errorCode, errorMsg)
            }
        }
        sPayCallback = null
    }

    /**
     * 检测是否支持微信支付
     */
    private fun check(): Boolean {
        return wXApi!!.isWXAppInstalled && wXApi!!.wxAppSupportAPI >= Build.PAY_SUPPORTED_SDK_INT
    }

    companion object {
        private var sPayCallback: IPayCallback? = null
        @JvmStatic
        val instance: WXPay = WXPay()

    }
}
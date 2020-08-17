package com.will.play.pay.wechat.wxpay;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.will.play.pay.base.IPayStrategy;
import com.will.play.pay.callback.IPayCallback;

public class WXPay implements IPayStrategy<WXPayInfoImpl> {

    private static WXPay mWXPay;
    private WXPayInfoImpl payInfoImpli;
    private static IPayCallback sPayCallback;
    private IWXAPI mWXApi;
    private boolean initializated;

    private WXPay() {

    }

    public static WXPay getInstance(){
        if(mWXPay == null){
            synchronized (WXPay.class){
                if(mWXPay == null) {
                    mWXPay = new WXPay();
                }
            }
        }
        return mWXPay;
    }

    public IWXAPI getWXApi() {
        return mWXApi;
    }

    private void initWXApi(Context context, String appId) {
        mWXApi = WXAPIFactory.createWXAPI(context.getApplicationContext(), appId);
        mWXApi.registerApp(appId);
        initializated = true;
    }

    @Override
    public void pay(Activity activity, WXPayInfoImpl payInfo, IPayCallback payCallback) {
        this.payInfoImpli = payInfo;
        sPayCallback = payCallback;

        if(payInfoImpli == null || TextUtils.isEmpty(payInfoImpli.appid) || TextUtils.isEmpty(payInfoImpli.partnerid)
                || TextUtils.isEmpty(payInfoImpli.prepayId) || TextUtils.isEmpty(payInfoImpli.packageValue) ||
                TextUtils.isEmpty(payInfoImpli.nonceStr) || TextUtils.isEmpty(payInfoImpli.timestamp) ||
                TextUtils.isEmpty(payInfoImpli.sign)) {
            if(payCallback != null) {
                payCallback.failed(WXErrCodeEx.CODE_ILLEGAL_ARGURE, WXErrCodeEx.getMessageByCode(WXErrCodeEx.CODE_ILLEGAL_ARGURE));
            }
            return;
        }

        if (!initializated) {
            initWXApi(activity.getApplicationContext(), payInfoImpli.appid);
        }

        if(!check()) {
            if(payCallback != null) {
                payCallback.failed(WXErrCodeEx.CODE_UNSUPPORT, WXErrCodeEx.getMessageByCode(WXErrCodeEx.CODE_UNSUPPORT));
            }
            return;
        }

        PayReq req = new PayReq();
        req.appId = payInfoImpli.appid;
        req.partnerId = payInfoImpli.partnerid;
        req.prepayId = payInfoImpli.prepayId;
        req.packageValue = payInfoImpli.packageValue;
        req.nonceStr = payInfoImpli.nonceStr;
        req.timeStamp = payInfoImpli.timestamp;
        req.sign = payInfoImpli.sign;

        mWXApi.sendReq(req);
    }

    /**
     * 支付回调响应
     */
    public void onResp(int errorCode, String errorMsg) {
        if(sPayCallback == null) {
            return;
        }

        if(errorCode == BaseResp.ErrCode.ERR_OK) {
            sPayCallback.success();
        } else if(errorCode == BaseResp.ErrCode.ERR_COMM) {
            sPayCallback.failed(errorCode, errorMsg);
        } else if(errorCode == BaseResp.ErrCode.ERR_USER_CANCEL) {
            sPayCallback.cancel();
        } else {
            sPayCallback.failed(errorCode, errorMsg);
        }

        sPayCallback = null;
    }

    /**
     * 检测是否支持微信支付
     */
    private boolean check() {
        return mWXApi.isWXAppInstalled() && mWXApi.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
    }
}

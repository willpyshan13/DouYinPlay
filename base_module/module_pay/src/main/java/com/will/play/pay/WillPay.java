package com.will.play.pay;

import android.app.Activity;

import com.will.play.pay.base.IPayInfo;
import com.will.play.pay.base.IPayStrategy;
import com.will.play.pay.callback.IPayCallback;


public class WillPay {
    public static <T extends IPayInfo> void pay(IPayStrategy<T> payWay, Activity mActivity, T payinfo, IPayCallback callback){
        payWay.pay(mActivity, payinfo, callback);
    }
}

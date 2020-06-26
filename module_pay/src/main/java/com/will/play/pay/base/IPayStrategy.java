package com.will.play.pay.base;

import android.app.Activity;

import com.will.play.pay.callback.IPayCallback;

public interface IPayStrategy<T extends IPayInfo> {
    void pay(Activity activity, T payInfo, IPayCallback payCallback);
}

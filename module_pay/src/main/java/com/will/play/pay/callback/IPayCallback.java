package com.will.play.pay.callback;

import androidx.annotation.Nullable;

public interface IPayCallback {
    void success();
    void failed(int code, @Nullable String message);
    void cancel();
}

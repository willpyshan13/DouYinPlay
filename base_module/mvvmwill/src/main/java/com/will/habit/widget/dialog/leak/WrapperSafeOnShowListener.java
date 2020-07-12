package com.will.habit.widget.dialog.leak;

import android.content.DialogInterface;

import androidx.annotation.Nullable;

import java.lang.ref.WeakReference;

/**
 * Desc:
 * <p>
 * Date: 2020-07-12 12:43
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: zhuanghongzhan
 */
class WrapperSafeOnShowListener implements DialogInterface.OnShowListener {

    private WeakReference<DialogInterface.OnShowListener> mListener;

    WrapperSafeOnShowListener(@Nullable DialogInterface.OnShowListener listener) {
        mListener = new WeakReference<>(listener);
    }

    @Override
    public void onShow(DialogInterface dialog) {
        DialogInterface.OnShowListener listener = mListener.get();
        if (listener != null) {
            listener.onShow(dialog);
        }
    }
}

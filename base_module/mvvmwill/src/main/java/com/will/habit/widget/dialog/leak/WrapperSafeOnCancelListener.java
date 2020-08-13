package com.will.habit.widget.dialog.leak;

import android.content.DialogInterface;

import androidx.annotation.Nullable;

import java.lang.ref.WeakReference;

/**
 * Desc:
 * Date: 2020-07-12 12:40
 * Updater:
 * Update Time:
 * Update Comments:
 *
 */
class WrapperSafeOnCancelListener implements DialogInterface.OnCancelListener {

    private WeakReference<DialogInterface.OnCancelListener> mListener;

    WrapperSafeOnCancelListener(@Nullable DialogInterface.OnCancelListener listener) {
        mListener = new WeakReference<>(listener);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        DialogInterface.OnCancelListener listener = mListener.get();
        if (listener != null) {
            listener.onCancel(dialog);
        }
    }
}


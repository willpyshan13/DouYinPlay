package com.will.habit.widget.dialog.leak;

import android.content.DialogInterface;

import androidx.annotation.Nullable;

import java.lang.ref.WeakReference;

/**
 * Desc:
 * <p>
 * Date: 2020-07-12 12:42
 * Updater:
 * Update Time:
 * Update Comments:
 *
 */
class WrapperSafeOnDismissListener implements DialogInterface.OnDismissListener {

    private WeakReference<DialogInterface.OnDismissListener> mListener;

    WrapperSafeOnDismissListener(@Nullable DialogInterface.OnDismissListener listener) {
        mListener = new WeakReference<>(listener);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        DialogInterface.OnDismissListener listener = mListener.get();
        if (listener != null) {
            listener.onDismiss(dialog);
        }
    }
}
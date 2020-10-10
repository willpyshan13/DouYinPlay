package com.will.habit.widget.dialog.interfaces;

import android.content.DialogInterface;
import android.util.Log;
import android.view.ViewTreeObserver;

import androidx.appcompat.app.AlertDialog;

import java.lang.ref.WeakReference;

/**
 * Desc:
 * <p>
 *
 * @Author: will
 */
public class DetachableDialogOnShowListener implements DialogInterface.OnShowListener {

    private DialogInterface.OnShowListener onShowListener;
    private static WeakReference<DialogInterface.OnShowListener> showListenerWeakReference;

    public static DetachableDialogOnShowListener wrap(DialogInterface.OnShowListener onShowListener) {
        showListenerWeakReference = new WeakReference<>(onShowListener);
        if (showListenerWeakReference.get() != null) {
            return new DetachableDialogOnShowListener(showListenerWeakReference.get());
        } else {
            return new DetachableDialogOnShowListener(onShowListener);
        }

    }

    private DetachableDialogOnShowListener(DialogInterface.OnShowListener onShowListener) {
        this.onShowListener = onShowListener;
    }

    @Override
    public void onShow(DialogInterface dialog) {
        if (onShowListener != null) {
            onShowListener.onShow(dialog);
        }
    }

    public void clearOnDetach(final AlertDialog dialog) {
        dialog.getWindow()
                .getDecorView()
                .getViewTreeObserver()
                .addOnWindowAttachListener(new ViewTreeObserver.OnWindowAttachListener() {
                    @Override
                    public void onWindowAttached() {
                    }

                    @Override
                    public void onWindowDetached() {
                        onShowListener = null;
                        dialog.setOnShowListener(null);
                    }
                });
    }


}

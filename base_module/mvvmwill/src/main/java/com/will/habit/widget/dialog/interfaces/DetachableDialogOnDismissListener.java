package com.will.habit.widget.dialog.interfaces;

import android.content.DialogInterface;

import java.lang.ref.WeakReference;

/**
 * Desc:
 * <p>
 *
 * @Author: will
 */
public class DetachableDialogOnDismissListener implements DialogInterface.OnDismissListener {

    private DialogInterface.OnDismissListener delegateOrNull;

    private DetachableDialogOnDismissListener(DialogInterface.OnDismissListener delegateOrNull) {
        this.delegateOrNull = delegateOrNull;
    }

    public static DetachableDialogOnDismissListener wrap(DialogInterface.OnDismissListener dismissListener) {
        WeakReference<DialogInterface.OnDismissListener> dismissListenerWeakReference = new WeakReference<>(dismissListener);
        if (dismissListenerWeakReference.get() != null) {
            return new DetachableDialogOnDismissListener(dismissListenerWeakReference.get());
        } else {
            return new DetachableDialogOnDismissListener(dismissListener);
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if (delegateOrNull != null) {
            delegateOrNull.onDismiss(dialog);
            delegateOrNull = null;
        }
    }
}
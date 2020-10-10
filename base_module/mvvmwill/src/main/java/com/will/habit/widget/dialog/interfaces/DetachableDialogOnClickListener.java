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
public class DetachableDialogOnClickListener implements DialogInterface.OnClickListener {

    private DialogInterface.OnClickListener delegateOrNull;

    public static DetachableDialogOnClickListener wrap(DialogInterface.OnClickListener delegate) {
        WeakReference<DialogInterface.OnClickListener> dialogWeakRefrence = new WeakReference<>(delegate);
        if (dialogWeakRefrence.get() != null) {
            return new DetachableDialogOnClickListener(dialogWeakRefrence.get());
        } else {
            return new DetachableDialogOnClickListener(delegate);
        }
    }

    private DetachableDialogOnClickListener(DialogInterface.OnClickListener delegate) {
        delegateOrNull = delegate;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (delegateOrNull != null) {
            delegateOrNull.onClick(dialog, which);
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
                        delegateOrNull = null;
                        dialog.setOnShowListener(null);
                    }
                });
    }

}

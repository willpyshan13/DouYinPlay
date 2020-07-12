package com.will.habit.widget.dialog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

/**
 * Desc:
 * <p>
 * Date: 2019-07-22
 *
 * @author: will
 */
public class AlertDialogProxy {

    @NonNull
    private final AlertDialog mDialog;

    AlertDialogProxy(@NonNull AlertDialog dialog) {
        mDialog = dialog;
    }

    public void show() {
        mDialog.show();
    }

    public boolean isShowing() {
        return mDialog.isShowing();
    }

    public void dismiss() {
        mDialog.dismiss();
    }

    public AlertDialog get() {
        return mDialog;
    }
}

package com.will.habit.widget.dialog.leak;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Desc:解决dialog泄漏问题
 * dialog的listener是用handler发送的，导致dialog被持有无法释放
 * 解决：包装一层listener，使用WeakReference持有
 * Date:
 * Updater:
 * Update Time:
 * Update Comments:
 *
 */
public class LeakSafeDialog extends Dialog {

    public LeakSafeDialog(@NonNull Context context) {
        super(context);
    }

    public LeakSafeDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected LeakSafeDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void setOnCancelListener(@Nullable OnCancelListener listener) {
        super.setOnCancelListener(new WrapperSafeOnCancelListener(listener));
    }

    @Override
    public void setOnShowListener(@Nullable OnShowListener listener) {
        super.setOnShowListener(new WrapperSafeOnShowListener(listener));
    }

    @Override
    public void setOnDismissListener(@Nullable OnDismissListener listener) {
        super.setOnDismissListener(new WrapperSafeOnDismissListener(listener));
    }
}


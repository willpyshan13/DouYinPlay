package com.will.habit.widget.dialog.leak;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialog;

/**
 * Desc: 解决dialog泄漏问题
 * dialog的listener是用handler发送的，导致dialog被持有无法释放
 * 解决：包装一层listener，使用WeakReference持有
 * <p>
 * Date: 2019-07-22
 * Copyright: Copyright (c) 2010-2019
 * Updater:
 * Update Time: 2019/9/3
 * Update Comments:
 *
 * @Author: pengyushan
 */
public class LeakSafeBottomDialog extends BottomSheetDialog {

    public LeakSafeBottomDialog(@NonNull Context context) {
        super(context);
    }

    public LeakSafeBottomDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected LeakSafeBottomDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
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

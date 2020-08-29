package com.will.habit.widget.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.will.habit.BuildConfig;
import com.will.habit.R;
import com.will.habit.widget.dialog.leak.LeakSafeBottomDialog;

import org.jetbrains.annotations.NotNull;

/**
 * Desc: 底部弹窗Dialog 通用设置
 * 处理 java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState 异常
 * <p>
 * Date: 2020-08-28
 * Copyright: Copyright (c) 2010-2019
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
public  class CommonBottomDialogFragment extends BottomSheetDialogFragment {

    private DialogInterface.OnDismissListener mOnDismissListener;
    private DialogInterface.OnCancelListener mOnCancelListener;
    private BottomSheetBehavior<View> mBehavior;

    @CallSuper
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new LeakSafeBottomDialog(requireContext(), getTheme());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, R.style.BottomDialogStyle);
    }

    @CallSuper
    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        view.post(new Runnable() {
            @Override
            public void run() {
                mBehavior = BottomSheetBehavior.from((View) view.getParent());
                mBehavior.setHideable(enableDragToClose());
                mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                mBehavior.setSkipCollapsed(enableDragToClose());
                if (!enableDragToClose()) {
                    mBehavior.setPeekHeight(view.getHeight());
                }
            }
        });
    }

    /**
     * 是否开启向下拖动关闭
     */
    protected boolean enableDragToClose() {
        return true;
    }

    @Override
    public void onDismiss(@NotNull DialogInterface dialog) {
        super.onDismiss(dialog);
        if (mOnDismissListener != null) {
            mOnDismissListener.onDismiss(dialog);
        }
    }

    @Override
    public void onCancel(@NotNull DialogInterface dialog) {
        super.onCancel(dialog);
        if (mOnCancelListener != null) {
            mOnCancelListener.onCancel(dialog);
        }
    }

    @SuppressLint("CommitTransaction")
    @Override
    public void show(FragmentManager manager, String tag) {
        this.show(manager.beginTransaction(), tag);
    }

    @Override
    public void dismiss() {
        try {
            super.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener listener) {
        mOnDismissListener = listener;
    }

    public void setOnCancelListener(DialogInterface.OnCancelListener listener) {
        mOnCancelListener = listener;
    }

    @Override
    public int show(@NotNull FragmentTransaction transaction, String tag) {
        if (isResumed() || isAdded() || isVisible() || isRemoving() || isDetached()) {
            if (BuildConfig.DEBUG) {
                boolean isResumed = isResumed();
                boolean isAdded = isAdded();
                boolean isVisible = isVisible();
                boolean isRemoving = isRemoving();
                boolean isDetached = isDetached();
                Log.d(getClass().getSimpleName(), "showDialog Failed !isResumed=" + isResumed
                        + ",isAdded=" + isAdded + ",isVisible=" + isVisible + ",isRemoving=" + isRemoving + ",isDetached=" + isDetached);
            }
            return -1;
        }
        try {
            return transaction.add(this, tag).commitAllowingStateLoss();
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), "showDialog Failed", e);
            e.printStackTrace();
            return -1;
        }
    }
}
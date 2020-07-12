package com.will.habit.widget.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.GradientDrawable;
import android.view.Display;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;

import com.will.habit.R;
import com.will.habit.utils.DpUtil;
import com.will.habit.widget.dialog.interfaces.DetachableDialogOnClickListener;
import com.will.habit.widget.dialog.interfaces.DetachableDialogOnDismissListener;
import com.will.habit.widget.dialog.interfaces.DetachableDialogOnShowListener;

/**
 * Desc:弹窗业务
 * <p>
 *
 * @Author: will
 */
public abstract class BaseDialog {

    protected Context mContext;
    /**
     * 弹窗圆角大小
     */
    protected float dialogCornersRadius;
    /**
     * 弹窗颜色
     */
    protected int dialogBgColor = -1;
    protected int dialogWidth;
    private int dialogHeight;
    protected int dialogGravity;
    /**
     * 弹出时背景的灰度
     */
    private float dimAmount;
    protected int screenWith;
    private int windowAnimation;
    private boolean cancleable = true;
    private boolean canceledOnTouchOutside = true;
    protected boolean isFromBottom;


    protected String positiveButtonText;
    protected String negativeButtonText;

    protected int positiveButtonColor;
    protected int negativeButtonColor;

    protected float positiveButtonSize;
    protected float negativeButtonSize;

    protected String message;
    protected String title;

    protected DetachableDialogOnClickListener positiveOnClickListener;
    protected DetachableDialogOnClickListener negativeOnClickListener;
    protected DetachableDialogOnDismissListener dialogOnDismissListener;
    protected DetachableDialogOnShowListener dialogOnShowListener;

    public BaseDialog(Context context) {
        this.mContext = context;

        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display =windowManager.getDefaultDisplay();
        Point outSize = new Point();
        display.getSize(outSize);
        /*
         * 得到屏幕的宽度
         */
        screenWith = outSize.x;
        dimAmount = 0.5f;
        dialogWidth = screenWith * 6 / 7;
        dialogGravity = Gravity.CENTER;
        dialogCornersRadius = 3f;
    }

    public AlertDialogProxy create() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(title)
                .setMessage(message)
                .setNegativeButton(negativeButtonText, negativeOnClickListener)
                .setPositiveButton(positiveButtonText, positiveOnClickListener);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setOnDismissListener(dialogOnDismissListener);
        alertDialog.setOnShowListener(dialogOnShowListener);
        if (dialogOnShowListener != null) {
            dialogOnShowListener.clearOnDetach(alertDialog);
        }
        if (negativeOnClickListener != null) {
            negativeOnClickListener.clearOnDetach(alertDialog);
        }
        if (positiveOnClickListener != null) {
            positiveOnClickListener.clearOnDetach(alertDialog);
        }
        return new AlertDialogProxy(alertDialog) {
            @Override
            public void show() {
                super.show();
                setDialogDetails(mContext, alertDialog);
                alertDialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
                alertDialog.setCancelable(cancleable);
                setDialogWindow(alertDialog);
            }
        };
    }

    /**
     * Desc:设置弹窗
     * <p>
     * @param context 上下文对象
     * @param alertDialog 创建弹窗
     */
    protected abstract void setDialogDetails(Context context, AlertDialog alertDialog);

    private void setDialogWindow(AlertDialog alertDialog) {

        Window window = alertDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        window.setDimAmount(dimAmount);
        window.setGravity(dialogGravity);

        if (dialogGravity != Gravity.CENTER) {
            lp.y = (screenWith - dialogWidth) / 2;
        }

        window.setLayout(dialogWidth, dialogHeight == 0 ? LinearLayout.LayoutParams.WRAP_CONTENT : dialogHeight);
        window.setBackgroundDrawable(getGradientDrawable(dialogBgColor == -1 ? Color.WHITE : dialogBgColor));

        if (windowAnimation != 0) {
            window.setWindowAnimations(windowAnimation);
        } else if (isFromBottom) {
            window.setWindowAnimations(R.style.dialog_anim_style);
        }

    }

    protected GradientDrawable getGradientDrawable(int color) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(color);
        float cornersRadius = DpUtil.dp2px(dialogCornersRadius);
        gradientDrawable.setCornerRadii(new float[]{cornersRadius, cornersRadius, cornersRadius, cornersRadius, cornersRadius, cornersRadius, cornersRadius, cornersRadius});
        return gradientDrawable;
    }

    /**
     *   dialog宽度，建议依屏幕的宽为参考
     */
    public BaseDialog setDialogWidth(int dialogWidthPx) {
        this.dialogWidth = dialogWidthPx;
        return this;
    }

    /**
     * dialog高度，建议依屏幕的高为参考
     */
    public BaseDialog setDialogHeight(int dialogHeightPx) {
        this.dialogHeight = dialogHeightPx;
        return this;
    }

    /**
     * dialog是否可取消
     */
    public BaseDialog setCancelable(boolean cancleable) {
        this.cancleable = cancleable;
        return this;
    }

    /**
     * 点击dialog外部是否可取消
     * @param canceledOnTouchOutside
     * @return
     */
    public BaseDialog setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
        this.canceledOnTouchOutside = canceledOnTouchOutside;
        return this;
    }

    /**
     * dialog是否底部弹出
     */
    public BaseDialog setIsFromBottom(boolean isFromBottom) {
        this.isFromBottom = isFromBottom;
        return this;
    }

    /**
     * dialog弹出动画
     */
    public BaseDialog setWindowAnimation(int windowAnimation) {
        this.windowAnimation = windowAnimation;
        return this;
    }

    /**
     * dialog弹出位置
     * @param gravity 方向
     * @return BaseDialog
     */
    public BaseDialog setDialogGravity(int gravity) {
        this.dialogGravity = gravity;
        return this;
    }

    /**
     * dialog的背景颜色
     */
    public BaseDialog setDialogBgColor(int dialogBgColor) {
        this.dialogBgColor = dialogBgColor;
        return this;
    }

    /**
     *     dialog弹出时背景的灰度 0.0f-1.0f 0.0f为透明 1.0f为全黑
     */
    public BaseDialog setDimAmount(float dimAmount) {
        this.dimAmount = dimAmount;
        return this;
    }

    /**
     * dialog的圆角
     * @param dialogCornersRadiusDp 圆角大小
     * @return
     */
    public BaseDialog setDialogCornersRadius(float dialogCornersRadiusDp) {
        this.dialogCornersRadius = dialogCornersRadiusDp;
        return this;
    }


}

package com.will.habit.widget.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Space;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AlertDialogLayout;
import androidx.appcompat.widget.ButtonBarLayout;

import com.will.habit.R;
import com.will.habit.utils.DpUtil;
import com.will.habit.widget.dialog.interfaces.DetachableDialogOnClickListener;
import com.will.habit.widget.dialog.interfaces.DetachableDialogOnDismissListener;


/**
 * Desc:通用弹窗
 * <p>
 * Date: 2019-05-28
 * Copyright: Copyright (c) 2018-2019
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
public class ConfirmDialog extends BaseDialog {


    private int titlePaddingTop;
    private int titlePaddingBottom;

    private int messagePaddingTop;
    private int messagePaddingBottom;

    private Typeface titleTypeface;
    private Typeface messageTypeface;

    private boolean titleBold;
    private boolean messageBold;

    private int iosDividerColor;
    private int iosDividerSize;
    private float messageSize;
    private int messageColor;
    private float titleSize;
    private int titleColor;

    private boolean isIos;
    private int confirmType;

    public ConfirmDialog(Context context) {
        this(context, false);
    }


    public ConfirmDialog(Context context, boolean isIos) {
        super(context);
        this.isIos = isIos;
        titleColor = Color.parseColor("#000000");
        messageColor = Color.parseColor("#66696d");
        if (isIos) {
            dialogCornersRadius = 13f;
            iosDividerSize = 1;
            iosDividerColor = Color.parseColor("#ededed");
            titleSize = 20f;
            messageSize = 16f;
            positiveButtonColor = Color.parseColor("#51b0ff");
            negativeButtonColor = Color.parseColor("#51b0ff");
            positiveButtonSize = 16f;
            negativeButtonSize = 16f;
            dialogWidth = screenWith * 3 / 4;
        } else {
            dialogCornersRadius = 3f;
        }
    }

    @Override
    protected void setDialogDetails(Context context, AlertDialog alertDialog) {

        Window window = alertDialog.getWindow();

        TextView titleView = window.findViewById(R.id.alertTitle);
        TextView messageView = window.findViewById(android.R.id.message);

        titleView.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleSize == 0 ? titleView.getTextSize() : DpUtil.sp2px(titleSize));
        titleView.setTextColor(titleColor == 0 ? titleView.getCurrentTextColor() : titleColor);
        titleView.setPadding(titleView.getPaddingLeft(), titlePaddingTop == 0 ? titleView.getPaddingTop() : DpUtil.dp2px(titlePaddingTop), titleView.getPaddingRight(), titlePaddingBottom == 0 ? titleView.getPaddingBottom() : DpUtil.dp2px(titlePaddingBottom));
        if (titleTypeface != null) {
            titleView.setTypeface(titleTypeface, titleBold ? Typeface.BOLD : Typeface.NORMAL);
        }

        messageView.setTextSize(TypedValue.COMPLEX_UNIT_PX, messageSize == 0 ? messageView.getTextSize() : DpUtil.sp2px(messageSize));
        messageView.setTextColor(messageColor == 0 ? messageView.getCurrentTextColor() : messageColor);
        messageView.setPadding(messageView.getPaddingLeft(), messagePaddingTop == 0 ? messageView.getPaddingTop() : DpUtil.dp2px(messagePaddingTop), messageView.getPaddingRight(), messagePaddingBottom == 0 ? messageView.getPaddingBottom() : DpUtil.dp2px(messagePaddingBottom));
        if (messageTypeface != null) {
            messageView.setTypeface(messageTypeface, messageBold ? Typeface.BOLD : Typeface.NORMAL);
        }

        Button negativeButton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);

        negativeButton.setTextColor(negativeButtonColor == 0 ? negativeButton.getCurrentTextColor() : negativeButtonColor);
        positiveButton.setTextColor(positiveButtonColor == 0 ? positiveButton.getCurrentTextColor() : positiveButtonColor);

        negativeButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, negativeButtonSize == 0 ? negativeButton.getTextSize() : DpUtil.sp2px(negativeButtonSize));
        positiveButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, positiveButtonSize == 0 ? positiveButton.getTextSize() : DpUtil.sp2px(positiveButtonSize));

        if (!(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(message))) {
            titleView.setPadding(titleView.getPaddingLeft(), DpUtil.dp2px(titlePaddingTop == 0 ? 15 : titlePaddingTop), titleView.getPaddingRight(), DpUtil.dp2px(titlePaddingBottom == 0 ? 20 : titlePaddingBottom));
            messageView.setPadding(messageView.getPaddingLeft(), DpUtil.dp2px(messagePaddingTop == 0 ? 15 : messagePaddingTop), messageView.getPaddingRight(), DpUtil.dp2px(messagePaddingBottom == 0 ? 20 : messagePaddingBottom));
        }


        titleView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        messageView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        messageView.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams buttomParams = new LinearLayout.LayoutParams(0, FrameLayout.LayoutParams.MATCH_PARENT, 1);
        buttomParams.setMargins(0, 0, 0, 0);
        negativeButton.setLayoutParams(buttomParams);
        positiveButton.setLayoutParams(buttomParams);
        negativeButton.setPadding(0, 0, 0, 0);
        positiveButton.setPadding(0, 0, 0, 0);

        Space space = window.findViewById(R.id.spacer);
        space.setVisibility(View.GONE);
        GradientDrawable divider = new GradientDrawable();
        divider.setColor(iosDividerColor);
        divider.setSize(iosDividerSize, iosDividerSize);

        ButtonBarLayout buttonBarLayout = (ButtonBarLayout) negativeButton.getParent();
        buttonBarLayout.setPadding(0, 0, 0, 0);
        buttonBarLayout.setDividerDrawable(divider);
        buttonBarLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);

        ScrollView scrollView = (ScrollView) buttonBarLayout.getParent();
        AlertDialogLayout parent = (AlertDialogLayout) scrollView.getParent();
        if (confirmType == 1 || isIos) {
            /**
             * 添加线条
             */
            View dividerView = new View(mContext);
            dividerView.setBackgroundColor(iosDividerColor);
            parent.addView(dividerView, 2, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1));
        } else {

        }


    }

    public ConfirmDialog setTtitle(String title) {
        this.title = title;
        return this;
    }

    public ConfirmDialog setTitleSize(float titleSizeSp) {
        this.titleSize = titleSizeSp;
        return this;
    }

    public ConfirmDialog setTitleColor(int titleColor) {
        this.titleColor = titleColor;
        return this;
    }

    public ConfirmDialog setTtitle(String title, float titleSizeSp) {
        this.title = title;
        this.titleSize = titleSizeSp;
        return this;
    }

    public ConfirmDialog setTtitle(String title, int titleColor) {
        this.title = title;
        this.titleColor = titleColor;
        return this;
    }

    public ConfirmDialog setTtitle(String title, float titleSizeSp, int titleColor) {
        this.title = title;
        this.titleSize = titleSizeSp;
        this.titleColor = titleColor;
        return this;
    }


    public ConfirmDialog setMessage(String message) {
        this.message = message;
        return this;
    }

    public ConfirmDialog setMessageSize(float messageSizeSp) {
        this.messageSize = messageSizeSp;
        return this;
    }

    public ConfirmDialog setMessageColor(int messageColor) {
        this.messageColor = messageColor;
        return this;
    }


    public ConfirmDialog setMessage(String message, float messageSizeSp) {
        this.message = message;
        this.messageSize = messageSizeSp;
        return this;
    }

    public ConfirmDialog setMessage(String message, int messageColor) {
        this.message = message;
        this.messageColor = messageColor;
        return this;
    }

    public ConfirmDialog setMessage(String message, float messageSizeSp, int messageColor) {
        this.message = message;
        this.messageSize = messageSizeSp;
        this.messageColor = messageColor;
        return this;
    }

    public ConfirmDialog setNegativeButton(String negativeButtonText) {
        this.negativeButtonText = negativeButtonText;
        return this;
    }

    public ConfirmDialog setNegativeButtonSize(float negativeButtonSizeSp) {
        this.negativeButtonSize = negativeButtonSizeSp;
        return this;
    }

    public ConfirmDialog setNegativeButtonColor(int negativeButtonColor) {
        this.negativeButtonColor = negativeButtonColor;
        return this;
    }

    public ConfirmDialog setNegativeButton(String negativeButtonText, float negativeButtonSizeSp) {
        this.negativeButtonText = negativeButtonText;
        this.negativeButtonSize = negativeButtonSizeSp;
        return this;
    }


    public ConfirmDialog setNegativeButton(String negativeButtonText, int negativeButtonColor) {
        this.negativeButtonText = negativeButtonText;
        this.negativeButtonColor = negativeButtonColor;
        return this;
    }

    public ConfirmDialog setNegativeButton(String negativeButtonText, float negativeButtonSizeSp, int negativeButtonColor) {
        this.negativeButtonText = negativeButtonText;
        this.negativeButtonSize = negativeButtonSizeSp;
        this.negativeButtonColor = negativeButtonColor;
        return this;
    }


    public ConfirmDialog setNegativeButton(String negativeButtonText, DialogInterface.OnClickListener negativeOnClickListener) {
        this.negativeButtonText = negativeButtonText;
        this.negativeOnClickListener = DetachableDialogOnClickListener.wrap(negativeOnClickListener);
        return this;
    }

    public ConfirmDialog setNegativeButton(String negativeButtonText, float negativeButtonSizeSp, DialogInterface.OnClickListener negativeOnClickListener) {
        this.negativeButtonText = negativeButtonText;
        this.negativeButtonSize = negativeButtonSizeSp;
        this.negativeOnClickListener = DetachableDialogOnClickListener.wrap(negativeOnClickListener);
        return this;
    }

    public ConfirmDialog setNegativeButton(String negativeButtonText, int negativeButtonColor, DialogInterface.OnClickListener negativeOnClickListener) {
        this.negativeButtonText = negativeButtonText;
        this.negativeButtonColor = negativeButtonColor;
        this.negativeOnClickListener = DetachableDialogOnClickListener.wrap(negativeOnClickListener);
        return this;
    }


    public ConfirmDialog setNegativeButton(String negativeButtonText, float negativeButtonSize, int negativeButtonColor, DialogInterface.OnClickListener negativeOnClickListener) {
        this.negativeButtonText = negativeButtonText;
        this.negativeButtonSize = negativeButtonSize;
        this.negativeButtonColor = negativeButtonColor;
        this.negativeOnClickListener = DetachableDialogOnClickListener.wrap(negativeOnClickListener);
        return this;
    }

    public ConfirmDialog setDialogDismissListener(DialogInterface.OnDismissListener dismissListener) {
        this.dialogOnDismissListener = DetachableDialogOnDismissListener.wrap(dismissListener);
        return this;
    }

    public ConfirmDialog setPositiveButton(String positiveButtonText) {
        this.positiveButtonText = positiveButtonText;
        return this;
    }

    public ConfirmDialog setPositiveButtonSize(float positiveButtonSizeSp) {
        this.positiveButtonSize = positiveButtonSizeSp;
        return this;
    }

    public ConfirmDialog setPositiveButtonColor(int positiveButtonColor) {
        this.positiveButtonColor = positiveButtonColor;
        return this;
    }

    public ConfirmDialog setPositiveButton(String positiveButtonText, float positiveButtonSizeSp) {
        this.positiveButtonText = positiveButtonText;
        this.positiveButtonSize = positiveButtonSizeSp;
        return this;
    }

    public ConfirmDialog setPositiveButton(String positiveButtonText, int positiveButtonColor) {
        this.positiveButtonText = positiveButtonText;
        this.positiveButtonColor = positiveButtonColor;
        return this;
    }

    public ConfirmDialog setPositiveButton(String positiveButtonText, float positiveButtonSizeSp, int positiveButtonColor) {
        this.positiveButtonText = positiveButtonText;
        this.positiveButtonSize = positiveButtonSizeSp;
        this.positiveButtonColor = positiveButtonColor;
        return this;
    }


    public ConfirmDialog setPositiveButton(String positiveButtonText, DialogInterface.OnClickListener positiveOnClickListener) {
        this.positiveButtonText = positiveButtonText;
        this.positiveOnClickListener = DetachableDialogOnClickListener.wrap(positiveOnClickListener);
        return this;
    }

    public ConfirmDialog setPositiveButton(String positiveButtonText, float positiveButtonSizeSp, DialogInterface.OnClickListener positiveOnClickListener) {
        this.positiveButtonText = positiveButtonText;
        this.positiveButtonSize = positiveButtonSizeSp;
        this.positiveOnClickListener = DetachableDialogOnClickListener.wrap(positiveOnClickListener);
        return this;
    }

    public ConfirmDialog setPositiveButton(String positiveButtonText, int positiveButtonColor, DialogInterface.OnClickListener positiveOnClickListener) {
        this.positiveButtonText = positiveButtonText;
        this.positiveButtonColor = positiveButtonColor;
        this.positiveOnClickListener = DetachableDialogOnClickListener.wrap(positiveOnClickListener);
        return this;
    }

    public ConfirmDialog setPositiveButton(String positiveButtonText, float positiveButtonSizeSp, int positiveButtonColor, DialogInterface.OnClickListener positiveOnClickListener) {
        this.positiveButtonText = positiveButtonText;
        this.positiveButtonSize = positiveButtonSizeSp;
        this.positiveButtonColor = positiveButtonColor;
        this.positiveOnClickListener = DetachableDialogOnClickListener.wrap(positiveOnClickListener);
        return this;
    }

    public ConfirmDialog setIosDividerColor(int iosDividerColor) {
        this.iosDividerColor = iosDividerColor;
        return this;
    }

    public ConfirmDialog setIosDividerSize(int iosDividerSizePx) {
        this.iosDividerSize = iosDividerSizePx;
        return this;
    }


    public ConfirmDialog setTitlePaddingTop(int titlePaddingTopDp) {
        this.titlePaddingTop = titlePaddingTopDp;
        return this;
    }

    public ConfirmDialog setTitlePaddingBottom(int titlePaddingBottomDp) {
        this.titlePaddingBottom = titlePaddingBottomDp;
        return this;
    }

    public ConfirmDialog setTitlePadding(int titlePaddingTopDp, int titlePaddingBottomDp) {
        this.titlePaddingTop = titlePaddingTopDp;
        this.titlePaddingBottom = titlePaddingBottomDp;
        return this;
    }

    public ConfirmDialog setMessagePaddingTop(int messagePaddingTopDp) {
        this.messagePaddingTop = messagePaddingTopDp;
        return this;
    }

    public ConfirmDialog setMessagePaddingBottom(int messagePaddingBottomDp) {
        this.messagePaddingBottom = messagePaddingBottomDp;
        return this;
    }

    public ConfirmDialog setMessagePadding(int messagePaddingTopDp, int messagePaddingBottomDp) {
        this.messagePaddingTop = messagePaddingTopDp;
        this.messagePaddingBottom = messagePaddingBottomDp;
        return this;
    }

    public ConfirmDialog setTitleTypeface(Typeface titleTypeface) {
        this.titleTypeface = titleTypeface;
        return this;
    }

    public ConfirmDialog setMessageTypeface(Typeface messageTypeface) {
        this.messageTypeface = messageTypeface;
        return this;
    }

    public ConfirmDialog setTitleTypeface(Typeface titleTypeface, boolean isBold) {
        this.titleTypeface = titleTypeface;
        this.titleBold = isBold;
        return this;
    }

    public ConfirmDialog setMessageTypeface(Typeface messageTypeface, boolean isBold) {
        this.messageTypeface = messageTypeface;
        this.messageBold = isBold;
        return this;
    }

    /**
     * Desc:设置弹窗类型
     * <p>
     * Date: 2019-07-09
     *
     * @param confirmType
     */
    public void setConfirmType(int confirmType) {
        this.confirmType = confirmType;
    }

    /**
     * Desc:设置圆角
     * <p>
     * Date: 2019-07-09
     *
     * @param dialogCornersRadiusDp
     */
    public ConfirmDialog setConfirmRadiusDp(float dialogCornersRadiusDp) {
        setDialogCornersRadius(dialogCornersRadiusDp);
        return this;
    }

    /**
     * Desc:点击返回键是否去除dialog
     * <p>
     * Date: 2019-07-11
     *
     * @param cancleable
     * @return confirm dialog
     */
    public ConfirmDialog setCancelableTouch(boolean cancleable) {
        setCancelable(cancleable);
        return this;
    }

    /**
     * Desc:点击外窗外面是否隐藏
     * <p>
     * Date: 2019-07-11
     *
     * @param canceledOnTouchOutside
     * @return confirm dialog
     */
    public ConfirmDialog setCanceledOnTouchOutsides(boolean canceledOnTouchOutside) {
        setCanceledOnTouchOutside(canceledOnTouchOutside);
        return this;
    }
}
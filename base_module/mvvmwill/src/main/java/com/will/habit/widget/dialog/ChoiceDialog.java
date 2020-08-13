package com.will.habit.widget.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.widget.NestedScrollView;

import com.will.habit.R;
import com.will.habit.utils.DpUtil;
import com.will.habit.utils.StringUtils;
import com.will.habit.widget.dialog.interfaces.DetachableDialogOnDismissListener;

/**
 * Desc:通用弹窗
 * <p>
 * Copyright: Copyright (c) 2010-2019
 *
 * @Author: pengyushan
 */
public class ChoiceDialog extends BaseDialog {

    /**
     * title的行数
     */
    private int titleMaxLines;
    /**
     * title的颜色
     */
    private int titleColor;
    /**
     * title的大小
     */
    private float titleSize;
    private Typeface titleTypeface;
    private int titlePaddingLeft, titlePaddingTop, titlePaddingRight, titlePaddingBottom;
    private int titleGravity;

    private int itemTextColor;
    private int cancelTextColor;
    private float itemTextSize;
    private Typeface itemTextTypeface;
    private int itemHeight;
    private int itemTextGravity;
    private int itemTextPaddingLeft, itemTextPaddingRight;

    private Typeface cancelButtonTextTypeface;

    private boolean titleBold;
    private boolean itemBold;
    private boolean cancelButtonBold;

    private int dividerColor;
    private int dividerHeight;

    private int itemDividerPadding;

    private Object[] items;

    private boolean hasCancleButton;
    private String cancleButtonText;

    private boolean isIos;

    private OnItemClickListener onItemClickListener;
    private OnTitleClickListener onTitleClickListener;

    public ChoiceDialog(Context context) {
        this(context, false);
    }

    public ChoiceDialog(Context context, boolean isIos) {
        super(context);
        this.isIos = isIos;
        titleMaxLines = 2;
        titlePaddingLeft = titlePaddingRight = titlePaddingTop = titlePaddingBottom = 15;
        itemHeight = 50;
        itemTextPaddingLeft = itemTextPaddingRight = 15;
        dividerColor = Color.LTGRAY;
        dividerHeight = 1;
        itemDividerPadding = 15;


        if (isIos) {
            dialogWidth = (screenWith - DpUtil.dp2px(30));
            titleGravity = Gravity.CENTER;
            titleColor = Color.parseColor("#2C7CF6");
            cancelTextColor = Color.parseColor("#2C7CF6");
            titleSize = 20f;
            itemTextGravity = Gravity.CENTER;
            itemTextColor = Color.parseColor("#2C7CF6");
            itemTextSize = 18f;
            isFromBottom = true;
            dialogGravity = Gravity.BOTTOM;
            dialogCornersRadius = 13f;
            cancelButtonTextTypeface = Typeface.defaultFromStyle(Typeface.NORMAL);
        } else {
            titleColor = Color.BLACK;
            titleGravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;
            titleSize = 16f;
            itemTextGravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;
            itemTextColor = Color.BLACK;
            itemTextSize = 14f;
            dialogCornersRadius = 3f;
            isFromBottom = false;
            dialogGravity = Gravity.CENTER;
            cancelButtonTextTypeface = Typeface.defaultFromStyle(Typeface.NORMAL);
        }

    }

    @Override
    protected void setDialogDetails(Context context, final AlertDialog alertDialog) {
        GradientDrawable divider = new GradientDrawable();
        divider.setColor(dividerColor);
        divider.setSize(dividerHeight, dividerHeight);

        LinearLayout allParentView = new LinearLayout(mContext);
        allParentView.setOrientation(LinearLayout.VERTICAL);

        LinearLayout parentView = new LinearLayout(mContext);
        parentView.setOrientation(LinearLayout.VERTICAL);
        parentView.setDividerDrawable(divider);
        parentView.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);

        initView(parentView);

        if (items == null || items.length <= 0) {
            throw new RuntimeException("items的长度不能为0");
        }

        NestedScrollView nestedScrollView = new NestedScrollView(mContext);
        LinearLayout itemParent = new LinearLayout(mContext);
        itemParent.setOrientation(LinearLayout.VERTICAL);
        itemParent.setDividerDrawable(divider);
        itemParent.setDividerPadding(DpUtil.dp2px(itemDividerPadding));
        itemParent.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);

        for (int i = 0; i < items.length; i++) {
            final TextView textView = getItemTextView(itemTextTypeface, itemBold);
            textView.setText(items[i].toString());
            textView.setTextColor(itemTextColor);
            final int finalI = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(textView, finalI);
                        onItemClickListener = null;
                    }
                    alertDialog.dismiss();
                }
            });

            itemParent.addView(textView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DpUtil.dp2px(itemHeight)));
        }

        nestedScrollView.addView(itemParent);
        parentView.addView(nestedScrollView);
        allParentView.addView(parentView);

        initDialog(alertDialog, allParentView, parentView);

        alertDialog.setContentView(allParentView);
    }

    private void initDialog(final AlertDialog alertDialog, LinearLayout allParentView, LinearLayout parentView) {
        if (hasCancleButton) {
            final TextView textView = getItemTextView(cancelButtonTextTypeface, cancelButtonBold);
            textView.setText(cancleButtonText == null ? StringUtils.Companion.getStringResource(R.string.base_dialog_cancel) : cancleButtonText);
            if (isIos) {
                parentView.setBackground(getGradientDrawable(dialogBgColor == -1 ? Color.WHITE : dialogBgColor));
                allParentView.addView(new Space(mContext), new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (screenWith - dialogWidth) / 2));
                textView.setBackground(getGradientDrawable(dialogBgColor == -1 ? Color.WHITE : dialogBgColor));
                setDialogBgColor(Color.TRANSPARENT);
            } else {
                View view = new View(mContext);
                view.setBackgroundColor(dividerColor);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dividerHeight);
                layoutParams.setMargins(DpUtil.dp2px(itemDividerPadding), 0, DpUtil.dp2px(itemDividerPadding), 0);
                allParentView.addView(view, layoutParams);
            }

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(textView, -1);
                        onItemClickListener = null;
                    }
                    alertDialog.dismiss();
                }
            });

            allParentView.addView(textView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DpUtil.dp2px(itemHeight)));
        }
    }

    private void initView(LinearLayout parentView) {
        if (!TextUtils.isEmpty(title)) {
            TextView titleView = new TextView(mContext);
            titleView.setTextSize(TypedValue.COMPLEX_UNIT_PX, DpUtil.sp2px(titleSize));
            titleView.setTextColor(titleColor);
            titleView.setMaxLines(titleMaxLines);
            titleView.setEllipsize(TextUtils.TruncateAt.END);
            titleView.setGravity(titleGravity);
            titleView.setPadding(DpUtil.dp2px(titlePaddingTop), DpUtil.dp2px(titlePaddingLeft), DpUtil.dp2px(titlePaddingRight), DpUtil.dp2px(titlePaddingBottom));
            if (titleTypeface != null) {
                titleView.setTypeface(titleTypeface, titleBold ? Typeface.BOLD : Typeface.NORMAL);
            }
            titleView.setText(title);
            titleView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onTitleClickListener != null) {
                        onTitleClickListener.onTitleClick(v);
                        onTitleClickListener = null;
                    }
                }
            });
            parentView.addView(titleView);
        }
    }

    private TextView getItemTextView(Typeface typeface, boolean isBold) {
        TextView textView = new TextView(mContext);

        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = textView.getContext().getTheme();
        if (theme.resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true)) {
            textView.setBackgroundResource(typedValue.resourceId);
        }
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, DpUtil.sp2px(itemTextSize));
        textView.setTextColor(cancelTextColor);
        textView.setMaxLines(1);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setGravity(itemTextGravity);
        textView.setPadding(DpUtil.dp2px(itemTextPaddingLeft), 0, DpUtil.dp2px(itemTextPaddingRight), 0);
        if (typeface != null) {
            textView.setTypeface(typeface, isBold ? Typeface.BOLD : Typeface.NORMAL);
        }
        return textView;
    }


    public ChoiceDialog setTtitle(String title) {
        this.title = title;
        return this;
    }

    public ChoiceDialog setTitleSize(float titleSizeSp) {
        this.titleSize = titleSizeSp;
        return this;
    }

    public ChoiceDialog setTitleColor(int titleColor) {
        this.titleColor = titleColor;
        return this;
    }

    public ChoiceDialog setCancelColor(int cancelTextColor) {
        this.cancelTextColor = cancelTextColor;
        return this;
    }

    public ChoiceDialog setTtitle(String title, float titleSizeSp) {
        this.title = title;
        this.titleSize = titleSizeSp;
        return this;
    }

    public ChoiceDialog setTtitle(String title, int titleColor) {
        this.title = title;
        this.titleColor = titleColor;
        return this;
    }

    public ChoiceDialog setTtitle(String title, float titleSizeSp, int titleColor) {
        this.title = title;
        this.titleSize = titleSizeSp;
        this.titleColor = titleColor;
        return this;
    }

    public ChoiceDialog setTtitlePadding(int titlePaddingLeftDp, int titlePaddingTopDp, int titlePaddingRightDp, int titlePaddingBottomDp) {
        this.titlePaddingLeft = titlePaddingLeftDp;
        this.titlePaddingTop = titlePaddingTopDp;
        this.titlePaddingRight = titlePaddingRightDp;
        this.titlePaddingBottom = titlePaddingBottomDp;
        return this;
    }

    public ChoiceDialog setItemTextSize(float itemTextSizeSp) {
        this.itemTextSize = itemTextSizeSp;
        return this;
    }

    public ChoiceDialog setItemTextColor(int itemTextColor) {
        this.itemTextColor = itemTextColor;
        return this;
    }

    public ChoiceDialog setItemText(float itemTextSizeSp, int itemTextColor) {
        this.itemTextSize = itemTextSizeSp;
        this.itemTextColor = itemTextColor;
        return this;
    }

    public ChoiceDialog setItemTextPaddingLeft(int itemTextPaddingLeftDp) {
        this.itemTextPaddingLeft = itemTextPaddingLeftDp;
        return this;
    }

    public ChoiceDialog setItemTextPaddingRight(int itemTextPaddingRightDp) {
        this.itemTextPaddingRight = itemTextPaddingRightDp;
        return this;
    }

    public ChoiceDialog setItemTextPadding(int itemTextPaddingLeftDp, int itemTextPaddingRightDp) {
        this.itemTextPaddingLeft = itemTextPaddingLeftDp;
        this.itemTextPaddingRight = itemTextPaddingRightDp;
        return this;
    }

    public ChoiceDialog setItems(Object[] items) {
        this.items = items;
        return this;
    }

    public ChoiceDialog setDividerColor(int dividerColor) {
        this.dividerColor = dividerColor;
        return this;
    }

    public ChoiceDialog setDividerHeight(int dividerHeightpX) {
        this.dividerHeight = dividerHeightpX;
        return this;
    }

    public ChoiceDialog hasCancleButton(boolean hasCancleButton) {
        this.hasCancleButton = hasCancleButton;
        return this;
    }

    public ChoiceDialog hasCancleButton(boolean hasCancleButton, String cancleButtonText) {
        this.hasCancleButton = hasCancleButton;
        this.cancleButtonText = cancleButtonText;
        return this;
    }

    public ChoiceDialog setTitleMaxLines(int titleMaxLines) {
        this.titleMaxLines = titleMaxLines;
        return this;
    }

    public ChoiceDialog setTitleTypeface(Typeface titleTypeface) {
        this.titleTypeface = titleTypeface;
        return this;
    }

    public ChoiceDialog setItemTextTypeface(Typeface itemTextTypeface) {
        this.itemTextTypeface = itemTextTypeface;
        return this;
    }

    public ChoiceDialog setTitleTypeface(Typeface titleTypeface, boolean isBold) {
        this.titleTypeface = titleTypeface;
        this.titleBold = isBold;
        return this;
    }

    public ChoiceDialog setItemTextTypeface(Typeface messageTypeface, boolean isBold) {
        this.itemTextTypeface = messageTypeface;
        this.itemBold = isBold;
        return this;
    }

    public ChoiceDialog setCancelButtonTypeface(Typeface messageTypeface, boolean isBold) {
        this.cancelButtonTextTypeface = messageTypeface;
        this.cancelButtonBold = isBold;
        return this;
    }

    public ChoiceDialog setTitleGravity(int titleGravity) {
        this.titleGravity = titleGravity;
        return this;
    }

    public ChoiceDialog setItemHeight(int itemHeight) {
        this.itemHeight = itemHeight;
        return this;

    }

    public ChoiceDialog setItemTextGravity(int itemTextGravity) {
        this.itemTextGravity = itemTextGravity;
        return this;
    }

    public ChoiceDialog setItemDividerPadding(int itemDividerPadding) {
        this.itemDividerPadding = itemDividerPadding;
        return this;
    }


    public ChoiceDialog setCancleButtonText(String cancleButtonText) {
        this.cancleButtonText = cancleButtonText;
        return this;
    }

    public ChoiceDialog setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    public ChoiceDialog setOnTitleClickListener(OnTitleClickListener onTitleClickListener) {
        this.onTitleClickListener = onTitleClickListener;
        return this;
    }

    public interface OnItemClickListener {
        /**
         * Desc:点击
         * <p>
         * Author: will
         * Date: 2019-12-23
         *
         * @param onClickView View
         * @param position    位置
         */
        void onItemClick(TextView onClickView, int position);
    }

    /**
     * Desc:标题点击
     * <p>
     * Date: 2019-12-23
     * Copyright: Copyright (c) 2010-2019
     * Update Time:
     * Update Comments:
     *
     * @Author: will
     */
    public interface OnTitleClickListener {
        /**
         * Desc:标题点击
         * <p>
         * Author: will
         * Date: 2019-12-23
         *
         * @param textView View
         */
        void onTitleClick(View textView);
    }

    /**
     * Desc:点击返回键是否去除dialog
     * <p>
     * Author: will
     * Date: 2019-07-11
     *
     * @param cancleable
     * @return confirm dialog
     */
    public ChoiceDialog setCancelableTouch(boolean cancleable) {
        setCancelable(cancleable);
        return this;
    }

    /**
     * Desc:点击外窗外面是否隐藏
     * <p>
     * Author: will
     * Date: 2019-07-11
     *
     * @param canceledOnTouchOutside
     * @return confirm dialog
     */
    public ChoiceDialog setCanceledOnTouchOutsides(boolean canceledOnTouchOutside) {
        setCanceledOnTouchOutside(canceledOnTouchOutside);
        return this;
    }

    public ChoiceDialog setDialogDismissListener(DialogInterface.OnDismissListener dismissListener) {
        this.dialogOnDismissListener = DetachableDialogOnDismissListener.wrap(dismissListener);
        return this;
    }
}
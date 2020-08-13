package com.will.play.base.widget.tag;

import android.content.Context;
import android.view.View;
import android.widget.Checkable;
import android.widget.FrameLayout;

/**
 * Desc:全埋点的一些常量字段
 * <p>
 * Date: 2019-11-11
 * Updater:
 * Update Time:
 * Update Comments:
 *
 */
public class TagView extends FrameLayout implements Checkable {
    private static final int[] CHECK_STATE = new int[]{android.R.attr.state_checked};
    private boolean isChecked;

    public TagView(Context context) {
        super(context);
    }

    public View getTagView() {
        return getChildAt(0);
    }

    @Override
    public int[] onCreateDrawableState(int extraSpace) {
        int[] states = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            mergeDrawableStates(states, CHECK_STATE);
        }
        return states;
    }

    /**
     * @return The current checked state of the view
     */
    @Override
    public boolean isChecked() {
        return isChecked;
    }

    /**
     * Change the checked state of the view
     *
     * @param checked The new checked state
     */
    @Override
    public void setChecked(boolean checked) {
        if (this.isChecked != checked) {
            this.isChecked = checked;
            refreshDrawableState();
        }
    }

    /**
     * Change the checked state of the view to the inverse of its current state
     */
    @Override
    public void toggle() {
        setChecked(!isChecked);
    }


}

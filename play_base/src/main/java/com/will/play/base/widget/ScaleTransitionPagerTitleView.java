package com.will.play.base.widget;

import android.content.Context;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

/**
 * Desc:带颜色渐变和缩放的指示器标题
 * <p>
 * @Author: pengyushan
 */
public class ScaleTransitionPagerTitleView extends ColorTransitionPagerTitleView {
	private float mMinScale = 0.75f;

	public ScaleTransitionPagerTitleView(Context context) {
		super(context);
		setIncludeFontPadding(false);
	}

	@Override
	public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
		// 实现颜色渐变
		super.onEnter(index, totalCount, enterPercent, leftToRight);
		setScaleX(mMinScale + (1.0f - mMinScale) * enterPercent);
		setScaleY(mMinScale + (1.0f - mMinScale) * enterPercent);
	}

	@Override
	public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
		// 实现颜色渐变
		super.onLeave(index, totalCount, leavePercent, leftToRight);
		setScaleX(1.0f + (mMinScale - 1.0f) * leavePercent);
		setScaleY(1.0f + (mMinScale - 1.0f) * leavePercent);
	}

	public float getMinScale() {
		return mMinScale;
	}

	public void setMinScale(float minScale) {
		mMinScale = minScale;
	}
}

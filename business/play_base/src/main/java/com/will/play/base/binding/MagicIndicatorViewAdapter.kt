package com.will.play.base.binding

import android.content.Context
import android.os.Build
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import androidx.viewpager.widget.ViewPager
import com.will.habit.binding.command.BindingCommand
import com.will.habit.utils.StringUtils
import com.will.play.base.R
import com.will.play.base.widget.ScaleTransitionPagerTitleView
import com.will.play.widget.utils.DpUtils
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator

/**
 * Desc:指示器绑定
 * <p>
 *
 * @Author: pengyushan
 */
@BindingMethods(BindingMethod(type = MagicIndicator::class, attribute = "setAdapterCount", method = "setAdapterCount"),
        BindingMethod(type = MagicIndicator::class, attribute = "setCustomAdapter", method = "setCustomAdapter"),
        BindingMethod(type = MagicIndicator::class, attribute = "pagerChangeCommand", method = "pagerChangeCommand"),
        BindingMethod(type = ViewPager::class, attribute = "setCurrentItem", method = "setCurrentItem")
)
class MagicIndicatorViewAdapter

@BindingAdapter(value = ["setCurrentItem"], requireAll = false)
fun ViewPager.setCurrentItem(index: Int) {
    this.currentItem = index
}


/**
 *
 * Desc:DataBinding设置MagicIndicator属性
 * <p>
 * @receiver MagicIndicator
 * @param mDataList ArrayList<String>  数据集
 * @param pagerChangeCommand BindingCommand<Int>? 选中的index回调，带切换viewpager属性
 * @param itemClickCommand BindingCommand<Int>? 点击了对应index的对调，不切换viewpager
 * @param isAdjustMode Boolean? 设置文本显示模式，自适应模式，适用于数目固定的、少量的title
 * @param itemPaddingLeft Float? 设置文本的左边距
 * @param itemPaddingRight Float? 设置文本的右边距
 * @param selectIndex Int? 设置选中对应的item和viewPager切换
 * @param viewPager ViewPager? 绑定viewPager
 * @param lineIndicatorWidth Float? 底部线的宽度
 * @param lineIndicatorHeight Float? 底部线的高度  线粗
 * @param lineIndicatorMode Int? 底部线的显示模式，包括自定义、整个item的宽度、和文本的宽度
 */
@BindingAdapter(
        value = ["setAdapterCount",
            "pagerChangeCommand",
            "itemClickCommand",
            "isAdjustMode",
            "itemPaddingLeft",
            "itemPaddingRight",
            "selectIndex",
            "viewPager",
            "textColorNormal",
            "textColorSelect",
            "lineIndicatorWidth",
            "lineIndicatorHeight",
            "lineIndicatorMode",
            "lineIndicatorScale"],
        requireAll = false
)
fun MagicIndicator.setAdapterCount(
        mDataList: List<String>?,
        pagerChangeCommand: BindingCommand<Int>?,
        itemClickCommand: BindingCommand<Int>?,
        isAdjustMode: Boolean?,
        itemPaddingLeft: Float?,
        itemPaddingRight: Float?,
        selectIndex: Int?,
        viewPager: ViewPager?,
        textColorNormal: Int?,
        textColorSelect: Int?,
        lineIndicatorWidth: Float?,
        lineIndicatorHeight: Float?,
        lineIndicatorMode: Int?,
        lineIndicatorScale: Float?
) {
    if (mDataList == null) {
        return
    }
    val commonNavigator = CommonNavigator(this.context)
    if (isAdjustMode != null) {
        commonNavigator.isAdjustMode = isAdjustMode
    }
    commonNavigator.adapter = object : CommonNavigatorAdapter() {
        override fun getCount(): Int {
            return mDataList.size
        }

        @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
        override fun getTitleView(context: Context, index: Int): IPagerTitleView {
            val simplePagerTitleView = ScaleTransitionPagerTitleView(context)
            simplePagerTitleView.normalColor = StringUtils.getColorResource(textColorNormal ?: R.color.color_F0F0F0)
            simplePagerTitleView.selectedColor = StringUtils.getColorResource(textColorSelect ?: R.color.color_FFFFFF)
            simplePagerTitleView.textSize = 18f
            simplePagerTitleView.minScale = lineIndicatorScale ?: 16f / 18f
            simplePagerTitleView.text = mDataList[index]
            simplePagerTitleView.setOnClickListener {
                pagerChangeCommand?.execute(index)
                //这边由于产品的需求，需要部分item点击之后不切换viewpager，而是显示toast，因此多了一个itemClickCommand 后续还是需要去掉的
                if (itemClickCommand != null) {
                    itemClickCommand.execute(index)
                } else {
                    viewPager?.currentItem = index
                }
            }

            simplePagerTitleView.post {
                if (simplePagerTitleView.layoutParams is ViewGroup.MarginLayoutParams) {
                    simplePagerTitleView.setPadding(0, 0, 0, 0)
                    val lp = simplePagerTitleView.layoutParams as ViewGroup.MarginLayoutParams
                    lp.marginStart = DpUtils.dp2px(itemPaddingLeft ?: 0f)
                    lp.marginEnd = DpUtils.dp2px(itemPaddingRight ?: 0f)
                    simplePagerTitleView.layoutParams = lp
                }
            }
            return simplePagerTitleView
        }

        override fun getIndicator(context: Context): IPagerIndicator {
            val linePagerIndicator = LinePagerIndicator(context)
            linePagerIndicator.mode = lineIndicatorMode ?: LinePagerIndicator.MODE_WRAP_CONTENT
            if (linePagerIndicator.mode == LinePagerIndicator.MODE_EXACTLY) {
                linePagerIndicator.lineWidth = DpUtils.dp2px(lineIndicatorWidth ?: 0f).toFloat()
            }
            linePagerIndicator.lineHeight = DpUtils.dp2px(lineIndicatorHeight ?: 2f).toFloat()
            linePagerIndicator.setColors(StringUtils.getColorResource(R.color.color_FFFFFF))
            return linePagerIndicator
        }
    }
    this.navigator = commonNavigator

    if (selectIndex != null && selectIndex >= 0) {
        this.navigator.onPageSelected(selectIndex)
    }
    viewPager?.let {
        ViewPagerHelper.bind(this, viewPager)
        this.onPageSelected(viewPager.currentItem)
    }
}
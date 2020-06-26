package com.will.play.widget.binding

import androidx.databinding.BindingAdapter
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.will.habit.binding.command.BindingCommand
import com.will.habit.binding.viewadapter.viewpager.ViewAdapter
import com.will.play.widget.banner.BannerView
import com.will.play.widget.banner.BindingBannerAdapter
import com.will.play.widget.banner.LOOP_TIME
import com.will.play.widget.banner.indicator.PointIndicator
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * Desc: Banner
 * <p>
 */
@BindingMethods(
        BindingMethod(type = BannerView::class, attribute = "itemBinding", method = "setAdapter"),
        BindingMethod(type = BannerView::class, attribute = "items", method = "setAdapter"),
        BindingMethod(type = BannerView::class, attribute = "loop", method = "setAdapter"),
        BindingMethod(type = BannerView::class, attribute = "loopTime", method = "setAdapter"),
        BindingMethod(type = BannerView::class, attribute = "onPageScrolledCommand", method = "onScrollChangeCommand"),
        BindingMethod(type = BannerView::class, attribute = "onPageSelectedCommand", method = "onScrollChangeCommand"),
        BindingMethod(type = BannerView::class, attribute = "onPageScrollStateChangedCommand", method = "onScrollChangeCommand"),
        BindingMethod(type = BannerView::class, attribute = "currentItem", method = "setCurrentItem"),
        BindingMethod(type = PointIndicator::class, attribute = "indicatorCount", method = "setIndicatorCount"),
        BindingMethod(type = PointIndicator::class, attribute = "indicatorIndex", method = "setIndicatorIndex")
)
class BannerViewAdapter

/**
 * Desc: BannerView Binding
 * <p>
 * @param loop 是否循环播放
 * @param loopTime banner切换等待时长
 */
@BindingAdapter(value = ["itemBinding", "items", "loop", "loopTime"], requireAll = false)
fun <T> BannerView.setAdapter(itemBinding: ItemBinding<in T>?,
                              items: List<T>?,
                              loop: Boolean?,
                              loopTime: Long?) {
    if (itemBinding != null) {
        this.isLoop = loop ?: true
        this.loopTime = loopTime ?: LOOP_TIME
        val oldAdapter = this.getViewPager().adapter as? BindingBannerAdapter<T>
        val adapter = oldAdapter ?: BindingBannerAdapter(this)
        adapter.itemBinding = itemBinding
        adapter.setItems(items)
        if (oldAdapter !== adapter) {
            this.getViewPager().adapter = adapter
        }
        this.initLoop()
    } else {
        this.getViewPager().adapter = null
    }
}

@BindingAdapter(value = ["onPageScrolledCommand", "onPageSelectedCommand", "onPageScrollStateChangedCommand"], requireAll = false)
fun BannerView.onScrollChangeCommand(onPageScrolledCommand: BindingCommand<ViewAdapter.ViewPagerDataWrapper?>?,
                                     onPageSelectedCommand: BindingCommand<Int?>?,
                                     onPageScrollStateChangedCommand: BindingCommand<ViewPagerScrollChange?>?) {
    setOnPageChangeListener(object : OnPageChangeListener {
        private var state = 0
        private var index = 0
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            onPageScrolledCommand?.execute(ViewAdapter.ViewPagerDataWrapper(position.toFloat(), positionOffset, positionOffsetPixels, state))
        }

        override fun onPageSelected(position: Int) {
            index = position
            onPageSelectedCommand?.execute(position)
        }

        override fun onPageScrollStateChanged(state: Int) {
            this.state = state
            onPageScrollStateChangedCommand?.execute(ViewPagerScrollChange(index, state))
        }
    })
}

class ViewPagerScrollChange(index: Int, state: Int) {

}


@BindingAdapter("indicatorCount")
fun PointIndicator.setIndicatorCount(count: Int) {
    this.setCount(count)
}

@BindingAdapter("indicatorIndex")
fun PointIndicator.setIndicatorIndex(index: Int) {
    this.setCurrent(index)
}
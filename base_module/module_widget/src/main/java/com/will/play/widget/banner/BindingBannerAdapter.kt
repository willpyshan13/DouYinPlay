package com.will.play.widget.banner

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import me.tatarka.bindingcollectionadapter2.BindingViewPagerAdapter

/**
 * Desc: banner的adapter
 * <p>
 * @author: will
 */
internal class BindingBannerAdapter<T>(private val bannerView: BannerView) :
        BindingViewPagerAdapter<T>() {

    /**
     * 真实的count
     */
    private var realCount = 0

    override fun getCount(): Int {
        realCount = super.getCount()
        return if (realCount > 1 && bannerView.isLoop) LOOP_NUM else realCount
    }

    fun getRealCount(): Int {
        return realCount
    }

    override fun getAdapterItem(position: Int): T {
        return super.getAdapterItem(toRealPosition(position))
    }

    override fun onBindBinding(binding: ViewDataBinding, variableId: Int, layoutRes: Int, position: Int, item: T) {
        super.onBindBinding(binding, variableId, layoutRes, toRealPosition(position), item)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return super.instantiateItem(container, toRealPosition(position))
    }

    override fun destroyItem(container: ViewGroup, position: Int, any: Any) {
        super.destroyItem(container, toRealPosition(position), any)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return super.getPageTitle(toRealPosition(position))
    }

    /**
     * Desc: 获取真实position
     */
    fun toRealPosition(position: Int): Int {
        if (realCount <= 0) return 0
        var realPosition = if (bannerView.isLoop) {
            (position - 1 + realCount) % realCount
        } else {
            (position + realCount) % realCount
        }
        if (realPosition < 0) realPosition += realCount
        return realPosition
    }
}
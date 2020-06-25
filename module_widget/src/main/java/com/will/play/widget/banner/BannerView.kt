package com.will.play.widget.banner

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.will.habit.extection.launch
import com.will.habit.licycle.BaseLifecycleObserver
import com.will.play.widget.banner.indicator.BannerIndicator
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay

/**
 * Desc: Banner
 * <p>
 *
 * @author: will
 */
class BannerView : FrameLayout, OnPageChangeListener {

    private val activity: FragmentActivity
    private var loopJob: Job? = null
    private lateinit var viewPager: ViewPager
    private var adapter: BindingBannerAdapter<*>? = null
    private var indicator: BannerIndicator? = null

    /**
     * 是否循环播放
     */
    internal var isLoop = true

    /**
     * 切换等待时长
     */
    internal var loopTime: Long = LOOP_TIME

    /**
     * 监听
     */
    private var listener: OnPageChangeListener? = null

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    init {
        val context = context
        if (context !is FragmentActivity) {
            throw IllegalArgumentException("context 必须为FragmentActivity")
        }
        activity = context
        registerLifecycle()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        viewPager = ViewPager(context)
        viewPager.addOnPageChangeListener(this)
        addView(viewPager, 0, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
        if (childCount > 2) {
            throw IllegalArgumentException("BannerView 只能有2个childView")
        } else if (childCount == 2) {
            val lastChild = getChildAt(childCount - 1)
            if (lastChild !is BannerIndicator) {
                throw IllegalArgumentException("BannerView 指示器必须实现BannerIndicator接口")
            }
            indicator = lastChild
        }
    }

    private fun registerLifecycle() {
        object : BaseLifecycleObserver(activity) {

            override fun onStop() {
                super.onStop()
                stopPlay()
            }

            override fun onStart() {
                super.onStart()
                startPlay()
            }

            override fun onDestroy() {
                super.onDestroy()
                stopPlay()
            }
        }
    }

    internal fun getViewPager() = viewPager

    /**
     * Desc: 重置循环
     * <p>
     * Author: will
     * Date: 2020/5/13
     */
    fun initLoop() {
        stopPlay()
        val adapter = viewPager.adapter ?: return
        if (adapter !is BindingBannerAdapter<*>) {
            throw IllegalArgumentException("BannerView adapter必须继承BindingBannerAdapter")
        }
        this.adapter = adapter
        val count = adapter.count
        indicator?.setCount(adapter.getRealCount())
        if (count == 0) {
            return
        }
        if (isLoop && count > 1) {
            viewPager.currentItem = LOOP_NUM / 2 - LOOP_NUM / 2 % count + 1
            startPlay()
        } else {
            viewPager.currentItem = 0
        }
    }

    /**
     * Desc: 开始播放
     * <p>
     * Author: will
     * Date: 2020/5/13
     */
    private fun startPlay() {
        if (isLoop) {
            loopJob?.cancel()
            loopJob = activity.launch({
                while (true) {
                    delay(loopTime)
                    viewPager.currentItem =
                        if (viewPager.currentItem == adapter?.count ?: 0 - 1) {
                            0
                        } else {
                            viewPager.currentItem.inc()
                        }
                }
            })
        }
    }

    /**
     * Desc: 停止播放
     * <p>
     * Author: will
     * Date: 2020/5/13
     */
    fun stopPlay() {
        loopJob?.cancel()
        loopJob = null
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (loopJob?.isActive != true) {
            startPlay()
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stopPlay()
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_UP,
            MotionEvent.ACTION_CANCEL,
            MotionEvent.ACTION_OUTSIDE -> startPlay()
            MotionEvent.ACTION_DOWN -> {
                stopPlay()
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    /**
     * Desc: 监听
     * <p>
     * Author: will
     * Date: 2020/6/8
     */
    fun setOnPageChangeListener(onPageChangeListener: OnPageChangeListener) {
        this.listener = onPageChangeListener
    }

    override fun onPageScrollStateChanged(state: Int) {
        listener?.onPageScrollStateChanged(state)
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        if (adapter != null) {
            listener?.onPageScrolled(
                adapter!!.toRealPosition(position),
                positionOffset,
                positionOffsetPixels
            )
        }
    }

    override fun onPageSelected(position: Int) {
        if (adapter != null) {
            val realPosition = adapter!!.toRealPosition(position)
            listener?.onPageSelected(realPosition)
            indicator?.setCurrent(realPosition)
        }
    }
}




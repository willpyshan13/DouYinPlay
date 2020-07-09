package com.will.play.widget.banner.indicator

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.will.play.widget.R
import com.will.play.widget.utils.DpUtils

/**
 * Desc: 一个简易版的圆点指示器
 *
 *
 * @Author: will
 */
class PointIndicator(context: Context, attrs: AttributeSet?) : View(context, attrs), BannerIndicator {

    private val paintIndicator: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    /**
     * 圆点数量
     */
    private var count: Int

    /**
     * 当前选中下标
     */
    private var position = 0

    /**
     * 为选中颜色
     */
    private val unSelectColor: Int

    /**
     * 选中颜色
     */
    private val selectColor: Int

    /**
     * 圆点半径
     */
    private val radius: Float

    /**
     * 选中圆点宽度，默认=圆点半径
     */
    private val selectWidth: Float

    /**
     * 圆点间距
     */
    private val offset: Float

    /**
     * 选中的原点宽度相对于默认原点半径的偏移量
     */
    private val selectOffset: Float

    /**
     * 选中的点是否是圆形
     */
    private val selectCircle: Boolean

    private val selectRect = RectF()

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.PointIndicator)
        radius = typedArray.getDimensionPixelOffset(R.styleable.PointIndicator_point_radius, DpUtils.dp2px(2f)).toFloat()
        offset = typedArray.getDimensionPixelOffset(R.styleable.PointIndicator_point_offset, DpUtils.dp2px(4f)).toFloat()
        selectWidth = typedArray.getDimensionPixelOffset(R.styleable.PointIndicator_point_select_point_width, (radius * 2).toInt()).toFloat()
        selectColor = typedArray.getColor(R.styleable.PointIndicator_point_select_color, Color.parseColor("#F38700"))
        unSelectColor = typedArray.getColor(R.styleable.PointIndicator_point_unselect_color, Color.parseColor("#D6D1D1"))
        count = typedArray.getInt(R.styleable.PointIndicator_point_count, 0)
        selectCircle = typedArray.getBoolean(R.styleable.PointIndicator_indicator_select_circle, false)
        selectOffset = (selectWidth - radius * 2) / 2
        typedArray.recycle()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (i in 0 until count) {
            if (position == i) {
                paintIndicator.color = selectColor
                val pointLeft = offset * i + radius * 2 * i
                selectRect[pointLeft, 0f, pointLeft + selectWidth] = radius * 2
                if (selectCircle) {
                    val cx = pointLeft + selectWidth
                    canvas.drawCircle(cx, height / 2.toFloat(), selectWidth, paintIndicator)
                } else {
                    canvas.drawRoundRect(selectRect, radius, radius, paintIndicator)
                }
            } else {
                paintIndicator.color = unSelectColor
                val pointLeft = offset * i + radius * 2 * i + selectOffset
                val cx = pointLeft + radius
                // 如果选中样式为圆形，则未选中点的中心点Y轴位置为中间高度
                canvas.drawCircle(cx, height / 2.toFloat(), radius, paintIndicator)
            }
        }
    }

    override fun setCount(count: Int) {
        if (this.count == count) {
            return
        }
        this.count = count
        requestLayout()
    }

    override fun setCurrent(index: Int) {
        if (this.position == index) {
            return
        }
        this.position = index
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val totalPointWidth = radius * 2 * count
        val totalOffsetWidth = offset * (count - 1)
        // 选中点样式为圆形或者其他形状时，分别设置不同的宽度和高度
        setMeasuredDimension((totalPointWidth + totalOffsetWidth + (if (selectCircle) selectWidth else selectOffset) * 2).toInt(),
                (if (selectCircle) selectWidth else radius).toInt() * 2)
    }
}
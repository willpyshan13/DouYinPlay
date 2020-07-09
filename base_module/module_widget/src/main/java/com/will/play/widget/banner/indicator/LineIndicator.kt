package com.will.play.widget.banner.indicator

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.will.habit.utils.ConvertUtils.dp2px
import com.will.play.widget.R
import com.will.play.widget.utils.DpUtils

/**
 * Desc: 线类型的指示器
 * <p>
 */
class LineIndicator(context: Context, attrs: AttributeSet?) : View(context, attrs), BannerIndicator {

    private val paintIndicator: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    /**
     * 指示器数量
     */
    private var count: Int

    /**
     * 线高
     */
    private val lineHeight: Int

    /**
     * 线颜色
     */
    private val color: Int

    /**
     * 线选中颜色
     */
    private val selectColor: Int

    /**
     * 线选中宽度
     */
    private val selectWidth: Int

    /**
     * 线圆角
     */
    private val corner: Float

    /**
     * 当前选中下标
     */
    private var position = 0

    private val selectRect = RectF()

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.LineIndicator)
        count = typedArray.getInt(R.styleable.LineIndicator_line_count, dp2px(4F))
        lineHeight = typedArray.getDimensionPixelOffset(R.styleable.LineIndicator_line_height, dp2px(4F))
        color = typedArray.getColor(R.styleable.LineIndicator_line_color, Color.parseColor("#EEEEEE"))
        selectColor = typedArray.getColor(R.styleable.LineIndicator_line_select_color, Color.parseColor("#F38700"))
        selectWidth = typedArray.getDimensionPixelOffset(R.styleable.LineIndicator_line_select_width, dp2px(16F))
        corner = typedArray.getDimensionPixelOffset(R.styleable.LineIndicator_line_corner, dp2px(4F)).toFloat()
        typedArray.recycle()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        selectRect.set(0F, 0F, width.toFloat(), height.toFloat())
        paintIndicator.color = color
        canvas.drawRoundRect(selectRect, corner, corner, paintIndicator)
        paintIndicator.color = selectColor
        selectRect.set(selectWidth * position.toFloat(), 0F, selectWidth * (position + 1).toFloat(), height.toFloat())
        canvas.drawRoundRect(selectRect, corner, corner, paintIndicator)
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
        setMeasuredDimension(selectWidth * count, lineHeight)
    }
}
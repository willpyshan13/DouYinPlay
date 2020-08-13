package com.will.play.base.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import com.will.play.base.R

/**
 * Desc: 圆角ImageView
 * <p>
 * Date: 2020/7/6
 * Updater:
 * Update Time:
 * Update Comments:
 */
class RoundCornerImageView(context: Context, attrs: AttributeSet?) : androidx.appcompat.widget.AppCompatImageView(context, attrs) {

    constructor(context: Context) : this(context, null)

    /**
     * 边框画笔
     */
    private val borderPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            this.style = Paint.Style.STROKE
        }
    }

    /**
     * 边框颜色
     */
    private var borderColor: Int = Color.BLACK

    /**
     * 边框宽度
     */
    private var borderWidth: Float = 0f

    /**
     * 宽
     */
    private var w = 0

    /**
     * 高
     */
    private var h = 0
    private val path by lazy { Path() }
    private var corners: FloatArray? = null

    init {
        if (null != attrs) {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundCornerImageView)
            val corner = typedArray.getDimension(R.styleable.RoundCornerImageView_round_corner_radius, 0f)
            corners = if (corner > 0) {
                floatArrayOf(corner, corner, corner, corner, corner, corner, corner, corner)
            } else {
                val leftTopCorner = typedArray.getDimension(R.styleable.RoundCornerImageView_round_corner_radius_left_top, 0f)
                val leftBottomCorner = typedArray.getDimension(R.styleable.RoundCornerImageView_round_corner_radius_left_bottom, 0f)
                val rightTopCorner = typedArray.getDimension(R.styleable.RoundCornerImageView_round_corner_radius_right_top, 0f)
                val rightBottomCorner = typedArray.getDimension(R.styleable.RoundCornerImageView_round_corner_radius_right_bottom, 0f)
                floatArrayOf(leftTopCorner, leftTopCorner, rightTopCorner, rightTopCorner, leftBottomCorner, leftBottomCorner, rightBottomCorner, rightBottomCorner)
            }
            borderColor = typedArray.getColor(R.styleable.RoundCornerImageView_round_corner_border_color, Color.BLACK)
            borderWidth = typedArray.getDimension(R.styleable.RoundCornerImageView_round_corner_border_width, 0f)
            typedArray.recycle()
        }
    }

    /**
     * Desc: 裁剪圆角
     * <p>
     */
    private fun drawCorner(canvas: Canvas) {
        if (corners != null && width > 0 && height > 0) {
            try {
                if (w != width || h != height) {
                    w = width
                    h = height
                    path.reset()
                    val cornerRectF = RectF(0F, 0F, width.toFloat(), height.toFloat())
                    path.addRoundRect(cornerRectF, corners!!, Path.Direction.CW)
                }
                canvas.clipPath(path)
            } catch (e: UnsupportedOperationException) {
                e.printStackTrace()
                corners = null
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        drawCorner(canvas)
        super.onDraw(canvas)
        drawBorder(canvas)
    }

    /**
     * Desc:绘制边框
     * <p>
     * Author: zhuanghongzhan
     * Date: 2020-07-16
     */
    private fun drawBorder(canvas: Canvas) {
        if (borderWidth == null || borderWidth == 0f) {
            return
        }
        borderPaint.strokeWidth = borderWidth
        borderPaint.color = borderColor
        canvas.save()
        canvas.drawPath(path, borderPaint)
        canvas.restore()
    }

}
@file:Suppress("PackageName")

package com.example.KSHLatio.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat
import com.example.ksh_latio.R

class HarvestChartView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    private var data: List<Pair<String, Float>> = emptyList()
    private val d = resources.displayMetrics.density

    private val barPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val gridPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 1f * d
        color = ContextCompat.getColor(context, R.color.stroke)
        pathEffect = DashPathEffect(floatArrayOf(4f * d, 4f * d), 0f)
    }

    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 2.5f * d
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
        color = ContextCompat.getColor(context, R.color.primary_dark)
    }

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textAlign = Paint.Align.CENTER
        textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10f, resources.displayMetrics)
        color = ContextCompat.getColor(context, R.color.text_gray)
    }

    private val activeTextPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textAlign = Paint.Align.CENTER
        isFakeBoldText = true
        textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10f, resources.displayMetrics)
        color = ContextCompat.getColor(context, R.color.text_dark)
    }

    fun setData(newData: List<Pair<String, Float>>) {
        data = newData
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (data.isEmpty()) return

        val labelH = 22f * d
        val chartH = height - labelH
        val maxValue = data.maxOf { it.second }
        val slot = width.toFloat() / data.size
        val barW = slot * 0.42f

        // garis bantu putus-putus
        for (i in 1..3) {
            val y = chartH * i / 4f
            canvas.drawLine(0f, y, width.toFloat(), y, gridPaint)
        }

        val xs = FloatArray(data.size)
        val ys = FloatArray(data.size)

        data.forEachIndexed { i, (label, value) ->
            val cx = slot * i + slot / 2f
            val h = chartH * 0.88f * (value / maxValue)
            val top = chartH - h
            xs[i] = cx
            ys[i] = top

            val isLast = i == data.lastIndex
            barPaint.color = ContextCompat.getColor(
                context, if (isLast) R.color.primary_light else R.color.mint
            )
            canvas.drawRoundRect(cx - barW / 2f, top, cx + barW / 2f, chartH, 8f * d, 8f * d, barPaint)
            canvas.drawText(label, cx, height - 5f * d, if (isLast) activeTextPaint else textPaint)
        }

        // garis tren halus (kurva bezier)
        val path = Path()
        path.moveTo(xs[0], ys[0])
        for (i in 1 until data.size) {
            val mx = (xs[i - 1] + xs[i]) / 2f
            path.cubicTo(mx, ys[i - 1], mx, ys[i], xs[i], ys[i])
        }
        canvas.drawPath(path, linePaint)
    }
}
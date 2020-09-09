package com.example.github.newsapplication.widget

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

/**
 *   Created by zhangziyi on 9/8/20 22:08
 */
open class CenterDrawableTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    override fun onDraw(canvas: Canvas?) {
        CenterDrawableHelper.preDraw(this, canvas)
        super.onDraw(canvas)
    }
}
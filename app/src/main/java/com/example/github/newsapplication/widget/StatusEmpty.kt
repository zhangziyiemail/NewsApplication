package com.example.github.newsapplication.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.example.github.newsapplication.R

/**
 *   Created by zhangziyi on 9/8/20 22:04
 */
class StatusEmpty @JvmOverloads constructor(
    context: Context,attr:AttributeSet?= null,defStyleAttr:Int = 0
): CenterDrawableTextView(context,attr,defStyleAttr){
    init {
        val errDrawable = ContextCompat.getDrawable(context, R.drawable.tag_empty)
        errDrawable?.setBounds(0, 0, errDrawable.minimumWidth / 2, errDrawable.minimumHeight / 2)
        compoundDrawablePadding = 12
        setCompoundDrawables(null, errDrawable, null, null)
        text = resources.getString(R.string.data_empty)
        setTextColor(Color.parseColor("#FFCCCCCC"))
    }
}
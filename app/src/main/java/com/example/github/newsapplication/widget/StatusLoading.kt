package com.example.github.newsapplication.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.example.github.newsapplication.R

/**
 *   Created by zhangziyi on 9/8/20 22:15
 */
class StatusLoading  @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CenterDrawableTextView(context, attrs, defStyleAttr) {

    init {
        val errDrawable = ContextCompat.getDrawable(context, R.drawable.tag_loading)
        errDrawable?.setBounds(0, 0, errDrawable.minimumWidth / 2, errDrawable.minimumHeight / 2)
        compoundDrawablePadding = 12
        setCompoundDrawables(null, errDrawable, null, null)
        text = resources.getString(R.string.loading_data)
        setTextColor(Color.parseColor("#FFCCCCCC"))
    }
}
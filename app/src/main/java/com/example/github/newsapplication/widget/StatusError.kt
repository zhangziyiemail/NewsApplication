package com.example.github.newsapplication.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.example.github.newsapplication.R
import com.example.github.newsapplication.base.ErrorReload

/**
 *   Created by zhangziyi on 9/8/20 22:17
 */
class StatusError @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CenterDrawableTextView(context, attrs, defStyleAttr) {

    var errorReload: ErrorReload? = null

    init {
        val errDrawable = ContextCompat.getDrawable(context, R.drawable.tag_load_error)
        errDrawable?.setBounds(0, 0, errDrawable.minimumWidth / 2, errDrawable.minimumHeight / 2)
        compoundDrawablePadding = 12
        setCompoundDrawables(null, errDrawable, null, null)
        text = resources.getString(R.string.reload_data)
        setTextColor(Color.parseColor("#FFCCCCCC"))
        setOnClickListener { errorReload?.reload() }
    }
}
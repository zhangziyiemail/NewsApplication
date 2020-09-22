package com.example.github.newsapplication.base

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Html
import android.text.method.MovementMethod
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.github.newsapplication.R
import com.example.github.newsapplication.widget.StatusError
import com.youth.banner.loader.ImageLoader

/**
 *   Created by zhangziyi on 9/20/20 22:26
 */

@BindingAdapter("bind:img")
fun loadImage(view: ImageView, url: String) {
    Glide.with(view.context)
        .load(url)
        .apply(RequestOptions.centerCropTransform())
        .into(view)
}

@BindingAdapter(value = ["bind:imgUrl", "bind:placeHolder", "bind:error"], requireAll = false)
fun loadImageWithPlace(view: ImageView, url: String, placeholder: Drawable, errorHolder: Drawable) {
    Glide.with(view.context)
        .load(url)
        .apply(
            RequestOptions
                .centerCropTransform()
                .placeholder(placeholder)
                .error(errorHolder)
        ).into(view)
}

@BindingAdapter("bind:circleImg")
fun bindCircleImage(imageView: ImageView, imgRes: Drawable) {
    Glide.with(imageView.context)
        .load(imgRes)
        .apply(RequestOptions.bitmapTransform(RoundedCorners(360)))
        .into(imageView)
}

class GlideLoader : ImageLoader() {
    override fun displayImage(context: Context, path: Any, imageView: ImageView) {
        Glide.with(context).load(path)
            .apply(RequestOptions.centerCropTransform().placeholder(R.drawable.image_place_holder))
            .into(imageView)
    }
}



@BindingAdapter("bind:listItemClick")
fun bindRecyclerItemClick(recyclerView: RecyclerView, listener: OnItemClickListener) {
    val adapter = recyclerView.adapter
    if (adapter == null || adapter !is BaseRecyclerAdapter<*>) return
    adapter.setOnItemListener(listener)

}

@BindingAdapter("bind:listItemLongClick")
fun bindRecyclerItemLOngClick(recyclerView: RecyclerView, listener: OnItemLongClickListener) {
    val adapter = recyclerView.adapter
    if (adapter == null || adapter !is BaseRecyclerAdapter<*>) return
    adapter.setOnItemLongListener(listener)
}


@BindingAdapter(
    value = ["bind:refreshColor", "bind:refreshState", "bind:refreshListener"],
    requireAll = false
)
fun bindRefreshColor(
    refreshLayout: SwipeRefreshLayout,
    color: Int,
    refreshState: Boolean,
    listener: SwipeRefreshLayout.OnRefreshListener
) {
    refreshLayout.setColorSchemeResources(color)
    refreshLayout.isRefreshing = refreshState
    refreshLayout.setOnRefreshListener(listener)
}

@BindingAdapter("bind:refreshEnable")
fun bindRefreshEnable(refreshLayout: SwipeRefreshLayout, enable: Boolean) {
    refreshLayout.isEnabled = enable
}

@BindingAdapter("bind:editAction")
fun bindEditAction(editText: EditText, editorActionListener: TextView.OnEditorActionListener) {
    editText.setOnEditorActionListener(editorActionListener)
}

@BindingAdapter("bind:movementMethod")
fun bindMovementMethod(textView: TextView, method: MovementMethod) {
    textView.movementMethod = method
}

@BindingAdapter("bind:viewclick")
fun bindviewClick(view: View, listener: View.OnClickListener) {
    view.setOnClickListener(listener)
}

@BindingAdapter("bind:reload")
fun bindReloadHandler(statusError: StatusError, handler: ErrorReload?) {
    statusError.errorReload = handler
}

@Suppress("DEPRECATION")
@BindingAdapter("bind:renderHtml")
fun bindRenderHtml(textView: TextView, description: String) {
    textView.text = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M)
        Html.fromHtml(description, HtmlCompat.FROM_HTML_MODE_COMPACT)
    else Html.fromHtml(description)
}








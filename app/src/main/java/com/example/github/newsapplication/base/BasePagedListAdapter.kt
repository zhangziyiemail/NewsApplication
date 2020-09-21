package com.example.github.newsapplication.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil

/**
 *   Created by zhangziyi on 9/15/20 16:08
 */
abstract class BasePagedListAdapter<T,VB : ViewDataBinding>(val callback : DiffUtil.ItemCallback<T>)
    : PagedListAdapter<T,BaseViewHolder<VB>>(callback) {

    private var itemListener : OnItemClickListener? = null
    private var itemLongerListener : OnItemLongClickListener?  = null

    fun setOnItemListener(listener: OnItemClickListener?) {
        this.itemListener = listener
    }

    fun setOnItemLongListener(listener: OnItemLongClickListener?) {
        this.itemLongerListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VB> {
        return BaseViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),getLayoutId(viewType),parent,false)
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<VB>, position: Int) {
        val data  = getItemData(position) ?: return
        setVariable(data,position,holder)

        holder.binding.executePendingBindings()
        holder.binding.root.setOnClickListener{
            view ->  itemListener?.onItemClick(position,view)
        }
        holder.binding.root.setOnLongClickListener {
            view -> itemLongerListener?.onItemLongClick(position,view)
            false
        }
    }

    abstract fun getLayoutId(layout : Int): Int
    open fun getItemData(position: Int): T? = getItem(position)
    abstract fun setVariable(data :T, position: Int,holder: BaseViewHolder<VB>)
}

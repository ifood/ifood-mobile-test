package com.ifood.challenge.base.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

open class BaseRecyclerViewAdapter<T>(
    private var items: MutableList<T> = mutableListOf(),
    @LayoutRes val layoutResId: Int,
    private val bindView: BaseRecyclerViewAdapter<T>.(view: View, item: T) -> Unit
) : RecyclerView.Adapter<BaseViewHolder<T>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        val viewItem = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return BaseViewHolder(this, viewItem, bindView)
    }

    override fun getItemViewType(position: Int): Int = layoutResId

    override fun getItemCount() = this.items.size

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bindView(getItem(position))
    }

    fun setItems(items: List<T>) {
        this.items = items.toMutableList()
        notifyDataSetChanged()
    }

    val isEmpty: Boolean get() = items.isEmpty()

    fun notifyChange(item: T) {
        val index = getPosition(item)
        if (index != -1) notifyItemChanged(index)
    }

    fun clearItems() {
        this.items.clear()
        notifyDataSetChanged()
    }

    private fun getItem(position: Int) = items[position]

    private fun getPosition(item: T): Int {
        items.forEachIndexed forEach@{ index, t ->
            if (t == item) {
                return index
            }
        }
        return -1
    }
}

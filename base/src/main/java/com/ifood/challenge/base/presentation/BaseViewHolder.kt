package com.ifood.challenge.base.presentation

import android.view.View
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder<T>(
    private val baseRecyclerViewAdapter: BaseRecyclerViewAdapter<T>,
    val view: View,
    private val executeBinding: BaseRecyclerViewAdapter<T>.(view: View, item: T) -> Unit
) :
    RecyclerView.ViewHolder(view) {

    fun bindView(item: T) {
        this.baseRecyclerViewAdapter.executeBinding(view, item)
    }
}

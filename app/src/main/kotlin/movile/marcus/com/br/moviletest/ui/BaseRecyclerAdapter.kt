package movile.marcus.com.br.moviletest.ui

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class BaseRecyclerAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var listener: OnItemClickListener? = null
    var recyclerList: ArrayList<T> = ArrayList()

    fun getList(): List<T>? {
        return recyclerList
    }

    @Synchronized
    fun clear() {
        val size = recyclerList.size
        if (size > 0) {
            recyclerList.clear()
            notifyItemRangeRemoved(0, size)
        }
    }

    fun addToList(list: ArrayList<T>) {
        recyclerList = list
        notifyDataSetChanged()
    }

    fun getItem(position: Int): T {
        return getList()!![position]
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }
}
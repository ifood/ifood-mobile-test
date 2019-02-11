package com.drss.ifoodmobiletest.view.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class TweetsItemDecoration(val verticalHeightSepation: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {

        outRect.bottom = verticalHeightSepation
    }
}
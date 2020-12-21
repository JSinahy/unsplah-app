package com.laraguzman.tribalproofactivity.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacesItemDecoration(space: Int) : RecyclerView.ItemDecoration() {
    val mSpace: Int = 0

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.left = mSpace
        outRect.right = mSpace
        outRect.bottom = mSpace
        if(parent.getChildAdapterPosition(view) == 0)
            outRect.top = mSpace
        super.getItemOffsets(outRect, view, parent, state)
    }
}
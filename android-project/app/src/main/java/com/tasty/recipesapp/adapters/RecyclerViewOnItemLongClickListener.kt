package com.tasty.recipesapp.adapters

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewOnItemLongClickListener(
    context: Context,
    private val onItemLongClick: (position: Int) -> Unit
) : RecyclerView.OnItemTouchListener {

    private val gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
        override fun onLongPress(e: MotionEvent) {
            // Find the item under the press
            val childView = recyclerView?.findChildViewUnder(e.x, e.y)
            if (childView != null) {
                val position = recyclerView?.getChildAdapterPosition(childView) ?: -1
                if (position != -1) {
                    onItemLongClick(position)
                }
            }
        }
    })

    private var recyclerView: RecyclerView? = null

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        recyclerView = rv
        gestureDetector.onTouchEvent(e)
        return false
    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
        gestureDetector.onTouchEvent(e)
    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
}

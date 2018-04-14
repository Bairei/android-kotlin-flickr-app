package com.example.bairei.flickapp

import android.content.Context
import android.support.v4.view.GestureDetectorCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

class RecyclerItemClickListener(context: Context, recyclerView: RecyclerView, private val listener: OnRecyclerClickListener)
    : RecyclerView.SimpleOnItemTouchListener(){
    private val TAG = "RecItemClickListener"

    interface OnRecyclerClickListener {
        fun onItemClick(view: View, position: Int)
        fun onItemLongClick(view: View, position: Int)
    }

    // gesture detector
    private val gestureDetector = GestureDetectorCompat(context, object: GestureDetector.SimpleOnGestureListener() {

        override fun onSingleTapUp(e: MotionEvent): Boolean {
            Log.d(TAG, ".onSingleTapUp: starts")
            val childView = recyclerView.findChildViewUnder(e.x, e.y)
            Log.d(TAG, ".onSingleTapUp calling listener.onItemClick")
            listener.onItemClick(childView, recyclerView.getChildAdapterPosition(childView))
            return true
        }

        override fun onLongPress(e: MotionEvent) {
            Log.d(TAG, ".onLongPress: starts")
            val childView = recyclerView.findChildViewUnder(e.x, e.y)
            Log.d(TAG, ".onLongPress calling listener.onItemLongClick")
            listener.onItemLongClick(childView, recyclerView.getChildAdapterPosition(childView))
        }
    })

    override fun onInterceptTouchEvent(rv: RecyclerView?, e: MotionEvent?): Boolean {
        Log.d(TAG, ".onInterceptTouchEvent called $e")
        val result = gestureDetector.onTouchEvent(e)
        Log.d(TAG, ".onInterceptTouchEvent() returning $result")
//        return super.onInterceptTouchEvent(rv, e )
        return result
    }
}
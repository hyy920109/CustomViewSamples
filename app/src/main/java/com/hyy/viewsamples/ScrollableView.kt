package com.hyy.viewsamples

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView

/**
 * Create by heyangyang on 2021/5/12.
 */

class ScrollableView constructor(context: Context, attributeSet: AttributeSet?) :
    View(context, attributeSet) {

    private var lastX = 0f
    private var lastY = 0f

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                lastX = event.x
                lastY = event.y
            }
            MotionEvent.ACTION_MOVE -> {
                val dx = (event.x - lastX).toInt()
                val dy = (event.y - lastY).toInt()

//                translationX += dx
//                translationY += dy
//                x += dx
//                y += dy
//                layout(left + dx, top + dy, right + dx, bottom + dy)
//                offsetLeftAndRight(dx)
//                offsetTopAndBottom(dy)
                Log.d(TAG, "onTouchEvent: dx-->$dx")
                Log.d(TAG, "onTouchEvent: dy-->$dy")
                (parent as View).scrollBy(-dx, -dy)

            }
            MotionEvent.ACTION_UP -> {
                //todo add scroller smooth scroll
            }

        }
        return true
    }

    companion object {
        const val TAG = "ScrollableView"
    }
}
package com.hyy.viewsamples

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.*
import android.widget.Scroller
import kotlin.math.abs

/**
 * Create by heyangyang on 2021/5/12.
 * 需要注意更改view的位置后会导致view的坐标系发生变化了，所以我们的计算的规则是和action_down初始值进行比较
 * 大家需要自己去实践理解下计算规则
 */

class ScrollableView constructor(context: Context, attributeSet: AttributeSet?) :
    View(context, attributeSet) {

    private var mActivePointerId: Int = 0
    private var lastX = 0f
    private var lastY = 0f

    private val scroller = Scroller(context)

    private var touchSlop: Int = 0
    private var minVelocity: Int = 0
    private var maxVelocity: Float = 0f
    init {
        initViewConfiguration()
    }

    private fun initViewConfiguration() {
        val viewConfiguration = ViewConfiguration.get(context)
        //最小滑动距离
        touchSlop = viewConfiguration.scaledTouchSlop
        //最小fling 速度
        minVelocity = viewConfiguration.scaledMinimumFlingVelocity
        //最大fling速度
        maxVelocity = viewConfiguration.scaledMaximumFlingVelocity.toFloat()
//        ScrollView
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                lastX = event.x
                lastY = event.y
                mActivePointerId = event.getPointerId(0)
            }
            MotionEvent.ACTION_MOVE -> {
                val dx = (event.x - lastX).toInt()
                val dy = (event.y - lastY).toInt()

//                translationX += dx
//                translationY += dy
//                x += dx
//                y += dy
//                layout(left + dx, top + dy, right + dx, bottom + dy)
                offsetLeftAndRight(dx)
                offsetTopAndBottom(dy)
                Log.d(TAG, "onTouchEvent: dx-->$dx")
                Log.d(TAG, "onTouchEvent: dy-->$dy")
//                (parent as View).scrollBy(0, -dy)

            }
            MotionEvent.ACTION_UP -> {
                startSmoothScroll()
            }

        }
        return true
    }

    private fun startSmoothScroll() {
        val centerX = (left + right) / 2
        val parentView = parent as View
        val parentCenterX = (parentView.left + parentView.right)/2
        if (centerX < parentCenterX) {
            scroller.startScroll(left, top, -left, 0, 450)
        }else {
            scroller.startScroll(left, top, parentView.right-left-width, 0, 450)
        }
        postInvalidateOnAnimation()
    }

    fun smoothScroll(startX: Int, startY: Int, deltaX: Int, deltaY: Int, duration: Int = 2000) {
        scroller.startScroll(startX, startY, deltaX, deltaY, duration)
        postInvalidateOnAnimation()
    }

    override fun computeScroll() {
        super.computeScroll()
        if (scroller.computeScrollOffset()) {
            val currX = scroller.currX
            left = currX
            right = left + measuredWidth

            val currY = scroller.currY
            top = currY
            bottom = top + measuredHeight
            postInvalidateOnAnimation()
        }
    }

    companion object {
        const val TAG = "ScrollableView"
    }
}
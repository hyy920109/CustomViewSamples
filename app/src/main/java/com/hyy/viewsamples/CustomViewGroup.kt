package com.hyy.viewsamples

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout

/**
 * Create by heyangyang on 2021/5/13.
 */

class CustomViewGroup constructor(context: Context, attributeSet: AttributeSet?):
    ConstraintLayout(context, attributeSet){

        init {
            setWillNotDraw(false)
        }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.run {
            translate(200f, 0f)
        }
    }
}
package com.example.snowflakes

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlin.random.Random


data class Snowflake(var x: Float, var y: Float, var velocity: Float, val radius: Float, val color: Int)
lateinit var snow: Array<Snowflake>
val paint = Paint()
var h = 1000; var w = 1000

class Snowflakes(ctx: Context) : View(ctx) {
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.parseColor("#ccf2ff"))
        for (s in snow) {
            paint.color = s.color
            canvas.drawCircle(s.x, s.y, s.radius, paint)
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        h = bottom - top
        w = right - left

        val random = Random(0)
        snow = Array(10) {
            Snowflake(
                random.nextFloat() * w,
                random.nextFloat() * h,
                15 + 10 * random.nextFloat(),
                30 + 20 * random.nextFloat(),
                Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256))
            )
        }
    }

    fun MoveSnowflakes() {
        for (s in snow) {
            s.y += s.velocity
            if (s.y > h) {
                s.y -= h
                s.velocity *= 0.95f
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        MoveSnowflakes()
        invalidate()
        return true
    }
}

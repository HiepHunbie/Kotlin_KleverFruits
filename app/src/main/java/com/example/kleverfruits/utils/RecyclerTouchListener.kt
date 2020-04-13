package com.example.kleverfruits.utils

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView

//  THIS SOURCE CODE IS THE INTELLECTUAL PROPERTY OF TELESENSE, INC. This source
//  code cannot be copied, modified, printed, reproduced or used in any way,
//  shape or form without prior permission from Telesense, Inc. ANY ATTEMPTS TO
//  COPY, MODIFY, PRINT, REPRODUCE OR USE THIS SOURCE CODE WITHOUT PERMISSION
//  FROM TELESENSE, INC ARE STRICTLY PROHIBITED.
//
//  Anyone creating, updating, or viewing this source code in any way, shape
//  or form is bound by this copyright message, including Telesense, Inc
//  employees, contractors, partners, or any other associated or non-associated
//  person, entity or a system.
//
//  Copyright 2019 Telesense, Inc., All rights reserved.

interface ClickListener {
    fun onClick(view: View, position: Int)

    fun onLongClick(view: View?, position: Int)
}

internal class RecyclerTouchListener(context: Context, recyclerView: RecyclerView, private val clickListener: ClickListener?) : RecyclerView.OnItemTouchListener {

    private val gestureDetector: GestureDetector

    init {
        gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent): Boolean {
                return true
            }

            override fun onLongPress(e: MotionEvent) {
                val child = recyclerView.findChildViewUnder(e.x, e.y)
                if (child != null && clickListener != null) {
                    clickListener.onLongClick(child, recyclerView.getChildPosition(child))
                }
            }
        })
    }

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {

        val child = rv.findChildViewUnder(e.x, e.y)
        if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
            clickListener.onClick(child, rv.getChildPosition(child))
        }
        return false
    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

    }
}
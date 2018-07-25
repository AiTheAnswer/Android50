package com.allen.recyclerviewdemo.divider

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * RecyclerView的分割线
 */
class DividerItemDecoration(var mContext: Context, var mOrientation: Int) : RecyclerView.ItemDecoration() {
    val ATTRS = intArrayOf(android.R.attr.listDivider)
    private var mDivider: Drawable? = null
    val HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL
    val VERTICAL_LIST = LinearLayoutManager.VERTICAL

    init {
        val a = mContext.obtainStyledAttributes(ATTRS)
        mDivider = a.getDrawable(0)
        a.recycle()
        setOrientation(mOrientation)
    }

    private fun setOrientation(mOrientation: Int) {
        if (mOrientation != HORIZONTAL_LIST && mOrientation != VERTICAL_LIST) {
            throw IllegalArgumentException("invalid orientation")
        }
        this.mOrientation = mOrientation
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        if (mOrientation == VERTICAL_LIST) {
            drawVertical(c, parent)
        } else {
            drawHorizontal(c, parent)
        }
    }

    /**
     * 绘制垂直列表的子条目分割线
     */
    private fun drawVertical(c: Canvas, parent: RecyclerView) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val layoutParams: RecyclerView.LayoutParams = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + layoutParams.bottomMargin
            val bottom = top + mDivider?.intrinsicHeight!!
            mDivider?.setBounds(left, top, right, bottom)
            mDivider?.draw(c)
        }
    }

    /**
     * 绘制水平列表的子条目的分割线
     */
    private fun drawHorizontal(c: Canvas, parent: RecyclerView) {
        val top = parent.paddingTop
        val bottom = parent.height - parent.paddingBottom
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val layoutParams = child.layoutParams as RecyclerView.LayoutParams
            val left = child.right + layoutParams.rightMargin
            val right = left + mDivider?.intrinsicWidth!!
            mDivider?.setBounds(left, top, right, bottom)
            mDivider?.draw(c)
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        if (mOrientation == VERTICAL_LIST) {
            outRect.set(0, 0, 0, mDivider?.intrinsicHeight!!)
        } else {
            outRect.set(0, 0, mDivider?.intrinsicWidth!!, 0)
        }
    }
}
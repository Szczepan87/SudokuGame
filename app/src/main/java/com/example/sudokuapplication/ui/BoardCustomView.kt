package com.example.sudokuapplication.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.sudokuapplication.R
import com.example.sudokuapplication.util.BOARD_SIZE
import com.example.sudokuapplication.util.SQUARE_SIZE
import kotlin.math.min

class BoardCustomView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private var cellSize = 0F

    private val boldLinePaint = Paint().apply {
        style = Paint.Style.STROKE
        color = Color.BLACK
        strokeWidth = 4F
    }

    private val normalLinePaint = Paint().apply {
        style = Paint.Style.STROKE
        color = Color.BLACK
        strokeWidth = 2F
    }

    private val selectedCellPaint = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
        color = resources.getColor(R.color.colorAccent)
    }

    private val nonEditableCell = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
        color = resources.getColor(R.color.colorDisabledCell)
    }

    private var listener: BoardCustomView.OnTouchListener? = null

    private var rowSelected = -1
    private var columnSelected = -1

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        // securing square shape
        val size = min(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(size, size)
    }

    override fun onDraw(canvas: Canvas) {
        cellSize = (width / BOARD_SIZE).toFloat()
        drawLines(canvas)
        selectCell(canvas)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                handleClick(event.x, event.y)
                true
            }
            else -> false
        }
    }

    private fun handleClick(x: Float, y: Float) {
        val selectedRow = (y / cellSize).toInt()
        val selectedColumn = (x / cellSize).toInt()
        listener?.onCellTouch(selectedRow, selectedColumn)
    }

    private fun drawLines(canvas: Canvas) {
        canvas.drawRect(0F, 0F, width.toFloat(), height.toFloat(), boldLinePaint)
        for (i in 1 until BOARD_SIZE) {
            val lineToDraw = when (i % SQUARE_SIZE) {
                0 -> boldLinePaint
                else -> normalLinePaint
            }
            // draw vertical lines
            canvas.drawLine(i * cellSize, 0F, i * cellSize, height.toFloat(), lineToDraw)
            // draw horizontal lines
            canvas.drawLine(0F, i * cellSize, width.toFloat(), i * cellSize, lineToDraw)
        }
    }

    private fun selectCell(canvas: Canvas) {
        if (columnSelected == -1 || rowSelected == -1) return

        for (row in 0..BOARD_SIZE) {
            for (column in 0..BOARD_SIZE) {
                if (row == rowSelected && column == columnSelected) {
                    colourCell(canvas, row, column, selectedCellPaint)
                }
            }
        }
    }

    private fun colourCell(canvas: Canvas, row: Int, column: Int, paint: Paint) {
        // draw rectangle from start of the cell to start of next cell
        canvas.drawRect(
            column * cellSize,
            row * cellSize,
            (column + 1) * cellSize,
            (row + 1) * cellSize,
            paint
        )
    }

    fun updateSelectedCell(row: Int, column: Int) {
        rowSelected = row
        columnSelected = column
        invalidate()
    }

    fun registerListener(listener: BoardCustomView.OnTouchListener) {
        this.listener = listener
    }

    interface OnTouchListener {
        fun onCellTouch(row: Int, column: Int)
    }
}
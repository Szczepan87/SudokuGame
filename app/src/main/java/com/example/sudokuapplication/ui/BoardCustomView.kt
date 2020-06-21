package com.example.sudokuapplication.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.sudokuapplication.R
import com.example.sudokuapplication.model.Cell
import com.example.sudokuapplication.util.*
import kotlin.math.min

class BoardCustomView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private var board: MutableList<Cell> =
        mutableListOf()

    private var cellSize = 0F

    private val boldLinePaint = getLinePaint(4F)

    private val normalLinePaint = getLinePaint(2F)

    private val selectedCellPaint = getCellPaint(resources.getColor(R.color.colorAccent))

    private val nonEditableCell = getCellPaint(resources.getColor(R.color.colorDisabledCell))

    private val textPaint = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
        color = Color.BLACK
        textSize = 48F
    }

    private var listener: OnTouchListener? = null

    private var rowSelected = OUTSIDE_BOARD
    private var columnSelected = OUTSIDE_BOARD

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        // securing square shape
        val size = min(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(size, size)
    }

    override fun onDraw(canvas: Canvas) {
        cellSize = (width / BOARD_SIZE).toFloat()
        handleCell(canvas)
        drawLines(canvas)
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

    private fun handleCell(canvas: Canvas) {
        if (board.isEmpty()) return
        for (row in 0 until BOARD_SIZE) {
            for (column in 0 until BOARD_SIZE) {
                if (row == rowSelected && column == columnSelected) {
                    colourCell(canvas, row, column, selectedCellPaint)
                } else if (board.getCell(row, column).isEditable.not()) {
                    colourCell(canvas, row, column, nonEditableCell)
                }
            }
        }
        fillCells(canvas)
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

    private fun fillCells(canvas: Canvas) {
        if (board.size == 0) return
        for (row in 0 until BOARD_SIZE) {
            for (column in 0 until BOARD_SIZE) {
                val cell = board.getCell(row, column)
                val stringValue = if (cell.value == EMPTY_VALUE) "" else cell.value.toString()

                val textBounds = Rect()
                textPaint.getTextBounds(stringValue, 0, stringValue.length, textBounds)
                val textWidth = textPaint.measureText(stringValue)
                val textHeight = textBounds.height()

                // setting text in center of cell
                canvas.drawText(
                    stringValue, (column * cellSize) + cellSize / 2 - textWidth / 2,
                    (row * cellSize) + cellSize / 2 + textHeight / 2, textPaint
                )
            }
        }
    }

    private fun getLinePaint(stroke: Float) = Paint().apply {
        style = Paint.Style.STROKE
        color = Color.BLACK
        strokeWidth = stroke
    }

    private fun getCellPaint(color: Int) = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
        this.color = color
    }

    fun updateSelectedCell(row: Int, column: Int) {
        rowSelected = row
        columnSelected = column
        invalidate()
    }

    fun registerListener(listener: OnTouchListener) {
        this.listener = listener
    }

    fun updateBoard(board: MutableList<Cell>) {
        this.board.addAll(board)
        invalidate()
    }

    interface OnTouchListener {
        fun onCellTouch(row: Int, column: Int)
    }
}
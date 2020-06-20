package com.example.sudokuapplication.util

import com.example.sudokuapplication.model.Cell

fun MutableList<Cell>.getCell(row: Int, column: Int): Cell {
    var cell = Cell(-1, -1, 0, false)
    forEach { if (it.row == row && it.column == column) cell = it }
    return cell
}

fun MutableList<MutableList<Int>>.toMutableListOfCells(): MutableList<Cell> {
    val editableBoard = mutableListOf<Cell>()
    for (row in 0 until BOARD_SIZE) {
        for (column in 0 until BOARD_SIZE) {
            val value = this[row][column]
            editableBoard.add(Cell(row, column, value, value == 0))
        }
    }
    return editableBoard
}
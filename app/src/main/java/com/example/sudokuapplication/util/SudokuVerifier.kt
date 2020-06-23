package com.example.sudokuapplication.util

import com.example.sudokuapplication.model.Cell

class SudokuVerifier {

    fun isSudokuSolved(board: MutableList<Cell>): Boolean {
        return isSquareCorrect(board) && isRowCorrect(board) && isColumnCorrect(board)
    }

    private fun isSquareCorrect(board: MutableList<Cell>): Boolean {
        val sublist = mutableListOf<Int>()
        for (column in 0 until BOARD_SIZE step SQUARE_SIZE) {
            for (row in 0 until BOARD_SIZE) {
                for (sqrCol in 0 until SQUARE_SIZE) {
                    sublist.add(board.getCell(row, column + sqrCol).value)
                }
                if ((row + 1) % SQUARE_SIZE == 0) {
                    if (!isInputCorrect(sublist)) return false else sublist.clear()
                }
            }
        }
        return true
    }

    private fun isRowCorrect(board: MutableList<Cell>): Boolean {
        val sublist = mutableListOf<Int>()
        for (row in 0 until BOARD_SIZE) {
            for (col in 0 until BOARD_SIZE) {
                sublist.add(board.getCell(row, col).value)
            }
            if (!isInputCorrect(sublist)) return false else sublist.clear()
        }
        return true
    }

    private fun isColumnCorrect(board: MutableList<Cell>): Boolean {
        val sublist = mutableListOf<Int>()
        for (col in 0 until BOARD_SIZE) {
            for (row in 0 until BOARD_SIZE) {
                sublist.add(board.getCell(row, col).value)
            }
            if (!isInputCorrect(sublist)) return false else sublist.clear()
        }
        return true
    }

    private fun isInputCorrect(list: List<Int>): Boolean {
        if (list.contains(EMPTY_VALUE)) return false
        val set = mutableSetOf<Int>()
        set.addAll(list)
        return set.size == list.size
    }
}
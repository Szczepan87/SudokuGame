package com.example.sudokuapplication.util

import com.example.sudokuapplication.model.Board

class SudokuVerifier {

    fun isSudokuSolved(board: Board): Boolean {
        return isSquareCorrect(board) && isRowCorrect(board) && isColumnCorrect(board)
    }

    private fun isSquareCorrect(board: Board): Boolean {
        TODO("not implemented")
    }

    private fun isRowCorrect(board: Board): Boolean {
        var correct = false
        board.board.forEach { correct = isInputCorrect(it) }
        return correct
    }

    private fun isColumnCorrect(board: Board): Boolean {
        val sublist = mutableListOf<Int>()
        for (i in 0..9) {
            board.board.forEach { sublist.add(it[i]) }
        }
        return isInputCorrect(sublist)
    }

    private fun isInputCorrect(list: List<Int>): Boolean {
        val set = mutableSetOf<Int>()
        set.addAll(list)
        return set.size == list.size
    }
}
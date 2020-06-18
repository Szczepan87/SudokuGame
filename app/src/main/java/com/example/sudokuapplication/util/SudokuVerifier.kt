package com.example.sudokuapplication.util

import com.example.sudokuapplication.model.Board

class SudokuVerifier {

    fun isSudokuSolved(board: Board): Boolean {
        return isSquareCorrect(board) && isRowCorrect(board) && isColumnCorrect(board)
    }

    private fun isSquareCorrect(board: Board): Boolean {
        val sublist = mutableListOf<Int>()
        for (i in 0 until 9) {
            sublist.addAll(board.board[i].subList(0, 3))
            if ((i + 1) % 3 == 0) {
                if (!isInputCorrect(sublist)) return false else sublist.clear()
            }
        }
        for (i in 0 until 9){
            sublist.addAll(board.board[i].subList(3, 6))
            if ((i + 1) % 3 == 0) {
                if (!isInputCorrect(sublist)) return false else sublist.clear()
            }
        }
        for (i in 0 until 9){
            sublist.addAll(board.board[i].subList(6,9))
            if ((i+1) % 3 == 0){
                if (!isInputCorrect(sublist)) return false else sublist.clear()
            }
        }
        return true
    }

    private fun isRowCorrect(board: Board): Boolean {
        val sublist = mutableListOf<Int>()
        for (i in 0 until 9) {
            if (!isInputCorrect(board.board[i])) return false
            else sublist.clear()
        }
        return true
    }

    private fun isColumnCorrect(board: Board): Boolean {
        val sublist = mutableListOf<Int>()
        for (i in 0 until 9) {
            board.board.forEach { sublist.add(it[i]) }
            if (!isInputCorrect(sublist)) return false
            else sublist.clear()
        }
        return true
    }

    private fun isInputCorrect(list: List<Int>): Boolean {
        val set = mutableSetOf<Int>()
        set.addAll(list)
        return set.size == list.size
    }
}
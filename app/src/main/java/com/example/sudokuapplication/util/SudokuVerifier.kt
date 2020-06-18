package com.example.sudokuapplication.util

import com.example.sudokuapplication.model.Board

class SudokuVerifier {

    fun isSudokuSolved(board: Board): Boolean {
        return isSquareCorrect(board) && isRowCorrect(board) && isColumnCorrect(board)
    }

    private fun isSquareCorrect(board: Board): Boolean {
        val sublist = mutableListOf<Int>()
        sublist.addAll(board.board[0].subList(0,2))
        sublist.addAll(board.board[1].subList(0,2))
        sublist.addAll(board.board[2].subList(0,2))
        if (!isInputCorrect(sublist)) return false else sublist.clear()
        sublist.addAll(board.board[3].subList(0,2))
        sublist.addAll(board.board[4].subList(0,2))
        sublist.addAll(board.board[5].subList(0,2))
        if (!isInputCorrect(sublist)) return false else sublist.clear()
        sublist.addAll(board.board[6].subList(0,2))
        sublist.addAll(board.board[7].subList(0,2))
        sublist.addAll(board.board[8].subList(0,2))
        if (!isInputCorrect(sublist)) return false else sublist.clear()
        sublist.addAll(board.board[0].subList(3,5))
        sublist.addAll(board.board[1].subList(3,5))
        sublist.addAll(board.board[2].subList(3,5))
        if (!isInputCorrect(sublist)) return false else sublist.clear()
        sublist.addAll(board.board[3].subList(3,5))
        sublist.addAll(board.board[4].subList(3,5))
        sublist.addAll(board.board[5].subList(3,5))
        if (!isInputCorrect(sublist)) return false else sublist.clear()
        sublist.addAll(board.board[6].subList(3,5))
        sublist.addAll(board.board[7].subList(3,5))
        sublist.addAll(board.board[8].subList(3,5))
        if (!isInputCorrect(sublist)) return false else sublist.clear()
        sublist.addAll(board.board[0].subList(6,8))
        sublist.addAll(board.board[1].subList(6,8))
        sublist.addAll(board.board[2].subList(6,8))
        if (!isInputCorrect(sublist)) return false else sublist.clear()
        sublist.addAll(board.board[3].subList(6,8))
        sublist.addAll(board.board[4].subList(6,8))
        sublist.addAll(board.board[5].subList(6,8))
        if (!isInputCorrect(sublist)) return false else sublist.clear()
        sublist.addAll(board.board[6].subList(6,8))
        sublist.addAll(board.board[7].subList(6,8))
        sublist.addAll(board.board[8].subList(6,8))
        if (!isInputCorrect(sublist)) return false else sublist.clear()
        return true
    }

    private fun isRowCorrect(board: Board): Boolean {
        val sublist = mutableListOf<Int>()
        for (i in 0 until 9){
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
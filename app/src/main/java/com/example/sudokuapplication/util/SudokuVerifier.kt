package com.example.sudokuapplication.util
//
//import com.example.sudokuapplication.model.Cell
//
//class SudokuVerifier {
//
//    fun isSudokuSolved(board: MutableList<Cell>): Boolean {
//        return isSquareCorrect(board) && isRowCorrect(board) && isColumnCorrect(board)
//    }
//
//    private fun isSquareCorrect(board: MutableList<Cell>): Boolean {
//        val sublist = mutableListOf<Int>()
//        for (col in 0 until SQUARE_SIZE step SQUARE_SIZE) {
//            for (row in 0 until BOARD_SIZE) {
//                sublist.addAll(board.board[row].subList(col, col + SQUARE_SIZE))
//                if ((row + 1) % SQUARE_SIZE == 0) {
//                    if (!isInputCorrect(sublist)) return false else sublist.clear()
//                }
//            }
//        }
//        return true
//    }
//
//    private fun isRowCorrect(board: MutableList<Cell>): Boolean {
//        val sublist = mutableListOf<Int>()
//        for (i in 0 until BOARD_SIZE) {
//            if (!isInputCorrect(board.board[i])) return false
//            else sublist.clear()
//        }
//        return true
//    }
//
//    private fun isColumnCorrect(board: MutableList<Cell>): Boolean {
//        val sublist = mutableListOf<Int>()
//        for (i in 0 until BOARD_SIZE) {
//            board.board.forEach { sublist.add(it[i]) }
//            if (!isInputCorrect(sublist)) return false
//            else sublist.clear()
//        }
//        return true
//    }
//
//    private fun isInputCorrect(list: List<Cell>): Boolean {
//        val set = mutableSetOf<Cell>()
//        set.addAll(list)
//        return set.size == list.size
//    }
//}
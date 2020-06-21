package com.example.sudokuapplication.repository

import com.example.sudokuapplication.model.Board
import com.example.sudokuapplication.model.Cell

interface SudokuRepository {

    suspend fun loadRemoteBoard(): Board

    fun databaseBoard(): MutableList<Cell>

    suspend fun saveBoardToDatabase(board: MutableList<Cell>)
}
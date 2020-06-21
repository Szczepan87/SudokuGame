package com.example.sudokuapplication.repository

import android.content.SharedPreferences
import com.example.sudokuapplication.model.Board
import com.example.sudokuapplication.model.Cell
import com.example.sudokuapplication.util.BOARD_KEY
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SudokuRepositoryImpl(
    private val sudokuApi: SudokuApi,
    private val sharedPreferences: SharedPreferences
) : SudokuRepository {

    override suspend fun loadRemoteBoard(): Board {
        return withContext(Dispatchers.IO) { return@withContext sudokuApi.getEasySudoku().await() }
    }

    override fun databaseBoard(): MutableList<Cell> {
        val boardString = sharedPreferences.getString(BOARD_KEY, "")
        return Gson().fromJson(boardString, object : TypeToken<MutableList<Cell>>() {}.type)
            ?: mutableListOf()
    }

    override suspend fun saveBoardToDatabase(board: MutableList<Cell>) {
        withContext(Dispatchers.IO) {
            val boardString = Gson().toJson(board)
            sharedPreferences.edit().putString(BOARD_KEY, boardString).apply()
        }
    }
}
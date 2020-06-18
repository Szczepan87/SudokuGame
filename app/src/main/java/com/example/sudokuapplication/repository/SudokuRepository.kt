package com.example.sudokuapplication.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.sudokuapplication.model.Board
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SudokuRepository(private val sudokuApi: SudokuApi, sudokuDatabase: SudokuDatabase) {

    private val sudokuDao = sudokuDatabase.getDao()

    suspend fun loadRemoteBoard(): Board {
        return withContext(Dispatchers.IO) { sudokuApi.getEasySudoku().await() }
    }

    fun databaseBoard(): LiveData<Board> {
        return sudokuDao.getBoard()
    }

    suspend fun saveBoardToDatabase(board: Board) {
        withContext(Dispatchers.IO) {
            Log.d("REPOSITORY", "Saving board: ${board}")
            sudokuDao.upsert(board)
        }
    }
}
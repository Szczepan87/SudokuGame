package com.example.sudokuapplication.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sudokuapplication.model.Board

@Dao
interface SudokuDao {

    @Query("SELECT * FROM board WHERE id = 0")
    fun getBoard(): LiveData<Board>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(board: Board)
}
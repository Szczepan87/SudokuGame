package com.example.sudokuapplication.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.sudokuapplication.model.Board
import com.example.sudokuapplication.util.BoardTypeConverters

@Database(entities = [Board::class], version = 1)
@TypeConverters(BoardTypeConverters::class)
abstract class SudokuDatabase: RoomDatabase() {
    abstract fun getDao(): SudokuDao
}
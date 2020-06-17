package com.example.sudokuapplication.util

import androidx.room.TypeConverter
import com.example.sudokuapplication.model.Board
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BoardTypeConverters {

    @TypeConverter
    fun saveBoard(board: List<List<Int>>): String {
        return Gson().toJson(board)
    }

    @TypeConverter
    fun restoreBoard(stringBoard: String): List<List<Int>> {
        return Gson().fromJson(stringBoard, object : TypeToken<Board>() {}.type)
    }
}
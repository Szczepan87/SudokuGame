package com.example.sudokuapplication.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Board(
    @SerializedName("board")
    val board: MutableList<MutableList<Int>>
){
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}
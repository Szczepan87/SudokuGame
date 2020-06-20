package com.example.sudokuapplication.model


import android.os.Parcelable
import com.example.sudokuapplication.util.BOARD_SIZE
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Board(
    @SerializedName("board")
    val board: MutableList<MutableList<Int>>
) : Parcelable {

    @Transient
    val editableBoard: MutableList<Cell> = mutableListOf()

    init {
        for (row in 0 until BOARD_SIZE){
            for (column in 0 until BOARD_SIZE){
                val value = board[row][column]
                editableBoard.add(Cell(row, column, value, value == 0))
            }
        }
    }
}
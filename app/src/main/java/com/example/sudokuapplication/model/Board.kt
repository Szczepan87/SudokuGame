package com.example.sudokuapplication.model


import com.google.gson.annotations.SerializedName

data class Board(
    @SerializedName("board")
    val board: List<List<Int>>?
)
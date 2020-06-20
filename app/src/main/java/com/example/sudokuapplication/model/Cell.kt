package com.example.sudokuapplication.model

data class Cell(val row: Int, val column: Int, var value: Int, val isEditable: Boolean)
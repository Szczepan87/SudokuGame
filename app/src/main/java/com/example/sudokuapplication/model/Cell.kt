package com.example.sudokuapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cell(val row: Int, val column: Int, var value: Int, val isEditable: Boolean) : Parcelable
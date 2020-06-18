package com.example.sudokuapplication.repository

import com.example.sudokuapplication.model.Board
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface SudokuApi {

    @GET("board?difficulty=easy")
    fun getEasySudoku(): Deferred<Board>
}
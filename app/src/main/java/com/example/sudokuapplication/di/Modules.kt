package com.example.sudokuapplication.di

import com.example.sudokuapplication.repository.SudokuApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val repositoryModule = module {
    single { provideSudokuApi() }
}

private fun provideSudokuApi() = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl("https://sugoku.herokuapp.com/")
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()
    .create(SudokuApi::class.java)

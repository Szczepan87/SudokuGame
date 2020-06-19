package com.example.sudokuapplication.di

import android.content.Context
import androidx.room.Room
import com.example.sudokuapplication.repository.SudokuApi
import com.example.sudokuapplication.repository.SudokuDatabase
import com.example.sudokuapplication.repository.SudokuRepository
import com.example.sudokuapplication.vm.SudokuViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val repositoryModule = module {
    single { provideSudokuApi() }
    single { provideDatabase(androidContext()) }
    single { SudokuRepository(get(), get()) }
}

val viewModelModule = module {
    single { SudokuViewModel(get()) }
}

private fun provideSudokuApi() = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl("https://sugoku.herokuapp.com/")
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()
    .create(SudokuApi::class.java)

private fun provideDatabase(context: Context) =
    Room.databaseBuilder(context, SudokuDatabase::class.java, "sudoku_db").build()

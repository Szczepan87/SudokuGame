package com.example.sudokuapplication.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.sudokuapplication.repository.SudokuApi
import com.example.sudokuapplication.repository.SudokuRepository
import com.example.sudokuapplication.util.BOARD_KEY
import com.example.sudokuapplication.vm.SudokuViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val repositoryModule = module {
    single { provideSudokuApi() }
    single { provideSharedPrefs(androidApplication()) }
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

private fun provideSharedPrefs(app: Application): SharedPreferences {
    return app.getSharedPreferences(BOARD_KEY, Context.MODE_PRIVATE)
}

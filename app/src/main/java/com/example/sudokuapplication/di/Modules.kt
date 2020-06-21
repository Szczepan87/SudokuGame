package com.example.sudokuapplication.di

import android.content.Context
import android.content.SharedPreferences
import com.example.sudokuapplication.repository.SudokuApi
import com.example.sudokuapplication.repository.SudokuRepositoryImpl
import com.example.sudokuapplication.util.BASE_URL
import com.example.sudokuapplication.util.BOARD_KEY
import com.example.sudokuapplication.vm.SudokuViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val repositoryModule = module {
    single { provideSudokuApi() }
    single { provideSharedPrefs(androidContext()) }
    single { SudokuRepositoryImpl(get(), get()) }
}

val viewModelModule = module {
    single { SudokuViewModel(get()) }
}

private fun provideSudokuApi() = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()
    .create(SudokuApi::class.java)

private fun provideSharedPrefs(context: Context): SharedPreferences {
    return context.getSharedPreferences(BOARD_KEY, Context.MODE_PRIVATE)
}

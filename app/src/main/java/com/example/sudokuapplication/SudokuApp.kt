package com.example.sudokuapplication

import android.app.Application
import com.example.sudokuapplication.di.repositoryModule
import com.example.sudokuapplication.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SudokuApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@SudokuApp.applicationContext)
            modules (repositoryModule, viewModelModule) }
    }
}
package com.example.sudokuapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sudokuapplication.R
import com.example.sudokuapplication.vm.SudokuViewModel
import org.koin.android.ext.android.get

class SudokuGameActivity : AppCompatActivity() {

    private val sudokuViewModel: SudokuViewModel = get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // sudokuViewModel.board.observe(this, Observer { board -> textView.text = board.toString() })
    }
}
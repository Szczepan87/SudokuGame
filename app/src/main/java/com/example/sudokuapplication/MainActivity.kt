package com.example.sudokuapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.get

class MainActivity : AppCompatActivity() {

    private val sudokuViewModel: SudokuViewModel = get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sudokuViewModel.board.observe(this, Observer { board -> textView.text = board.toString() })
    }
}
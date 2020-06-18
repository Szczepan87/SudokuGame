package com.example.sudokuapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    private val sudokuViewModel: SudokuViewModel = get()
    private lateinit var job: Job
    override val coroutineContext: CoroutineContext = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        job = Job()

        launch {  }
        sudokuViewModel.board.observe(this, Observer { board -> textView.text = board.toString() })
    }

    override fun onDestroy() {
        job.cancel()

        super.onDestroy()
    }
}
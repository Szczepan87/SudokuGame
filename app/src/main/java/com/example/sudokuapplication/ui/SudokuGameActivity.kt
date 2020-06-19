package com.example.sudokuapplication.ui

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.sudokuapplication.R
import com.example.sudokuapplication.vm.SudokuViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.get

class SudokuGameActivity : AppCompatActivity(), BoardCustomView.OnTouchListener {

    private val sudokuViewModel: SudokuViewModel = get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val keypad = listOf<Button>(
            erase_button,
            one_button,
            two_button,
            three_button,
            four_button,
            five_button,
            six_button,
            seven_button,
            eight_button,
            nine_button
        )

        sudoku_board_custom_view.registerListener(this)
        sudokuViewModel.selectedFieldLiveData.observe(
            this,
            Observer { updateSelectedFieldOnBoard(it) })
        sudokuViewModel.board.observe(this, Observer {
            updateBoard(it.board)
            Log.d("ACTIVITY", "Updating UI with ${it.board}")
        })
        keypad.forEachIndexed { index, button -> button.setOnClickListener {
            sudokuViewModel.updateFieldValue(index)
        } }
    }

    private fun updateBoard(board: MutableList<MutableList<Int>>) {
        sudoku_board_custom_view.updateBoard(board)
    }

    private fun updateSelectedFieldOnBoard(field: Pair<Int, Int>?) = field?.let {
        sudoku_board_custom_view.updateSelectedCell(field.first, field.second)
    }

    override fun onCellTouch(row: Int, column: Int) {
        sudokuViewModel.updateSelectedField(row, column)
    }
}
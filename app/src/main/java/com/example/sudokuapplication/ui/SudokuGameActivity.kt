package com.example.sudokuapplication.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.sudokuapplication.R
import com.example.sudokuapplication.model.Cell
import com.example.sudokuapplication.ui.dialogs.NoBoardDialog
import com.example.sudokuapplication.ui.dialogs.SolvedSudokuDialog
import com.example.sudokuapplication.ui.dialogs.WrongSudokuDialog
import com.example.sudokuapplication.util.SOLVED_DIALOG_TAG
import com.example.sudokuapplication.util.UNSOLVED_DIALOG_TAG
import com.example.sudokuapplication.vm.SudokuViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.get

class SudokuGameActivity : AppCompatActivity(),
    BoardCustomView.OnTouchListener {

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
            if (it.isNullOrEmpty()) {
                NoBoardDialog().show(supportFragmentManager, "no board")
            }
            updateBoard(it)
        })

        handleKeypad(keypad)
        handleCheckButton(check_button)
    }

    private fun handleKeypad(keypad: List<Button>) {
        keypad.forEachIndexed { index, button ->
            button.setOnClickListener {
                sudokuViewModel.updateFieldValue(index)
            }
        }
    }

    private fun handleCheckButton(checkButton: Button) {
        checkButton.setOnClickListener {
            if (sudokuViewModel.isSudokuSolved()) SolvedSudokuDialog()
                .show(
                    supportFragmentManager,
                    SOLVED_DIALOG_TAG
                ) else WrongSudokuDialog().show(
                supportFragmentManager,
                UNSOLVED_DIALOG_TAG
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.new_game_menu_item -> {
                sudokuViewModel.loadRemoteBoardData()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun updateBoard(board: MutableList<Cell>) {
        sudoku_board_custom_view.updateBoard(board)
    }

    private fun updateSelectedFieldOnBoard(field: Pair<Int, Int>?) = field?.let {
        sudoku_board_custom_view.updateSelectedCell(field.first, field.second)
    }

    override fun onCellTouch(row: Int, column: Int) {
        sudokuViewModel.updateSelectedField(row, column)
    }
}
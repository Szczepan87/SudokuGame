package com.example.sudokuapplication.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.sudokuapplication.R

class SolvedSudokuDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        with(builder) {
            setTitle("Nice!")
            setMessage("You got this! Sudoku is solved correctly!")
            setIcon(R.drawable.ic_baseline_checked_circle_48)
            setPositiveButton(
                "Deamn, I'm good!"
            ) { dialog, _ -> dialog.dismiss() }
        }
        return super.onCreateDialog(savedInstanceState)
    }
}
package com.example.sudokuapplication.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.sudokuapplication.R

class WrongSudokuDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(requireActivity())

        with(builder) {
            setTitle("Woah!")
            setMessage("This sudoku is not solved correctly!")
            setIcon(R.drawable.ic_baseline_error_outline_48)
            setPositiveButton(
                "Ok, I'll keep trying..."
            ) { dialog, _ -> dialog.dismiss() }
        }
        return builder.create()
    }
}
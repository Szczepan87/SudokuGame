package com.example.sudokuapplication.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.sudokuapplication.R

class WrongSudokuDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(requireActivity())

        with(builder) {
            setTitle(getString(R.string.woah))
            setMessage(getString(R.string.not_solved))
            setIcon(R.drawable.ic_baseline_error_outline_48)
            setPositiveButton(
                getString(R.string.keep_trying)
            ) { dialog, _ -> dialog.dismiss() }
        }
        return builder.create()
    }
}
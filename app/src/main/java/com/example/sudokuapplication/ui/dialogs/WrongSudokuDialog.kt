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
            setTitle(getString(R.string.dialog_unsolved_title))
            setMessage(getString(R.string.dialog_unsolved_message))
            setIcon(R.drawable.ic_baseline_error_outline_48)
            setPositiveButton(
                getString(R.string.dialog_unsolved_ok_button)
            ) { dialog, _ -> dialog.dismiss() }
        }
        return builder.create()
    }
}
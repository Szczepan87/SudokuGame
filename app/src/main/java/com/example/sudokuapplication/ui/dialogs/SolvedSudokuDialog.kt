package com.example.sudokuapplication.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.sudokuapplication.R

class SolvedSudokuDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        with(builder) {
            setTitle(getString(R.string.nice))
            setMessage(getString(R.string.you_got_it))
            setIcon(R.drawable.ic_baseline_checked_circle_48)
            setPositiveButton(
                getString(R.string.im_good)
            ) { dialog, _ -> dialog.dismiss() }
        }
        return builder.create()
    }
}
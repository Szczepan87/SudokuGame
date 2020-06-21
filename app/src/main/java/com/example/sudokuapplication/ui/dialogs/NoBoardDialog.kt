package com.example.sudokuapplication.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.sudokuapplication.R

class NoBoardDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())

        with(builder) {
            setTitle(getString(R.string.dialog_error_title))
            setMessage(getString(R.string.dialog_error_message))
            setIcon(R.drawable.ic_baseline_wifi_48)
            setPositiveButton(
                getString(R.string.dialog_error_ok_button)
            ) { dialog, _ -> dialog.dismiss() }
        }
        return builder.create()
    }
}
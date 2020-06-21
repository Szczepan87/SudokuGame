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
            setTitle(getString(R.string.oops))
            setMessage("Something went wrong with loading new board. Please check your internet connection and start new game.")
            setIcon(R.drawable.ic_baseline_wifi_48)
            setPositiveButton(
                "OK, thanks!"
            ) { dialog, _ -> dialog?.dismiss() }
        }
        return builder.create()
    }
}
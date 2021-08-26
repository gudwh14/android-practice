package jjo.practice.todo.presentation.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import jjo.practice.todo.R
import java.lang.IllegalStateException

class AddTodoDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater

            builder.setView(inflater.inflate(R.layout.dialog_addtodo,null))
                .setPositiveButton(R.string.dialog_ok,
                    DialogInterface.OnClickListener{
                        dialogInterface, i -> Toast.makeText(context,"Ok Click!",Toast.LENGTH_SHORT).show()
                    })
                .setNegativeButton(R.string.dialog_cancel,
                    DialogInterface.OnClickListener{
                        dialogInterface, i -> dialogInterface.cancel()
                    })
                .setCancelable(false)
            builder.create()
        } ?: throw IllegalStateException("액태비티를 찾을 수 없습니다.")
    }
}
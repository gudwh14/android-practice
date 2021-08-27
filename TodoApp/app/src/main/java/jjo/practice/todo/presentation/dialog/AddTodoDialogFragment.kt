package jjo.practice.todo.presentation.dialog

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import jjo.practice.todo.R
import jjo.practice.todo.data.model.TodoEntity
import jjo.practice.todo.data.repository.todo.TodoDatabase
import jjo.practice.todo.databinding.DialogAddtodoBinding
import java.lang.ClassCastException
import java.lang.IllegalStateException

class AddTodoDialogFragment : DialogFragment() {

    internal lateinit var listener : AddTodoDialogListener
    private var _binding : DialogAddtodoBinding? = null
    private val binding get() = _binding!!
    lateinit var db : TodoDatabase

    interface AddTodoDialogListener {
        fun onDialogPositiveClick()
    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        try {
//            listener = context as AddTodoDialogListener
//        } catch (e : ClassCastException) {
//            throw ClassCastException((context.toString() + "must implement AddTodoDialogListener"))
//        }
//    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            _binding = DialogAddtodoBinding.inflate(LayoutInflater.from(context))
            db = TodoDatabase.getInstance(this.requireContext())!!

            builder.setView(binding.root)
                .setPositiveButton(R.string.dialog_ok,
                    DialogInterface.OnClickListener{
                        dialogInterface, i ->
                        val todo = TodoEntity(null, binding.etDialogDate.text.toString(), binding.etDialogTodo.text.toString())
                        insertTodo(todo)
                    })
                .setNegativeButton(R.string.dialog_cancel,
                    DialogInterface.OnClickListener{
                        dialogInterface, i -> dialogInterface.cancel()
                    })
                .setCancelable(false)
            builder.create()
        } ?: throw IllegalStateException("액태비티를 찾을 수 없습니다.")
    }

    @SuppressLint("StaticFieldLeak")
    fun insertTodo(todo : TodoEntity) {
        val insertTask = object : AsyncTask<Unit,Unit,Unit>(){
            override fun doInBackground(vararg p0: Unit?) {
                db.todoDAO().insert(todo)
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
//                listener.onDialogPositiveClick()
            }
        }
        insertTask.execute()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
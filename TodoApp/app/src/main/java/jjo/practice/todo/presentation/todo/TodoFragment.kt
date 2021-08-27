package jjo.practice.todo.presentation.todo

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import jjo.practice.todo.R
import jjo.practice.todo.data.model.TodoEntity
import jjo.practice.todo.data.repository.todo.TodoDatabase
import jjo.practice.todo.databinding.FragmentTodoBinding
import jjo.practice.todo.presentation.MainActivity
import jjo.practice.todo.presentation.dialog.AddTodoDialogFragment

class TodoFragment : Fragment(), AddTodoDialogFragment.AddTodoDialogListener {

    private var _binding : FragmentTodoBinding? = null
    private val binding get() = _binding!!

    lateinit var db : TodoDatabase
    var todoList = listOf<TodoEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTodoBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = TodoDatabase.getInstance(this.requireContext())!!
        binding.btnTodoAdd.setOnClickListener {
            AddTodoDialogFragment().show(requireFragmentManager(),null)
        }

        binding.recyclerViewTodo.layoutManager = LinearLayoutManager(context);
        getAllTodo()
    }

    override fun onDialogPositiveClick() {
        getAllTodo()
    }

    @SuppressLint("StaticFieldLeak")
    fun getAllTodo () {
        val getAllTask = object : AsyncTask<Unit,Unit,Unit>() {
            override fun doInBackground(vararg p0: Unit?) {
                todoList = db.todoDAO().getAll()
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                setRecyclerView(todoList)
            }
        }
        getAllTask.execute()
    }

    fun setRecyclerView(todoList : List<TodoEntity>) {
        binding.recyclerViewTodo.adapter = TodoListAdapter(this.requireContext(),todoList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

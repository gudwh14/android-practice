package jjo.practice.todo.presentation.todo

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import jjo.practice.todo.data.model.TodoEntity
import jjo.practice.todo.data.repository.todo.TodoDatabase
import jjo.practice.todo.databinding.FragmentTodoBinding
import jjo.practice.todo.presentation.dialog.AddTodoDialogFragment

@SuppressLint("StaticFieldLeak")
class TodoFragment : Fragment(), OnTodoAdapterListener{

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

    private fun deleteTodo (todo : TodoEntity) {
        val deleteTask = object : AsyncTask<Unit,Unit,Unit>() {
            override fun doInBackground(vararg p0: Unit?) {
                db.todoDAO().delete(todo)
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                getAllTodo()
            }
        }
        deleteTask.execute()
    }

    private fun doneTodo (todo : TodoEntity) {
        val doneTask = object : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg p0: Unit?) {
                todo.status = true
                db.todoDAO().update(todo)
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                getAllTodo();
            }
        }
        doneTask.execute()
    }

    override fun onDeleteListener(todo: TodoEntity) {
        deleteTodo(todo)
    }

    override fun onDoneListener(todo: TodoEntity) {
        doneTodo(todo)
    }

    fun setRecyclerView(todoList : List<TodoEntity>) {
        binding.recyclerViewTodo.adapter = TodoListAdapter(this.requireContext(),todoList,this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

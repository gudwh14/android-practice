package jjo.practice.todo.presentation.todo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jjo.practice.todo.R
import jjo.practice.todo.data.model.TodoEntity
import jjo.practice.todo.databinding.ItemTodoBinding

class TodoListAdapter(private val context : Context,
                      private var list : List<TodoEntity>,
                      private val onTodoAdapterListener : OnTodoAdapterListener) : RecyclerView.Adapter<TodoListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemBinding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val todo : TodoEntity = list[position]
        holder.bind(todo)
        holder.itemBinding.btnTodoItemDelete.setOnClickListener {
            onTodoAdapterListener.onDeleteListener(todo)
        }
        holder.itemBinding.btnTodoItemDone.setOnClickListener {
            onTodoAdapterListener.onDoneListener(todo)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyViewHolder(val itemBinding : ItemTodoBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(todo : TodoEntity) {
            itemBinding.tvTodoItemDate.text = todo.date
            itemBinding.tvTodoItemTodo.text = todo.todo
            if(todo.status) {
                itemBinding.tvTodoItemStatus.text = context.getString(R.string.item_todo_status_done)
                itemBinding.btnTodoItemDone.visibility = View.GONE
            }
            else {
                itemBinding.tvTodoItemStatus.text = context.getString(R.string.item_todo_status_yet)
            }
        }
    }
}
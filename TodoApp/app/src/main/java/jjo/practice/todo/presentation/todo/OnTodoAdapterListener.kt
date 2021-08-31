package jjo.practice.todo.presentation.todo

import jjo.practice.todo.data.model.TodoEntity

interface OnTodoAdapterListener {
    fun onDeleteListener(todo : TodoEntity)
    fun onDoneListener(todo : TodoEntity)
}
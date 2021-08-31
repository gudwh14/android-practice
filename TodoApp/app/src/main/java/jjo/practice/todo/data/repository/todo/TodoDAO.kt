package jjo.practice.todo.data.repository.todo

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import jjo.practice.todo.data.model.TodoEntity

@Dao
interface TodoDAO {

    // PK 값이 이미 존재하는 data 가 들어오면 덮어 쓴다.
    @Insert(onConflict = REPLACE)
    fun insert(todo : TodoEntity)

    @Query("SELECT * FROM todo")
    fun getAll() : List<TodoEntity>

    @Update
    fun update(todo : TodoEntity)

    @Delete
    fun delete(todo : TodoEntity)
}
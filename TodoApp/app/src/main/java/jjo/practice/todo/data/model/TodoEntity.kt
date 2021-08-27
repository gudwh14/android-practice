package jjo.practice.todo.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true) val id : Long?,
    @ColumnInfo(name = "date") val date : String,
    @ColumnInfo(name = "todo") val todo : String,
    @ColumnInfo(name = "status") val status : Boolean = false
)
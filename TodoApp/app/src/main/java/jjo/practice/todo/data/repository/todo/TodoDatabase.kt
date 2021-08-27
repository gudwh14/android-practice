package jjo.practice.todo.data.repository.todo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import jjo.practice.todo.data.model.TodoEntity

@Database(entities = arrayOf(TodoEntity::class), version = 1)
abstract class TodoDatabase : RoomDatabase(){
    abstract fun todoDAO() : TodoDAO

    // Database 를 만들때는 객채를 한번만 생성 하는게 유리
    companion object {
        var INSTANCE : TodoDatabase? = null

        fun getInstance(context : Context) : TodoDatabase? {
            if(INSTANCE == null) {
                synchronized(TodoDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,TodoDatabase::class.java,"todo.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }

            return INSTANCE
        }
    }
}
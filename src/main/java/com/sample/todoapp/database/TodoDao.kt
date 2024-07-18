package com.sample.todoapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sample.todoapp.ToDoData

@Dao
interface TodoDao {

    @Query("SELECT * FROM ToDoData")
    fun getAllTodo(): LiveData<List<ToDoData>>

    @Insert
    fun addTodo(todo : ToDoData)

    @Query("DELETE FROM ToDoData WHERE id = :id")
    fun deleteTodo(id : Int)
}
package com.sample.todoapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sample.todoapp.ToDoData

@Database(entities = [ToDoData::class], version = 1)
@TypeConverters(convertors::class)
abstract class TodoDatabase : RoomDatabase(){

    companion object {
        const val NAME = "Todo_DB"
    }

    abstract fun getTodoDao(): TodoDao
}
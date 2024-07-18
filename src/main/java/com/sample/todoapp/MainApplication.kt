package com.sample.todoapp

import android.app.Application
import androidx.room.Room
import com.sample.todoapp.database.TodoDatabase

class MainApplication : Application() {

    companion object {
        lateinit var todoDatabase: TodoDatabase

    }

    override fun onCreate() {
        super.onCreate()
        todoDatabase = Room.databaseBuilder(
            applicationContext,
            TodoDatabase::class.java,
            TodoDatabase.NAME
        ).build()
    }

}
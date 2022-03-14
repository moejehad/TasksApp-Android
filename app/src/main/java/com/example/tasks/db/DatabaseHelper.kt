package com.example.tasks.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.tasks.model.Task

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    private var db: SQLiteDatabase

    init {
        db = writableDatabase
    }

    companion object {
        const val DATABASE_NAME = "Tasks"
        const val DATABASE_VERSION = 1
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        p0!!.execSQL(Task.TABLE_CREATE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("DROP TABLE IF EXISTS ${Task.TABLE_NAME}")
        onCreate(p0)
    }

    /*------------------------------------------*/

    fun insertTask(title: String, details: String, date: String, time: String ): Boolean {
        val cv = ContentValues()
        cv.put(Task.COL_TITLE, title)
        cv.put(Task.COL_DETAILS, details)
        cv.put(Task.COL_DATE, date)
        cv.put(Task.COL_TIME, time)
        return db.insert(Task.TABLE_NAME, null, cv) > 0
    }


    fun getAllTasks(): ArrayList<Task> {

        var TasksList = ArrayList<Task>()
        val c = db.rawQuery(
            "select * from ${Task.TABLE_NAME} order by ${Task.COL_ID} desc",
            null
        )
        c.moveToFirst()
        while (!c.isAfterLast) {
            val s = Task(
                c.getString(0),
                c.getString(1),
                c.getString(2),
                c.getString(3)
            )
            TasksList.add(s)
            c.moveToNext()
        }
        c.close()
        return TasksList
    }


}
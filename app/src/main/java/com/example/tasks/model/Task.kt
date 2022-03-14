package com.example.tasks.model

data class Task(var date:String , var title:String , var details:String , var time:String) {

    companion object{
        const val TABLE_NAME = "Tasks"
        const val COL_ID = "id"
        const val COL_TITLE = "title"
        const val COL_DETAILS = "details"
        const val COL_DATE = "date"
        const val COL_TIME = "time"

        const val TABLE_CREATE =
            "create table $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT , $COL_TITLE TEXT NOT NULL , " +
                    "$COL_DETAILS TEXT NOT NULL , $COL_DATE TEXT NOT NULL , $COL_TIME TEXT NOT NULL)"

    }
}
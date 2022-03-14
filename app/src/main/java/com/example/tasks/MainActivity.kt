package com.example.tasks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tasks.adapter.TaskAdapter
import com.example.tasks.db.DatabaseHelper
import com.example.tasks.model.Task
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = DatabaseHelper(this)
        val data = db.getAllTasks()

        if (data.isEmpty()) {
            noData.setText("No Tasks added yet")
        } else {
            noData.setText("")
            recyTasks.layoutManager = LinearLayoutManager(this)
            val studentAdapter = TaskAdapter(this,data)
            recyTasks.adapter = studentAdapter
        }


        add.setOnClickListener {
            val i = Intent(this,addTask::class.java)
            startActivity(i)
        }


    }
}
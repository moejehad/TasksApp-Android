package com.example.tasks

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tasks.db.DatabaseHelper
import kotlinx.android.synthetic.main.activity_add_task.*
import java.util.*

class addTask : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        val db = DatabaseHelper(this)

        back.setOnClickListener {
            onBackPressed()
        }

        var date = ""
        var time = ""

        TaskDate.setOnClickListener {
            val currentDate = Calendar.getInstance()
            val day = currentDate.get(Calendar.DAY_OF_MONTH)
            val month = currentDate.get(Calendar.MONTH)
            val year = currentDate.get(Calendar.YEAR)

            val picker = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener { datePicker, y, m, d ->
                    date = "$y . ${m+1} . $d"
                    TaskDate.setText("$y - ${m+1} - $d")
                },year,month,day)

            picker.show()
        }

        TaskTime.setOnClickListener {
            val currentTime = Calendar.getInstance()
            val hour = currentTime.get(Calendar.HOUR_OF_DAY)
            val minute = currentTime.get(Calendar.MINUTE)

            val picker = TimePickerDialog(this,
                TimePickerDialog.OnTimeSetListener { timePicker, h, m ->
                    time = "$h : $m"
                    TaskTime.setText("$h : $m")
                },
                hour,minute,false
            )
            picker.show()
        }


        addTask.setOnClickListener {
            if (Tasktitle.text.isEmpty() || TaskDetails.text.isEmpty() || TaskDate.text.isEmpty() || TaskTime.text.isEmpty()){
                Toast.makeText(this, "Fill Fields Please", Toast.LENGTH_LONG).show()
            }else {
                val addTask = db.insertTask(Tasktitle.text.toString(),TaskDetails.text.toString(),date.toString(),time.toString())
                if(addTask){
                    Toast.makeText(this, "Added Successfully", Toast.LENGTH_LONG).show()
                    val i = Intent(this,MainActivity::class.java)
                    startActivity(i)
                    finish()
                } else {
                    Toast.makeText(this, "Added Failed", Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}
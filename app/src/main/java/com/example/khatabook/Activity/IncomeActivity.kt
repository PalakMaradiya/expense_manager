package com.example.khatabook.Activity

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.khatabook.Adapter.CatAdapter
import com.example.khatabook.Class.DatabaseHelper
import com.example.khatabook.Modal.ModalClass
import com.example.khatabook.R
import com.example.khatabook.databinding.ActivityIncomeBinding
import java.text.SimpleDateFormat
import java.util.*

class IncomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityIncomeBinding
    var Categorylist = ArrayList<ModalClass>()
    lateinit var SpinnerCategory: Spinner
    lateinit var database: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityIncomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        database = DatabaseHelper(this, "CategoryTable.db", null, 1)

        initview()
    }

    private fun initview() {


        val myCalendar = Calendar.getInstance()
        val datapicker = DatePickerDialog.OnDateSetListener { view, year, month, dayofmonth ->

            myCalendar.set(Calendar.DAY_OF_MONTH, dayofmonth)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.YEAR, year)
            updateLabal(myCalendar)

        }
        binding.txtDate.setOnClickListener {

            DatePickerDialog(
                this, datapicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }


        binding.txtTime.setOnClickListener {

            val calendar = Calendar.getInstance()
            val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
            val currentMinute = calendar.get(Calendar.MINUTE)

            val textView: TextView = findViewById(R.id.txtTime)


            val timePickerDialog = TimePickerDialog(
                this,
                TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                    val selectedTime = String.format("%02d:%02d", currentHour, minute)
                    textView.text = selectedTime
                },
                currentHour,
                currentMinute,
                false
            )

            timePickerDialog.show()

        }

//
//        binding.SpinnerCategory.setOnClickListener {
//            Categorylist = database.DisplayCategory()
//        }

//
//        var adapter = CatAdapter(this, Categorylist)
//        SpinnerCategory.adapter = adapter


    }


    private fun updateLabal(myCalendar: Calendar) {


        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        binding.txtDate.setText(sdf.format(myCalendar.time))

    }


}


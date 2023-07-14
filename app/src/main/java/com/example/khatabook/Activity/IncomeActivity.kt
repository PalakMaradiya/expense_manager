package com.example.khatabook.Activity

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.khatabook.Adapter.CatAdapter
import com.example.khatabook.Adapter.ModeAdapter
import com.example.khatabook.Class.DatabaseHelper
import com.example.khatabook.Modal.ModalClass
import com.example.khatabook.Modal.ModeModalclass
import com.example.khatabook.R
import com.example.khatabook.databinding.ActivityIncomeBinding
import java.text.SimpleDateFormat
import java.util.*

class IncomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityIncomeBinding
    var Categorylist = ArrayList<ModalClass>()
    var Modelist = ArrayList<ModeModalclass>()
    lateinit var database: DatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityIncomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        database = DatabaseHelper(this, "Database.db", null, 1)

        initview()
    }


    private fun initview() {


//        for calander view

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


//for time picker
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


        Categorylist = database.DisplayCategory()


        var adapter = CatAdapter(this, Categorylist)
        binding.SpinnerCategory.adapter = adapter
//
//        binding.SpinnerCategory.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener{
//            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                Toast.makeText(applicationContext, Categorylist.get(p2)"", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//                Toast.makeText(this, "Select any Category", Toast.LENGTH_SHORT).show()
//            }
//
//        }



        Modelist = database.DisplayMode()
        var Madapter = ModeAdapter(this, Modelist)
        binding.spinnerMode.adapter = Madapter




        binding.imgDone.setOnClickListener {
            val amt = binding.edtAmount.text.toString()
            val category = binding.txtCategories.text.toString()
            val date = binding.txtDate.text.toString()
            val mode = binding.txtMode.text.toString()
            val note = binding.txtNote.text.toString()
            if (amt.isEmpty())
            {
                Toast.makeText(this, "Please Enter Your Amount Name ", Toast.LENGTH_SHORT).show()
            }

            else if (category.isEmpty())
            {

                Toast.makeText(this, "Please Enter Your Category Name ", Toast.LENGTH_SHORT).show()
            }


            else if (date.isEmpty())
            {

                Toast.makeText(this, "Please Enter Your Date Name ", Toast.LENGTH_SHORT).show()
            }


            else if (mode.isEmpty())
            {

                Toast.makeText(this, "Please Enter Your Mode Name ", Toast.LENGTH_SHORT).show()
            }


            else if (note.isEmpty())
            {

                Toast.makeText(this, "Please Enter note Mode Name ", Toast.LENGTH_SHORT).show()
            }


            else
            {
                database.InsertTableData(amt ,category,date, mode,note)
                Toast.makeText(this, "Your Data Add SuccesFully", Toast.LENGTH_SHORT).show()
            }
        }



//        binding.SpinnerCategory.onItemSelectedListener = object :
//            AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(
//                parent: AdapterView<*>,
//                view: View,
//                position: Int,
//                id: Long
//            ) {
//                val selectedItem = parent.getItemAtPosition(position).toString()
//                if (selectedItem == "Add new category") {
//
//                }
//            }
//
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//            }
//        }




    }



    fun updateLabal(myCalendar: Calendar) {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        binding.txtDate.setText(sdf.format(myCalendar.time))
    }
}


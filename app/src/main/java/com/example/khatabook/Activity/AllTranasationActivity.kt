package com.example.khatabook.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.khatabook.Adapter.DisplayAdapter
import com.example.khatabook.Class.DatabaseHelper
import com.example.khatabook.Modal.ModalClassDisplay
import com.example.khatabook.databinding.ActivityAllTranasationBinding

class AllTranasation : AppCompatActivity() {
    lateinit var  binding: ActivityAllTranasationBinding
    var Displaylist = ArrayList<ModalClassDisplay>()
    lateinit var database: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllTranasationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initview()
    }

    private fun initview() {

        database = DatabaseHelper(this, "Database.db", null)
        Displaylist =  database.DisplayTableData()
        var manager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        var Dadapter = DisplayAdapter(this,Displaylist)
        binding.rcv.adapter = Dadapter
        binding.rcv.layoutManager= manager

    }
}
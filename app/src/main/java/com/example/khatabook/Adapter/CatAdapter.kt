package com.example.khatabook.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.khatabook.Activity.IncomeActivity
import com.example.khatabook.Modal.ModalClass
import com.example.khatabook.R

class CatAdapter(var incomeActivity: IncomeActivity, var Categorylist: ArrayList<ModalClass>) :
    BaseAdapter() {
    override fun getCount(): Int {
        return Categorylist.size
    }

    override fun getItem(p0: Int): Any? {

        return null


    }

    override fun getItemId(p0: Int): Long {
        return 0

    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        val v: View = LayoutInflater.from(incomeActivity).inflate(R.layout.item_file_, null)
        val Category  = v.findViewById<TextView>(R.id.txtCategories)
        Category.text.get(p0)
        return v


    }


    fun refresh(list: ArrayList<ModalClass>) {

    }


}
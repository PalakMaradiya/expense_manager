package com.example.khatabook.Class

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract.Intents.Insert
import android.util.Log
import com.example.khatabook.Modal.ModalClass
import com.example.khatabook.Modal.ModeModalclass

class DatabaseHelper(context : Context, name : String, factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context,name,factory,1) {



    override fun onCreate(db: SQLiteDatabase?) {
        var  ctable = "create table CategoryTable(id Integer primary key autoincrement,name text)"
        db?.execSQL(ctable)

        var Modetbl = "create table ModeTable(id Integer primary key autoincrement ,name text)"
        db?.execSQL(Modetbl)

        var tbl = "create table IncomeExpenseTable(id Integer primary key autoincrement ,amount Integer ,category text ,date text , mode text ,note text ,type Integer)"
        db?.execSQL(tbl)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun InsertData( name : String)
    {

        var c = ContentValues()
        c.put("name",name)
        var write = writableDatabase

        var result = write.insert("CategoryTable",null,c)

        Log.e("TAG", "insertData: "+result)

    }

    fun InsertModeData(name : String)
    {

        var c = ContentValues()
        c.put("name",name)
        var write = writableDatabase

        var result = write.insert("ModeTable",null,c)

        Log.e("TAG", "insertData: "+result)
    }

    fun DisplayCategory(): ArrayList<ModalClass> {
        var list = ArrayList<ModalClass>()
        list.clear()
        var read = readableDatabase
        var query = "select * from CategoryTable"
        var cursor : Cursor = read.rawQuery(query,null)
        cursor.moveToFirst()

        do {
            var id = cursor.getString(0)
            var name = cursor.getString(1)
            Log.e("TAG", "DisplayCategory: " + name )
            list.add(ModalClass(name,id))
        }while (cursor.moveToNext())
        return list

    }



    fun DisplayMode(): ArrayList<ModeModalclass> {

        var read = readableDatabase
        var modelist = ArrayList<ModeModalclass>()
        var query = "select * from ModeTable"
        var cursor : Cursor = read.rawQuery(query,null)
        cursor.moveToFirst()

        do {
            var id = cursor.getString(0)
            var name = cursor.getString(1)
            Log.e("TAG", "DisplayMode: " + name )
            modelist.add(ModeModalclass(name,id))
        }while (cursor.moveToNext())

        return modelist
    }


    fun InsertTableData(amount: String, category: String, date: String, mode: String, note: String)
    {

        var c = ContentValues()
        c.put("amount",amount)
        c.put("category",category)
        c.put("date",date)
        c.put("mode",mode)
        c.put("type","0")
        c.put("note",note)

        var write = writableDatabase

        var result = write.insert("IncomeExpenseTable",null,c)

        Log.e("TAG", "InsertTableData: "+result)

    }


    fun InsertDataForExpense(amount: String, category: String, date: String, mode: String, note: String)
    {

        var c = ContentValues()
        c.put("amount",amount)
        c.put("category",category)
        c.put("date",date)
        c.put("mode",mode)
        c.put("type","1")
        c.put("note",note)

        var write = writableDatabase

        var result = write.insert("IncomeExpenseTable",null,c)

        Log.e("TAG", "InsertDataForExpense: "+result)

    }


}





package com.example.khatabook.Class

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.khatabook.Modal.ModalClass

class DatabaseHelper(context : Context, name : String, factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context,name,factory,1) {

    var list = ArrayList<ModalClass>()
    override fun onCreate(db: SQLiteDatabase?) {
        var  ctable = "create table CategoryTable(id Integer primary key autoincrement,name text)"
        db?.execSQL(ctable)

        var Modetbl = "create table ModeTable(id Integer primary key autoincrement ,name text)"
        db?.execSQL(Modetbl)

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



    fun DisplayMode(name: String): ArrayList<ModalClass> {
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
}
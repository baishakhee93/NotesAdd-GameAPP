package com.findmyage.com

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.widget.Toast

class DbManager {
    val dbName="MyNotes"
    val dbTable="Notes"
    val colID="ID"
    val colTitle="Title"
    val colDesc = "Description"
    val dbVersion=1
    val sqlCreateTable="CREATE TABLE IF NOT EXISTS "+dbTable+"("+colID+" INTEGER PRYMARY KEY, "+colTitle+ " TEXT,"+colDesc+" TEXT);"
    var sqlDB:SQLiteDatabase?=null
    constructor(context:Context){
        var db=DatabaseHelperNotes(context)
        sqlDB=db.writableDatabase

    }
    inner class DatabaseHelperNotes:SQLiteOpenHelper{
        var context:Context?=null
        constructor(context: Context):super(context,dbName,null,dbVersion){
            this.context=context
        }

        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL(sqlCreateTable)
            Toast.makeText(this.context,"database is created",Toast.LENGTH_LONG).show()
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("Drop table IF EXISTS"+dbTable)
        }

    }
    fun insert(values: ContentValues):Long{
        val ID=sqlDB!!.insert(dbTable,"",values)
        return ID
    }


    fun query(projection: Array<String>,selection:String,selectionArgs:Array<String>,sortOrder: String):Cursor{
        val qb=SQLiteQueryBuilder()
        qb.tables=dbTable
        val cursor=qb.query(sqlDB,projection,selection,selectionArgs,null,null,sortOrder)
        return cursor
    }
    fun Delete(selection:String,selectionArgs:Array<String>):Int{
        val count=sqlDB!!.delete(dbTable,selection,selectionArgs)
        return  count
    }

    fun Update(values:ContentValues,selection:String,selectionargs:Array<String>):Int{

        val count=sqlDB!!.update(dbTable,values,selection,selectionargs)
        return count
    }


}



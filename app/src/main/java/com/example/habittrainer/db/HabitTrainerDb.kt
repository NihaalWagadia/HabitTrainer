package com.example.habittrainer.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class HabitTrainerDb(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    //BLOB-- binary large object used for image

    private val SQL_CREATE_ENTRIES = "CREATE TABLE ${HabitEntry.TABLE_NAME} (" +
            "${HabitEntry._ID} INTEGER PRIMARY KEY," +
            "${HabitEntry.TITLE_COL} TEXT," +
            "${HabitEntry.DESC_COL} TEXT," +
            "${HabitEntry.IMAGE_COL} BLOB" +
            ")"

    private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${HabitEntry.TABLE_NAME}"

    override fun onCreate(db: SQLiteDatabase) {
        //what should happen when database is created
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        //what should happen when database version is increased
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }
}

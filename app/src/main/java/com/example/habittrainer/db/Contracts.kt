package com.example.habittrainer.db

import android.provider.BaseColumns

//db file extension
val DATABASE_NAME = "habittrainer.db"
val DATABASE_VERSION =10

//defining table
//we have using only one table
object HabitEntry : BaseColumns{
    val TABLE_NAME = "habit"
    val _ID = "id"
    val TITLE_COL = "title"
    val DESC_COL = "description"
    val IMAGE_COL = "image"
}

package com.example.habittrainer.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.example.habittrainer.Habit
import java.io.ByteArrayOutputStream

class HabitDbTable(context: Context) {

    private val TAG = HabitDbTable::class.java.simpleName

    private val dbHelper = HabitTrainerDb(context)

    fun store(habit: Habit): Long {
        val db = dbHelper.writableDatabase

        val values = ContentValues()
        with(values) {
            put(HabitEntry.TITLE_COL, habit.title)
            put(HabitEntry.DESC_COL, habit.description)
            put(HabitEntry.IMAGE_COL, toByteArrayHabit(habit.image))
        }

//        db.beginTransaction()
//        val id = db.insert(HabitEntry.TABLE_NAME, null, values)
//        db.close()

        val id: Long = db.transaction {
            insert(HabitEntry.TABLE_NAME, null, values)
        }

        Log.d(TAG, "Store new habit to the DB $habit")

        return id
    }


    fun readAllHabits(): List<Habit> {

        val colums =
            arrayOf(HabitEntry._ID, HabitEntry.TITLE_COL, HabitEntry.DESC_COL, HabitEntry.IMAGE_COL)

        val order = "${HabitEntry._ID} ASC"

        val db = dbHelper.readableDatabase

        val cursor = db.doQuery(HabitEntry.TABLE_NAME, colums, orderBy = order)

        val habits = mutableListOf<Habit>()
        while (cursor.moveToNext()) {
            val title = cursor.getString(HabitEntry.TITLE_COL)
            val desc = cursor.getString(HabitEntry.DESC_COL)
            val bitmap = cursor.getBitmap((HabitEntry.IMAGE_COL))
            //val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
            habits.add(Habit(title, desc, bitmap))
        }
        cursor.close()
        return habits
    }

}

private fun Cursor.getString(columnName:String)=
    getString(getColumnIndex(columnName))

private fun Cursor.getBitmap(columnName: String):Bitmap{
    val bytes = getBlob(getColumnIndex(columnName))
    return  BitmapFactory.decodeByteArray(bytes,0,bytes.size)
}



private fun SQLiteDatabase.doQuery(
    table: String, columns: Array<String>, selection: String? = null,
    selectionArgs: Array<String>? = null, groupBy: String? = null, having: String? = null,
    orderBy: String? = null
): Cursor {
    return query(table, columns, selection, selectionArgs, groupBy, having, orderBy)
}

private fun toByteArrayHabit(bitmap: Bitmap): ByteArray {
    val stream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream)
    return stream.toByteArray()
}


//extension function
//T any type(generic)
private inline fun <T> SQLiteDatabase.transaction(function: SQLiteDatabase.() -> T): T {
    beginTransaction()
    val result = try {
        val returnValue = function()
        setTransactionSuccessful()
        returnValue
    } finally {
        endTransaction()
    }
    close()
    return result
}


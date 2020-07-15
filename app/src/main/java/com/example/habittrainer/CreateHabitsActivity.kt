package com.example.habittrainer

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Images.Media.getBitmap
import android.util.Log
import android.view.View
import android.widget.EditText
import com.example.habittrainer.db.HabitDbTable
import kotlinx.android.synthetic.main.activity_create_habits.*
import kotlinx.android.synthetic.main.single_card.*
import java.io.IOException

class CreateHabitsActivity : AppCompatActivity() {

    private val TAG = CreateHabitsActivity::class.java.simpleName
    private var imageBitmap: Bitmap? = null

    private val CHOOSE_IMAGE_REQUEST = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_habits)
    }

    fun storeHabit(v: View) {
        if (edit_title.isBlank() || edit_description.isBlank()) {
            Log.d(TAG, "Title/Descripion is missing")
            displayErrorMessage("Your title or description is missing")
            return
        } else if (imageBitmap == null) {
            Log.d(TAG, "Image missing")
            displayErrorMessage("Your image is missing")
            return
        }
        //store the habit...
//        error_message.visibility = View.INVISIBLE

        val title = edit_title.text.toString()
        val description = edit_description.text.toString()
        val habit = Habit(title, description, imageBitmap!!)

        val id = HabitDbTable(this).store(habit)
        if(id==-1L){
            displayErrorMessage("Habit cannot be stored")
        }
        else{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun displayErrorMessage(message: String) {

        error_message.text = message
        error_message.visibility = View.VISIBLE
    }

    fun chooseImage(v: View) {
        val intent = Intent()
        //type of contect --image/*
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        val chooser = Intent.createChooser(intent, "Choose image for habit")
        startActivityForResult(chooser, CHOOSE_IMAGE_REQUEST)
        Log.d(TAG, "Intent to choose image sent...")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CHOOSE_IMAGE_REQUEST && resultCode == Activity.RESULT_OK
            && data != null && data.data != null
        ) {
            Log.d(TAG, "An image was chosen by the user")
            val bitmap = tryReadBitMap(data.data)
            bitmap?.let {
                this.imageBitmap = bitmap
                image_view.setImageBitmap(bitmap)
                Log.d(TAG, "Read image bitmap and updated image view")
            }
        }
    }

    private fun tryReadBitMap(data: Uri): Bitmap? {
        return try {
            getBitmap(contentResolver, data)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }

    }

}

//Extension function
//we are not defining return type because compiler can identify that
private fun EditText.isBlank() = this.text.toString().isBlank()
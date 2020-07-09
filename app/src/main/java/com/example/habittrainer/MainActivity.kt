package com.example.habittrainer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        tv_description.text = getString(R.string.Description)
        tv_title.text = getString(R.string.Drink)
        iv_con.setImageDrawable(
            ContextCompat.getDrawable(
                applicationContext, R.drawable.water))

    }
}

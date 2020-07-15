package com.example.habittrainer

import android.graphics.Bitmap

//data class because it's going to hold data
data class Habit(val title: String, val description: String, val image: Bitmap)

        //returns list of Habit
//fun getSampleHabits():List<Habit>{
//            return listOf(
//                Habit("Go for a walk",
//                "Morning walk is good!", R.drawable.water),
//
//                Habit("Go for a Run",
//                    "Morning run is good!", R.drawable.walk),
//
//                Habit("walkie-talkie",
//                    "Morning talk!", R.drawable.water),
//
//                Habit("Reading",
//                    "Morning read is good!", R.drawable.walk),
//
//                Habit("Go for a skip",
//                    "Morning jump is good!", R.drawable.water),
//
//                Habit("Go for a swim",
//                    "Morning swim is good!", R.drawable.walk)
//            )
//        }
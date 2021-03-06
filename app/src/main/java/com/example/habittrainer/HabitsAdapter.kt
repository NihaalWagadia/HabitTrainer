package com.example.habittrainer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.single_card.view.*

//: means extend
class HabitsAdapter(val habits:List<Habit>) :
    RecyclerView.Adapter<HabitsAdapter.HabitViewHolder>() {

    class HabitViewHolder (val card : View) : RecyclerView.ViewHolder(card)

    //create a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_card, parent, false)
        return HabitViewHolder(view)
    }

    override fun getItemCount() = habits.size


    //specifies the contents for the card
    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        if(holder != null){
            holder.card.tv_title.text = habits[position].title
            holder.card.tv_description.text = habits[position].description
            holder.card.iv_con.setImageBitmap( habits[position].image)

        }
    }
}
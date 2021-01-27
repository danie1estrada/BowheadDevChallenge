package com.daniel.estrada.mobilewellnessdapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.daniel.estrada.mobilewellnessdapp.R

class AnswerLogsRecyclerViewAdapter: RecyclerView.Adapter<AnswerLogsRecyclerViewAdapter.ViewHolder>() {

    var healthData = listOf<ByteArray>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val feeling: TextView = itemView.findViewById(R.id.feeling)
        val didSleepWell: TextView = itemView.findViewById(R.id.didSleepWell)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = holder.itemView.context
        val data = String(healthData[position], Charsets.UTF_8).trim().split(" ")

        holder.feeling.text = when(data[0]) {
            context.getString(R.string.value_group_1_opt_1) -> context.getString(R.string.label_group_1_opt_1)
            context.getString(R.string.value_group_1_opt_2) -> context.getString(R.string.label_group_1_opt_2)
            context.getString(R.string.value_group_1_opt_3) -> context.getString(R.string.label_group_1_opt_3)
            else -> ""
        }

        holder.didSleepWell.text = when(data[1]) {
            context.getString(R.string.value_group_2_opt_1) -> "Good"
            context.getString(R.string.value_group_2_opt_2) -> "Bad"
            else -> ""
        }
    }

    override fun getItemCount(): Int = healthData.size
}
package com.example.biasaaja_companyprofile.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.biasaaja_companyprofile.databinding.GamesListItemBinding
import com.example.biasaaja_companyprofile.databinding.ScheduleListItemBinding
import com.example.biasaaja_companyprofile.model.Game
import com.example.biasaaja_companyprofile.model.Schedule
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class ScheduleListAdapter(val scheduleList: ArrayList<Schedule>) :
    RecyclerView.Adapter<ScheduleListAdapter.ScheduleViewHolder>(){
    class ScheduleViewHolder(var binding: ScheduleListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val binding = ScheduleListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        binding.cardSchedule.setOnClickListener {
            Toast.makeText(parent.context, "Detail", Toast.LENGTH_SHORT).show()
        }

        return ScheduleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        holder.binding.txtDate.text = scheduleList[position].date
        holder.binding.txtMonth.text = scheduleList[position].month
        holder.binding.txtName.text = scheduleList[position].name
        holder.binding.txtGameTeam.text = scheduleList[position].game_name + " - " + scheduleList[position].team_name
    }

    override fun getItemCount(): Int {
        return scheduleList.size
    }

    fun updateScheduleList(newScheduleList: ArrayList<Schedule>) {
        scheduleList.clear()
        scheduleList.addAll(newScheduleList)
        notifyDataSetChanged()
    }
}
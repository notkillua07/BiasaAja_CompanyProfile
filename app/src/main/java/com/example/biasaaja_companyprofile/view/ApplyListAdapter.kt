package com.example.biasaaja_companyprofile.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.biasaaja_companyprofile.databinding.ApplyListItemBinding
import com.example.biasaaja_companyprofile.databinding.FragmentApplyTeamBinding
import com.example.biasaaja_companyprofile.model.Apply
import com.example.biasaaja_companyprofile.viewmodel.GameViewModel
import com.example.biasaaja_companyprofile.viewmodel.TeamViewModel

class ApplyListAdapter(val applyList: ArrayList<Apply>, private val teamViewModel: TeamViewModel) :
    RecyclerView.Adapter<ApplyListAdapter.ApplyViewHolder>() {

    class ApplyViewHolder(var binding: ApplyListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApplyViewHolder {
        val binding = ApplyListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ApplyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ApplyViewHolder, position: Int) {
        holder.binding.apply = applyList[position]  // Bind the Apply object
        holder.binding.teamViewModel = teamViewModel  // Bind the GameViewModel
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return applyList.size
    }

    fun updateApplyList(newApplyList: List<Apply>) {
        applyList.clear()
        applyList.addAll(newApplyList)
        notifyDataSetChanged()
    }
}

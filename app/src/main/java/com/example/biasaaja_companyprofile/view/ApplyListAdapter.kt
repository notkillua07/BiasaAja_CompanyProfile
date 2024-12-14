package com.example.biasaaja_companyprofile.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.biasaaja_companyprofile.databinding.FragmentApplyTeamBinding
import com.example.biasaaja_companyprofile.model.Apply

class ApplyListAdapter(val applyList: ArrayList<Apply>, val adapterOnClick: (Apply) -> Unit) :
    RecyclerView.Adapter<ApplyListAdapter.ApplyViewHolder>() {

    class ApplyViewHolder(var binding: FragmentApplyTeamBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApplyViewHolder {
        val binding = FragmentApplyTeamBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ApplyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ApplyViewHolder, position: Int) {
        val apply = applyList[position]

        holder.binding.btnAdd.setOnClickListener {
            adapterOnClick(apply)
        }
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

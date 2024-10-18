package com.example.biasaaja_companyprofile.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.biasaaja_companyprofile.databinding.MembersListItemBinding
import com.example.biasaaja_companyprofile.model.Member
import com.squareup.picasso.Picasso

class MemberListAdapter(val membersList: ArrayList<Member>) :
    RecyclerView.Adapter<MemberListAdapter.MemberViewHolder>() {
        class MemberViewHolder(var binding: MembersListItemBinding):
            RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val binding = MembersListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MemberViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return membersList.size
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        var member = membersList[position]

        holder.binding.txtName.text = member.name
        holder.binding.txtRole.text = member.role
        Picasso.get()
            .load(member.imageUrl)
            .into(holder.binding.imgProfile)
        holder.binding.imgProfile.visibility = View.VISIBLE

    }
}
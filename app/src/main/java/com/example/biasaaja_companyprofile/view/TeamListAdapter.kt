package com.example.biasaaja_companyprofile.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.biasaaja_companyprofile.databinding.GamesListItemBinding
import com.example.biasaaja_companyprofile.databinding.TeamsListItemBinding
import com.example.biasaaja_companyprofile.model.Game
import com.example.biasaaja_companyprofile.model.Team
import com.example.biasaaja_companyprofile.view.GamesListAdapter.GamesViewHolder
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class TeamListAdapter(val teamsList: ArrayList<Team>) :
    RecyclerView.Adapter<TeamListAdapter.TeamViewHolder>() {

    class TeamViewHolder(var binding: TeamsListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val binding = TeamsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        var team = teamsList[position]

        // Load the data based on the game or apply filtering logic here if needed
        holder.binding.btnDetail.text = team.name

        holder.binding.btnDetail.setOnClickListener {
            val team = teamsList[holder.adapterPosition]
            val action = TeamsFragmentDirections.actionTeamsToMembersFragment(team)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return teamsList.size
    }
}
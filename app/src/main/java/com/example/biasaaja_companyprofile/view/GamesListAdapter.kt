package com.example.biasaaja_companyprofile.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.biasaaja_companyprofile.databinding.GamesListItemBinding
import com.example.biasaaja_companyprofile.model.Game
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class GamesListAdapter(val gamesList: ArrayList<Game>) :
    RecyclerView.Adapter<GamesListAdapter.GamesViewHolder>() {
    class GamesViewHolder(var binding: GamesListItemBinding) :
            RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder {
            val binding = GamesListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            return GamesViewHolder(binding)

        }

        override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
            val picasso = Picasso.Builder(holder.itemView.context)
            picasso.listener { picasso, uri, exception ->
                exception.printStackTrace()
            }
            picasso.build().load(gamesList[position].imageUrl).into(holder.binding.imgGame)
            object:Callback{
                override fun onSuccess() {
                    holder.binding.progressBar.visibility = View.INVISIBLE
                    holder.binding.imgGame.visibility = View.VISIBLE

                }

                override fun onError(e: Exception?) {
                    Log.e("picasso_error", e.toString())
                }

            }
            holder.binding.txtName.text = gamesList[position].name
            holder.binding.txtDescription.text = gamesList[position].description

//            val id = studentList[position].id
//            val name = studentList[position].name
//            val bod = studentList[position].bod
//            val phone = studentList[position].phone
//            holder.binding.btnTeams.setOnClickListener {
//                val action = StudentListFragmentDirections.actionStudentDetail(id.toString(), name.toString(), bod.toString(), phone.toString())
//                Navigation.findNavController(it).navigate(action)
//            }

            holder.binding.btnAchievements.setOnClickListener {
                val action = WhatWePlayFragmentDirections.actionWhatWePlayToAchievementFragment(
                    gamesList[position].name.toString(), gamesList[position].imageUrl.toString())
                Navigation.findNavController(it).navigate(action)
            }
        }

        override fun getItemCount(): Int {
            return gamesList.size
        }

        fun updateStudentList(newStudentList: ArrayList<Game>) {
            gamesList.clear()
            gamesList.addAll(newStudentList)
            notifyDataSetChanged()
        }
}
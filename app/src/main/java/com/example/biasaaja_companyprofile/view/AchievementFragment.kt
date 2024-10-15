package com.example.biasaaja_companyprofile.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.biasaaja_companyprofile.databinding.FragmentAchievementBinding
import com.example.biasaaja_companyprofile.model.Achievement
import com.example.biasaaja_companyprofile.viewmodel.AchievementViewModel
import com.example.biasaaja_companyprofile.viewmodel.GameViewModel
import com.example.biasaaja_companyprofile.viewmodel.ScheduleViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class AchievementFragment : Fragment() {
    private lateinit var viewModel: AchievementViewModel
    private lateinit var binding: FragmentAchievementBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAchievementBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(AchievementViewModel::class.java)
        viewModel.refresh()

        val gameName =arguments?.getString("game")
        val image =arguments?.getString("image")


        val picasso = Picasso.Builder(view.context)
        picasso.listener { picasso, uri, exception ->
            exception.printStackTrace()
        }
        picasso.build().load(image).into(binding.imgGame)

        picasso.build().load(image)
            .into(binding.imgGame, object: Callback {
                override fun onSuccess() {
                    binding.imgGame.visibility = View.VISIBLE
                }

                override fun onError(e: Exception?) {
                    Log.e("picasso_error", e.toString())
                }
            })

        binding.txtGame.text = gameName

        val years = (2014..2024).map { it.toString() }.toMutableList()
        years.add(0, "All")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, years)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerYear.adapter = adapter

        viewModel.achievementLD.observe(viewLifecycleOwner, Observer { achievements ->
            loadAchievements(gameName!!, "All", achievements)
        })

        binding.spinnerYear.onItemSelectedListener = object : android.widget.AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: android.widget.AdapterView<*>?) {}

            override fun onItemSelected(parent: android.widget.AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedYear = years[position]
                viewModel.achievementLD.value?.let { achievements ->
                    loadAchievements(gameName!!, selectedYear, achievements)
                }
            }
        }

        viewModel.refresh()
    }

    private fun loadAchievements(gameName: String, selectedYear: String, achievements: List<Achievement>) {
        val filteredAchievements = achievements.filter { it.game_name == gameName }
            .let { if (selectedYear == "All") it else it.filter { achievement -> achievement.year == selectedYear.toInt() } }

        val achievementsText = if (filteredAchievements.isNotEmpty()) {
            filteredAchievements.mapIndexed { index, achievement ->
                "${index + 1}. ${achievement.name} - ${achievement.team_name} (${achievement.year})"
            }.joinToString(separator = "\n")
        } else {
            "No achievements found."
        }

        // Display the achievements in the TextView
        binding.txtAchievement.text = achievementsText
    }

}
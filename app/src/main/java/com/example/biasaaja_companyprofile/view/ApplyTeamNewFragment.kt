package com.example.biasaaja_companyprofile.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.biasaaja_companyprofile.R
import com.example.biasaaja_companyprofile.databinding.FragmentApplyTeamNewBinding
import com.example.biasaaja_companyprofile.model.Apply
import com.example.biasaaja_companyprofile.viewmodel.ApplyTeamViewModel
import com.example.biasaaja_companyprofile.viewmodel.GameViewModel
import com.example.biasaaja_companyprofile.viewmodel.TeamViewModel

class ApplyTeamNewFragment : Fragment(), StoreApplyClickListener {

    private lateinit var viewModel: ApplyTeamViewModel
    private lateinit var binding: FragmentApplyTeamNewBinding
    private lateinit var gameViewModel: GameViewModel
    private lateinit var teamViewModel: TeamViewModel

    private var username: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentApplyTeamNewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ApplyTeamViewModel::class.java)
        gameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        teamViewModel = ViewModelProvider(this).get(TeamViewModel::class.java)

        // Retrieve arguments from navigation
        arguments?.let {
            username = ApplyTeamNewFragmentArgs.fromBundle(it).username
        }

        // Observe LiveData from ViewModel for Apply
        viewModel.applyLD.observe(viewLifecycleOwner, { applyList ->
            // Update the UI or notify when new data is loaded
            // Example: You can populate a RecyclerView or handle the UI accordingly
        })

        // Observe loading/error states
        viewModel.loadingLD.observe(viewLifecycleOwner, { isLoading ->
            // Show loading spinner or handle loading state
        })
        viewModel.applyLoadErrorLD.observe(viewLifecycleOwner, { isError ->
            // Handle error state (e.g., show a Toast or Snackbar)
        })

        // Observe game names from GameViewModel to populate the game spinner
        gameViewModel.gamesLD.observe(viewLifecycleOwner, { gameNames ->
            val gameAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, gameNames)
            gameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spnGame.adapter = gameAdapter
        })

        // Load the games (this might be triggered by some action or lifecycle event)
        gameViewModel.refresh()

        // Observe filtered teams based on selected game
        teamViewModel.filteredTeamsLD.observe(viewLifecycleOwner, { filteredTeams ->
            val teamNames = filteredTeams.map { it.name }  // assuming 'name' is the field to display
            val teamAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, teamNames)
            teamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spnTeam.adapter = teamAdapter
        })


        binding.spnGame.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedGame = gameViewModel.gamesLD.value?.get(position)
                selectedGame?.let {
                    teamViewModel.selectGame(it.id)
                    Log.d("ApplyTeamNewFragment", "Game selected: ${it.name}, Game ID: ${it.id}")
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle case when no item is selected, if needed
            }
        })



    }

    override fun onStoreApplyClick(v: View) {
        // Check for null username
        val username = this.username
        if (username.isNullOrEmpty()) {
            Toast.makeText(v.context, "Username is missing", Toast.LENGTH_LONG).show()
            return
        }

        // Get the selected team index from Spinner (or validate the selected team)
        val selectedTeamPosition = binding.spnTeam.selectedItemPosition
        if (selectedTeamPosition == -1) {
            Toast.makeText(v.context, "Please select a team", Toast.LENGTH_LONG).show()
            return
        }

        // Create the Apply object
        val apply = Apply(
            username = username,
            team = selectedTeamPosition,  // Use selected item position
            reason = binding.txtReason.text.toString(),
            status = "Waiting"
        )

        // Add the apply using ViewModel
        val applyList = listOf(apply)
        viewModel.addApply(applyList)

        // Show success message
        Toast.makeText(v.context, "Application submitted", Toast.LENGTH_LONG).show()

        // Navigate back
        Navigation.findNavController(v).popBackStack()
    }
}

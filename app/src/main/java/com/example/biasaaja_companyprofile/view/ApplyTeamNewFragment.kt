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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
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
    private var teamIdList: List<Int> = emptyList() // List to store team IDs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentApplyTeamNewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.storeListener = this

        // Initialize ViewModels
        viewModel = ViewModelProvider(this).get(ApplyTeamViewModel::class.java)
        gameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        teamViewModel = ViewModelProvider(this).get(TeamViewModel::class.java)

        // Retrieve arguments passed via navigation
        arguments?.let {
            username = ApplyTeamNewFragmentArgs.fromBundle(it).username
        }

        // Observe LiveData from GameViewModel to populate the Game Spinner
        gameViewModel.gamesLD.observe(viewLifecycleOwner, { gameList ->
            val gameNames = gameList.map { it.name ?: "Unknown Game" } // Extract names
            val gameAdapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, gameNames)
            gameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            // Set the adapter to the spinner
            binding.spnGame.adapter = gameAdapter

            // Set the listener after adapter is initialized
            binding.spnGame.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>, view: View?, position: Int, id: Long
                ) {
                    val selectedGame = gameList[position]
                    selectedGame?.let {
                        teamViewModel.selectGame(it.id)
                        Log.d("ApplyTeamNewFragment", "Selected Game: ${it.name}, ID: ${it.id}")
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    Log.d("ApplyTeamNewFragment", "No game selected")
                }
            }
        })

        // Observe filtered teams to populate the Team Spinner
        val teamAdapter = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_item,
            mutableListOf()
        )
        teamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spnTeam.adapter = teamAdapter

        teamViewModel.filteredTeamsLD.observe(viewLifecycleOwner, { filteredTeams ->
            val teamNames = filteredTeams.map { it.name }
            teamIdList = filteredTeams.map { it.id ?: 999 } // Extract team IDs and save them

            Log.d("ApplyTeamNewFragment", "Filtered Teams Size: ${teamNames.size}")
            Log.d("ApplyTeamNewFragment", "Filtered Teams Data: $teamNames")

            teamAdapter.clear()
            teamAdapter.addAll(teamNames)
            binding.spnTeam.adapter = teamAdapter // Re-attach adapter to ensure it updates
            teamAdapter.notifyDataSetChanged()
            binding.spnTeam.setSelection(0) // Default to first selection
        })

        // Load games data
        gameViewModel.refresh()
    }

    override fun onStoreApplyClick(v: View) {
        val username = this.username
        if (username.isNullOrEmpty()) {
            Toast.makeText(v.context, "Username is missing", Toast.LENGTH_LONG).show()
            return
        }

        // Get selected team position
        val selectedTeamPosition = binding.spnTeam.selectedItemPosition
        if (selectedTeamPosition == -1 || teamIdList.isEmpty()) {
            Toast.makeText(v.context, "Please select a valid team", Toast.LENGTH_LONG).show()
            return
        }

        // Get the selected team ID
        val selectedTeamId = teamIdList[selectedTeamPosition]

        // Get reason text
        val reason = binding.txtReason.text.toString()
        if (reason.isEmpty()) {
            Toast.makeText(v.context, "Please provide a reason", Toast.LENGTH_LONG).show()
            return
        }

        // Create Apply object
        val apply = Apply(
            username = username,
            team = selectedTeamId, // Use the selected team ID
            reason = reason,
            status = "Waiting"
        )

        // Add application via ViewModel
        val applyList = listOf(apply)
        viewModel.addApply(applyList)

        Toast.makeText(v.context, "Application submitted", Toast.LENGTH_LONG).show()
        Log.d("ApplyTeamNewFragment", "Application Submitted: $apply")

        // Navigate back
        Navigation.findNavController(v).popBackStack()
    }
}

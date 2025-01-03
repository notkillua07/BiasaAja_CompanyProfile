package com.example.biasaaja_companyprofile.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.biasaaja_companyprofile.model.Team
import com.example.biasaaja_companyprofile.model.Team2
import com.example.biasaaja_companyprofile.util.TeamDataProvider

class TeamViewModel : ViewModel() {

    val teamsLD = MutableLiveData<ArrayList<Team2>>() // All teams
    val selectedGameIdLD = MutableLiveData<Int?>()   // Selected game ID (Nullable to indicate no selection)
    val filteredTeamsLD = MutableLiveData<List<Team2>>() // Filtered teams based on game ID

    init {
        fetchTeams()
    }

    private fun fetchTeams() {
        // Load all teams initially
        teamsLD.value = TeamDataProvider.teams
    }

    // To update selected game ID and trigger filtering
    fun selectGame(gameId: Int?) {
        selectedGameIdLD.value = gameId
        filterTeamsByGame(gameId)
    }

    // Filter teams based on the selected game ID
    fun filterTeamsByGame(gameId: Int?) {
        val allTeams = teamsLD.value ?: arrayListOf()
        filteredTeamsLD.value = if (gameId != null) {
//            allTeams
            allTeams.filter { it.game?.id == gameId } // Ensure game ID is properly compared
        } else {
            allTeams // Return all teams if no game is selected
        }

        // Debug log to see filtered teams
        Log.d("TeamViewModel", "Filtered teams: ${filteredTeamsLD.value}")
    }

    fun getGameNameByTeamId(teamId: Int) : String {
        val teams = teamsLD.value ?: return "No Team"
        val team = teams.find { it.id == teamId }
        if (team == null) {
            Log.e("TeamViewModel", "Team not found for teamId: $teamId")
        }
        return team?.game?.name ?: "Unknown Game"
//        return "Unknown Game"
    }

}
